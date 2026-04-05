package org.example;

import java.io.IOException;
import java.io.RandomAccessFile;

public class ArrayRandomAccessFile {
    public static int[] readFromPosition(String filename, long position, int count) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(filename, "r")) {
            raf.seek(position);
            int[] array = new int[count];
            for (int i = 0; i < count; i++) {
                array[i] = raf.readInt();
            }
            return array;
        }
    }
}
