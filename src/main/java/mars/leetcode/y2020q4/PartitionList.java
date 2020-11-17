package mars.leetcode.y2020q4;

import mars.leetcode.util.ListNode;
import org.junit.Test;

public class PartitionList {

    /**
     * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
     *
     * 你应当保留两个分区中每个节点的初始相对位置。
     *
     *  
     *
     * 示例:
     *
     * 输入: head = 1->4->3->2->5->2, x = 3
     * 输出: 1->2->2->4->3->5
     */
    @Test
    public void test() {

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(2);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        ListNode res = partition(node1, 3);
        System.currentTimeMillis();

    }

    public ListNode partition(ListNode head, int x) {
        ListNode l1 = null;
        ListNode l2 = null;
        ListNode h1 = null;
        ListNode h2 = null;

        while (head != null) {
            ListNode node = new ListNode(head.val);
            if (head.val<x) {
                if (l1 == null) {
                    l1 = node;
                    h1 = node;
                } else {
                    l1.next = node;
                    l1 = node;
                }
            } else {
                if (l2 == null) {
                    l2 = node;
                    h2 = node;
                } else {
                    l2.next = node;
                    l2 = node;
                }
            }

            head = head.next;
        }

        if (l1!=null) {
            l1.next = h2;
            return h1;
        } else {
            return h2;
        }
    }



}


