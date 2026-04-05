package org.example;

import java.io.*;

public class ArraySymbols {
    public void writeArrayToSymbols(int[] array, String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

        try (writer) {
            for (int i : array) {
                writer.write(String.valueOf(i));
                writer.write(" ");
            }
        }
    }

    public int[] readArrayToSymbols(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        try (reader) {
            String line = reader.readLine();
            String[] parts = line.split(" ");
            int[] array = new int[parts.length];

            for (int i = 0; i < parts.length; i++) {
                array[i] = Integer.parseInt(parts[i]);
            }

            return array;
        }
    }
}
