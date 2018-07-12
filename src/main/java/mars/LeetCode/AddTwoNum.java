//package mars.LeetCode;
//
//public class AddTwoNum {
//
//    /**
//     * You are given two non-empty linked lists representing two non-negative integers.The digits are stored in reverse
//     * order and each of their nodes contain a single digit.Add the two numbers and return it as a linked list.
//     * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
//     *
//     * Example:
//     *   Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
//     *   Output: 7 -> 0 -> 8
//     *   Explanation: 342 + 465 = 807.
//     */
//
//    public static void main(String [] args) {
//        ListNode l1 = new ListNode(9);
//
//        ListNode n1 = new ListNode(1);
//        ListNode n2 = new ListNode(9);
//        ListNode n3 = new ListNode(9);
//        ListNode n4 = new ListNode(9);
//        ListNode n5 = new ListNode(9);
//        ListNode n6 = new ListNode(9);
//        ListNode n7 = new ListNode(9);
//        ListNode n8 = new ListNode(9);
//        ListNode n9 = new ListNode(9);
//        ListNode n10 = new ListNode(9);
//        n1.next = n2;
//        n2.next = n3;
//        n3.next = n4;
//        n4.next = n5;
//        n5.next = n6;
//        n6.next = n7;
//        n7.next = n8;
//        n8.next = n9;
//        n9.next = n10;
//
//        ListNode result = addTwoNumbers(l1, n1);
//    }
//
//    private static ListNode addTwoNumbersError(ListNode l1, ListNode l2) {
//        long numberA = 0;
//        long numberB = 0;
//
//        long place = 1;
//        ListNode lNode = l1;
//        while (lNode != null) {
//            numberA += lNode.val * place;
//            place = place * 10;
//
//            lNode = lNode.next;
//        }
//
//        place = 1;
//        ListNode rNode = l2;
//        while (rNode != null) {
//            numberB += rNode.val * place;
//            place = place * 10;
//
//            rNode = rNode.next;
//        }
//
//        ListNode head = null;
//        ListNode countNode = null;
//
//        long numberTotal = numberA + numberB;
//        if (numberTotal == 0) {
//            return new ListNode(0);
//        }
//
//        Long numberTemp;
//        long count = numberTotal;
//        while (count != 0) {
//            numberTemp = count % 10;
//            if (countNode == null) {
//                countNode = new ListNode(numberTemp.intValue());
//
//                head = countNode;
//            } else {
//                ListNode node = new ListNode(numberTemp.intValue());
//                countNode.next = node;
//
//                countNode = node;
//            }
//
//            count = count / 10;
//        }
//
//        return head;
//    }
//
//
//}
//
//class ListNode {
//    int val;
//    ListNode next;
//    ListNode(int x) { val = x; }
//
//}
