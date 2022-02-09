package base.leetcode.Problem134;

import java.util.Arrays;

public class GasStation {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int sum = (gas[0] - cost[0]);
        int minValue = sum;
        int minIndex = 0;

        for (int i = 1; i < n; i++) {
            sum += (gas[i] - cost[i]);
            if (sum < minValue) {
                minIndex = i;
                minValue = sum;
            }
        }
        if (sum >= 0) {
            return (minIndex) % n;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] gas = new int[] {1, 2, 3, 4, 5};
        int[] costs = new int[] {3, 4, 5, 1, 2};
        GasStation gasStation = new GasStation();
        System.out.println(gasStation.canCompleteCircuit(gas, costs));
    }

}
