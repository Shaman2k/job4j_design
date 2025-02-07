package ru.job4j.algo;

import java.util.Arrays;

public class SmallestRangeFinder {
    public static int[] findSmallestRange(int[] nums, int k) {
        int[] result = null;
        int left = 0;
        int right = 0;
        while (left <= nums.length - k) {
            while (right < k - 1) {
                if (nums[left + right] == nums[left + right + 1]) {
                    left++;
                    right = 0;
                    break;
                }
                right++;
            }
            if (right > 0) {
                result = new int[]{left, left + k - 1};
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9};
        int k = 3;
        int[] result = findSmallestRange(nums, k);
        if (result != null) {
            System.out.println("Наименьший диапазон с " + k + " различными элементами: " + Arrays.toString(result));
        } else {
            System.out.println("Такой диапазон не существует.");
        }
    }
}