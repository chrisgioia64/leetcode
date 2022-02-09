package base.leetcode;

import base.leetcode.problem002.ListNode;
import base.leetcode.problem203.RemoveLinkedList;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

public class RemoveLinkedListTest {

    private RemoveLinkedList function;

    @BeforeClass
    public void setup() {
        function = new RemoveLinkedList();
    }

    @Test
    public void test1() {
        ListNode head = ListNode.createFromList(Arrays.asList(2, 3, 5, 4, 3));
        ListNode result = function.removeElements(head, 3);
        TestingUtils.assertSameList(result, Arrays.asList(2, 5, 4));
    }

    @Test
    public void test2() {
        ListNode head = ListNode.createFromList(Arrays.asList());
        ListNode result = function.removeElements(head, 3);
        TestingUtils.assertSameList(result, Arrays.asList());
    }

    @Test
    public void test3() {
        ListNode head = ListNode.createFromList(Arrays.asList(2, 2, 2, 2));
        ListNode result = function.removeElements(head, 2);
        TestingUtils.assertSameList(result, Arrays.asList());
    }

    @Test
    public void test4() {
        ListNode head = ListNode.createFromList(Arrays.asList(2, 3, 5, 4, 3));
        ListNode result = function.removeElements(head, 2);
        TestingUtils.assertSameList(result, Arrays.asList(3, 5, 4, 3));
    }

    @Test
    public void test5() {
        ListNode head = ListNode.createFromList(Arrays.asList(2, 3, 5, 4, 3));
        ListNode result = function.removeElements(head, 5);
        TestingUtils.assertSameList(result, Arrays.asList(2, 3, 4, 3));
    }

}
