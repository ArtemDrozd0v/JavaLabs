package org.example.products;

import java.util.Objects;

public class PieceProduct extends Product{
    private final double pieceWeight;

    public PieceProduct(String name, String description, double weight) {
        super(name, description);

        if (weight <= 0) {
            throw new IllegalArgumentException("Неправильно задан вес");
        }

        this.pieceWeight = weight;
    }

    public double getPieceWeight() {
        return pieceWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PieceProduct that = (PieceProduct) o;
        return Double.compare(pieceWeight, that.pieceWeight) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pieceWeight);
    }

    @Override
    public String toString() {
        return "PieceProduct{" +
                "pieceWeight=" + pieceWeight +
                '}';
    }
}
