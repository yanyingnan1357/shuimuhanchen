package sort;

import java.util.Arrays;

/**
 * 直接插入排序-暂存待插入元素，已排序好的元素不断覆盖它，直到找到该元素应插入的位置
 * - 最小时间复杂度O(n)
 * - 平均时间复杂度O(n^2)
 * - 最大时间复杂度O(n^2)
 * - 空间复杂度O(1)
 * - 稳定
 */
public class InsertSort {
    public static void sort(int[] array) {
        if(array.length <=1) {
            return;
        }
        for(int i=1; i<array.length; i++) {
            int temp = array[i];
            int j;

            for(j=i-1; j>=0 && array[j]>temp; j--) {//这里是> 不是>= 因此算法稳定
                array[j+1] = array[j];
            }
            array[j+1] = temp;
        }
    }
    public static void main(String[] args){
        int[] array = {3, 2, 1, 4};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
