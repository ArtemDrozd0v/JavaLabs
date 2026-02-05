import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.example.*;

public class MatrixServiceTest {
    @Test
    void testCompare1() {
        Matrix m1 = new Matrix(2);
        m1.set(0, 0, 1);
        m1.set(0, 1, 2);
        m1.set(1, 0, 3);
        m1.set(1, 1, 4);

        Matrix m2 = new Matrix(2);
        m2.set(0, 0, 1);
        m2.set(0, 1, 2);
        m2.set(1, 0, 3);
        m2.set(1, 1, 4);

        MatrixComparator comparator = new MatrixComparator();
        assertEquals(0, comparator.compare(m1, m2));
    }

    @Test
    void testCompare2() {
        Matrix m1 = new Matrix(2);
        m1.set(0, 0, 1);
        m1.set(0, 1, 2);
        m1.set(1, 0, 3);
        m1.set(1, 1, 4);

        Matrix m2 = new Matrix(2);
        m2.set(0, 0, 1);
        m2.set(0, 1, 2);
        m2.set(1, 0, 3);
        m2.set(1, 1, 5);

        MatrixComparator comparator = new MatrixComparator();
        assertTrue(comparator.compare(m1, m2) < 0);
    }


    @Test
    void testSort() {
        Matrix m1 = new Matrix(2); //-2
        m1.set(0, 0, 1);
        m1.set(0, 1, 2);
        m1.set(1, 0, 3);
        m1.set(1, 1, 4);

        Matrix m2 = new Matrix(2); //1
        m2.set(0, 0, 2);
        m2.set(0, 1, 1);
        m2.set(1, 0, 1);
        m2.set(1, 1, 1);

        Matrix m3 = new Matrix(2); //0
        m3.set(0, 0, 1);
        m3.set(0, 1, 2);
        m3.set(1, 0, 2);
        m3.set(1, 1, 4);

        IMatrix[] matrices = {m1, m2, m3};

        MatrixService.arrangeMatrices(matrices);

        assertEquals(-2, matrices[0].determinant());
        assertEquals(0, matrices[1].determinant());
        assertEquals(1, matrices[2].determinant());
    }

    @Test
    void testArrangeMatricesWithDifferentTypes() {
        DiagMatrix diag = new DiagMatrix(3, 2, 1); // 6

        UpTriangleMatrix tri = new UpTriangleMatrix(2); // 8
        tri.set(0, 0, 2);
        tri.set(0, 1, 5);
        tri.set(1, 1, 4);

        Matrix normal = new Matrix(2); //-2
        normal.set(0, 0, 1);
        normal.set(0, 1, 2);
        normal.set(1, 0, 3);
        normal.set(1, 1, 4);

        IMatrix[] matrices = {diag, tri, normal};

        MatrixService.arrangeMatrices(matrices);

        assertEquals(-2, matrices[0].determinant());
        assertEquals(6, matrices[1].determinant());
        assertEquals(8, matrices[2].determinant());
    }

    @Test
    void testArrangeMatricesWithNullArray() {
        assertThrows(IllegalArgumentException.class, () -> {
            MatrixService.arrangeMatrices(null);
        });
    }
}
