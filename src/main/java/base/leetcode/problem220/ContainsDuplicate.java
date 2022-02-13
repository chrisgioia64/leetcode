package base.leetcode.problem220;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ContainsDuplicate {
    private TreeMap<Integer, Integer> countMap;
    private int[] nums;

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        this.countMap = new TreeMap<>();
        this.nums = nums;
        int r = Math.min(nums.length-1,k);
        for (int i = 0; i <= r; i++) {
            int value = nums[i];
            Integer above = countMap.ceilingKey(value);
            if (above != null && (above - nums[i]) >= 0 && (above-nums[i]) <= t) {
                return true;
            }
            Integer below = countMap.floorKey(value) ;
            if (below != null && (nums[i] - below) >= 0 && (nums[i] - below) <= t) {
                return true;
            }
            expand(i);
//            System.out.println(i + " : " + nums[i] + " " + above + " " + below);
        }
        for (int i = k + 1; i < nums.length; i++) {
            int lowerIndex = i - k - 1;
            contract(lowerIndex);
            int value = nums[i];
            Integer above = countMap.ceilingKey(value);
            if (above != null && (above - nums[i]) >= 0 && (above - nums[i]) <= t) {
                return true;
            }
            Integer below = countMap.floorKey(value);
            if (below != null && (nums[i] - below) >= 0 && (nums[i] - below) <= t) {
                return true;
            }
            expand(i);
        }
        return false;
    }

    public void expand(int i) {
        int value = nums[i];
        countMap.put(value, countMap.getOrDefault(value, 0) + 1);
    }

    public void contract(int i) {
        int value = nums[i];
        Integer count = countMap.get(value);
        if (count == 1) {
            countMap.remove(value);
        } else {
            countMap.put(value, count - 1);
        }
    }

//    public static void main(String[] args) {
//        int y = -2147483648;
//        int x = 2147483647;
//        int diff = (int)(x - y);
//        System.out.println(diff);
//    }

}
