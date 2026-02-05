package org.example;

import java.util.Comparator;

public class MatrixComparator implements Comparator<IMatrix> {

    @Override
    public int compare(IMatrix m1, IMatrix m2) {
        double det1 = m1.determinant();
        double det2 = m2.determinant();

        double diff = det1 - det2;

        if (Math.abs(diff) < 1e-10) {
            return 0;
        } else if (diff < 0) {
            return -1;
        } else {
            return 1;
        }
    }
}
