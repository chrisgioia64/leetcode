package base.leetcode.problem002;

import java.util.List;

public class ListNode {

    public ListNode next;
    public int val;

    public ListNode(ListNode next, int value) {
        this.val = value;
        this.next = next;
    }

    public ListNode(int value, ListNode next) {
        this.val = value;
        this.next = next;
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

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
}
