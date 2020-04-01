public class DeaThreadTest implements Runnable{

    //锁对象用final修饰表示对象必须被初始化，不能被修改；
    //非final的对象可以被重新赋值，锁对象就不受管控了；
    //当一个锁被其他对象占有时，当前线程可以对锁对象重新赋值，从而会拿到了运行的权利。
    private final String lock1;
    private final String lock2;

    public DeaThreadTest(String lock1, String lock2){
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    public void run(){
        synchronized(lock1){
            try {
                System.out.println(Thread.currentThread().getName() + "获取了锁:" + lock1);
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized(lock2){//拿着lock1寻找lock2
                System.out.println("能获取锁:" + lock2 + "嘛？");
            }
        }
    }

    public static void main(String[] args) {

        new Thread(new DeaThreadTest("lock1", "lock2"), "线程1").start();
        new Thread(new DeaThreadTest("lock2", "lock1"), "线程2").start();

    }

}
