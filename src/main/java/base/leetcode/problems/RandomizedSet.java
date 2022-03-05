package base.leetcode.problems;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

class RandomizedSet {


    private int capacity = 8;
    private int size;
    private Integer[] array;

    public RandomizedSet() {
        array = new Integer[capacity];
    }

    private void resize(int newCapacity) {
        this.capacity = newCapacity;
        Integer[] oldArray = this.array;
        this.array = new Integer[newCapacity];
        for (Integer integer : oldArray) {
            if (integer != null) {
                add(integer);
            }
        }
    }

    private int getIndex(int val) {
        return Math.abs(val) % capacity;
    }

    private boolean add(int val) {
        int index = getIndex(val);
        while (true) {
            if (array[index] == null) {
                break;
            } else {
                if (array[index] == val) {
                    return false;
                }
                index = (index + 1) % capacity;
            }
        }
        array[index] = val;
        return true;
    }

    private boolean delete(int val) {
        int index = getIndex(val);
        int count = 0;
        while (count < capacity) {
            if (array[index] == null) {
                return false;
            } else {
                if (array[index] == val) {
                    array[index] = null;
                    return true;
                }
                index = (index + 1) % capacity;
            }
            count++;
        }
        return false;
    }

    public boolean insert(int val) {
        boolean b = add(val);
        if (b) {
            size++;
        }
        if (size > (capacity * 0.6)) {
            resize(capacity * 2);
        }
        return b;
    }

    public boolean remove(int val) {
        boolean b = delete(val);
        if (b) {
            size--;
        }
        if (size < (capacity * 0.3) && capacity > 8) {
            resize(capacity / 2);
        }
        return b;
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
        RandomizedSet rs = new RandomizedSet();
        rs.insert(3);
        rs.insert(-2);
        rs.remove(2);
        rs.insert(1);
        rs.insert(-3);
        rs.insert(-2);
        rs.remove(-2);
        rs.remove(3);

        rs.insert(-1);
        System.out.println(Arrays.toString(rs.array));

        boolean b = rs.remove(-3);
        System.out.println(b);
        System.out.println(Arrays.toString(rs.array));

    }
}
