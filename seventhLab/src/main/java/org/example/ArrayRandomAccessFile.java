package org.example;

import java.io.IOException;
import java.io.RandomAccessFile;

public class ArrayRandomAccessFile {
    public static int[] readFromPosition(String filename, int position) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(filename, "r")) {
            raf.seek(position * 4L);
            int count = (int) ((raf.length() - position*4)/4);
            int[] array = new int[count];
            for (int i = 0; i < count; i++) {
                array[i] = raf.readInt();
            }
            return array;
        }
    }
}
