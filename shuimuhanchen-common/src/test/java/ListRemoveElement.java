import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListRemoveElement {
    /**
     * 删除集合中小于0的元素
     * @param args
     */
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
//        List<Integer> list = new LinkedList<>();

        list.add(1);
        list.add(-1);
        list.add(-1);
        list.add(-1);
        list.add(2);
        list.add(3);

        System.out.println(list);

        //方法一：成功删除了，注意i--是必要的，否则可能漏删
//        for (int i=0; i<list.size(); i++) {
//            if (list.get(i) < 0) {
//                list.remove(i);
//                i--;
//            }
//        }
        //方法二：只能成功删除第一个小于0的元素，注意break是必要的，否则报错
//        for (Integer i : list) {
//            if (i < 0) {
//                list.remove(i);
//                break;
//            }
//        }
        //方法三：随便删，注意要使用iterator的remove方法
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            if (i < 0) {
                iterator.remove();
            }
        }
        System.out.println(list);
    }

}
