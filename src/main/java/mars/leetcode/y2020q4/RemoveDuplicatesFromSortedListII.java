package mars.leetcode.y2020q4;

import mars.leetcode.util.ListNode;
import org.junit.Test;

public class RemoveDuplicatesFromSortedListII {

    /**
     * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/
     */

    @Test
    public void test() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(4);
        ListNode node6 = new ListNode(4);
        ListNode node7 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;

        deleteDuplicates(node1);
        System.currentTimeMillis();
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        boolean needDelete = false;
        ListNode prepre = null;
        ListNode pre = head;
        ListNode after = head.next;
        while (after!=null) {
            while (after != null && after.val == pre.val) {
                after = after.next;
                needDelete = true;
            }

            if (needDelete) {
                if (prepre == null) {
                    head = after;

                    prepre = null;
                    pre = after;
                    after = after == null ? null:after.next;
                } else {
                    prepre.next = after;

                    pre = after;
                    after = after == null ? null:after.next;
                }
            } else {
                prepre = pre;
                pre = after;
                after = after.next;
            }

            needDelete = false;
        }

        return head;
    }

}
