package base.leetcode.problem290;

import java.util.*;
import java.util.stream.Collectors;

public class WordPattern {

    public boolean wordPattern(String pattern, String s) {
        Map<Character, String> wordMap = new HashMap<>();
        String[] words = s.split("\\s+");
        if (pattern.length() != words.length) {
            return false;
        }
        char[] patternAry = pattern.toCharArray();
        Set<String> wordsSeen = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            char c = patternAry[i];
            if (wordMap.containsKey(c)) {
                if (!wordMap.get(c).equals(word)) {
                    return false;
                }
            } else {
                if (wordsSeen.contains(word)) {
                    return false;
                }
                wordsSeen.add(word);
                wordMap.put(c, word);
            }
        }
        return true;
    }

    public String getHint(String secret, String guess) {
        int bulls = 0;
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();

        for (int i = 0; i < 4; i++) {
            char x = secret.charAt(i);
            char y = guess.charAt(i);
            if (x == y) {
                bulls++;
            } else {
                map1.put(x, map1.getOrDefault(x, 0) + 1);
                map2.put(y, map2.getOrDefault(y, 0) + 1);
            }
        }

        int cows = 0;
        for (Character c : map1.keySet()) {
            int count1 = map1.get(c);
            int count2 = map2.getOrDefault(c, 0);
            cows += Math.min(count1, count2);
        }
        return bulls + "A" + cows + "B";
    }


    public static void main(String[] args) {
        String s = "hello";
        s.chars().
                mapToObj( x -> Character.valueOf((char) (x+1))).
                forEach(System.out::println);
        String res =
                s.chars().
                mapToObj( x -> "" + Character.valueOf((char) (x + 1))).
                collect(Collectors.joining(""));

        Map<Character, Long> map =
                s.chars().mapToObj(x -> (char) x).collect(
                        Collectors.groupingBy( x -> x, Collectors.counting())
                );
        System.out.println(map);

        Map<Character, Integer> map2 = new HashMap<>();
        for (char c : s.toCharArray()) {
            map2.put(c, map2.getOrDefault(c, 0) + 1);
        }
        System.out.println(map2);

//        s.chars().mapToObj(x -> x).collect(Collectors.toList());
//        System.out.println(res);
    }


}
