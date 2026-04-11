package org.example;

import java.io.File;

public class FileSearch {
    public File[] findFilesByExtension(File dir, String extension) {
        if (!dir.exists() || !dir.isDirectory()){
            return new File[0];
        }

        return dir.listFiles(new ExtensionFilter(extension));
    }
}
