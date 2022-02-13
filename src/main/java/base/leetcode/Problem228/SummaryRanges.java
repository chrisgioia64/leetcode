package base.leetcode.Problem228;

import java.util.LinkedList;
import java.util.List;

public class SummaryRanges {

    public List<String> summaryRanges(int[] nums) {
        List<String> result = new LinkedList<>();
        if (nums.length == 0) {
            return result;
        }
        int low = nums[0];
        int high = nums[0];
        for (int i = 1; i < nums.length ; i++) {
            int num = nums[i];
            if (num > high + 1) {
                result.add(addValue(low, high));
                low = num;
                high = num;
            } else {
                high = num;
            }
        }
        result.add(addValue(low, high));
        return result;
    }

    private String addValue(int low, int high) {
        if (low == high) {
            return low + "";
        } else {
            return low + "->" + high;
        }
    }

}
