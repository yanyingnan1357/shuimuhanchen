package generic;

public class GeneticClassTest<T> {

    private T t;

    public void add(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    public static void main(String[] args) {
        GeneticClassTest<Integer> integerBox = new GeneticClassTest<>();
        GeneticClassTest<String> stringBox = new GeneticClassTest<>();

        integerBox.add(10);
        stringBox.add("hahahahah");

        System.out.printf("整型值为 :%d", integerBox.get());
        System.out.println();
        System.out.printf("字符串为 :%s", stringBox.get());
    }

}
