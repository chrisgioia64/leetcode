package base.leetcode;

import base.leetcode.problem002.AddTwoNumbers;
import base.leetcode.problem002.AddTwoNumbersImpl;
import base.leetcode.problem002.ListNode;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.AssertJUnit.*;

public class AddTwoNumbersBaseTest {

    private AddTwoNumbers addTwoNumbers;

    @BeforeMethod
    public void setup() {
        addTwoNumbers = new AddTwoNumbersImpl();
    }

    @Test
    public void testCreateList() {
        ListNode listNode = ListNode.createFromList(Arrays.asList());
        assertTrue(listNode == null);
        listNode = ListNode.createFromList(Arrays.asList(1));
        assertEquals(listNode.getValue(), 1);
        assertTrue(listNode.getNext() == null);
        listNode = ListNode.createFromList(Arrays.asList(1, 2));
        assertEquals(listNode.getValue(), 1);
        assertEquals(listNode.getNext().getValue(), 2);
        assertTrue(listNode.getNext().getNext() == null);
    }

    @Test
    public void assertSameWorksCorrectlyTest() {
        ListNode node = ListNode.createFromList(Arrays.asList(1, 2, 3));
        assertSame(node, Arrays.asList(1, 2, 3));
    }

    @Test
    public void test1() {
        ListNode num1 = ListNode.createFromList(Arrays.asList(1));
        ListNode num2 = ListNode.createFromList(Arrays.asList(2));
        ListNode result = addTwoNumbers.addTwoNumbers(num1, num2);
        assertSame(result, Arrays.asList(3));
    }

    @Test
    public void test2() {
        ListNode num1 = ListNode.createFromList(Arrays.asList(4));
        ListNode num2 = ListNode.createFromList(Arrays.asList(8));
        ListNode result = addTwoNumbers.addTwoNumbers(num1, num2);
        assertSame(result, Arrays.asList(2, 1));
    }

    @Test
    public void test3() {
        ListNode num1 = ListNode.createFromList(Arrays.asList(6, 1));
        ListNode num2 = ListNode.createFromList(Arrays.asList(4, 2));
        ListNode result = addTwoNumbers.addTwoNumbers(num1, num2);
        assertSame(result, Arrays.asList(0, 4));
    }

    @Test
    public void test4() {
        ListNode num1 = ListNode.createFromList(Arrays.asList(8, 9, 5));
        ListNode num2 = ListNode.createFromList(Arrays.asList(3, 0, 1));
        ListNode result = addTwoNumbers.addTwoNumbers(num1, num2);
        assertSame(result, Arrays.asList(1, 0, 7));
    }

    @Test
    public void test5() {
        ListNode num1 = ListNode.createFromList(Arrays.asList(6));
        ListNode num2 = ListNode.createFromList(Arrays.asList(5, 9, 2));
        ListNode result = addTwoNumbers.addTwoNumbers(num1, num2);
        assertSame(result, Arrays.asList(1, 0, 3));
    }

    @Test
    public void test6() {
        ListNode num1 = ListNode.createFromList(Arrays.asList(3, 5, 7));
        ListNode num2 = ListNode.createFromList(Arrays.asList(8, 1));
        ListNode result = addTwoNumbers.addTwoNumbers(num1, num2);
        assertSame(result, Arrays.asList(1, 7, 7));
    }

    public void assertSame(ListNode node, List<Integer> list) {
        ListNode current = node;
        for (Integer value : list) {
            assertNotNull(current);
            assertEquals((int) value, node.getValue());
            node = node.getNext();
        }
        assertNull(node);
    }

}
