package ru.job4j.algo.hash;

import java.util.Set;
import java.util.HashSet;

public class LongestUniqueSubstring {
    public static String longestUniqueSubstring(String str) {
        StringBuilder currentResult = new StringBuilder();
        StringBuilder bestResult = new StringBuilder();
        Set<Character> set = new HashSet<>();
        int left = 0;
        int right = 0;
        while (left < str.length() && right < str.length()) {
            set.add(str.charAt(left));
            currentResult.append(str.charAt(left));
            right = left + 1;
            while (right < str.length()) {
                if (set.contains(str.charAt(right))) {
                    set.clear();
                    if (bestResult.length() < currentResult.length()) {
                        bestResult = currentResult;
                    }
                    currentResult = new StringBuilder();
                    left++;
                    break;
                } else {
                    set.add(str.charAt(right));
                    currentResult.append(str.charAt(right));
                    right++;
                }
            }
        }
        return bestResult.length() < currentResult.length() ? currentResult.toString() : bestResult.toString();
    }
}