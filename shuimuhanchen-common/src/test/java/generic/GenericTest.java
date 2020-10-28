package generic;

import java.util.*;

public class GenericTest {

    public static void main(String[] args) {
        List<String> name = new ArrayList<>();
        List<Integer> age = new ArrayList<>();
        List<Number> number = new ArrayList<>();

        name.add("icon");
        age.add(18);
        number.add(314);

        getData(name);
        getData(age);
        getData(number);

        /////////////////////////////////////////

//        getUperNumber(name);//类型通配符只限定
        getUperNumber(age);//2
        getUperNumber(number);//3

    }

    //类型通配符，用Object需要强转化，不如？方便
    private static void getData(List<?> data) {
        System.out.println("data :" + data.get(0));
    }

    private static void getUperNumber(List<? extends Number> data) {
        System.out.println("data :" + data.get(0));
    }
}
