package thread;

public class SingletonTest {

    private SingletonTest(){}//私有构造器 不让其它类创建本类对象

    //new一个对象实际分三步走：分配内存空间->初始化对象->指向内存空间
    //可能进行指令重排：分配内存空间->指向内存空间->初始化对象  就会造成双重检查也不见得100%单例
    //因此加volatile禁止指令重排
    private static volatile SingletonTest uniqueInstance;

    public static SingletonTest getingleton(){
        if(uniqueInstance == null){//第一次检查
            synchronized (SingletonTest.class){
                if(uniqueInstance == null){//第二次检查

                    uniqueInstance = new SingletonTest();
                }
            }
        }
        return uniqueInstance;
    }

}
