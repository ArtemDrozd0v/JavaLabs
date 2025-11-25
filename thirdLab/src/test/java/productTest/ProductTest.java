package productTest;

import org.example.products.Product;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void testProduct() {
        Product product = new Product("Автомобиль", "Новый");

        assertEquals("Автомобиль", product.getName());
        assertEquals("Новый", product.getDescription());

        assertThrows(IllegalArgumentException.class,
                () -> new Product("","Описание"));

        assertThrows(IllegalArgumentException.class,
                () -> new Product(null,"Описание"));

        assertThrows(IllegalArgumentException.class,
                () -> new Product("123",null));

    }

    @Test
    void testProductEHT() {
        Product product1 = new Product("Кот", "");
        Product product2 = new Product("Собака", "");
        Product product3 = new Product("Кот", "");
        Product product4 = new Product("Кот", "Чистый");

        assertNotEquals(product1, product2);
        assertEquals(product1, product3);
        assertNotEquals(product1, product4);
        assertNotEquals(null, product1);

        Product product5 = new Product("Мышь", "Серая");
        Product product6 = new Product("Мышь", "Серая");

        assertEquals(product5.hashCode(), product6.hashCode());

        Product product = new Product("Конь", "Белый");
        String result = product.toString();

        assertTrue(result.contains("Product{"));
        assertTrue(result.contains("name='Конь'"));
        assertTrue(result.contains("description='Белый'"));
    }
}