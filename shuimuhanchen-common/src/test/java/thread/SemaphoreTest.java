package thread;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class SemaphoreTest {

    private static final int poolSize = 6;

    private static final ThreadFactory THREAD_FACTORY = new ThreadFactoryBuilder().setNameFormat("ThreadPoolContainer-thread-%d").build();

    /**
     * 自测-信号量，用来控制流量的  常用于秒杀，抢车位等等
     * @param args
     */
    public static void main(String[] args) {

        ArrayList<String> demoList = Lists.newArrayList();
        demoList.add("000");
        demoList.add("222");
        demoList.add("333");
        demoList.add("444");
        demoList.add("555");
        demoList.add("111");


        List<List<String>> lists = Lists.partition(demoList, 1);

        //创建线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(poolSize, poolSize, 6, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), THREAD_FACTORY, new ThreadPoolExecutor.AbortPolicy());

        //一共有三个资源
        Semaphore semaphore = new Semaphore(3);

        //循环开启线程
        for (List<String> list : lists) {
            Thread task = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName() + "抢到资源");
                        System.out.println();
                        TimeUnit.SECONDS.sleep(10);
                        System.out.println(Thread.currentThread().getName() + "放开资源");
                        System.out.println();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        semaphore.release();
                    }
                }
            });
            //每次开启一个线程都加入线程池
            executor.submit(task);
        }

        //关闭线程池
        executor.shutdown();

    }

}
