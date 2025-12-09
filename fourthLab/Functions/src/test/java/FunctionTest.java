import org.example.ExponentialFunction;
import org.example.LinearFunction;
import org.example.RationalFunction;
import org.example.SinusFunction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FunctionTest {

    @Test
    void testLinearFunction(){
        LinearFunction f = new LinearFunction(2, 3, 0, 5);

        assertEquals(0, f.getLowerBound());
        assertEquals(5, f.getUpperBound());

        assertEquals(3, f.calculate(0));
        assertEquals(7, f.calculate(2));
        assertEquals(13, f.calculate(5));

        assertThrows(IllegalArgumentException.class, () -> f.calculate(-1));

        assertThrows(IllegalArgumentException.class,
                () -> new LinearFunction(2, 3, 5, 0));
    }

    @Test
    void testSinusFunction(){
        SinusFunction f = new SinusFunction(2, 1, 0, 5);

        assertEquals(0, f.getLowerBound());
        assertEquals(5, f.getUpperBound());

        assertEquals(0, f.calculate(0));
        assertEquals(2, f.calculate(Math.PI/2));

        assertThrows(IllegalArgumentException.class, () -> f.calculate(-1));

        assertThrows(IllegalArgumentException.class,
                () -> new LinearFunction(2, 3, 5, 0));
    }

    @Test
    void testRationalFunction(){
        RationalFunction f = new RationalFunction(1, 2, 3, 4, 0, 5);

        assertEquals(0, f.getLowerBound());
        assertEquals(5, f.getUpperBound());

        assertEquals(0.5, f.calculate(0)); // x= 0 ( 1 * 0 + 2 )/( 3 *0 +4) = 2/4 = 0.5
        assertEquals(3.0/7.0, f.calculate(1)); // x=1 (1* 1 +2 )/(3 * 1 +4 ) = 3/7

        assertThrows(IllegalArgumentException.class, () -> f.calculate(-1));

        assertThrows(IllegalArgumentException.class,
                () -> new LinearFunction(2, 3, 5, 0));


        RationalFunction f2 = new RationalFunction(1, 2, 1, -2, 0, 5);

        assertThrows(ArithmeticException.class, () -> f2.calculate(2));
    }

    @Test
    void testExponentialFunction(){
        ExponentialFunction f = new ExponentialFunction(2, 3, 0, 5);

        assertEquals(0, f.getLowerBound());
        assertEquals(5, f.getUpperBound());

        assertEquals(5, f.calculate(0));
        assertEquals(2*Math.E + 3, f.calculate(1));

        assertThrows(IllegalArgumentException.class, () -> f.calculate(-1));

        assertThrows(IllegalArgumentException.class,
                () -> new LinearFunction(2, 3, 5, 0));
    }
}
