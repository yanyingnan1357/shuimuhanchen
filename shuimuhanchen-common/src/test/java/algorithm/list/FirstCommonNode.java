package algorithm.list;

/**
 * 两个链表，找出第一个相交的点，没有交点返回null
 * 判断是否相交，若相交，求链表长度差值d，长链表先走d步,再共同向前走即可在第一个交点相遇
 */
public class FirstCommonNode {

    public static ListNode firstCommonNode(ListNode head1, ListNode head2) {
        //判断是否相交
        if(!intersect(head1, head2)) {
            return null;
        }

        ListNode p = head1;
        int len1 = 1;
        ListNode q = head2;
        int len2 = 1;
        while(p.next != null) {
            p = p.next;
            len1 ++;
        }
        while(q.next != null) {
            q = q.next;
            len2 ++;
        }
        p = head1;
        q = head2;
        if (len1 > len2) {
            int d = len1 - len2;
            while (d > 0) {
                d --;
                p = p.next;
            }
        } else {
            int d = len2 - len1;
            while (d > 0) {
                d --;
                q = q.next;
            }
        }
        while (p != q) {
            p = p.next;
            q = q.next;
        }
        return p;
    }

    private static Boolean intersect(ListNode head1, ListNode head2) {

        ListNode p = head1;
        ListNode q = head2;
        while(p.next != null) {
            p = p.next;
        }
        while(q.next != null) {
            q = q.next;
        }
        return p == q;
    }

    public static void main(String[] args) {
        ListNode list = new ListNode(1);
        ListNode list2 = new ListNode(2);
        ListNode list3 = new ListNode(3);
        ListNode list4 = new ListNode(4);
        ListNode list5 = new ListNode(5);
        list.next = list2;
        list2.next = list3;
        list3.next = list4;
        list4.next = list5;

        ListNode list22 = new ListNode(22);
        list22.next = list3;

        ListNode p = firstCommonNode(list, list22);
        if (p != null) {
            System.out.print(p.val);
        }
//        System.out.println(null == null);//true
    }

}
