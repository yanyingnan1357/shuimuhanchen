package algorithm.list;

/**
 * 链表中间节点。偶数节点以第一个中间节点为准
 */
public class ListMiddle {

    public static ListNode listMiddle(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = head;
        ListNode q = head;

        while(q.next != null && q.next.next != null){
            p = p.next;
            q = q.next.next;
        }

        return p;
    }

    public static void main(String[] args) {
        ListNode list = new ListNode(1);
        ListNode list2 = new ListNode(1);
        ListNode list3 = new ListNode(2);
        ListNode list4 = new ListNode(3);
//        ListNode list5 = new ListNode(3);
        list.next = list2;
        list2.next = list3;
        list3.next = list4;
//        list4.next = list5;

        System.out.print(listMiddle(list).val);
    }

}
