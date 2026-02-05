import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.example.DiagMatrix;

public class DiagMatrixTest {
    @Test
    void testConstructor() {
        DiagMatrix matrix = new DiagMatrix(3);
        assertEquals(3, matrix.getSize());
        assertEquals(0, matrix.get(0, 0));


        DiagMatrix matrix2 = new DiagMatrix(1, 2, 3);
        assertEquals(3, matrix2.getSize());

        assertEquals(1, matrix2.get(0, 0));
        assertEquals(2, matrix2.get(1, 1));
        assertEquals(3, matrix2.get(2, 2));
        assertEquals(0, matrix2.get(0, 1));
    }

    @Test
    void testSet() {
        DiagMatrix matrix = new DiagMatrix(3);

        matrix.set(0, 0, 1);
        matrix.set(1, 1, 2);
        matrix.set(2, 2, 3);

        assertEquals(1, matrix.get(0, 0));
        assertEquals(2, matrix.get(1, 1));
        assertEquals(3, matrix.get(2, 2));

        matrix.set(0, 1, 0);
        assertEquals(0, matrix.get(0, 1));


        DiagMatrix matrix2 = new DiagMatrix(3);

        assertThrows(IllegalArgumentException.class, () -> matrix2.set(0, 1, 1));
        assertThrows(IllegalArgumentException.class, () -> matrix2.set(1, 0, 2));
        assertThrows(IllegalArgumentException.class, () -> matrix2.set(2, 0, 3));

        assertDoesNotThrow(() -> matrix2.set(0, 1, 0));
    }

    @Test
    void testDeterminant() {
        DiagMatrix matrix = new DiagMatrix(2, 3, 4);
        assertEquals(2 * 3 * 4, matrix.determinant());
    }
}
