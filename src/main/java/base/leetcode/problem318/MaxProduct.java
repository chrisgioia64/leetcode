package base.leetcode.problem318;

public class MaxProduct {

    public int maxProduct(String[] words) {
        int n = words.length;
        boolean[][] chars = new boolean[n][26];
        for (int i = 0; i < n; i++) {
            char[] word = words[i].toCharArray();
            for (char c : word) {
                chars[i][(int)(c-'a')] = true;
            }
        }
        int prod = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (!overlaps(chars, i, j)) {
                    prod = Math.max(prod, words[i].length() * words[j].length());
                }
            }
        }
        return prod;
    }

    private boolean overlaps(boolean[][] chars, int i, int j) {
        boolean[] a = chars[i];
        boolean[] b = chars[j];
        for (int k = 0; k < 26; k++) {
            if (a[k] == b[k] && a[k] == true) {
                return true;
            }
        }
        return false;
    }


}
