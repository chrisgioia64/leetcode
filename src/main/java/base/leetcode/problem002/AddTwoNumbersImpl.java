package base.leetcode.problem002;

public class AddTwoNumbersImpl implements AddTwoNumbers {

    @Override
    public ListNode addTwoNumbers(ListNode l1, ListNode l2)
    {
        ListNode head = new ListNode(null, 0);
        ListNode current = head;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int sum = carry;
            sum += (l1 != null ? l1.getVal() : 0);
            sum += (l2 != null ? l2.getVal() : 0);
            ListNode newNode = new ListNode(null, sum % 10);
            carry = sum / 10;
            current.setNext(newNode);
            current = newNode;
            if (l1 != null) {
                l1 = l1.getNext();
            }
            if (l2 != null) {
                l2 = l2.getNext();
            }
        }
        if (carry != 0) {
            ListNode newNode = new ListNode(null, carry);
            current.setNext(newNode);
        }
        return head.getNext();
    }
}
