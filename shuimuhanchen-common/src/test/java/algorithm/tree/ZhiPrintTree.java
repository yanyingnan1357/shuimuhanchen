package algorithm.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，
 * 第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推
 *
 */
public class ZhiPrintTree {

    private static List<List<Integer>> zhiPrintTree(TreeNode root) {

        List<List<Integer>> list = new ArrayList<>();
        if(root == null) {
            return list;
        }

        int flag = 0;//flag作为行数标记
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        s1.add(root);
        ArrayList<Integer> row = new ArrayList<>();//记录每一行

        while(!s1.isEmpty()){//如果s1 和 s2 空了  代表结点遍历完了  因此  下面用到了 s1 s2交换  这是这道题的经典
            TreeNode node = s1.pop();
            row.add(node.val);
            if(flag == 0){//flag=0 表示下一行从左到右入栈  否则 从右到左入栈
                if(node.left!=null)
                    s2.add(node.left);
                if(node.right!=null)
                    s2.add(node.right);
            }else{
                if(node.right!=null)
                    s2.add(node.right);
                if(node.left!=null)
                    s2.add(node.left);
            }

            if(s1.isEmpty()){//当s1空  就交换两个栈  经典所在。 本来要交替控制处理s1s2，利用交换 只需要每次都控制s1就好了
                Stack<TreeNode> s = s1;
                s1 = s2;
                s2 = s;
                flag = 1-flag;
                list.add(new ArrayList<>(row));
                row.clear();
            }
        }
        return list;
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

        List<List<Integer>> lists = zhiPrintTree(root);
        for (List<Integer> list : lists) {
            for (Integer i : list) {
                System.out.print(i);
            }
        }

    }

}
