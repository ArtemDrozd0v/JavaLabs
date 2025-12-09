package org.example;

public class RationalFunction implements SingleVariableFunction{
    private final double a;
    private final double b;
    private final double c;
    private final double d;
    private final double lowerBound;
    private final double upperBound;

    public RationalFunction(double a, double b, double c, double d, double lowerBound, double upperBound) {
        if (lowerBound > upperBound) {
            throw new IllegalArgumentException("Левая граница должна быть меньше правой");
        }
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public double calculate(double x) {
        if (x < getLowerBound() || x > getUpperBound()) {
            throw new IllegalArgumentException("Аргумент вне отрезка");
        }
        if (c * x + d == 0) {
            throw new ArithmeticException("Деление на 0");
        }
        return (a * x + b) / (c * x + d);
    }

    @Override
    public double getLowerBound() {
        return lowerBound;
    }

    @Override
    public double getUpperBound() {
        return upperBound;
    }
}
