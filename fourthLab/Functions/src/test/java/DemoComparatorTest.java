import org.example.comparators.ComparatorDemo;
import org.example.comparators.ProductComparator;
import org.example.products.Product;
import org.example.products.PieceProduct;
import org.example.products.WeightProduct;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class DemoComparatorTest {
    private Comparator<Product> comparator;

    @Test
    public void testSortGoodsSimple() {
        comparator = new ProductComparator();
        Product[] products = {
                new Product("Banana", "Yellow"),
                new Product("Apple", "Red"),
                new Product("Cherry", "Small")
        };

        Product[] expected = {
                new Product("Apple", "Red"),
                new Product("Banana", "Yellow"),
                new Product("Cherry", "Small")
        };

        ComparatorDemo.sortGoods(products, comparator);
        assertArrayEquals(expected, products);
    }

    @Test
    public void testSortGoods_WithEqualNames() {
        comparator = new ProductComparator();
        Product[] products = {
                new Product("Apple", "Green"),
                new Product("Apple", "Red"),
                new Product("Apple", "Yellow")
        };

        Product[] expected = {
                new Product("Apple", "Green"),
                new Product("Apple", "Red"),
                new Product("Apple", "Yellow")
        };

        ComparatorDemo.sortGoods(products, comparator);
        assertArrayEquals(expected, products);
    }

    @Test
    public void testSortGoodsEmpty() {
        comparator = new ProductComparator();
        Product[] emptyArray = new Product[0];
        ComparatorDemo.sortGoods(emptyArray, comparator);
        assertEquals(0, emptyArray.length);
    }

    @Test
    public void testSortGoodsONe() {
        comparator = new ProductComparator();
        Product[] single = { new Product("Apple", "Red") };
        Product[] expected = { new Product("Apple", "Red") };

        ComparatorDemo.sortGoods(single, comparator);
        assertArrayEquals(expected, single);
    }

    @Test
    public void testSortGoodsPieceProduct() {
        comparator = new ProductComparator();
        PieceProduct[] products = {
                new PieceProduct("Banana", "Yellow", 1),
                new PieceProduct("Apple", "Red", 1),
                new PieceProduct("Cherry", "Small", 1)
        };

        PieceProduct[] expected = {
                new PieceProduct("Apple", "Red", 1),
                new PieceProduct("Banana", "Yellow", 1),
                new PieceProduct("Cherry", "Small", 1)
        };

        ComparatorDemo.sortGoods(products, comparator);
        assertArrayEquals(expected, products);
    }

    @Test
    public void testSortGoodsWeightProduct() {
        comparator = new ProductComparator();
        WeightProduct[] products = {
                new WeightProduct("Sugar", "White"),
                new WeightProduct("Salt", "Sea"),
                new WeightProduct("Flour", "Wheat")
        };

        WeightProduct[] expected = {
                new WeightProduct("Flour", "Wheat"),
                new WeightProduct("Salt", "Sea"),
                new WeightProduct("Sugar", "White")
        };


        ComparatorDemo.sortGoods(products, comparator);
        assertArrayEquals(expected, products);
    }

}
