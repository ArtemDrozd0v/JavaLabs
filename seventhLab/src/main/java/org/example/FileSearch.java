package org.example;

import java.io.File;

public class FileSearch {
    public File[] findFilesByExtension(String path, String extension) {
        File dir = new File(path);

        if (!dir.exists() || !dir.isDirectory()){
            return new File[0];
        }

        return dir.listFiles(new ExtensionFilter(extension));
    }
}
