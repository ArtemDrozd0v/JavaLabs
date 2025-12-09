import org.example.QuadraticMaxRootFinderr;
import org.example.QuadraticTrinomiall;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuadraticMaxRootFinderTest {

    @Test
    void testConstructorValid() {
        QuadraticTrinomiall trinomial = new QuadraticTrinomiall(1, 2, 1);
        QuadraticMaxRootFinderr finder = new QuadraticMaxRootFinderr(trinomial);

        assertEquals(trinomial, finder.getTrinomial());

        assertThrows(IllegalArgumentException.class,
                () -> new QuadraticMaxRootFinderr(null));

        QuadraticTrinomiall trinomial0 = new QuadraticTrinomiall(1, 0, 1);
        QuadraticMaxRootFinderr finder0 = new QuadraticMaxRootFinderr(trinomial0);

        assertThrows(IllegalArgumentException.class,
                () -> finder0.getMaxRoot());


        QuadraticTrinomiall trinomial2 = new QuadraticTrinomiall(1, -5, 6);
        QuadraticMaxRootFinderr finder2 = new QuadraticMaxRootFinderr(trinomial2);

        assertEquals(3.0, finder2.getMaxRoot());

        QuadraticTrinomiall trinomial3 = new QuadraticTrinomiall(1, -4, 4);
        QuadraticMaxRootFinderr finder3 = new QuadraticMaxRootFinderr(trinomial3);

        assertEquals(2.0, finder3.getMaxRoot());
    }
}
