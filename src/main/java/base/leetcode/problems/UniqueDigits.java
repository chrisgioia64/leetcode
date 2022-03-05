package base.leetcode.problems;

public class UniqueDigits {

    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return 10;
        } else if (n == 2) {
            return 91;
        }
        int base = 8;
        int pow = 81;
        int sum = 91;
        for (int i = 2; i < n; i++) {
            pow = pow * base;
            base--;
            sum += pow;
        }
        return sum;
    }

    public static void main(String[] args) {
        UniqueDigits ud = new UniqueDigits();
        System.out.println(ud.countNumbersWithUniqueDigits(4));
    }

}
