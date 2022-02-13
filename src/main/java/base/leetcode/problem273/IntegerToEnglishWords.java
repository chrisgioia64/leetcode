package base.leetcode.problem273;

import java.util.HashMap;
import java.util.Map;

public class IntegerToEnglishWords {
    private Map<Integer, String> digitMap;
    private Map<Integer, String> teenDigitMap;
    private Map<Integer, String> tensDigitMap;
    private Map<Integer, String> threeMap;

    public String numberToWords(int num) {
        initializeMap();
        StringBuilder b = new StringBuilder();
        int count = 0;
        while (num > 0) {
            int r = num % 1000;
            String portion = getPortion(r);
            if (portion.length() > 0) {
                b.insert(0, portion + " " + threeMap.get(count));
            }
            count++;
            num = num / 1000;
        }
        if (b.length() == 0) {
            return "Zero";
        }
        return b.toString().trim();
    }

    private void initializeMap() {
        digitMap = new HashMap<>();
        digitMap.put(1, "One");
        digitMap.put(2, "Two");
        digitMap.put(3, "Three");
        digitMap.put(4, "Four");
        digitMap.put(5, "Five");
        digitMap.put(6, "Six");
        digitMap.put(7, "Seven");
        digitMap.put(8, "Eight");
        digitMap.put(9, "Nine");

        teenDigitMap = new HashMap<>();
        teenDigitMap.put(10, "Ten");
        teenDigitMap.put(11, "Eleven");
        teenDigitMap.put(12, "Twelve");
        teenDigitMap.put(13, "Thirteen");
        teenDigitMap.put(14, "Fourteen");
        teenDigitMap.put(15, "Fifteen");
        teenDigitMap.put(16, "Sixteen");
        teenDigitMap.put(17, "Seventeen");
        teenDigitMap.put(18, "Eighteen");
        teenDigitMap.put(19, "Nineteen");

        tensDigitMap = new HashMap<>();
        tensDigitMap.put(1, "Ten");
        tensDigitMap.put(2, "Twenty");
        tensDigitMap.put(3, "Thirty");
        tensDigitMap.put(4, "Forty");
        tensDigitMap.put(5, "Fifty");
        tensDigitMap.put(6, "Sixty");
        tensDigitMap.put(7, "Seventy");
        tensDigitMap.put(8, "Eighty");
        tensDigitMap.put(9, "Ninety");

        threeMap = new HashMap<>();
        threeMap.put(0, "");
        threeMap.put(1, "Thousand");
        threeMap.put(2, "Million");
        threeMap.put(3, "Million");
    }

    public String getPortion(int num) {
        StringBuilder b = new StringBuilder();
        int x = num / 100;
        if (x >= 1) {
            b.append(" " + this.digitMap.get(x) + " hundred");
        }
        int y = num % 100;
        if (y >= 1 && y <= 9) {
            b.append(" " + digitMap.get(y));
        } else if (y >= 10 && y <= 19) {
            b.append(" " + teenDigitMap.get(y));
        } else if (y >= 20) {
            int r = y / 10;
            b.append(" " + this.tensDigitMap.get(r));
            int s = y % 10;
            if (s > 0) {
                b.append(" " + digitMap.get(s));
            }
        }
        return b.toString();
    }

    public static void main(String[] args) {
        IntegerToEnglishWords words = new IntegerToEnglishWords();
        System.out.println(words.numberToWords(23135));

        System.out.println(words.numberToWords(175));
        System.out.println(words.numberToWords(1234000316));


    }

}
