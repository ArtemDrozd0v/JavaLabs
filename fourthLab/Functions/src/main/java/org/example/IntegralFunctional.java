package org.example;

public class IntegralFunctional implements Functional<SingleVariableFunction>{
    private final double integralA;
    private final double integralB;

    public IntegralFunctional(double integralA, double integralB){
        if (integralA >= integralB) {
            throw new IllegalArgumentException("Нижний предел интегрирования должен быть меньше верхнего");
        }
        this.integralA = integralA;
        this.integralB = integralB;
    }

    @Override
    public double calculate(SingleVariableFunction function) {
        if (integralA < function.getLowerBound() || integralB > function.getUpperBound()) {
            throw new IllegalArgumentException("Неверный промежуток интегрирования");
        }

        double partitions = 10000;
        double width = (integralB - integralA) / partitions;
        double sum = 0.0;

        for (int i = 0; i < partitions; i++) {
            double x = integralA + i * width + width/2;
            sum += function.calculate(x) * width;
        }

        return sum;
    }
}
