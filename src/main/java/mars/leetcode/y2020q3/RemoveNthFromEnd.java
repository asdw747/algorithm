package mars.leetcode.y2020q3;

import java.util.HashMap;
import java.util.Map;

public class RemoveNthFromEnd {

    /*
    给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

    示例：

    给定一个链表: 1->2->3->4->5, 和 n = 2.

    当删除了倒数第二个节点后，链表变为 1->2->3->5.
    说明：

    给定的 n 保证是有效的。

    进阶：

    你能尝试使用一趟扫描实现吗？
     */
    public static void main(String [] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = (node2);
        node2.next = (node3);
        node3.next = (node4);
        node4.next = (node5);

        ListNode node = removeNthFromEnd(node1, 2);
        System.currentTimeMillis();
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = head;
        ListNode fast = head;
        for (int i=0; i<n-1; i++) {
            fast = fast.next;
        }

        ListNode prevSlow = null;
        while (fast.next != null) {
            prevSlow = slow;
            slow = slow.next;
            fast = fast.next;
        }

        if (prevSlow == null) {
            return slow.next;
        }

        prevSlow.next = slow.next;
        return head;
    }

}


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

