package org.example;

public class SinusFunction implements SingleVariableFunction{
    private final double a;
    private final double b;
    private final double lowerBound;
    private final double upperBound;

    public SinusFunction(double a, double b, double lowerBound, double upperBound) {
        if (lowerBound > upperBound) {
            throw new IllegalArgumentException("Левая граница должна быть меньше правой");
        }
        this.a = a;
        this.b = b;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public double calculate(double x) {
        if (x < getLowerBound() || x > getUpperBound()) {
            throw new IllegalArgumentException("Аргумент вне отрезка");
        }
        return a * Math.sin(b * x);
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
