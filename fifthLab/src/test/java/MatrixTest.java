import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.example.Matrix;

public class MatrixTest {

    @Test
    void testConstructor() {
        Matrix matrix = new Matrix(3);
        assertEquals(3, matrix.getSize());

        assertThrows(IllegalArgumentException.class, () -> new Matrix(0));
    }

    @Test
    void testGetSet() {
        Matrix matrix = new Matrix(2);
        assertEquals(0, matrix.get(0, 0));

        matrix.set(0, 0, 1);
        matrix.set(0, 1, 2);
        matrix.set(1, 0, 3);
        matrix.set(1, 1, 4);

        assertEquals(1, matrix.get(0, 0));
        assertEquals(2, matrix.get(0, 1));
        assertEquals(3, matrix.get(1, 0));
        assertEquals(4, matrix.get(1, 1));

        assertThrows(IndexOutOfBoundsException.class, () -> matrix.get(2, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.get(-1, 0));
    }

    @Test
    void testDeterminant() {
        Matrix matrix = new Matrix(2);
        matrix.set(0, 0, 1.0);
        matrix.set(0, 1, 2.0);
        matrix.set(1, 0, 3.0);
        matrix.set(1, 1, 4.0);

        assertEquals(-2.0, matrix.determinant());


        Matrix matrix2 = new Matrix(3);

        int count = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix2.set(i, j,  count++);
            }
        }

        assertEquals(0, matrix2.determinant());
    }

    @Test
    void testEqualsHashCode() {
        Matrix matrix1 = new Matrix(2);
        matrix1.set(0, 0, 1);
        matrix1.set(0, 1, 2);
        matrix1.set(1, 0, 3);
        matrix1.set(1, 1, 4);

        Matrix matrix2 = new Matrix(2);
        matrix2.set(0, 0, 1);
        matrix2.set(0, 1, 2);
        matrix2.set(1, 0, 3);
        matrix2.set(1, 1, 4);

        Matrix matrix3 = new Matrix(2);
        matrix3.set(0, 0, 1);
        matrix3.set(0, 1, 76);
        matrix3.set(1, 0, 3);
        matrix3.set(1, 1, 42);

        Matrix matrix4 = new Matrix(3);

        assertEquals(matrix1, matrix2);
        assertNotEquals(matrix1, matrix3);
        assertNotEquals(matrix1, matrix4);
        assertNotEquals(matrix1, null);

        assertEquals(matrix1.hashCode(), matrix2.hashCode());
    }

    @Test
    void testDeterminantCache() {
        Matrix matrix = new Matrix(2);
        matrix.set(0, 0, 1);
        matrix.set(0, 1, 2);
        matrix.set(1, 0, 3);
        matrix.set(1, 1, 4);

        double det1 = matrix.determinant();
        assertEquals(-2, det1);

        double det2 = matrix.determinant();
        assertEquals(det1, det2);

        matrix.set(1, 1, 5);
        double det3 = matrix.determinant();
        assertEquals(-1, det3, 1e-10);

        matrix.set(1, 1, 5);
        assertEquals(-1, matrix.getCachedDet(), 1e-10);

        matrix.set(1, 1, 10);
        assertFalse(matrix.getFlag());
    }

}
