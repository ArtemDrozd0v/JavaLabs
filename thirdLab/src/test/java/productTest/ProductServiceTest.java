package productTest;

import org.example.filter.Filter;
import org.example.filter.BeginStringFilter;
import org.example.filter.ContainsStringFilter;
import org.example.filter.EndStringFilter;
import org.example.products.*;
import org.example.products.Package;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    @Test
    void testCountByFilter() {
        WeightProduct product1 = new WeightProduct("Сахар", "Белый");
        WeightProduct product2 = new WeightProduct("Соль", "Морская");
        WeightProduct product3 = new WeightProduct("Перец", "Черный");
        Package pack = new Package("Пакет", 0.1);

        PackagedWeightProduct packaged1 = new PackagedWeightProduct(product1, 1.0, pack);
        PackagedWeightProduct packaged2 = new PackagedWeightProduct(product2, 0.5, pack);
        PackagedWeightProduct packaged3 = new PackagedWeightProduct(product3, 0.2, pack);

        List<IPackagedProduct> products = Arrays.asList(packaged1, packaged2, packaged3);
        ProductBatch batch = new ProductBatch("Партия специй", products);

        Filter filter = new BeginStringFilter("С");

        int count = ProductService.countByFilter(batch, filter);
        assertEquals(2, count);
    }

    @Test
    void testCountByFilterWithSets() {
        WeightProduct product1 = new WeightProduct("Сахар", "Белый");
        WeightProduct product2 = new WeightProduct("Соль", "Морская");
        Package itemPack = new Package("Пакет", 0.1);
        Package setPack = new Package("Коробка", 0.5);

        PackagedWeightProduct packaged1 = new PackagedWeightProduct(product1, 1.0, itemPack);
        PackagedWeightProduct packaged2 = new PackagedWeightProduct(product2, 0.5, itemPack);

        List<IPackagedProduct> setItems = Arrays.asList(packaged1, packaged2);
        PackagedProductSet productSet = new PackagedProductSet("Набор продуктов", setPack, setItems);

        List<IPackagedProduct> products = Arrays.asList(productSet, packaged1);
        ProductBatch batch = new ProductBatch("Партия с набором", products);

        Filter filter = new BeginStringFilter("Набор");

        int count = ProductService.countByFilter(batch, filter);
        assertEquals(1, count);
    }

    @Test
    void testCountByFilterDeep() {
        WeightProduct sugar = new WeightProduct("Сахар", "Белый");
        WeightProduct salt = new WeightProduct("Соль", "Морская");
        WeightProduct pepper = new WeightProduct("Перец", "Черный");
        Package smallPack = new Package("Пакет", 0.1);
        Package mediumPack = new Package("Коробка", 0.3);
        Package largePack = new Package("Ящик", 0.8);

        PackagedWeightProduct packagedSugar = new PackagedWeightProduct(sugar, 1.0, smallPack);
        PackagedWeightProduct packagedSalt = new PackagedWeightProduct(salt, 0.5, smallPack);
        PackagedWeightProduct packagedPepper = new PackagedWeightProduct(pepper, 0.2, smallPack);

        List<IPackagedProduct> innerItems = Arrays.asList(packagedSugar, packagedSalt);
        PackagedProductSet innerSet = new PackagedProductSet("Внутренний набор", mediumPack, innerItems);

        List<IPackagedProduct> outerItems = Arrays.asList(innerSet, packagedPepper);
        PackagedProductSet outerSet = new PackagedProductSet("Внешний набор", largePack, outerItems);

        List<IPackagedProduct> products = Arrays.asList(outerSet, packagedPepper);
        ProductBatch batch = new ProductBatch("Глубокая партия", products);

        Filter filter = new ContainsStringFilter("Сахар");

        int shallowCount = ProductService.countByFilter(batch, filter);
        int deepCount = ProductService.countByFilterDeep(batch, filter);

        assertEquals(0, shallowCount);
        assertEquals(1, deepCount);
    }

    @Test
    void testCheckAllWeighted() {
        WeightProduct product1 = new WeightProduct("Сахар", "Белый");
        WeightProduct product2 = new WeightProduct("Соль", "Морская");
        Package pack = new Package("Пакет", 0.1);

        PackagedWeightProduct packaged1 = new PackagedWeightProduct(product1, 1.0, pack);
        PackagedWeightProduct packaged2 = new PackagedWeightProduct(product2, 0.5, pack);

        List<IPackagedProduct> weightedProducts = Arrays.asList(packaged1, packaged2);
        ProductBatch weightedBatch = new ProductBatch("Только весовые", weightedProducts);

        assertTrue(ProductService.checkAllWeighted(weightedBatch));

        PieceProduct chocolate = new PieceProduct("Шоколад", "Молочный", 0.1);
        PackagedPieceProduct packagedChocolate = new PackagedPieceProduct(chocolate, 3, pack);

        List<IPackagedProduct> mixedProducts = Arrays.asList(packaged1, packagedChocolate);
        ProductBatch mixedBatch = new ProductBatch("Смешанная партия", mixedProducts);

        assertFalse(ProductService.checkAllWeighted(mixedBatch));
    }

    @Test
    void testCheckAllWeightedWithNestedSets() {
        WeightProduct sugar = new WeightProduct("Сахар", "Белый");
        WeightProduct salt = new WeightProduct("Соль", "Морская");
        Package smallPack = new Package("Пакет", 0.1);
        Package bigPack = new Package("Коробка", 0.5);

        PackagedWeightProduct packagedSugar = new PackagedWeightProduct(sugar, 1.0, smallPack);
        PackagedWeightProduct packagedSalt = new PackagedWeightProduct(salt, 0.5, smallPack);

        List<IPackagedProduct> setItems = Arrays.asList(packagedSugar, packagedSalt);
        PackagedProductSet weightedSet = new PackagedProductSet("Набор весовых", bigPack, setItems);

        List<IPackagedProduct> products1 = Arrays.asList(weightedSet, packagedSugar);
        ProductBatch batch1 = new ProductBatch("Партия с набором весовых", products1);

        assertTrue(ProductService.checkAllWeighted(batch1));

        PieceProduct chocolate = new PieceProduct("Шоколад", "Молочный", 0.1);
        PackagedPieceProduct packagedChocolate = new PackagedPieceProduct(chocolate, 3, smallPack);

        List<IPackagedProduct> mixedSetItems = Arrays.asList(packagedSugar, packagedChocolate);
        PackagedProductSet mixedSet = new PackagedProductSet("Смешанный набор", bigPack, mixedSetItems);

        List<IPackagedProduct> products2 = Arrays.asList(mixedSet);
        ProductBatch batch2 = new ProductBatch("Партия со смешанным набором", products2);

        assertFalse(ProductService.checkAllWeighted(batch2));
    }

    @Test
    void testCountByFilterWithDifferentFilters() {
        WeightProduct apple = new WeightProduct("Яблоко", "Свежее");
        WeightProduct banana = new WeightProduct("Банан", "Спелый");
        WeightProduct orange = new WeightProduct("Апельсин", "Сочный");
        Package pack = new Package("Пакет", 0.1);

        PackagedWeightProduct packagedApple = new PackagedWeightProduct(apple, 1.0, pack);
        PackagedWeightProduct packagedBanana = new PackagedWeightProduct(banana, 0.8, pack);
        PackagedWeightProduct packagedOrange = new PackagedWeightProduct(orange, 0.9, pack);

        List<IPackagedProduct> products = Arrays.asList(packagedApple, packagedBanana, packagedOrange);
        ProductBatch batch = new ProductBatch("Фрукты", products);

        Filter beginFilter = new BeginStringFilter("А");
        int beginCount = ProductService.countByFilter(batch, beginFilter);
        assertEquals(1, beginCount);

        Filter containsFilter = new ContainsStringFilter("ан");
        int containsCount = ProductService.countByFilter(batch, containsFilter);
        assertEquals(1, containsCount);

        Filter endFilter = new EndStringFilter("н");
        int endCount = ProductService.countByFilter(batch, endFilter);
        assertEquals(2, endCount);
    }

}