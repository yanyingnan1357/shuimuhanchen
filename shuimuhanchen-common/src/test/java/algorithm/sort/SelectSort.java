package algorithm.sort;

import java.util.Arrays;

/**
 * 直接选择排序-每次选择最小的放在最前面
 * - 最小时间复杂度O(n^2)
 * - 平均时间复杂度O(n^2)
 * - 最大时间复杂度O(n^2)
 * - 空间复杂度O(1)
 * - 不稳定
 */
public class SelectSort {
    public static void sort(int[] array) {
        if(array.length <=1) {
            return;
        }
        for(int i=0; i<array.length; i++) {
            int min = i;

            for(int j=i+1; j<array.length; j++) {//存在跳跃式移动，因此会导致此排序不稳定
                if(array[j] < array[min]){
                    min = j;
                }
            }
            int temp = array[i];
            array[i] = array[min];
            array[min] = temp;
        }
    }
    public static void main(String[] args){
        int[] array = {3, 2, 1, 4};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
