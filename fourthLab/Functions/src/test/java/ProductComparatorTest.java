import org.example.comparators.ProductComparator;
import org.example.products.PieceProduct;
import org.example.products.Product;
import org.example.products.WeightProduct;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class ProductComparatorTest {
    private Comparator<Product> comparator;

    @Test
    public void testCompare() {
        comparator = new ProductComparator();

        Product p1 = new Product("Apple", "Red apple");
        Product p2 = new Product("Apple", "Green apple");

        assertTrue(comparator.compare(p1, p2) > 0);
        assertTrue(comparator.compare(p2, p1) < 0);


        Product p3 = new Product("Apple", "Fresh");
        Product p4 = new Product("Banana", "Yellow");

        assertTrue(comparator.compare(p3, p4) < 0);
        assertTrue(comparator.compare(p4, p3) > 0);

        Product p5 = new Product("Apple", "Red");
        Product p6 = new Product("Apple", "Red");

        assertEquals(0, comparator.compare(p5, p6));
        assertEquals(0, comparator.compare(p6, p5));

    }

    @Test()
    public void testCompareNull() {
        comparator = new ProductComparator();

        Product p1 = null;
        Product p2 = new Product("Apple", "Red");

        assertThrows(IllegalArgumentException.class,
                () -> comparator.compare(p1, p2));
    }

    @Test
    public void testCompareDerivedTypes() {
        comparator = new ProductComparator();

        PieceProduct p1 = new PieceProduct("Apple", "Red", 1);
        WeightProduct p2 = new WeightProduct("Banana", "Yellow");

        assertTrue(comparator.compare(p1, p2) < 0);
        assertTrue(comparator.compare(p2, p1) > 0);
    }

    @Test
    public void testCompareMixedTypes() {
        comparator = new ProductComparator();

        Product p1 = new Product("Apple", "Red");
        PieceProduct p2 = new PieceProduct("Apple", "Green", 1);
        WeightProduct p3 = new WeightProduct("Apple", "Yellow");

        // G < R < Y
        assertTrue(comparator.compare(p2, p1) < 0);
        assertTrue(comparator.compare(p1, p3) < 0);
        assertTrue(comparator.compare(p2, p3) < 0);
    }
}
