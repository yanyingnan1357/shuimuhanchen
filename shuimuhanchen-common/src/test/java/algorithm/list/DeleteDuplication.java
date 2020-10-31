package algorithm.list;

/**
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
 */
public class DeleteDuplication {

    public static ListNode deleteDuplication(ListNode head) {

        if(head == null || head.next == null) {
            return head;
        }
        //手动新建一个不会重复的节点-999
        ListNode tmp = new ListNode(-999);
        tmp.next = head;

        ListNode pre = tmp;
        ListNode p = head;

        while(p != null) {
            //找重复节点的最后一个节点
            while(p.next != null && p.val == p.next.val) {
                p = p.next;
            }

            if(pre.next == p) {
                pre = p;
                p = p.next;
            } else {
                p = p.next;
                pre.next = p;
            }
        }
        return tmp.next;
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

        list = deleteDuplication(list);
        while(list != null) {
            System.out.print(list.val);
            list = list.next;
        }
    }

}
