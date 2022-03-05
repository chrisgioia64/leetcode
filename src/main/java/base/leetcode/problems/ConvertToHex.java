package base.leetcode.problems;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConvertToHex {

    private Map<Integer, String> getMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "0");
        map.put(1, "1");
        map.put(2, "2");
        map.put(3, "3");
        map.put(4, "4");
        map.put(5, "5");
        map.put(6, "6");
        map.put(7, "7");
        map.put(8, "8");
        map.put(9, "9");
        map.put(10, "a");
        map.put(11, "b");
        map.put(12, "c");
        map.put(13, "d");
        map.put(14, "e");
        map.put(15, "f");
        return map;
    }

    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }
        if (num > 0) {
            return getUnsigned(num);
        } else {
            long x = ((long) (num)) + (1L << 32);
            return getUnsigned(x);
        }

    }

    private String getUnsigned(long num) {
        Map<Integer, String> map = getMap();
        LinkedList<String> list = new LinkedList<>();
        while (num > 0) {
            int mod = (int) (num % 16);
            String s = map.get(mod);
            list.addFirst(s);
            num = num / 16;
        }
        return list.stream().collect(Collectors.joining());
    }

    public static void main(String[] args) {
        ConvertToHex ch = new ConvertToHex();
        System.out.println(ch.toHex(-2));
        System.out.println(ch.toHex(60));
    }

}
