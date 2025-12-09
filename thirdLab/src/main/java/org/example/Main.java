package org.example;

import org.example.products.PieceProduct;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class Main {
    public static void main(String[] args) {
        PieceProduct product1 = new PieceProduct("Конфета", "Карамель", 0.02);
        PieceProduct product2 = new PieceProduct("Печенье", "Карамель", 0.02);
        PieceProduct product3 = new PieceProduct("Конфета", "Карамель", 0.02);
        PieceProduct product4 = new PieceProduct("Конфета", "Карамель", 0.03);
        PieceProduct product5 = new PieceProduct("Конфета", "Шоколадная", 0.02);

        assertNotEquals(product1, product2);
        assertEquals(product1, product3);
        assertNotEquals(product1, product4);
        assertNotEquals(product1, product5);
        assertNotEquals(null, product1);

        PieceProduct product6 = new PieceProduct("Мыло", "", 0.1);
        PieceProduct product7 = new PieceProduct("Мыло", "", 0.1);

        assertEquals(product6.hashCode(), product7.hashCode());

        PieceProduct product = new PieceProduct("Книга", "Детская", 0.5);
        String result = product.toString();

        System.out.println(result);

    }
}