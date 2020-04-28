import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalTest {

    public static void main(String[] args) throws Exception {
        Thread thread[] = new Thread[3];
        for (int i = 0; i < 3; i++) {
            DemoTask demoTask = new DemoTask();
            thread[i] = new Thread(demoTask, "Thread " + i);
        }
        for (int i = 0; i < 3; i++) {
            thread[i].start();
            TimeUnit.SECONDS.sleep((int) Math.rint(Math.random() * 10));
        }
    }

    public static class DemoTask implements Runnable {
        // 包含要分配的下一个线程ID的原子整数
        private static final AtomicInteger nextId = new AtomicInteger(0);

        // 包含每个线程的ID的ThreadLocal变量
        private static final ThreadLocal<Integer> threadId = new ThreadLocal<Integer>() {
            @Override
            protected Integer initialValue() {
                return nextId.getAndIncrement();// 获取值，并递增
            }
        };

        // 返回当前线程的唯一ID，必要时为其赋值
        public int getThreadId() {
            return threadId.get();
        }

        // 返回当前线程的启动时间戳
        private static final ThreadLocal<Date> startDate = new ThreadLocal<Date>() {
            protected Date initialValue() {
                return new Date();
            }
        };

        public void run() {
            System.out.printf("Starting Thread: %s : %s\n", getThreadId(), startDate.get());
            try {
                TimeUnit.SECONDS.sleep((int) Math.rint(Math.random() * 10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("Thread Finished: %s : %s\n", getThreadId(), startDate.get());
        }
    }

}
