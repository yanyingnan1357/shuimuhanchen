package algorithm.list;

/**
 * 链表的反转。
 */
public class Reverse {

    public static ListNode reverse(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        ListNode p1 = head;
        ListNode p2 = p1.next;
        ListNode p3 = p2.next;
        head.next = null;//这个很关键 否则循环链表了
        while (p2 != null) {
            p2.next = p1;
            p1 = p2;
            p2 = p3;
            if(p3 != null) {
                p3 = p3.next;
            }
        }

        return p1;
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

        ListNode p = reverse(list);
        while (p != null) {
            System.out.print(p.val);
            p = p.next;
        }
    }

}
