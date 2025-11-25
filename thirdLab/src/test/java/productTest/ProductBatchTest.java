package productTest;

import org.example.products.*;
import org.example.products.Package;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ProductBatchTest {

    @Test
    void testProductBatch() {
        WeightProduct product1 = new WeightProduct("Сахар", "Белый");
        WeightProduct product2 = new WeightProduct("Соль", "Морская");
        Package pack = new Package("Пакет", 0.1);

        PackagedWeightProduct packaged1 = new PackagedWeightProduct(product1, 1, pack);
        PackagedWeightProduct packaged2 = new PackagedWeightProduct(product2, 0.5, pack);
        List<IPackagedProduct> products = Arrays.asList(packaged1, packaged2);

        ProductBatch batch = new ProductBatch("Партия продуктов", products);

        assertEquals("Партия продуктов", batch.getDescription());
        assertEquals(products, batch.getProducts());

        // общая брутто = 1 + 0.1 + 0.5 + 0.1 = 1.7
        assertEquals(1.7, batch.totalBruttoMass(),0.000000000000002);
    }

    @Test
    void testProductBatchEHT() {
        WeightProduct product = new WeightProduct("Мука", "Пшеничная");
        Package pack = new Package("Мешок", 0.5);
        PackagedWeightProduct packaged = new PackagedWeightProduct(product, 5.0, pack);

        List<IPackagedProduct> products1 = List.of(packaged);
        List<IPackagedProduct> products2 = Arrays.asList(packaged, packaged);

        ProductBatch batch1 = new ProductBatch("Партия1", products1);
        ProductBatch batch2 = new ProductBatch("Партия2", products1);
        ProductBatch batch3 = new ProductBatch("Партия1", products1);
        ProductBatch batch4 = new ProductBatch("Партия1", products2);

        assertNotEquals(batch1, batch2);
        assertEquals(batch1, batch3);
        assertNotEquals(batch1, batch4);
        assertNotEquals(null, batch1);

        ProductBatch batch5 = new ProductBatch("Партия", products1);
        ProductBatch batch6 = new ProductBatch("Партия", products1);

        assertEquals(batch5.hashCode(), batch6.hashCode());

        ProductBatch batch = new ProductBatch("Тестовая партия", products1);
        String result = batch.toString();

        assertTrue(result.contains("ProductBatch{"));
        assertTrue(result.contains("products="));
        assertTrue(result.contains("description='Тестовая партия'"));
    }

    @Test
    void testProductBatchMultiProducts() {
        WeightProduct weightProduct = new WeightProduct("Рис", "Белый");
        Package weightPack = new Package("Пакет", 0.05);
        PackagedWeightProduct packagedWeight = new PackagedWeightProduct(weightProduct, 2.0, weightPack);

        PieceProduct pieceProduct = new PieceProduct("Шоколад", "Молочный", 0.1);
        Package piecePack = new Package("Фольга", 0.02);
        PackagedPieceProduct packagedPiece = new PackagedPieceProduct(pieceProduct, 5, piecePack);

        Package setPack = new Package("Коробка", 0.3);
        List<IPackagedProduct> setItems = List.of(packagedWeight);
        PackagedProductSet productSet = new PackagedProductSet("Набор", setPack, setItems);

        List<IPackagedProduct> batchProducts = Arrays.asList(packagedWeight, packagedPiece, productSet);
        ProductBatch batch = new ProductBatch("Смешанная партия", batchProducts);

        assertEquals(3, batch.getProducts().size());
        assertEquals("Смешанная партия", batch.getDescription());
    }

    @Test
    void testProductBatchCalc() {
        WeightProduct product1 = new WeightProduct("Сахар", "Белый");
        WeightProduct product2 = new WeightProduct("Соль", "Морская");
        Package pack = new Package("Пакет", 0.1);

        PackagedWeightProduct packaged1 = new PackagedWeightProduct(product1, 1.0, pack);
        PackagedWeightProduct packaged2 = new PackagedWeightProduct(product2, 0.5, pack);
        List<IPackagedProduct> products = Arrays.asList(packaged1, packaged2);

        ProductBatch batch = new ProductBatch("Тест масса", products);

        // брутто1 = 1 + 0.1 = 1.1
        // брутто2 = 0.5 + 0.1 = 0.6
        // общая = 1.1 + 0.6 = 1.7
        assertEquals(1.7, batch.totalBruttoMass(),0.000000000000002);
    }

    @Test
    void testProductBatchWithSingleProduct() {
        WeightProduct product = new WeightProduct("Кофе", "Арабика");
        Package pack = new Package("Банка", 0.2);
        PackagedWeightProduct packaged = new PackagedWeightProduct(product, 0.5, pack);

        List<IPackagedProduct> products = List.of(packaged);
        ProductBatch batch = new ProductBatch("Одиночная партия", products);

        assertEquals(1, batch.getProducts().size());
        assertEquals(0.7, batch.totalBruttoMass()); // 0.5 + 0.2
    }

    @Test
    void testProductBatchEmptyList() {
        List<IPackagedProduct> emptyProducts = List.of();
        ProductBatch batch = new ProductBatch("Пустая партия", emptyProducts);

        assertEquals("Пустая партия", batch.getDescription());
        assertEquals(0, batch.getProducts().size());
        assertEquals(0.0, batch.totalBruttoMass());
    }

    @Test
    void testProductBatchGetters() {
        WeightProduct product = new WeightProduct("Чай", "Зеленый");
        Package pack = new Package("Упаковка", 0.1);
        PackagedWeightProduct packaged = new PackagedWeightProduct(product, 0.2, pack);

        List<IPackagedProduct> products = List.of(packaged);
        ProductBatch batch = new ProductBatch("Тест геттеры", products);

        assertEquals("Тест геттеры", batch.getDescription());
        assertEquals(products, batch.getProducts());
        assertEquals(1, batch.getProducts().size());
        assertEquals(packaged, batch.getProducts().get(0));
    }
}