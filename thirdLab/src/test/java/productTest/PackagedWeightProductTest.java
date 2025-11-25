package productTest;

import org.example.products.PackagedWeightProduct;
import org.example.products.Package;
import org.example.products.WeightProduct;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PackagedWeightProductTest {

    @Test
    void testPackagedWeightProduct() {
        WeightProduct product = new WeightProduct("Сахар", "Белый");
        Package pack = new Package("Мешок", 0.5);
        PackagedWeightProduct packagedProduct = new PackagedWeightProduct(product, 10.0, pack);

        assertEquals("Сахар", packagedProduct.getName());
        assertEquals(10.0, packagedProduct.getNetMass());
        assertEquals(10.5, packagedProduct.getBruttoMass());
        assertEquals(product, packagedProduct.getProduct());
        assertEquals(10.0, packagedProduct.getProductWeight());
        assertEquals(pack, packagedProduct.getPack());

        assertThrows(IllegalArgumentException.class,
                () -> new PackagedWeightProduct(product, 0.0, pack));

        assertThrows(IllegalArgumentException.class,
                () -> new PackagedWeightProduct(product, -5.0, pack));


        WeightProduct product1 = new WeightProduct("Кофе", "Арабика");
        Package pack1 = new Package("Банка", 0.2);
        PackagedWeightProduct packaged = new PackagedWeightProduct(product1, 0.5, pack1);

        assertEquals("Кофе", packaged.getName());
    }

    @Test
    void testPackagedWeightProductEHT() {
        WeightProduct product1 = new WeightProduct("Соль", "Морская");
        WeightProduct product2 = new WeightProduct("Перец", "Черный");
        Package pack1 = new Package("Пакет", 0.1);
        Package pack2 = new Package("Коробка", 0.3);

        PackagedWeightProduct packaged1 = new PackagedWeightProduct(product1, 2.0, pack1);
        PackagedWeightProduct packaged2 = new PackagedWeightProduct(product2, 2.0, pack1);
        PackagedWeightProduct packaged3 = new PackagedWeightProduct(product1, 2.0, pack1);
        PackagedWeightProduct packaged4 = new PackagedWeightProduct(product1, 3.0, pack1);
        PackagedWeightProduct packaged5 = new PackagedWeightProduct(product1, 2.0, pack2);

        assertNotEquals(packaged1, packaged2);
        assertEquals(packaged1, packaged3);
        assertNotEquals(packaged1, packaged4);
        assertNotEquals(packaged1, packaged5);
        assertNotEquals(null, packaged1);

        PackagedWeightProduct packaged6 = new PackagedWeightProduct(product1, 2.0, pack1);
        PackagedWeightProduct packaged7 = new PackagedWeightProduct(product1, 2.0, pack1);

        assertEquals(packaged6.hashCode(), packaged7.hashCode());

        PackagedWeightProduct packaged = new PackagedWeightProduct(product1, 5.0, pack1);
        String result = packaged.toString();

        assertTrue(result.contains("PackagedWeightProduct{"));
        assertTrue(result.contains("product="));
        assertTrue(result.contains("productWeight=5.0"));
        assertTrue(result.contains("pack="));
    }

}
