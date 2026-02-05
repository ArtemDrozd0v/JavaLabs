import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.example.UpTriangleMatrix;


public class UpTriangleMatrixTest {

    @Test
    void testConstructor() {
        UpTriangleMatrix matrix = new UpTriangleMatrix(3);
        assertEquals(3, matrix.getSize());
        assertEquals(0, matrix.get(0, 0));
    }

    @Test
    void testSetValid() {
        UpTriangleMatrix matrix = new UpTriangleMatrix(3);

        matrix.set(0, 0, 1);
        matrix.set(0, 1, 2);
        matrix.set(0, 2, 3);
        matrix.set(1, 1, 4);
        matrix.set(1, 2, 5);
        matrix.set(2, 2, 6);

        assertEquals(1.0, matrix.get(0, 0));
        assertEquals(2.0, matrix.get(0, 1));
        assertEquals(3.0, matrix.get(0, 2));
        assertEquals(0.0, matrix.get(1, 0));
    }

    @Test
    void testSetInvalid() {
        UpTriangleMatrix matrix = new UpTriangleMatrix(3);

        assertThrows(IllegalArgumentException.class, () -> matrix.set(1, 0, 1));
        assertThrows(IllegalArgumentException.class, () -> matrix.set(2, 0, 2));
        assertThrows(IllegalArgumentException.class, () -> matrix.set(2, 1, 3));

        assertDoesNotThrow(() -> matrix.set(0, 1, 1));
        assertDoesNotThrow(() -> matrix.set(1, 2, 2));
    }

    @Test
    void testDeterminant() {
        UpTriangleMatrix matrix = new UpTriangleMatrix(3);
        matrix.set(0, 0, 1);
        matrix.set(0, 1, 2);
        matrix.set(0, 2, 3);
        matrix.set(1, 1, 4);
        matrix.set(1, 2, 5);
        matrix.set(2, 2, 6);

        assertEquals(24, matrix.determinant());
    }
}
