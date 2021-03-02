package algorithm.tree;

/**
 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
 * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针
 *
 */
public class GetNext {

    public TreeNode getNext(TreeNode node){
        if (node == null || (node.left == null && node.right == null)) {
            return null;
        }
        if (node.next == null && node.right == null) {
            return null;
        }

        TreeNode tmp;
        if (node.right != null) {//中序遍历，下一步一定找右节点的最左侧节点
            tmp = node.right;
            while (tmp.left != null) {
                tmp = tmp.left;
            }
            return tmp;
        } else {//没有右节点
            if (node.next.left == node) { //该结点是左子结点
                return node.next;
            }
            if (node.next.right == node) { //该结点是右子结点
                tmp = node.next;
                while (tmp.next != null) {
                    if (tmp.next.left == tmp) {
                        return tmp.next;
                    }
                    tmp = tmp.next;
                }
            }
        }
        return null;
    }
}
