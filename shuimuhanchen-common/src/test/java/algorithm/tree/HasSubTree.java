package algorithm.tree;

/**
 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 *
 */
public class HasSubTree {

    public boolean hasSubTree(TreeNode root1, TreeNode root2) {
        boolean flag = false;
        if (root1 == null || root2 == null) {
            return flag;
        }

        if (root1.val == root2.val) {
            flag = isSubTree(root1, root2);
        }
        if (!flag) {
            flag = hasSubTree(root1.left, root2);
        }
        if (!flag) {
            flag = hasSubTree(root1.right, root2);
        }
        return flag;
    }

    private static boolean isSubTree(TreeNode root1, TreeNode root2) {
        if (root2 == null) {
            return true;
        }
        if (root1 == null) {
            return false;
        }
        boolean flag = false;
        if (root1.val == root2.val) {
            flag = isSubTree(root1.left, root2.left) && isSubTree(root1.right, root2.right);
        }
        return flag;
    }

}
