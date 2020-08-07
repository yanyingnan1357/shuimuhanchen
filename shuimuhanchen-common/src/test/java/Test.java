import java.lang.ref.WeakReference;

public class Test {


//    public static void main(String[] args) {
//
//        class WeakestRef {
//            private void something()
//            {
//                System.out.println("This is printed out on the screen");
//            }
//        }
//
//        WeakestRef obj1 = new WeakestRef();
//        obj1.something();
//
////        SoftReference<WeakestRef> weakref = new SoftReference<>(obj1);
//        WeakReference<WeakestRef> weakref = new WeakReference<>(obj1);
//
//        obj1 = null;
//
//        System.gc();
//        obj1 = weakref.get();
//        obj1.something();
//    }

    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("sss");
        String s = threadLocal.get();
        System.out.println(s);
        threadLocal.remove();
    }

}
