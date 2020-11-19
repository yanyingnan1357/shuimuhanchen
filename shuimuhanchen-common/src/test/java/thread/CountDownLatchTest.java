package thread;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class CountDownLatchTest {

    private static final int poolSize = 3;

    private static final ThreadFactory THREAD_FACTORY = new ThreadFactoryBuilder().setNameFormat("ThreadPoolContainer-thread-%d").build();

    /**
     * 自测多线程闭锁，当计数器减到0时，表示被计数的线程都已经执行完成，适合与FutureTask一起使用，可以判断异步执行结果
     * @param args
     */
    public static void main(String[] args) {

        ArrayList<String> demoList = Lists.newArrayList();
        demoList.add("111");
        demoList.add("222");
        demoList.add("333");

        List<List<String>> lists = Lists.partition(demoList, 1);

        //创建线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(poolSize, poolSize, 2, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), THREAD_FACTORY, new ThreadPoolExecutor.AbortPolicy());

        //创建计数器、FutureTask接受每个线程的处理结果
        CountDownLatch latch = new CountDownLatch(lists.size());
        List<FutureTask<Boolean>> taskList = new ArrayList<>();

        //循环开启线程
        for (List<String> list : lists) {
            FutureTask<Boolean> task = new FutureTask<>(new Callable<Boolean>() {
                @Override
                public Boolean call() {
                    Boolean result = false;
                    try {
                        System.out.println(list + Thread.currentThread().getName());
                        result = true;
                    } finally {
                        latch.countDown();
                    }
                    return result;
                }
            });
            //每次开启一个线程都加入线程池
            executor.submit(task);
            //每个线程的处理结果放入集合
            taskList.add(task);
        }

        //线程等待
        try {
            //latch.await();//不设置时间就一只等待知道所有线程都执行完成
            boolean allFinished = latch.await(10, TimeUnit.SECONDS);
            if (!allFinished) {
                log.error("多线程闭锁等待了10s，仍未执行完毕");
            }
        } catch (InterruptedException e) {
            log.error("线程中断！", e);
            //通过调用thread.currentThread().interrupt()，可以设置线程的中断标志，这样更高级别的中断处理程序就会注意到它，并可以适当地处理它。
            Thread.currentThread().interrupt();
        }

        //解析每个线程的执行结果
        for (FutureTask<Boolean> task : taskList) {
            Boolean result = false;
            try {
                result = task.get();
                System.out.println(result + "-" + task);
            } catch (Exception e) {
                log.error("线程中断！", e);
                //通过调用thread.currentThread().interrupt()，可以设置线程的中断标志，这样更高级别的中断处理程序就会注意到它，并可以适当地处理它。
                Thread.currentThread().interrupt();
            }
            if(!result){
                System.out.println("多线程闭锁存在某线程处理异常！");
            }
        }
        //关闭线程池
        executor.shutdown();
    }

}
