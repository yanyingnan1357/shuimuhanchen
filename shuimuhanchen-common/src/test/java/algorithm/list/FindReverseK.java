package algorithm.list;

/**
 * 求链表的倒数第k个元素。
 */
public class FindReverseK {

    public static ListNode findReverseK(ListNode head, int k) {

        if(k < 1 || head == null) {
            return new ListNode(-999);
        }
        ListNode p = head;
        ListNode q = head;
        while (k-- > 0) {
            if (p == null) {
                return new ListNode(-999);
            }
            p = p.next;
        }
        while (p != null) {
            p = p.next;
            q = q.next;
        }
        return q;
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

        System.out.print(findReverseK(list, 10).val);

    }

}
