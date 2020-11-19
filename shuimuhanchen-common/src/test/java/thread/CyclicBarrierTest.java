package thread;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class CyclicBarrierTest {

    private static final int poolSize = 5;

    private static final ThreadFactory THREAD_FACTORY = new ThreadFactoryBuilder().setNameFormat("ThreadPoolContainer-thread-%d").build();

    /**
     * 自测-栅栏，当被标记的线程都执行到某一个点时，再执行第二个参数的线程任务，最后执行线程的栅栏点之后的任务
     * @param args
     */
    public static void main(String[] args) {

        ArrayList<String> demoList = Lists.newArrayList();
        demoList.add("111");
        demoList.add("222");
        demoList.add("333");
        demoList.add("444");
        demoList.add("555");


        List<List<String>> lists = Lists.partition(demoList, 1);

        //创建线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(poolSize, poolSize, 5, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), THREAD_FACTORY, new ThreadPoolExecutor.AbortPolicy());


        //创建线程屏障，其它线程执行完成之后再执行Thread.currentThread() 也即主线程
        CyclicBarrier cyclicBarrier = new CyclicBarrier(lists.size(), new Runnable() {
            @Override
            public void run() {
                System.out.println("这个线程打个叉！");
            }
        });

        //循环开启线程
        for (List<String> list : lists) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(list + Thread.currentThread().getName());
                    try {
                        cyclicBarrier.await();
                        System.out.println(Thread.currentThread().getName() + "接着执行线程");
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
            //每次开启一个线程都加入线程池
            executor.submit(thread);
        }
        //关闭线程池
        executor.shutdown();

    }

}
