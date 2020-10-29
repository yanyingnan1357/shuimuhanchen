package algorithm.sort;

import java.util.Arrays;

/**
 * 归并排-归并思想是三个while循环，排序思想是递归。
 * - 最小时间复杂度O(nlogn)
 * - 平均时间复杂度O(nlogn)
 * - 最大时间复杂度O(nlogn)
 * - 空间复杂度O(n)
 * - 稳定
 */
public class MergeSort {

    public static void sort(int[] a){
        sort(a,0,a.length-1);
    }

    private static void sort(int[] a, int left, int right) {
        if(left<right){
            int center = (left+right)/2;
            sort(a,left,center);
            sort(a,center+1,right);
            merge(a,left,center,right);
        }
    }

    private static void merge(int[] a, int left, int center, int right) {
        int mid = center+1;
        int index = left;//index为tmpA数组的临时下标
        int tmp = left;//用于拷贝回原数组的时候用的临时下标
        int[] tmpA = new int[a.length];
        while(left<=center && mid<=right){
            if(a[left]<a[mid])//< 不是 <= 所以稳定
                tmpA[index++] = a[left++];
            else
                tmpA[index++] = a[mid++];
        }
        while(left<=center)
            tmpA[index++] = a[left++];
        while(mid<=right)
            tmpA[index++] = a[mid++];
        //拷贝回原数组
        while(tmp<=right)
            a[tmp] = tmpA[tmp++];
    }

    public static void main(String[] args){
        int[] array = {3, 2, 1, 4};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
