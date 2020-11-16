package algorithm.list;

/**
 * 删除链表指定节点，不知道头指针:即要求算法时间度为o(1)
 */
public class DeleteNode {

    public static void deleteNode(ListNode node) {
        if (node == null || node.next == null) {
            return;
        }
        node.val = node.next.val;
        node.next = node.next.next;
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

        deleteNode(list3);
        ListNode p = list;
        while (p != null) {
            System.out.print(p.val);
            p = p.next;
        }
    }

}
