package algorithm.tree;

/**
 * 判断是否是对称二叉树
 */
public class IsMirrorTree {

    public static boolean isMirrorTree(TreeNode root) {

        if (root == null) {
            return false;
        }
        return isMirrorTree(root.left, root.right);
    }

    //关键就在于这个重载方法
    private static boolean isMirrorTree(TreeNode left, TreeNode right) {

        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val == right.val) {
            return isMirrorTree(left.left, right.right) && isMirrorTree(left.right, right.left);
        }
        return false;
    }

}
