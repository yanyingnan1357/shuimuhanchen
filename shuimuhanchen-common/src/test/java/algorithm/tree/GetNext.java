package algorithm.tree;

/**
 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
 * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针
 *
 */
public class GetNext {

    public TreeNode getNext(TreeNode pNode){
        if(pNode == null)
            return null;
        if(pNode.next == null && pNode.right == null)//如果pNode是根节点，且无右子树，返回空。
            return null;

        TreeNode tmp;
        if(pNode.right != null){//如果该节点有右子树 ，返回右子树最左端结点。
            tmp = pNode.right;
            while(tmp.left!=null)
                tmp = tmp.left;
            return tmp;
        }else{                  //该节点无右子树，分两种情况。
            if(pNode == pNode.next.left){//如果该节点是左孩子，返回它的父节点。
                return pNode.next;
            }else{                      //该节点是右孩子，向上找一个左孩子的父节点返回。
                tmp = pNode.next;
                while(tmp.next != null){
                    if(tmp.next.left == tmp){
                        return tmp.next;
                    }
                    tmp = tmp.next;
                }
            }
        }
        return null;
    }
}
