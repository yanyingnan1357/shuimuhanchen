import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.concurrent.Executors;

public class MethodHandleTest {

    private MethodHandle getHandler() {
        MethodHandle mh = null;
        //通过MethodType的静态工厂方法，先生成一个包含方法返回类型、方法参数类型、的 方法类型
        MethodType methodType = MethodType.methodType(String.class, int.class, int.class);
        MethodHandles.Lookup lookup = MethodHandles.lookup();

        try {
            mh = lookup.findVirtual(String.class, "substring", methodType);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return mh;
    }

    public static void main(String[] args) throws Throwable {
        MethodHandle mh = new MethodHandleTest().getHandler();
        String str = "hello world";

        Object result1 = mh.invoke(str, 1, 6);
        Object result2 = (String) mh.invokeExact(str, 1, 3);

        System.out.println("result 1:" + result1);
        System.out.println("result 2:" + result2);
    }

}
