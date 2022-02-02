package base.leetcode.problem002;

import java.util.List;

public class ListNode {

    public ListNode next;
    public int value;

    public ListNode(ListNode next, int value) {
        this.value = value;
    }

    public static ListNode createFromList(List<Integer> values) {
        ListNode head = new ListNode(null, 0);
        ListNode current = head;
        for (Integer value : values) {
            ListNode newNode = new ListNode(null, value);
            current.next = newNode;
            current = newNode;
        }
        return head.next;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
