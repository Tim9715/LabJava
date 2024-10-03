package Lab2.task7;

import Lab2.task2.functions.FunctionPoint;
import Lab2.task6.functions.*;

public class Main {
    public static void main(String[] args) {
        double leftX = -2;
        double rightX = 2;
        int pointsCount = 5;
        double[] values = new double[] {4, 1, 0, 1, 4};
        TabulatedFunction function = new TabulatedFunction(leftX, rightX, values);

        System.out.println("Табулированные точки функции y = x^2:");
        System.out.println(function);
        System.out.println("Значения функции в точках:");
        double[] testPoints = {-3, -2, -1.5, 0, 1.5, 2, 3};
        for (double x : testPoints) {
            System.out.printf("f(%.1f) = %.2f%n", x, function.getFunctionValue(x));
        }
        System.out.println("\nИзменение ординаты точки с индексом 2 (точка (0; 0)) на (0; 2):");
        function.setPointY(2, 2);
        System.out.println(function);
        System.out.println("Добавление новой точки (-1.5, 2.25):");
        function.addPoint(new FunctionPoint(-1.5, 2.25));
        System.out.println(function);
        System.out.println("Удаление точки с индексом 1:");
        function.deletePoint(1);
        System.out.println(function);
        System.out.println("Значения функции в точках после изменений:");
        for (double x : testPoints) {
            System.out.printf("f(%.1f) = %.2f%n", x, function.getFunctionValue(x));
        }
    }
}
