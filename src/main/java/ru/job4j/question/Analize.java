package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;

        Map<Integer, User> previosMap = new HashMap<>();
        for (User user : previous) {
            previosMap.put(user.getId(), user);
        }

        for (User user : current) {
            if (previosMap.containsKey(user.getId())) {
                if (!previosMap.get(user.getId()).getName().equals(user.getName())) {
                    changed++;
                }
                previosMap.remove(user.getId());
            } else {
                added++;
            }
            deleted = previosMap.size();
        }
        return new Info(added, changed, deleted);
    }
}