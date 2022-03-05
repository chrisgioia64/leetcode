package base.leetcode.problems;

import java.util.HashMap;

public class LongestPalindrome {

    public int longestPalindrome(String s) {
        int[] counts = new int[60];
        for (char c : s.toCharArray()) {
            counts[c-'A']++;
        }
        int count = 0;
        int odd = 0;
        for (Integer value : counts) {
            if (value % 2 == 1) {
                odd = 1;
            }
            count += (value / 2) * 2;
        }
        return count + odd;
    }

    private int odd(char[] ary, int i) {
        int width = 1;
        while ((i - width) >= 0 && (i + width) < ary.length) {
            if (ary[i-width] != ary[i+width]) {
                break;
            }
            width++;
        }
        return width * 2 - 1;
    }

    private int even(char[] ary, int i) {
        int width = 0;
        while ((i - width) >= 0 && (i + 1 + width) < ary.length) {
            if (ary[i-width] != ary[i+1+width]) {
                break;
            }
            width++;
        }
        return width * 2;
    }

}
