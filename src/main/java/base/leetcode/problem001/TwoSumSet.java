package base.leetcode.problem001;

import java.util.HashMap;
import java.util.Map;

public class TwoSumSet implements base.leetcode.problem001.TwoSum {

    @Override
    public int[] twoSum(int[] arr, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int complement = target - arr[i];
            if (map.containsKey(complement)) {
                return new int[] {map.get(complement), i};
            } else {
                map.put(arr[i], i);
            }
        }
        return new int[] {-1, -1};
    }
}
