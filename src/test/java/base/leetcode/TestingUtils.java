package base.leetcode;

import base.leetcode.problem002.ListNode;

import java.util.List;

import static org.testng.AssertJUnit.*;

public class TestingUtils {

    public static void assertSameList(ListNode node, List<Integer> list) {
        ListNode current = node;
        for (Integer value : list) {
            assertNotNull(node);
            assertEquals((int) value, node.getVal());
            node = node.getNext();
        }
        assertNull(node);
    }
}
