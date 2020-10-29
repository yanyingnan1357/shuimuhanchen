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

    public static void sort(int[] a){
        subSort(a,0,a.length-1);
    }

    private static void subSort(int[] a, int start, int end) {//使用了递归栈空间所以空间效率稍微差一点

        if(start<end){//递归跳出条件
            int i = start, j = end+1;

            while(true){//这个while循环 对应快排的一趟

                while(i<end && a[++i]<a[start]);//a[start]为基数 i找比基数大的
                while(j>start && a[--j]>a[start]);//a[start]为基数 j找比基数小的

                if(i<j) {
                    swap(a,i,j);
                }
                else {
                    break;
                }
            }
            swap(a,start,j);
            subSort(a, start, j-1);
            subSort(a, j+1, end);
        }
    }

    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args){
        int[] array = {3, 2, 1, 4};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
