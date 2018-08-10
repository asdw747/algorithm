package mars.LeetCode.Y2018M08;

public class AddTwoNum {

    /**
     * You are given two non-empty linked lists representing two non-negative integers.The digits are stored in reverse
     * order and each of their nodes contain a single digit.Add the two numbers and return it as a linked list.
     * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     *
     * Example:
     *   Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
     *   Output: 7 -> 0 -> 8
     *   Explanation: 342 + 465 = 807.
     */

    public static void main(String [] args) {
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(4);
        ListNode l3 = new ListNode(3);
        l1.next = l2;
        l2.next = l3;

        ListNode m1 = new ListNode(5);
        ListNode m2 = new ListNode(6);
        ListNode m3 = new ListNode(4);
        m1.next = m2;
        m2.next = m3;

        ListNode result1 = addTwoNumbers(l1, m1);
    }

    /**
     * 本题实际上就是加法进位操作
     */
    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode headNode = null;
        ListNode traversalNode = null;

        ListNode l1Traversal = l1;
        ListNode l2Traversal = l2;
        int redundant = 0;
        while (l1Traversal != null || l2Traversal != null || redundant == 1) {
            ListNode newNode;
            int number1 = l1Traversal == null ? 0:l1Traversal.val;
            int number2 = l2Traversal == null ? 0:l2Traversal.val;
            int currentValue = number1 + number2;

            if (currentValue + redundant > 9) {
                newNode = new ListNode(currentValue + redundant - 10);
                redundant = 1;
            } else {
                newNode = new ListNode(currentValue + redundant);
                redundant = 0;
            }

            if (headNode == null) {
                headNode = newNode;
                traversalNode = newNode;
            } else {
                traversalNode.next = newNode;
                traversalNode = newNode;
            }

            l1Traversal = l1Traversal == null ? null:l1Traversal.next;
            l2Traversal = l2Traversal == null ? null:l2Traversal.next;
        }

        return headNode;
    }

    //这是一个错误的实现
    private static ListNode addTwoNumbersError(ListNode l1, ListNode l2) {
        long numberA = 0;
        long numberB = 0;

        long place = 1;
        ListNode lNode = l1;
        while (lNode != null) {
            numberA += lNode.val * place;
            place = place * 10;

            lNode = lNode.next;
        }

        place = 1;
        ListNode rNode = l2;
        while (rNode != null) {
            numberB += rNode.val * place;
            place = place * 10;

            rNode = rNode.next;
        }

        ListNode head = null;
        ListNode countNode = null;

        long numberTotal = numberA + numberB;
        if (numberTotal == 0) {
            return new ListNode(0);
        }

        Long numberTemp;
        long count = numberTotal;
        while (count != 0) {
            numberTemp = count % 10;
            if (countNode == null) {
                countNode = new ListNode(numberTemp.intValue());

                head = countNode;
            } else {
                ListNode node = new ListNode(numberTemp.intValue());
                countNode.next = node;

                countNode = node;
            }

            count = count / 10;
        }

        return head;
    }


}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

}
