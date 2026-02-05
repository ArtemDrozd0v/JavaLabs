package org.example;

import java.util.Arrays;

public class MatrixService {
    public static void arrangeMatrices(IMatrix[] matrices) {
        if (matrices == null) {
            throw new IllegalArgumentException("Массив матриц это null");
        }

        MatrixComparator comparator = new MatrixComparator();

        Arrays.sort(matrices, comparator);
    }
}
