package mars.leetcode.y2020q3;

import mars.leetcode.util.ListNode;

public class SwapPairs {

    /*
    给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。

    你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。


    示例:

    给定 1->2->3->4->5->6, 你应该返回 2->1->4->3->6->5.

     */
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        ListNode node = swapPairs(null);
        System.currentTimeMillis();
    }


    public static ListNode swapPairs(ListNode head) {
        ListNode preNode = null;
        ListNode aNode = head;
        ListNode result = aNode == null ? null:(aNode.next == null ? aNode:aNode.next);

        while (aNode!=null) {
            ListNode bNode = aNode.next;
            ListNode cNode = aNode.next == null ? null:aNode.next.next;

            if (bNode!=null) {
                aNode.next = cNode;
                bNode.next = aNode;
                if (preNode != null) {
                    preNode.next = bNode;
                }
            }

            preNode = bNode==null ? null:aNode;
            aNode = bNode==null ? null:aNode.next;
        }

        return result;
    }

}

