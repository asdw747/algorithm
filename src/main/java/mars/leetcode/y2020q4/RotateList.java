package mars.leetcode.y2020q4;

import mars.leetcode.util.ListNode;
import org.junit.Test;

public class RotateList {


    /**
     * https://leetcode-cn.com/problems/rotate-list/
     *
     * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
     *
     * 示例 1:
     *
     * 输入: 1->2->3->4->5->NULL, k = 2
     * 输出: 4->5->1->2->3->NULL
     * 解释:
     * 向右旋转 1 步: 5->1->2->3->4->NULL
     * 向右旋转 2 步: 4->5->1->2->3->NULL
     *
     */
    @Test
    public void test() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = (node2);
        node2.next = (node3);
        node3.next = (node4);
        node4.next = (node5);


        ListNode res = rotateRight(node1, 7);
        System.currentTimeMillis();
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        int len = 1;
        ListNode foreach = head;
        while (foreach.next!=null) {
            foreach = foreach.next;
            len++;
        }

        if (k>=len) {
            k = k%len;
        }

        if (k == 0) {
            return head;
        }

        int fastIndex = 0;
        ListNode fast = head;
        while (fastIndex++<k) {
            fast = fast.next;
        }

        ListNode slow = head;
        while (fast.next!=null) {
            fast = fast.next;
            slow = slow.next;
        }

        ListNode newBegin = slow.next;
        slow.next = null;
        fast.next = head;
        return newBegin;
    }

}
