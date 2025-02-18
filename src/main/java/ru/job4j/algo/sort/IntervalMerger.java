package ru.job4j.algo.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class IntervalMerger {
    public int[][] merge(int[][] intervals) {
        List<int[]> result = new ArrayList<>();
        Arrays.sort(intervals, Comparator.comparingInt((x) -> x[0]));

        int start = intervals[0][0];
        int finish = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] > finish) {
                result.add(new int[]{start, finish});
                start = intervals[i][0];
                finish = intervals[i][1];
            } else if (intervals[i][1] > finish) {
                finish = intervals[i][1];
            }
        }
        result.add(new int[]{start, finish});
        return result.toArray(new int[result.size()][]);
    }
}
