package base.leetcode.problems;

import java.util.*;
import java.util.stream.Collectors;

public class FindKPairs {

    public static class Pair {
        private int sum;
        private int x;
        private int y;

        public Pair(int sum, int x, int y) {
            this.sum = sum;
            this.x = x;
            this.y = y;
        }
    }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> y.sum - x.sum);
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                pq.add(new Pair(nums1[i]+nums2[j], nums1[i], nums2[j]));
                if (pq.size() > k) {
                    pq.poll();
                }
            }
        }
        List<List<Integer>> list = pq.stream().map( x -> getPair(x.x, x.y)).collect(Collectors.toList());
        return list;
    }

    public List<List<Integer>> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        int i = 0;
        int j = 0;
        List<List<Integer>> result = new LinkedList<>();
        result.add(getPair(nums1[i], nums2[j]));
        int count = 1;
        while (count < k) {
            if (i == nums1.length - 1 ||
                    ((j < nums2.length - 1) && nums1[i] + nums2[j+1] <= nums1[i+1] + nums2[j])) {
                j++;
                for (int l = 0; l <= i; l++) {
                    if (count == k) {
                        return result;
                    }
                    count++;
                    result.add(getPair(nums1[l], nums2[j]));
                }
            } else {
                i++;
                for (int l = 0; l <= j; l++) {
                    if (count == k) {
                        return result;
                    }
                    count++;
                    result.add(getPair(nums1[i], nums2[j]));
                }
            }
            if (i == nums1.length - 1 && j == nums2.length - 1) {
                break;
            }
        }
        return result;
    }

    private List<Integer> getPair(int x, int y) {
        return new ArrayList<>(Arrays.asList(x, y));
    }

    public static void main(String[] args) {
        FindKPairs fkp = new FindKPairs();
        int[] nums1 = new int[] {2, 5, 7, 12};
        int[] nums2 = new int[] {3, 4, 8};
        System.out.println(fkp.kSmallestPairs(nums1, nums2, 5));

        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> y - x);
        pq.add(5);
        pq.add(3);
        pq.add(8);
        pq.add(6);
        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
    }

}
