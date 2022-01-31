package base.leetcode;

import base.leetcode.problem002.ListNode;
import base.leetcode.problem003.PartitionList;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.AssertJUnit.*;

public class PartitionListTest {

    private PartitionList partition;

    @BeforeMethod
    public void setUp() {
        partition = new PartitionList();
    }

    @Test
    public void test1() {
        ListNode node = ListNode.createFromList(Arrays.asList(1, 4, 3, 2, 5, 2));
        ListNode result = partition.partitionList(node, 3);
        assertSame(result, Arrays.asList(1, 2, 2, 4, 3, 5));
    }

    @Test
    public void test2() {
        ListNode node = ListNode.createFromList(Arrays.asList(3, 5, 0, 1, 4));
        ListNode result = partition.partitionList(node, 2);
        assertSame(result, Arrays.asList(0, 1, 3, 5, 4));
    }

    @Test
    public void test3() {
        ListNode node = ListNode.createFromList(Arrays.asList(1, 0));
        ListNode result = partition.partitionList(node, 1);
        assertSame(result, Arrays.asList(0, 1));
    }

    @Test
    public void test4() {
        ListNode node = ListNode.createFromList(Arrays.asList(1, 0));
        ListNode result = partition.partitionList(node,0);
        assertSame(result, Arrays.asList(1, 0));
    }

    @Test
    public void test5() {
        ListNode node = ListNode.createFromList(Arrays.asList(1, 0));
        ListNode result = partition.partitionList(node,5);
        assertSame(result, Arrays.asList(1, 0));
    }

    public void assertSame(ListNode node, List<Integer> list) {
        ListNode current = node;
        for (Integer value : list) {
            assertNotNull(node);
            assertEquals((int) value, node.getValue());
            node = node.getNext();
        }
        assertNull(node);
    }
}
