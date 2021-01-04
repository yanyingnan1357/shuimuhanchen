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

    private static void merge(int[] a, int left, int mid, int right) {
        int midRight = mid + 1;
        int[] tmpA = new int[a.length];
        int index = left;//index为tmpA数组的临时下标
        int tmp = left;//需要拷贝回原数组，因此暂存a的left下标

        while (left<= mid && right>=midRight) {
            if (a[left] < a[midRight]) {//不是 <= 所以稳定
                tmpA[index++] = a[left++];
            } else {
                tmpA[index++] = a[midRight++];
            }
        }
        while (left <= mid) {
            tmpA[index++] = a[left++];
        }
        while (midRight <= right) {
            tmpA[index++] = a[midRight++];
        }
        //排好序的位置覆盖回原数组
        while (tmp <= right) {
            a[tmp] = tmpA[tmp++];
        }
    }


    public static void main(String[] args){
        int[] array = {3, 2, 1, 4};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
