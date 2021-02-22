package algorithm.sort;

import java.util.Arrays;

/**
 * 快排-第一个元素作为基数，前后指针以基数为准分两边，递归进行下去即可。
 * - 最小时间复杂度O(nlogn)
 * - 平均时间复杂度O(nlogn)
 * - 最大时间复杂度O(n^2)
 * - 空间复杂度O(logn)
 * - 不稳定
 */
public class QuickSort {

    private static void quickSort(int[] a, int start, int end) {

        if(start < end) {//先写递归跳出条件
            int i = start + 1;
            int j = end;

            while (i < j) {//第一趟快排
                while (i < end && a[i] < a[start]) {
                    i++;
                }//a[start]为基数
                while (j > start && a[j] > a[start]) {
                    j--;
                }//a[start]为基数
                if(i<j) {
                    swap(a, i, j);
                }
            }
            swap(a, start, j);
            quickSort(a, start, j-1);
            quickSort(a, j+1, end);
        }
    }

    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args){
        int[] array = {3, 2, 1, 0};
        quickSort(array, 0, array.length-1);
        System.out.println(Arrays.toString(array));
    }
}
