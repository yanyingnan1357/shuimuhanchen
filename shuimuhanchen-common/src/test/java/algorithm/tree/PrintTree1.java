package algorithm.tree;

import java.util.*;

/**
 * 层序遍历二叉树
 *
 */
public class PrintTree1 {

    private static List<List<Integer>> printTree(TreeNode root) {

        List<List<Integer>> list = new ArrayList<>();
        if(root == null) {
            return list;
        }
        List<Integer> row = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();//ArrayDeque LinkedList都是接口Queue的实现类
        queue.add(root);
        int num = 1;//记录当前行还有多少结点需要打印。
        int nextNum = 0;//记录下一层的节点数。

        while(!queue.isEmpty()){
            if(queue.element().left!=null){
                queue.add(queue.element().left);
                nextNum++;//下一层加一
            }
            if(queue.element().right!=null){
                queue.add(queue.element().right);
                nextNum++;//下一层加一
            }
            row.add(queue.poll().val);
            num--;//这一层减一
            if(num == 0){
                list.add(new ArrayList<>(row));
                row.clear();
                num = nextNum;//上下层记录交换
                nextNum = 0;
            }
        }
        return list;
    }

    //用两个队列就不需要上面那样 记录当前行还有多少结点需要打印了
    public static List<List<Integer>> printTree2 (TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        List<Integer> row = new ArrayList<>();

        Queue<TreeNode> q1 = new ArrayDeque<>();
        Queue<TreeNode> q2 = new ArrayDeque<>();
        q1.add(root);

        while(!q1.isEmpty()) {
            TreeNode tmp = q1.poll();
            row.add(tmp.val);
            if (tmp.left != null) {
                q2.add(tmp.left);
            }
            if (tmp.right != null) {
                q2.add(tmp.right);
            }

            if (q1.isEmpty()) {
                res.add(new ArrayList(row));
                row.clear();//记得清除
                Queue<TreeNode> q = q1;
                q1 = q2;
                q2 = q;
            }
        }
        return res;
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;

        List<List<Integer>> lists = printTree(root);
        for (List<Integer> list : lists) {
            for (Integer i : list) {
                System.out.print(i);
            }
        }
        System.out.println();
        System.out.println();
        List<List<Integer>> listz = printTree2(root);
        for (List<Integer> list : listz) {
            for (Integer i : list) {
                System.out.print(i);
            }
        }
    }

}
