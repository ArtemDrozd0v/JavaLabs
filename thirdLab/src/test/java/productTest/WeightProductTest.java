package productTest;

import org.example.products.Product;
import org.example.products.WeightProduct;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WeightProductTest {
    @Test
    void testWeightProduct() {
        WeightProduct product = new WeightProduct("Сахар", "Белый сахар");

        assertEquals("Сахар", product.getName());
        assertEquals("Белый сахар", product.getDescription());

        assertThrows(IllegalArgumentException.class,
                () -> new WeightProduct("", "Описание"));

        assertThrows(IllegalArgumentException.class,
                () -> new WeightProduct(null, "Описание"));

        assertThrows(IllegalArgumentException.class,
                () -> new WeightProduct("Сахар", null));
    }

    @Test
    void testWeightProductEHT() {
        WeightProduct product1 = new WeightProduct("Соль", "Морская");
        WeightProduct product2 = new WeightProduct("Перец", "Морская");
        WeightProduct product3 = new WeightProduct("Соль", "Морская");
        WeightProduct product4 = new WeightProduct("Соль", "Поваренная");

        assertNotEquals(product1, product2);
        assertEquals(product1, product3);
        assertNotEquals(product1, product4);
        assertNotEquals(null, product1);

        WeightProduct product5 = new WeightProduct("Мука", "Пшеничная");
        WeightProduct product6 = new WeightProduct("Мука", "Пшеничная");

        assertEquals(product5.hashCode(), product6.hashCode());

        WeightProduct product = new WeightProduct("Рис", "Басмати");
        String result = product.toString();

        assertTrue(result.contains("WeightProduct{"));
        assertTrue(result.contains("name='Рис'"));
        assertTrue(result.contains("description='Басмати'"));
    }

    @Test
    void testWPExt() {
        WeightProduct weightProduct = new WeightProduct("Крупа", "Гречневая");

        assertEquals("Крупа", weightProduct.getName());
        assertEquals("Гречневая", weightProduct.getDescription());

        assertTrue(weightProduct instanceof Product);
    }
}
