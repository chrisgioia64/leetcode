package base.leetcode.problems;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class SuperUglyNumber {

    private class Number {
        private int factor;
        private int value;

        public Number(int factor, int value) {
            this.factor = factor;
            this.value = value;
        }
    }

    public int nthSuperUglyNumber(int n, int[] primes) {
        PriorityQueue<Number> pq = new PriorityQueue<>((x, y) -> x.value - y.value);
//        {
//            if (x.value < y.value) {
//                return -1;
//            } else if (x.value > y.value) {
//                return 1;
//            } else {
//                return y.factor - x.factor;
//            }
//        });
        int[] res = new int[n];
        res[0] = 1;
        for (int prime : primes) {
            pq.add(new Number(prime, prime));
        }
        for (int i = 1; i < n; i++) {
            Number num = pq.poll();
            int val = num.value;
//            while (!pq.isEmpty() && pq.peek().value == val) {
//                Number x = pq.poll();
//            }
            for (int prime : primes) {
                if (prime < num.factor) {
                    continue;
                }
                if (Integer.MAX_VALUE  / val > prime) {
                    pq.add(new Number(prime, prime * val));
                } else {
                    break;
                }
            }
            res[i] = val;
        }
        System.out.println(res[n-1]);
        System.out.println("size: " + pq.size());
        return res[n-1];
    }

    public static void main(String[] args) {
        SuperUglyNumber sun = new SuperUglyNumber();
        long time0 = System.currentTimeMillis();
        sun.nthSuperUglyNumber(100000, new int[] {7,19,29,37,41,47,53,59,61,79,83,89,101,103,109,127,131,137,139,157,167,179,181,199,211,229,233,239,241,251});
        int[] arr = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101,103,107,109,113,127,131,137,139,149,151,157,163,167,173,179,181,191,193,197,199,211,223,227,229,233,239,241,251,257,263,269,271,277,281,283,293,307,311,313,317,331,337,347,349,353,359,367,373,379,383,389,397,401,409,419,421,431,433,439,443,449,457,461,463,467,479,487,491,499,503,509,521,523,541};
        System.out.println(System.currentTimeMillis() - time0);
        int n = 1000000;
        long time = System.currentTimeMillis();
        sun.nthSuperUglyNumber(n, arr);
        long now = System.currentTimeMillis();
        System.out.println(now - time);
    }

}
