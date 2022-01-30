package base.leetcode;

import base.leetcode.problem001.TwoSum;
import base.leetcode.problem001.TwoSumNaive;
import base.leetcode.problem001.TwoSumSet;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public abstract class TwoSumBaseTest<T extends TwoSum> {

    private TwoSum twoSum;

    public abstract T createInstance();

    @BeforeMethod
    public void setup() {
        twoSum = createInstance();
    }

    public void twoSumTest(int[] arr, int target, int index1, int index2) {
        int[] result = twoSum.twoSum(arr, target);
        assertEquals(index1, result[0]);
        assertEquals(index1, result[0]);
        assertEquals(index1, result[0]);
        assertEquals(index2, result[1]);
    }

    @Test
    public void test1() {
        twoSumTest(new int[]{3, 4, 7, 10, 12}, 14, 1, 3);
    }

    @Test
    public void test2() {
        twoSumTest(new int[]{5, 8, 10, 11}, 12, -1, -1);
    }

    @Test
    public void test3() {
        twoSumTest(new int[]{3, 8, 5, 11, 15}, 20, 2, 4);
    }

    @Test
    public void test4() {
        twoSumTest(new int[]{5, 3, 4, 6, 1}, 7, 1, 2);
    }

}