package generic;

public class GenericMethodTest {

    // 泛型方法 printArray
    public static <E> void printArray(E[] inputArray) {
        for (E element : inputArray) {
            System.out.printf("%s ", element);
        }
    }

    // 泛型方法 printArray2
    public static <E, T> void printArray2(E[] inputArray, T[] inputArray2) {
        for (E element : inputArray) {
            System.out.printf("%s ", element);
        }
        System.out.println();
        for (T element : inputArray2) {
            System.out.printf("%s ", element);
        }
    }

    /////////////////////////////////////////////////////

    // 比较三个值并返回最大值-传入的范型必须继承Comparable
    public static <T extends Comparable<T>> T maximum(T x, T y, T z) {
        T max = x; // 假设x是初始最大值
        if (y.compareTo(max) > 0) {
            max = y; //y 更大
        }
        if (z.compareTo(max) > 0) {
            max = z; // 现在 z 更大
        }
        return max; // 返回最大对象
    }


    public static void main(String[] args) {
        Integer[] inputArray = {1, 2, 3};
        String[] inputArray2 = {"1", "2", "3"};
        printArray(inputArray);
        System.out.println();
        printArray(inputArray2);

        System.out.println();System.out.println();
        printArray2(inputArray, inputArray2);

        /////////////
        System.out.println();System.out.println();
        System.out.printf("%d, %d 和 %d 中最大的数为 %d",
                3, 4, 5, maximum(3, 4, 5));

        System.out.println();
        System.out.printf("%.1f, %.1f 和 %.1f 中最大的数为 %.1f",
                6.6, 8.8, 7.7, maximum(6.6, 8.8, 7.7));

        System.out.println();
        System.out.printf("%s, %s 和 %s 中最大的数为 %s\n", "pear",
                "apple", "orange", maximum("pear", "apple", "orange"));


    }

}
