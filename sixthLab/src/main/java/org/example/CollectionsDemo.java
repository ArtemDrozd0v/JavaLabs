package org.example;

import java.util.List;

public class CollectionsDemo {
    public int countString(List<String> list, char c) {
        int count = 0;
        for (String s : list) {
            if (s.charAt(0) == c) {
                count++;
            }
        }
        return count;
    }
}
