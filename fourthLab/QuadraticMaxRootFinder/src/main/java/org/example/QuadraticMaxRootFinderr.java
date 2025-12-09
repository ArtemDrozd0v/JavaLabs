package org.example;
import org.example.QuadraticTrinomiall;

public class QuadraticMaxRootFinderr {
    private final QuadraticTrinomiall trinomial;

    public QuadraticMaxRootFinderr(QuadraticTrinomiall trinomial) {
        if (trinomial == null) {
            throw new IllegalArgumentException("Трехчлен не может быть null");
        }
        this.trinomial = trinomial;
    }

    public QuadraticTrinomiall getTrinomial() {
        return trinomial;
    }

    public double getMaxRoot() {
        double[] roots = trinomial.solve();

        if (roots.length == 0) {
            throw new IllegalArgumentException("Уравнение не имеет действительных корней");
        }

        double maxRoot = roots[0];

        for (double root : roots) {
            if (root > maxRoot) {
                maxRoot = root;
            }
        }

        return maxRoot;
    }
}
