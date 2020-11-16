package algorithm.list;

import java.util.ArrayList;
import java.util.List;

/**
 * 反向打印链表
 * 注意：存放反向的链表元素值的list必须是类的成员变量，或者作为方法入参，递归过程中的局部变量要慎用
 */
public class PrintListFromTailToHead {

//    private static List<Integer> list = new ArrayList<>();

    public static List<Integer> printListFromTailToHead(ListNode head, List<Integer> list) {

        if (head != null) {
            printListFromTailToHead(head.next, list);
            list.add(head.val);
        }
        return list;
    }

    public static void main(String[] args) {
        ListNode list = new ListNode(1);
        ListNode list2 = new ListNode(1);
        ListNode list3 = new ListNode(2);
        ListNode list4 = new ListNode(3);
        ListNode list5 = new ListNode(3);
        list.next = list2;
        list2.next = list3;
        list3.next = list4;
        list4.next = list5;

        System.out.println(printListFromTailToHead(list, new ArrayList<>()));

    }

}
