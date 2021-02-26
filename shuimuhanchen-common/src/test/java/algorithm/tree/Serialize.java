package algorithm.tree;

/**
 * 请实现两个函数，分别用来序列化和反序列化二叉树
 */
public class Serialize {

    String serialize(TreeNode root) {
        StringBuffer sb = new StringBuffer();
        if (root == null){
            sb.append("$,");//$表示null值
            return sb.toString();
        }
        sb.append(root.val+",");//说白了就是前序遍历的应用
        sb.append(serialize(root.left));
        sb.append(serialize(root.right));
        return sb.toString();

    }

    int index = -1;//用来控制递归调用到str结尾停止

    TreeNode deserialize(String str) {
        index++;
        if(index>=str.length())
            return null;
        String[] s = str.split(",");
        TreeNode node = null;
        if(!"$".equals(s[index])){
            node = new TreeNode(Integer.valueOf(s[index]));
            node.left = deserialize(str);//还是前序遍历的应用
            node.right = deserialize(str);
        }
        return node;
    }

}