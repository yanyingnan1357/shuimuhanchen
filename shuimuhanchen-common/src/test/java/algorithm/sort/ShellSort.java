package algorithm.sort;

import java.util.Arrays;

/**
 * 希尔排序-是直接插入排序的改进，常用h=3*h+1（h=1开始）作为步长计算公式，并依次减少步长，扩大了移动范围，减少了移动步数，当h=1时已经相对有序了，这时就是直接插入排序了。
 * - 最小时间复杂度O(n)
 * - 平均时间复杂度O(nlogn)
 * - 最大时间复杂度O(n^s)1-2
 * - 空间复杂度O(1)
 * - 不稳定
 */
public class ShellSort {
    public static void sort(int[] array) {
        if(array.length <=1) {
            return;
        }

        int h = array.length;
        while(h<=array.length/3)
            h = h*3+1;
        while(h>0){//while循环中与直接插入排序几乎一样
            for(int i=h; i<array.length; i++){//这里令i=h思考其原理
                int tmp = array[i];
                int j;
                if(array[i]<array[i-h]){
                    for(j=i-h; j>=0 && array[j]>tmp; j-=h){//从j-h开始以h步长往后覆盖;因为有步长的因素 所以不稳定
                        array[j+h] = array[j];
                    }
                    array[j+h] = tmp;
                }
            }
            h=(h-1)/3;
        }

    }
    public static void main(String[] args){
        int[] array = {3, 2, 1, 4};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
