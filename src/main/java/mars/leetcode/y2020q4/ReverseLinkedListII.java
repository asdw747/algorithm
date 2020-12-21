package mars.leetcode.y2020q4;

import mars.leetcode.util.ListNode;
import org.junit.Test;

public class ReverseLinkedListII {

    /**
     * https://leetcode-cn.com/problems/reverse-linked-list-ii/
     */
    @Test
    public void test() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
//        ListNode node6 = new ListNode(6);
//        ListNode node7 = new ListNode(7);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
//        node5.next = node6;
//        node6.next = node7;
        reverseBetween(node1, 3, 3);

        System.currentTimeMillis();
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode reversePre = null;
        ListNode reverseHead = head;
        int index = 1;
        while (index<m) {
            reversePre = reverseHead;
            reverseHead = reverseHead.next;
            index ++;
        }

        ListNode cur = reverseHead;
        ListNode curNext = reverseHead.next;
        while (index<n) {
            ListNode newNext = curNext.next;
            curNext.next = cur;

            cur = curNext;
            curNext = newNext;
            index++;
        }

        if (reversePre != null) {
            reversePre.next = cur;
        } else {
            head = cur;
        }
        reverseHead.next = curNext;

        return head;
    }

}
