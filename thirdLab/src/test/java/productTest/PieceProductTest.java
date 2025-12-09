package productTest;

import org.example.products.PieceProduct;
import org.example.products.Product;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PieceProductTest {

    @Test
    void testPieceProduct() {
        PieceProduct product = new PieceProduct("Шоколад", "Молочный", 0.1);

        assertEquals("Шоколад", product.getName());
        assertEquals("Молочный", product.getDescription());
        assertEquals(0.1, product.getPieceWeight());

        assertThrows(IllegalArgumentException.class,
                () -> new PieceProduct("", "Описание", 0.1));

        assertThrows(IllegalArgumentException.class,
                () -> new PieceProduct(null, "Описание", 0.1));

        assertThrows(IllegalArgumentException.class,
                () -> new PieceProduct("Товар", null, 0.1));

        assertThrows(IllegalArgumentException.class,
                () -> new PieceProduct("Товар", "Описание", 0.0));

        assertThrows(IllegalArgumentException.class,
                () -> new PieceProduct("Товар", "Описание", -1.0));
    }

    @Test
    void testPieceProductEHT() {
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

        assertTrue(result.contains("pieceWeight=0.5"));
    }

    @Test
    void testPieceProductInheritance() {
        PieceProduct pieceProduct = new PieceProduct("Игрушка", "Плюшевая", 0.3);

        assertEquals("Игрушка", pieceProduct.getName());
        assertEquals("Плюшевая", pieceProduct.getDescription());
        assertEquals(0.3, pieceProduct.getPieceWeight());

        assertTrue(pieceProduct instanceof Product);
    }
}
