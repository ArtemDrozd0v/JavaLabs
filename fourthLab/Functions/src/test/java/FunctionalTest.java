import org.example.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FunctionalTest {
    @Test
    void testSumFunctionalLinear() {
        SingleVariableFunction linearFunction = new LinearFunction(2, 1, 0, 4);
        SingleVariableFunction linearFunction2 = new LinearFunction(0, 5, 0, 10);
        Functional<SingleVariableFunction> sumFunctional = new SumFunctional();

        assertEquals(15, sumFunctional.calculate(linearFunction)); // f(0)=1 f(2)=5 f(4)=9
        assertEquals(15, sumFunctional.calculate(linearFunction2)); // f(0)=5, f(5)=5, f(10)=5
    }

    @Test
    void testSumFunctionalSin() {
        SingleVariableFunction sinFunction = new SinusFunction(1, 1, 0, Math.PI);
        Functional<SingleVariableFunction> sumFunctional = new SumFunctional();

        // f(0)=0, f(pi/2)=1, f(pi)=0
        assertEquals(1, sumFunctional.calculate(sinFunction), 0.001);
    }

    @Test
    void testSumFunctionalWithRational() {
        SingleVariableFunction rationalFunction = new RationalFunction(1, 1, 1, 2, 0, 2);
        Functional<SingleVariableFunction> sumFunctional = new SumFunctional();

        double a = rationalFunction.calculate(0);   // (0+1)/(0+2) = 0.5
        double mid = rationalFunction.calculate(1); // (1+1)/(1+2) = 2/3
        double b = rationalFunction.calculate(2);   // (2+1)/(2+2)= 0.75

        assertEquals(a + mid + b, sumFunctional.calculate(rationalFunction));
    }

    @Test
    void testSumFunctionalWithExponential() {
        SingleVariableFunction expFunction = new ExponentialFunction(1, 0, 0, 2);
        Functional<SingleVariableFunction> sumFunctional = new SumFunctional();

        double expected = Math.exp(0) + Math.exp(1) + Math.exp(2);
        assertEquals(expected, sumFunctional.calculate(expFunction));
    }

    @Test
    void testIntegralFunctional() {
        assertThrows(IllegalArgumentException.class,
                () -> new IntegralFunctional(1, 0));

    }

    @Test
    void testIntegralFunctionalDomain() {
        SingleVariableFunction linearFunction = new LinearFunction(1, 0, 0, 0.5);
        Functional<SingleVariableFunction> integralFunctional = new IntegralFunctional(0, 1);

        assertThrows(IllegalArgumentException.class,
                () -> integralFunctional.calculate(linearFunction));

    }

    @Test
    void testIntegralFunctionalLinear() {
        // 2x+1 -> x^2-x |1 0 = 1 + 1 - 0 -0= 2
        SingleVariableFunction linearFunction = new LinearFunction(2, 1, 0, 1);
        Functional<SingleVariableFunction> integralFunctional = new IntegralFunctional(0, 1);

        assertEquals(2, integralFunctional.calculate(linearFunction), 0.001);
    }

    @Test
    void testIntegralFunctionalCalculateSin() {
        // sinx -> -cosx | pi 0 = (-cos(pi)) - (-cos(0)) = 1 +1 =2
        SingleVariableFunction sinFunction = new SinusFunction(1, 1, 0, Math.PI);
        Functional<SingleVariableFunction> integralFunctional = new IntegralFunctional(0, Math.PI);

        assertEquals(2, integralFunctional.calculate(sinFunction), 0.001);
    }

    @Test
    void testIntegralFunctionalCalculateRational() {
        // 1 /(x+1)-> ln(x+1) | 1 0 = ln2 - ln1 = ln2
        SingleVariableFunction rationalFunction = new RationalFunction(0, 1, 1, 1, 0, 1);
        Functional<SingleVariableFunction> integralFunctional = new IntegralFunctional(0, 1);

        assertEquals(Math.log(2), integralFunctional.calculate(rationalFunction), 0.001);
    }

    @Test
    void testIntegralFunctionalCalculateExponential() {
        // e^x -> e^x |1 0 = e^1 - e^0 = e-1
        SingleVariableFunction expFunction = new ExponentialFunction(1, 0, 0, 1);
        Functional<SingleVariableFunction> integralFunctional = new IntegralFunctional(0, 1);

        assertEquals(Math.E - 1, integralFunctional.calculate(expFunction), 0.001);
    }

}
