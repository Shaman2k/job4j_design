package ru.job4j.algo.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Brackets {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        for (int i = 0; i < s.length(); i++) {
            char target = s.charAt(i);
            if (map.containsValue(target)) {
                stack.push(target);
            } else {
                if (stack.empty() || !map.get(target).equals(stack.pop())) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
