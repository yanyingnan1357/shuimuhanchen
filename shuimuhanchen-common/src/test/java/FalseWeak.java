/**
 * 为什么会有虚假唤醒（如果用if判断之后进行wait()或者await()）：两个线程没问题，两个以上就有问题
 * 举个例子：如果商品本来没有货物，突然进了一件商品，这时所有的线程都被唤醒了，但是只能一个人买，所以其他人都是假唤醒，获取不到对象的锁
 */
public class FalseWeak {

    private int number = 0;

    public synchronized void increment() throws InterruptedException {
        if (number != 0) {//应该用while 否则会虚假唤醒
            this.wait();
        }
        ++number;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        if (number == 0) {//应该用while 否则会虚假唤醒
            this.wait();
        }
        --number;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        this.notifyAll();
    }

    public static void main(String[] args) {
        FalseWeak sd = new FalseWeak();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    Thread.sleep(200);
                    sd.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    Thread.sleep(300);     //模拟执行其他代码
                    sd.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    Thread.sleep(400);
                    sd.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    Thread.sleep(500);
                    sd.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}
