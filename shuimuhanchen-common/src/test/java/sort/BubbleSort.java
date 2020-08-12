package sort;

import java.util.Arrays;

/**
 * 冒泡排序-最小的元素上浮
 * - 最小时间复杂度O(n)
 * - 平均时间复杂度O(n^2)
 * - 最大时间复杂度O(n^2)
 * - 空间复杂度O(1)
 * - 稳定
 */
public class BubbleSort {
    public static void sort(int[] array) {
        if(array.length <=1) {
            return;
        }
        for(int i=1; i<array.length; i++) {
            int temp;

            for(int j=i; j>0; j--) {
                if(array[j] < array[j-1]) {//这里是> 不是>= 因此算法稳定
                    temp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = temp;
                }
            }
        }
    }
    public static void main(String[] args){
        int[] array = {3, 2, 1, 4};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
