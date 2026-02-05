package org.example;

public class UpTriangleMatrix extends Matrix{
    public UpTriangleMatrix(int size) {
        super(size);
    }

    @Override
    public void set(int row, int col, double n) {
        if (row > col && Math.abs(n) > 1e-10) {
            throw new IllegalArgumentException("Нельзя установить ненулевое значение ниже диагонали");
        }
        super.set(row, col, n);
    }

    @Override
    public double get(int row, int col) {
        return super.get(row, col);
    }

    @Override
    public double determinant() {
        double det = 1.0;
        int size = getSize();
        for (int i = 0; i < size; i++) {
            det *= get(i, i);
        }
        return det;
    }

}
