package cn.yyn.common;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * 服务内线程池统一管理
 *
 */
@Component
@Slf4j
public class ThreadPoolUtil {

    private static final int poolSize = 4;

    private static final ThreadFactory THREAD_FACTORY = new ThreadFactoryBuilder().setNameFormat("ThreadPoolContainer-thread-%d").build();

    public ThreadPoolExecutor getCustomExecutorService() {
        return customExecutorService;
    }

    public ScheduledThreadPoolExecutor getScheduledThreadPoolExecutor() {
        return scheduledThreadPoolExecutor;
    }

    //ThreadPoolExecutor.AbortPolicy()表示如果当前线程池无法继续处理任务了，此时又进来了一个任务，处理策略是抛异常，也是默认的处理策略。
    //LinkedBlockingQueue必须定义大小，因为它默认2^31-1
    private ThreadPoolExecutor customExecutorService = new ThreadPoolExecutor(poolSize, poolSize, 2, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1024), ThreadPoolUtil.THREAD_FACTORY, new ThreadPoolExecutor.AbortPolicy());

    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, THREAD_FACTORY);

}
