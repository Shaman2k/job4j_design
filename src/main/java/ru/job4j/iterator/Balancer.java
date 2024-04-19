package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        int cursor = 0;
        while (source.hasNext()) {
            if (cursor == nodes.size()) {
                cursor = 0;
            }
            nodes.get(cursor++).add(source.next());
        }
    }
}
