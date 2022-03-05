package base.leetcode.problems;

import java.util.TreeSet;

public class SummaryRange {

    public class Interval implements Comparable<Interval> {
        private int start;
        private int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Interval interval) {
            return start - interval.start;
        }
    }

    private TreeSet<Interval> orderedSet;

    public SummaryRange() {
        orderedSet = new TreeSet<>();
    }

    public void addNum(int val) {
        Interval dummy = new Interval(val, val);
        Interval lower = orderedSet.floor(dummy);
        Interval upper = orderedSet.ceiling(dummy);
        if (lower == null && upper == null) {
            orderedSet.add(dummy);
        } else if (lower == upper) {
            // do nothing -- already merged
        } else if (lower != null && upper == null) {
            if (lower.end == val - 1) {
                lower.end = val;
            } else if (lower.end < val - 1) {
                orderedSet.add(dummy);
            }
        } else if (lower == null && upper != null) {
            if (upper.start == val + 1) {
                orderedSet.remove(upper);
                upper.start = val;
                orderedSet.add(upper);
            } else if (upper.start > val + 1) {
                orderedSet.add(dummy);
            }
        } else {
            if (lower.end == val - 1 && upper.start == val + 1) {
                Interval interval = new Interval(lower.start, upper.end);
                orderedSet.remove(lower);
                orderedSet.remove(upper);
                orderedSet.add(interval);
            } else if (lower.end == val - 1) {
                lower.end = val;
            } else if (lower.end >= val) {
              // do nothing
            } else if (upper.start == val + 1) {
                orderedSet.remove(upper);
                upper.start = val;
                orderedSet.add(upper);
            } else {
                orderedSet.add(dummy);
            }
        }
    }

    public int[][] getIntervals() {
        int[][] res = new int[this.orderedSet.size()][2];
        int index = 0;
        for (Interval interval : orderedSet) {
            res[index][0] = interval.start;
            res[index][1] = interval.end;
            index++;
        }
        return res;
    }

}
