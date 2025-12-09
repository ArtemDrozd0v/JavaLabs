package org.example;

public class SumFunctional implements Functional<SingleVariableFunction>{

    @Override
    public double calculate(SingleVariableFunction function) {
        double a = function.getLowerBound();
        double b = function.getUpperBound();
        double mid = (a+b) / 2;

        return function.calculate(a) + function.calculate(b) + function.calculate(mid);
    }
}
