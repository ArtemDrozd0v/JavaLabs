package org.example;

import java.io.*;

public class ArrayBin {
    public void writeArrayToBin(int[] array, String fileName) throws IOException {
        DataOutputStream out = new DataOutputStream(new FileOutputStream(fileName));

        try (out) {
            out.writeInt(array.length);
            for (int i : array) {
                out.writeInt(i);
            }
        }
    }

    public int[] readArrayToBin(String filename) throws IOException {
        DataInputStream in = new DataInputStream(new FileInputStream(filename));

        try (in) {
            int n = in.readInt();
            int[] array = new int[n];

            for (int i = 0; i < n; i++) {
                array[i] = in.readInt();
            }

            return array;
        }
    }

}
