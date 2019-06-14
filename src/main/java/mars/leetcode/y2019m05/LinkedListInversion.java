package mars.leetcode.y2019m05;

import lombok.Data;

/**
 * 单链表反转
 **/
public class LinkedListInversion {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        node5.setNext(node6);

        printLinkedList(node1);
        Node headNode = reverse(node1);
        printLinkedList(headNode);

    }

    private static void printLinkedList(Node firstNode) {
        int counter = 0;

        Node item = firstNode;
        while (item!=null) {
            counter ++;
            if (counter>1000) {
                break;
            }

            System.out.print(item.getValue());
            item = item.getNext();
        }

        System.out.println();
    }

    private static Node reverse(Node firstNode) {
        int counter = 0;

        Node item = firstNode;
        Node next = item.getNext();

        item.setNext(null);
        while (next!=null) {
            counter ++;
            if (counter>1000) {
                break;
            }

            Node back = next.getNext();
            next.setNext(item);

            item = next;
            next = back;
        }

        return item;
    }

}

@Data
class Node {
    private Node next;
    private int value;

    public Node(int value) {
        this.value = value;
    }
}
