package base.leetcode;

import base.leetcode.Problem228.SummaryRanges;
import base.leetcode.problems.SummaryRange;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import org.hamcrest.Matchers.*;
import org.hamcrest.MatcherAssert;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.fail;

public class SummaryRangeTest {

    private SummaryRange summaryRange;

    @Test(dataProvider = "input", dataProviderClass = SummaryRangeDataProvider.class)
    public void test(SummaryRangeDataProvider.OperationSequence sequence) {
        SummaryRange summaryRange = new SummaryRange();
        for (SummaryRangeDataProvider.Operation operation : sequence.getSequence()) {
            summaryRange.addNum(operation.getNumberAdded());
            assertEqualsMethod(operation.getIntervals(), summaryRange.getIntervals());
        }
    }

    @Test
    public void test2() {
        summaryRange = new SummaryRange();
        int[] nums = new int[] {49,97,53,5,33,65,62,51,100,38,61,45,74,27,64,17,36,17,96,12,79,32,68,90,77,18,39,12,93,9,87,42,60,71,12,45,55,40,78,81,26,70,61,56,66,33,7,70,1,11,92,51,90,100,85,80,0,78,63,42,31,93,41,90,8,24,72,28,30,18,69,57,11,10,40,65,62,13,38,70,37,90,15,70,42,69,26,77,70,75,36,56,11,76,49,40,73,30,37,23};
        for (int num : nums) {
            summaryRange.addNum(num);
            System.out.println(num + " " + Arrays.deepToString(summaryRange.getIntervals()));
        }
    }

    private void assertEqualsMethod(int[][] expected, int[][] actual) {
        if (expected.length != actual.length) {
            fail("lengths do not match between : " + Arrays.deepToString(expected) + " and "
                + Arrays.deepToString(actual));
        }
        for (int i = 0; i < expected.length; i++) {
            int[] e = expected[i];
            int[] a = actual[i];
            if (e.length != 2 || a.length != 2) {
                fail("interval does not consist of 2 elements");
            }
            assertEquals(a, e);
        }
    }

}
