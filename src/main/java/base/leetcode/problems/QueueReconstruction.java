package base.leetcode.problems;

import java.util.*;
import java.util.stream.Collectors;

public class QueueReconstruction {

    public static class Pair {
        private int h;
        private int k;

        public Pair(int h, int k) {
            this.h = h;
            this.k = k;
        }
        @Override
        public String toString() {
            return "(" + h + ", " + k + ")";
        }
    }

    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (x, y) -> x[0] == y[0] ? x[1] - y[1] : y[0] - x[0]);
        List<int[]> list = new LinkedList<>();
        for (int[] person : people) {
            list.add(person[1], person);
        }
        return list.toArray(new int[0][0]);
    }

    public int[][] reconstructQueueSlow(int[][] people) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int[] p : people) {
            List<int[]> sublist = map.getOrDefault(p[0], new LinkedList<>());
            sublist.add(p);
            map.put(p[0], sublist);
        }
        int[][] result = new int[people.length][2];
        for (int i = 0; i < result.length; i++) {
            result[i][0] = -1;
        }
        List<Integer> keys = new LinkedList<>(map.keySet());
        Collections.sort(keys);
        for (int height : keys) {
            List<int[]> pairs = map.get(height);
            for (int[] pair : pairs) {
                setIndex(result, pair[1], pair);
            }
        }
//        int[][] r = new int[result.length][2];
//        for (int i = 0; i < result.length; i++) {
//            r[i][0] = result[i].h;
//            r[i][1] = result[i].k;
//        }
        return result;
    }

    private void setIndex(int[][] result, int index, int[] pair) {
//        System.out.println(Arrays.toString(pair));
        int count = 0;
        int i = 0;
        while (result[i][0] != -1 && result[i][0] < pair[0]) {
            i++;
        }
        while (count < index) {
            i++;
            while (result[i][0] != -1 && result[i][0] < pair[0]) {
                i++;
            }
            count++;
        }
        result[i] = pair;
//        System.out.println(Arrays.deepToString(result));
    }

    public static void main(String[] args) {
        int[][] people = new int[][] {
                {7,0}, {4,4}, {7,1}, {5,0},{6,1},{5,2}
        };
        QueueReconstruction qr = new QueueReconstruction();
        int[][] result = qr.reconstructQueue(people);
        System.out.println(Arrays.deepToString(result));
    }


}
