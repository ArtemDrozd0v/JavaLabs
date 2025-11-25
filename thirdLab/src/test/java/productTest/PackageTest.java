package productTest;

import org.example.products.Package;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PackageTest {

    @Test
    void testPackage() {
        Package pack = new Package("Коробка", 1);

        assertEquals("Коробка", pack.getName());
        assertEquals(1, pack.getWeight());

        assertThrows(IllegalArgumentException.class,
                () -> new Package("", 1.0));

        assertThrows(IllegalArgumentException.class,
                () -> new Package(null, 1.0));

        assertThrows(IllegalArgumentException.class,
                () -> new Package("Коробка", -1.0));
    }

    @Test
    void testPackageEHT() {
        Package pack1 = new Package("Пакет", 0.1);
        Package pack2 = new Package("Контейнер", 0.1);
        Package pack3 = new Package("Пакет", 0.1);
        Package pack4 = new Package("Пакет", 0.2);

        assertNotEquals(pack1, pack2);
        assertEquals(pack1, pack3);
        assertNotEquals(pack1, pack4);
        assertNotEquals(null, pack1);

        Package pack5 = new Package("Ящик", 2.5);
        Package pack6 = new Package("Ящик", 2.5);

        assertEquals(pack5.hashCode(), pack6.hashCode());

        Package pack = new Package("Бочка", 15.0);
        String result = pack.toString();

        assertTrue(result.contains("ProductPackaging{"));
        assertTrue(result.contains("name='Бочка'"));
        assertTrue(result.contains("weight=15.0"));
    }

}