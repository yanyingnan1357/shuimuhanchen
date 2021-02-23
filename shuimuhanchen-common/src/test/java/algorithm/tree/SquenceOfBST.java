package algorithm.tree;

/**
 * 给定一个序列，判断是否是二叉搜索树的后序遍历序列
 *
 */
public class SquenceOfBST {

    public boolean squenceOfBST(int [] sequence) {
        if (sequence == null || sequence.length == 0) {
            return false;
        }
        return sequenceOfBST(sequence, 0, sequence.length-1);
    }

    private boolean sequenceOfBST(int[] sequence, int start, int end) {
        if (start >= end) { //递归跳出条件
            return true;
        }
        int root = sequence[end];
        int i;
        for (i=start; i<end; i++) {
            if (sequence[i] > root) {
                break;
            }
        }

        for (int j=i; j<end; j++) {
            if (sequence[j] < root) {
                return false;
            }
        }
        return sequenceOfBST(sequence, start, i-1) && sequenceOfBST(sequence, i, end-1);
    }

}
