import org.example.QuadraticTrinomiall;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuadraticTrinomialTest {
    @Test
    void QuadraticTrinomialTest() {
        QuadraticTrinomiall trinomial1 = new QuadraticTrinomiall(1, 2, 3);

        assertEquals(1, trinomial1.getA());
        assertEquals(2, trinomial1.getB());
        assertEquals(3, trinomial1.getC());


        QuadraticTrinomiall trinomial2 = new QuadraticTrinomiall(1, -5, 6);
        double[] roots = trinomial2.solve();

        assertEquals(2.0, roots[0]);
        assertEquals(3.0, roots[1]);


        QuadraticTrinomiall trinomial3 = new QuadraticTrinomiall(1, -4, 4);
        double[] roots2 = trinomial3.solve();

        assertEquals(2.0, roots2[0]);


        QuadraticTrinomiall trinomial4 = new QuadraticTrinomiall(0, 0, 0);
        double[] roots3 = trinomial4.solve();

        assertEquals(0, roots3.length);


        QuadraticTrinomiall trinomial5 = new QuadraticTrinomiall(0, 0, 3);
        double[] roots4 = trinomial5.solve();

        assertEquals(0, roots4.length);
    }
}
