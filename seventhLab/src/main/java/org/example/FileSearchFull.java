package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSearchFull {
    public List<String> recursiveSearcher(File root, String pattern) {
        List<String> result = new ArrayList<>();

        if (root.exists() && root.isDirectory()) {
            search(root, pattern, result);
        }

        return result;
    }

    private void search(File directory, String pattern, List<String> result) {
        File[] files = directory.listFiles();

        if (files == null) {
            return;
        }

        for (File file : files) {
            if (file.getName().matches(pattern)) {
                result.add(file.getAbsolutePath());
            }
            if (file.isDirectory()) {
                search(file, pattern, result);
            }
        }
    }
}
