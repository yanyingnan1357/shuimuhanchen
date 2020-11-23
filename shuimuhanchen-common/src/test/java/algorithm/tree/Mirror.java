package algorithm.tree;

/**
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 *
 */
public class Mirror {

    private static void mirror(TreeNode root) {
        if(root == null) {
            return;
        }

        mirror(root.left);
        mirror(root.right);

        TreeNode tree;
        tree = root.left;
        root.left = root.right;
        root.right = tree;

    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node2.left = node4;

        printTree(root);

        mirror(root);
        System.out.println();

        printTree(root);

    }

    private static void printTree(TreeNode root) {

        if (root != null) {
            System.out.print(root.val);
            printTree(root.left);
            printTree(root.right);
        }

    }
}
