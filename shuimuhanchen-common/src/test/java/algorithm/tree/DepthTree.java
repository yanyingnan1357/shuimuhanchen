package algorithm.tree;

/**
 * 求二叉树深度
 */
public class DepthTree {

    public static int depthTree(TreeNode root) {

        if (root == null) {
            return 0;
        }
        return Math.max(depthTree(root.left), depthTree(root.right)) + 1;
    }


    public static void main(String[] args) {

        TreeNode root=new TreeNode();
        TreeNode n1=new TreeNode();
        TreeNode n2=new TreeNode();
        TreeNode n3=new TreeNode();
        TreeNode n4=new TreeNode();
        root.left=n1;
        root.right=n2;
        n1.left=n3;
        n1.right=n4;

        root.val=10;
        n1.val=5;
        n2.val=12;
        n3.val=4;
        n4.val=7;
        System.out.println(depthTree(root));
    }

}
