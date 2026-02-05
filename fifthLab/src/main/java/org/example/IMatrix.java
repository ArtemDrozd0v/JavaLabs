package org.example;

public interface IMatrix {
    double get(int i, int j);
    void set(int i, int j, double n);
    double determinant();

    int getSize();
}
