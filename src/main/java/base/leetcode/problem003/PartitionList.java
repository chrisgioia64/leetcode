package base.leetcode.problem003;

import base.leetcode.problem002.ListNode;

public class PartitionList {

    public ListNode partitionList(ListNode head, int x) {
        ListNode lowerHead = new ListNode(null, 0);
        ListNode upperHead = new ListNode(null, 0);
        ListNode lower = lowerHead;
        ListNode upper = upperHead;
        ListNode current = head;
        while (current != null) {
            if (current.getValue() < x) {
                lower.setNext(current);
                lower = current;
            } else {
                upper.setNext(current);
                upper = current;
            }
            current = current.getNext();
        }
        upper.setNext(null);
        lower.setNext(upperHead.getNext());
        return lowerHead.getNext();
    }

}
