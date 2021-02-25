package algorithm.tree;

/**
 * 是否是平衡二叉树 (其中包含求二叉树的深度的算法)
 */
public class IsBalancedTree {

    public boolean IsBalanced_Solution(TreeNode root) {
        return depth(root) != -1;
    }

    //求平衡二叉树的深度，不是平衡二叉树 返回-1  （删除后三个if语句 就是求二叉树的深度）
    private int depth(TreeNode root) {

        if (root == null) {
            return 0;
        }
        int left = depth(root.left);
        if (left == -1) {
            return -1;
        }
        int right = depth(root.right);
        if (right == -1) {
            return -1;
        }
        if (Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;

    }

}
