package algorithm.sort;

import java.util.Arrays;

/**
 * 冒泡排序-小元素上浮
 * - 最小时间复杂度O(n)
 * - 平均时间复杂度O(n^2)
 * - 最大时间复杂度O(n^2)
 * - 空间复杂度O(1)
 * - 稳定
 */
public class BubbleSort {

    private static void sort(int[] arr) {
        for (int i=0; i<arr.length-1; i++) {
            for (int j=0; j<arr.length-1-i; j++) {
                if(arr[j] > arr[j+1]) {
                    swap(arr, j, j+1);
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        if(i > arr.length-1 || j > arr.length-1 || i < 0 || j<0) {
            return;
        }
        int tmp;
        tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args){
        int[] array = {3, 2, 1, 4};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
