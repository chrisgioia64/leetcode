package base.leetcode.problems;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class RandomizedSet2 {

    private int capacity = 8;
    private Integer[] array;
    private Map<Integer, Integer> map;

    public RandomizedSet2() {
        array = new Integer[capacity];
        map = new HashMap<>();
    }

    private void resize(int newCapacity) {
        this.capacity = newCapacity;
        this.array = new Integer[capacity];
        Set<Integer> set = map.keySet();
        for (Integer val : set) {
            int index = getIndex(val);
            while (true) {
                if (array[index] == null) {
                    break;
                } else {
                    index = (index + 1) % capacity;
                }
            }
            map.put(val, index);
            array[index] = val;
        }
    }

    private int getIndex(int val) {
        return Math.abs(val) % capacity;
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        } else {
            int index = getIndex(val);
            while (true) {
                if (array[index] == null) {
                    break;
                } else {
                    index = (index + 1) % capacity;
                }
            }
            map.put(val, index);
            array[index] = val;
            if (map.size() > (capacity * 0.7)) {
                resize(capacity * 2);
            }
            return true;
        }
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        } else {
            array[map.get(val)] = null;
            map.remove(val);
            if (map.size() < (capacity * 0.3) && capacity > 8) {
                resize(capacity / 2);
            }
            return true;
        }
    }

    public int getRandom() {
        Random rand = new Random();
        while (true) {
            int index = rand.nextInt(capacity);
            if (array[index] != null) {
                return array[index];
            }
        }
    }

    public static void main(String[] args) {
        RandomizedSet2 rs = new RandomizedSet2();
        rs.insert(-7);
        rs.getRandom();
        rs.getRandom();
        rs.insert(6);
        rs.insert(7);
        rs.insert(10);
        rs.insert(3);
        rs.insert(8);
        rs.getRandom();

    }

}
