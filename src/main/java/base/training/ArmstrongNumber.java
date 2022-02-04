package base.training;

import java.util.*;

public class ArmstrongNumber {

    public static boolean isArmstrong(int num) {
        List<Integer> digits = new LinkedList<>();
        int k = num;
        while (k > 0) {
            digits.add(k % 10);
            k = k / 10;
        }
        int sum = 0;
        for (Integer digit : digits) {
            sum += (digit * digit * digit);
        }
        return sum == num;
    }

    public static String reverse(String reverse) {
        StringBuilder b = new StringBuilder();
        for (int i = reverse.length() - 1; i >= 0; i--) {
            b.append(reverse.charAt(i));
        }
        return b.toString();
    }

    public static void findDuplicates(String str) {
        Map<Character, Integer> map = new HashMap<>();

        char[] ary = str.toCharArray();
        for (char c : ary) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (Character c : map.keySet()) {
            if (map.get(c) > 1) {
                System.out.print(c + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 999; i++) {
            if (isArmstrong(i)) {
                System.out.println(i);
            }
        }
        int[] arr = new int[] {5, 2, 4};
        Arrays.sort(arr);

        String s = "hello world what up";
        findDuplicates("  hello world what up   ");
        System.out.println(s.trim());
        System.out.println(s.strip());

    }

}
