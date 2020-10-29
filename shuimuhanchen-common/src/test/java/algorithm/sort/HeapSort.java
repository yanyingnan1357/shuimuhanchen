package algorithm.sort;

import java.util.Arrays;

/**
 * 堆排序-先循环建立大顶堆，再交换头尾保证大数在末尾，树结构是存放在数组中的。(其实不每次都建立大顶堆，只找到最大节点在最顶点，然后交换最末叶子节点也可以，如：不写注释掉的代码部分依然可以完成排序)
 * - 最小时间复杂度O(nlogn)
 * - 平均时间复杂度O(nlogn)
 * - 最大时间复杂度O(nlogn)
 * - 空间复杂度O(1)
 * - 不稳定
 */
public class HeapSort {

    public static void sort(int[] a){
        for(int i=0; i<a.length-1; i++){//循环建立大顶堆，并在每次循环后都完成交换；
            buildMaxHeap(a,a.length-1-i);
            swap(a, 0, a.length-1-i);
        }
    }

    private static void buildMaxHeap(int[] a, int lastIndex) {//建立大顶堆，排序后就是由小到大
        for(int i=(lastIndex-1)/2; i>=0; i--){
            int k = i;//k用来完成while循环
//            while(2*k+1<=lastIndex){//如果当前节点存在子节点

                int biggerIndex = 2*k+1;//先令左子节点为较大的结点
                if(biggerIndex<lastIndex){//如果存在右子节点
                    if(a[biggerIndex] < a[biggerIndex+1])//若右子节点大,始终保存大的结点
                        biggerIndex++;
                }

                if(a[k]<a[biggerIndex]){
                    swap(a, k, biggerIndex);
                    k = biggerIndex;
                }else{
//                    break;//while循环的目的是保证交换之后的子节点依然大于它的子节点 而如果没有交换就没必要执行下一次while了 因此直接break
                }
            }
//        }
    }

    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args){
        int[] array = {3, 2, 1, 4, 7, 9, 7};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
