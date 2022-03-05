package base.leetcode.problem287;

import java.util.Arrays;

public class FindDuplicateNumber {

    public int findDuplicate(int[] nums) {
        int sum = Arrays.stream(nums).reduce(0, (x, y) -> x + y);
        int n = nums.length - 1;
        int normal = (n * (n + 1)) / 2;
        return sum - normal;
    }

    public static void main(String[] args) {
        FindDuplicateNumber fdn = new FindDuplicateNumber();
        int x = fdn.findDuplicate(new int[] {3, 1, 4, 3, 2});
        System.out.println(x);
    }

}
