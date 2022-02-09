package base.leetcode.problem203;

import base.leetcode.problem002.ListNode;

public class RemoveLinkedList {

    public ListNode removeElements(ListNode head, int val) {
        ListNode sentinel = new ListNode(0, head);
        ListNode current = sentinel;
        while (current != null && current.next != null) {
            if (current.next.val == val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return sentinel.next;
    }



}
