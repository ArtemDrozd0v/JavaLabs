package productTest;

import org.example.products.Package;
import org.example.products.PackagedPieceProduct;
import org.example.products.PieceProduct;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PackagedPieceProductTest {

    @Test
    void testPackagedPieceProduct() {
        PieceProduct product = new PieceProduct("Шоколад", "Молочный", 0.1);
        Package pack = new Package("Коробка", 0.2);
        PackagedPieceProduct packagedProduct = new PackagedPieceProduct(product, 10, pack);

        assertEquals("Шоколад", packagedProduct.getName());
        assertEquals(1.0, packagedProduct.getNetMass());
        assertEquals(1.2, packagedProduct.getBruttoMass());
        assertEquals(product, packagedProduct.getProduct());
        assertEquals(10, packagedProduct.getQuantity());
        assertEquals(pack, packagedProduct.getPack());

        assertThrows(IllegalArgumentException.class,
                () -> new PackagedPieceProduct(product, 0, pack));

        assertThrows(IllegalArgumentException.class,
                () -> new PackagedPieceProduct(product, -5, pack));
    }

    @Test
    void testPackagedPieceProductEHT() {
        PieceProduct product1 = new PieceProduct("Конфета", "Карамель", 0.02);
        PieceProduct product2 = new PieceProduct("Печенье", "Овсяное", 0.05);
        Package pack1 = new Package("Пакет", 0.1);
        Package pack2 = new Package("Банка", 0.3);

        PackagedPieceProduct packaged1 = new PackagedPieceProduct(product1, 5, pack1);
        PackagedPieceProduct packaged2 = new PackagedPieceProduct(product2, 5, pack1);
        PackagedPieceProduct packaged3 = new PackagedPieceProduct(product1, 5, pack1);
        PackagedPieceProduct packaged4 = new PackagedPieceProduct(product1, 10, pack1);
        PackagedPieceProduct packaged5 = new PackagedPieceProduct(product1, 5, pack2);

        assertNotEquals(packaged1, packaged2);
        assertEquals(packaged1, packaged3);
        assertNotEquals(packaged1, packaged4);
        assertNotEquals(packaged1, packaged5);
        assertNotEquals(null, packaged1);

        PackagedPieceProduct packaged6 = new PackagedPieceProduct(product1, 5, pack1);
        PackagedPieceProduct packaged7 = new PackagedPieceProduct(product1, 5, pack1);

        assertEquals(packaged6.hashCode(), packaged7.hashCode());

        PackagedPieceProduct packaged = new PackagedPieceProduct(product1, 8, pack1);
        String result = packaged.toString();

        assertTrue(result.contains("PackagedPieceProduct{"));
        assertTrue(result.contains("product="));
        assertTrue(result.contains("quantity=8"));
        assertTrue(result.contains("pack="));
    }
}