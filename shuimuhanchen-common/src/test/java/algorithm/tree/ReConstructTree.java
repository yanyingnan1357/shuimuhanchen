package algorithm.tree;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 已知前序（后序）、中序，重建二叉树
 *
 */
public class ReConstructTree {

    private static TreeNode reConstructTreePreIn(int[] pre, int[] in, int preStart, int preEnd, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) { //递归跳出条件
            return null;
        }

        TreeNode root = new TreeNode(pre[preStart]);
        for (int i = inStart; i <= inEnd; i++) {
            if (in[i] == pre[preStart]) {
                root.left = reConstructTreePreIn(pre, in, preStart+1, i-inStart+preStart, inStart, i-1);
                root.right = reConstructTreePreIn(pre, in, i+1+preEnd-inEnd, preEnd, i+1, inEnd);
            }
        }
        return root;
    }

    private static TreeNode reConstructTreeNextIn(int[] next, int[] in, int nextStart, int nextEnd, int inStart, int inEnd) {
        if (nextStart > nextEnd || inStart > inEnd) { //递归跳出条件
            return null;
        }

        TreeNode root = new TreeNode(next[nextEnd]);
        for (int i = inStart; i <= inEnd; i++) {
            if (in[i] == next[nextEnd]) {
                root.left = reConstructTreeNextIn(next, in, nextStart, i-1-inStart+nextStart, inStart, i-1);
                root.right = reConstructTreeNextIn(next, in, i-inEnd+nextEnd, nextEnd-1, i+1, inEnd);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        int pre[] = new int[] {1,2,3,4,5,6,7};
        int in[] = new int[] {3,2,4,1,6,5,7};
        int next[] = new int[] {3,4,2,6,7,5,1};

        printTree(reConstructTreePreIn(pre, in, 0, pre.length-1, 0, in.length-1));
        System.out.println();
        printTree(reConstructTreeNextIn(next, in, 0, next.length-1, 0, in.length-1));
    }

    //层序遍历用于测试
    private static void printTree(TreeNode root) {
        if(root == null) {
            return ;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            System.out.print(node.val + ",");

            if(node.left!=null){
                queue.add(node.left);
            }
            if(node.right!=null){
                queue.add(node.right);
            }
        }
    }

}
