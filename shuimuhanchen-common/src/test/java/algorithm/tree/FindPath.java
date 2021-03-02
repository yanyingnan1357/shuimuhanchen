package algorithm.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 求二叉树中和为某一值的路径 根结点到叶子结点为一个路径 可能有负数呢
 */
public class FindPath {

    public static List<List<Integer>> findPath(TreeNode root, Integer target) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        List<Integer> tmp = new ArrayList<>();

        findPath(root, target, res, tmp);
        return res;
    }

    private static void findPath(TreeNode root, Integer target, List<List<Integer>> res, List<Integer> tmp) {
        if (root == null) {
            return;
        }
        target = target - root.val;
        tmp.add(root.val);

        //有可能有负数 所以小于零也不能return！
//        if(target < 0){
//            return;
//        }

        if (target == 0 && root.left == null && root.right == null) {
            res.add(tmp);
            return;
        }
        //必须new新的tmp  因为左右子树都要用！
        findPath(root.left, target, res, new ArrayList<>(tmp));
        findPath(root.right, target, res, new ArrayList<>(tmp));
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
