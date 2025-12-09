package org.example;

public class QuadraticTrinomiall {
    private final double a;
    private final double b;
    private final double c;

    public QuadraticTrinomiall(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public double[] solve() {
        if (getA() == 0) {
            if (getB() == 0) {
                if (getC() == 0) {
                    return new double[]{};
                } else {
                    return new double[]{};
                }
            } else {
                double x = -c / b;
                return new double[]{x};
            }
        }
        else {
            double discriminant = b * b - 4 * a * c;

            if (discriminant > 0) {
                double x1 = (-b + Math.sqrt(discriminant)) / (2 * a);
                double x2 = (-b - Math.sqrt(discriminant)) / (2 * a);

                if (x1 > x2) {
                    double temp = x1;
                    x1 = x2;
                    x2 = temp;
                }

                return new double[]{x1, x2};
            } else if (discriminant == 0) {
                double x = -b / (2 * a);
                return new double[]{x};
            } else {
                return new double[]{};
            }
        }
    }
}