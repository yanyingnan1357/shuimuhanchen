package cn.yyn.common;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * 服务内线程池统一管理
 *
 * 1、最大线程数设置：
 * io密集型  cpu核数*2
 * cpu密集型 cpu核数+1
 *
 * 2、拒绝策略四种：
 * ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
 * ThreadPoolExecutor.DiscardPolicy：丢弃任务，但是不抛出异常。
 * ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新提交被拒绝的任务
 * ThreadPoolExecutor.CallerRunsPolicy：由调用线程（提交任务的线程）处理该任务
 * 备注：作为阻塞队列模拟生产者消费者模式时可以自己写一种策略：见BlockingPolicy
 *
 */
@Component
@Slf4j
public class ThreadPoolUtil {

    private static final int poolSizeForIO = 4;

    private static final int poolSizeForCPU = 3;

    private static final ThreadFactory THREAD_FACTORY = new ThreadFactoryBuilder().setNameFormat("ThreadPoolContainer-thread-%d").build();

    public ThreadPoolExecutor getCustomExecutorService() {
        return customExecutorService;
    }

    public ScheduledThreadPoolExecutor getScheduledThreadPoolExecutor() {
        return scheduledThreadPoolExecutor;
    }

    //ThreadPoolExecutor.AbortPolicy()表示如果当前线程池无法继续处理任务了，此时又进来了一个任务，处理策略是抛异常，也是默认的处理策略。
    //LinkedBlockingQueue必须定义大小，因为它默认2^31-1
    private ThreadPoolExecutor customExecutorService = new ThreadPoolExecutor(poolSizeForIO, poolSizeForIO, 2, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1024), ThreadPoolUtil.THREAD_FACTORY, new BlockingPolicy());

    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, THREAD_FACTORY);

}
