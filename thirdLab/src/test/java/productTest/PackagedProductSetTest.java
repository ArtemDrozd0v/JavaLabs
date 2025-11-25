package productTest;

import org.example.products.*;
import org.example.products.Package;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PackagedProductSetTest {

    @Test
    void testPackagedProductSet() {
        WeightProduct product1 = new WeightProduct("Сахар", "Белый");
        WeightProduct product2 = new WeightProduct("Соль", "Морская");
        Package itemPack = new Package("Пакет", 0.2);
        Package setPack = new Package("Коробка", 0.5);

        PackagedWeightProduct packaged1 = new PackagedWeightProduct(product1, 1.0, itemPack);
        PackagedWeightProduct packaged2 = new PackagedWeightProduct(product2, 0.5, itemPack);
        List<IPackagedProduct> items = Arrays.asList(packaged1, packaged2);

        PackagedProductSet productSet = new PackagedProductSet("Набор продуктов", setPack, items);

        assertEquals("Набор продуктов", productSet.getName());
        assertEquals(setPack, productSet.getPack());
        assertEquals(items, productSet.getPackedItems());

        // масса нетто = 1 + 0.2 + 0.5 + 0.2 = 1.9
        // масса брутто = 1.9 + 0.5 = 2.4
        assertEquals(1.9, productSet.getNetMass());
        assertEquals(2.4, productSet.getBruttoMass());

        assertThrows(IllegalArgumentException.class,
                () -> new PackagedProductSet("", setPack, items));

        assertThrows(IllegalArgumentException.class,
                () -> new PackagedProductSet(null, setPack, items));

        assertThrows(IllegalArgumentException.class,
                () -> new PackagedProductSet("Набор", null, items));

        assertThrows(IllegalArgumentException.class,
                () -> new PackagedProductSet("Набор", setPack, null));

        assertThrows(IllegalArgumentException.class,
                () -> new PackagedProductSet("Набор", setPack, List.of()));
    }

    @Test
    void testPackagedProductSetEHT() {
        WeightProduct product = new WeightProduct("Мука", "Пшеничная");
        Package itemPack = new Package("Пакет", 0.1);
        Package setPack1 = new Package("Коробка", 0.5);
        Package setPack2 = new Package("Ящик", 1.0);

        PackagedWeightProduct packaged = new PackagedWeightProduct(product, 2.0, itemPack);
        List<IPackagedProduct> items1 = List.of(packaged);
        List<IPackagedProduct> items2 = Arrays.asList(packaged, packaged);

        PackagedProductSet set1 = new PackagedProductSet("Набор1", setPack1, items1);
        PackagedProductSet set2 = new PackagedProductSet("Набор2", setPack1, items1);
        PackagedProductSet set3 = new PackagedProductSet("Набор1", setPack1, items1);
        PackagedProductSet set4 = new PackagedProductSet("Набор1", setPack2, items1);
        PackagedProductSet set5 = new PackagedProductSet("Набор1", setPack1, items2);

        assertNotEquals(set1, set2);
        assertEquals(set1, set3);
        assertNotEquals(set1, set4);
        assertNotEquals(set1, set5);
        assertNotEquals(null, set1);

        PackagedProductSet set6 = new PackagedProductSet("Набор", setPack1, items1);
        PackagedProductSet set7 = new PackagedProductSet("Набор", setPack1, items1);

        assertEquals(set6.hashCode(), set7.hashCode());

        PackagedProductSet set = new PackagedProductSet("Подарок", setPack1, items1);
        String result = set.toString();

        assertTrue(result.contains("PackedProductSet{"));
        assertTrue(result.contains("name='Подарок'"));
        assertTrue(result.contains("pack="));
        assertTrue(result.contains("packedItems="));
    }

    @Test
    void testPackagedProductSetMultiItems() {
        WeightProduct weightProduct = new WeightProduct("Рис", "Белый");
        Package weightPack = new Package("Пакет", 0.02);
        PackagedWeightProduct packagedWeight = new PackagedWeightProduct(weightProduct, 1.0, weightPack);

        PieceProduct pieceProduct = new PieceProduct("Шоколад", "Молочный", 0.1);
        Package piecePack = new Package("Фольга", 0.02);
        PackagedPieceProduct packagedPiece = new PackagedPieceProduct(pieceProduct, 3, piecePack);

        Package setPack = new Package("Корзина", 0.3);

        List<IPackagedProduct> items = Arrays.asList(packagedWeight, packagedPiece);
        PackagedProductSet productSet = new PackagedProductSet("Продуктовый набор", setPack, items);

        //весовой брутто = 1.0 + 0.02 = 1.02
        // шт нетто = 3 * 0.1 = 0.3
        // шт брутто = 0.3 + 0.02 = 0.32
        // корзина нетто = 1.02 + 0.32 = 1.34
        // корзина брутто = 1.34 + 0.3 = 1.64
        assertEquals(1.34, productSet.getNetMass(),0.0000001);
        assertEquals(1.64, productSet.getBruttoMass(),0.0000001);

        assertEquals("Продуктовый набор", productSet.getName());
        assertEquals(setPack, productSet.getPack());
        assertEquals(items, productSet.getPackedItems());
    }
}
