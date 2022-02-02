package base.leetcode.Problem093;

import java.util.LinkedList;
import java.util.List;

/**
 * A classic backtracking algorithm
 */
public class RestoreIP {

    private char[] input;
    private String[] current;
    private List<String> result;

    public List<String> restoreIpAddresses(String s) {
        input = s.toCharArray();
        current = new String[4];
        result = new LinkedList<>();
        iterate(0, 0, 0);
        return result;
    }

    private void iterate(int index, int slotNumber, int left) {
        if (index >= input.length) {
            if (slotNumber >= 4) {
                result.add(createIpAddress());
            }
        } else if (slotNumber < 4) {
            // add a dot
            String portion = new String(input).substring(left, index + 1);
            if (isValid(portion)) {
                current[slotNumber] = portion;
                iterate(index + 1, slotNumber + 1, index + 1);
                current[slotNumber] = "";
            }
            // do not add a dot
            iterate(index + 1, slotNumber, left);
        }
    }

    private boolean isValid(String portion) {
        try {
            Integer value = Integer.parseInt(portion);
            if (portion.length() > 1 && portion.charAt(0) == '0') {
                return false;
            }
            return value >= 0 && value <= 255;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    private String createIpAddress() {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            b.append(current[i] + ".");
        }
        b.append(current[3]);
        return b.toString();
    }

    public static void main(String[] args) {
        RestoreIP ip = new RestoreIP();
        List<String> result = ip.restoreIpAddresses("25525511135");
        System.out.println(result);

        result = ip.restoreIpAddresses("101023");
        System.out.println(result);
    }

}
