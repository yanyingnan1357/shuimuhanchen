package cn.yyn.common;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
public class BlockingPolicy implements RejectedExecutionHandler{
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        try {
            // blockingqueue的offer改成put阻塞方法，BlockingQueue的几个方法：
            // add() 将指定的元素插入到此队列中（如果立即可行且不会违反容量限制），在成功时返回 true，如果当前没有可用空间，则抛出 IllegalStateException。
            // offer()将指定元素插入到此队列的尾部（如果立即可行且不会超出此队列的容量），在成功时返回 true，如果此队列已满，则返回 false
            // put()将指定元素插入到此队列的尾部，如有必要，则等待空间变得可用。
            executor.getQueue().put(r);
        } catch (InterruptedException e) {
            log.error("reject handler exception ", e);
            Thread.currentThread().interrupt();
        }
    }
}
