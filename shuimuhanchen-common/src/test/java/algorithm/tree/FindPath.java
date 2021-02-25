package algorithm.tree;

import java.util.ArrayList;

/**
 * 求二叉树中和为某一值的路径 根结点到叶子结点为一个路径
 */
public class FindPath {

    public static ArrayList<ArrayList<Integer>> findPath(TreeNode root,int target) {

        ArrayList<ArrayList<Integer>> listResult = new ArrayList<>();

        if(root==null || root.val>target)
            return listResult;

        ArrayList<Integer> list = new ArrayList<>();

        findPath(root, target, listResult, list);

        return listResult;

    }

    private static void findPath(TreeNode root, int target, ArrayList<ArrayList<Integer>> listResult, ArrayList<Integer> list) {

        //如果当前结点为空 或者 当前结点值大于target 清空路径
        if(root==null || root.val>target) {
            //清空该路径
            list.clear();
        //如果当前结点值等于target && 是叶子节点  将该结点添加到list中 否则清除
        } else if(root.val==target) {
            if(root.left==null && root.right==null) {
                list.add(root.val);
                listResult.add(new ArrayList<>(list));
            }
        //如果当前结点值小于target，递归找他的左右子树
        } else {
            list.add(root.val);

            //再拷贝一个list的用途在于，二叉树按照左右两个方向递归找寻剩余路径都要用到目前list中已存在的路径
            ArrayList<Integer> list2 = new ArrayList<>(list);

            target -= root.val;

            findPath(root.left, target, listResult, list);
            findPath(root.right, target, listResult, list2);

        }
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
        System.out.println(findPath(root, 22));
    }

}
