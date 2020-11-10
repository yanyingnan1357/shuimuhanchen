package algorithm.list;

/**
 * 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
 * 先求出环的长度，一个指针先走环的长度，另一个指针开始一起走，一定在环的入口相遇，
 * 原因：单链表的环一定出现在末尾，如果想从头节点走到环的入口，必须要走 总长度-环长度 ，那么先走环的长度，从这里开始走要走到环的入口，也是需要走 总长度-环长度
 */
public class EntryNodeOfLoop {

    public static ListNode entryNodeOfLoop(ListNode head) {
        //判断是否有环
        if (!hasLoop(head)) {
            return null;
        }
        //求出环的长度
        int len = loopLen(head);
        ListNode p = head;
        ListNode q = head;
        while (len > 0) {
            len --;
            p = p.next;
        }
        while (p != q) {
            p = p.next;
            q = q.next;
        }
        return p;
    }

    /**
     * 求环的长度
     */
    private static int loopLen(ListNode head) {
        //判断是否有环
        if (!hasLoop(head)) {
            return 0;
        }
        int len = 1;
        ListNode p = head;
        ListNode q = head;
        while (p != null && q.next != null) {
            p = p.next;
            q = q.next.next;
            if(p == q) {
                break;
            }
        }
        p = p.next;
        while (p != q) {
            len ++;
            p = p.next;
        }
        return len;
    }

    /**
     * 判断链表是否有环
     */
    private static Boolean hasLoop(ListNode head) {
        ListNode p = head;
        ListNode q = head;
        while (p != null && q.next != null) {
            p = p.next;
            q = q.next.next;
            if(p == q) {
                return true;
            }
        }
        return false;
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
        list5.next = list2;

        System.out.print(entryNodeOfLoop(list).val);

    }

}
