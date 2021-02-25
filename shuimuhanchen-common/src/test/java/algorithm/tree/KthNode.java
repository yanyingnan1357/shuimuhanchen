package algorithm.tree;

import java.util.Stack;

/**
 * 二叉搜索树的第k个结点
 */
public class KthNode {

    public static TreeNode kthNode(TreeNode root, int k) {

        if (root == null || k < 1) {
            return null;
        }

        //用栈模拟中序遍历，用递归的话不好控制k的值自减
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;

        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.add(node);
                node = node.left;
            } else {
                node = stack.pop();
                if (--k == 0) {
                    return node;
                }
                node = node.right;
            }
        }
        return null;
    }

}
