package org.example;

import java.util.Arrays;
import java.util.Objects;

public class Matrix implements IMatrix{
    private final int size;
    private final double[] arr;

    private boolean flag;
    private double cachedDet = 0;

    public Matrix(int size){
        if (size <= 0){
            throw new IllegalArgumentException("Неверный размер матрицы");
        }
        this.size = size;
        this.arr = new double[size * size];
        this.flag = false;
    }

    @Override
    public double get(int row, int col) {
        correctIndex(row, col);
        return arr[row * size + col];
    }

    @Override
    public void set(int row, int col, double n) {
        correctIndex(row, col);
        double tmp = arr[row * size + col];

        arr[row * size + col] = n;

        if (Math.abs(tmp - n) > 1e-10) {
            flag = false;
        }
    }

    @Override
    public double determinant() {
        if (flag) {
            return cachedDet;
        }

        double[][] matrix = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = get(i, j);
            }
        }

        double det = 1;
        int n = matrix.length;

        for (int i = 0; i < n; i++) {
            int maxi = i;
            for (int k = i + 1; k < n; k++) {
                if (Math.abs(matrix[k][i]) > Math.abs(matrix[maxi][i])) {
                    maxi = k;
                }
            }

            if (matrix[maxi][i] == 0) {
                cachedDet = 0;
                flag = true;
                return cachedDet;
            }

            if (maxi != i) {
                double[] tmp = matrix[i];
                matrix[i] = matrix[maxi];
                matrix[maxi] = tmp;
                det *= -1;
            }

            det *= matrix[i][i];

            for (int k = i + 1; k < n; k++) {
                double del = matrix[k][i] / matrix[i][i];
                for (int j = i; j < n; j++) {
                    matrix[k][j] -= del * matrix[i][j];
                }
            }
        }

        cachedDet = det;
        flag = true;

        return det;
    }

    private void correctIndex(int row, int col){
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IndexOutOfBoundsException("Индексы вне массива");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix = (Matrix) o;
        return size == matrix.size && Objects.deepEquals(arr, matrix.arr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, Arrays.hashCode(arr));
    }

    @Override
    public int getSize() {
        return size;
    }

    public double getCachedDet() {
        return cachedDet;
    }

    public boolean getFlag() {
        return flag;
    }
}
