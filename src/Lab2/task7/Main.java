package Lab2.task6;

import Lab2.task2.functions.FunctionPoint;
import Lab2.task6.functions.TabulatedFunction;

public class Main {
    public static void main(String[] args) {
        // Создание функции с границами -2 и 2, содержащей 5 точек с нулевыми значениями
        TabulatedFunction func = new TabulatedFunction(-2, 2, 5);
        System.out.println("Исходная табулированная функция:");
        printPoints(func);

        // Создание функции с границами -2 и 2 и значениями для точек
        double[] values = {4, 1, 0, 1, 4};
        TabulatedFunction funcWithValues = new TabulatedFunction(-2, 2, values);
        System.out.println("\nФункция с заданными значениями:");
        printPoints(funcWithValues);

        // Добавление новой точки и вывод до и после
        System.out.println("\nДобавление новой точки (1.5, 2.5):");
        System.out.println("До добавления:");
        printPoints(funcWithValues);

        funcWithValues.addPoint(new FunctionPoint(1.5, 2.5));

        System.out.println("После добавления:");
        printPoints(funcWithValues);

        // Удаление точки и вывод до и после
        System.out.println("\nУдаление точки с индексом 2:");
        System.out.println("До удаления:");
        printPoints(funcWithValues);

        funcWithValues.deletePoint(2);

        System.out.println("После удаления:");
        printPoints(funcWithValues);

        // Изменение значений X и Y точки и вывод до и после
        System.out.println("\nИзменение значения X и Y точки с индексом 1:");
        System.out.println("До изменения:");
        printPoints(funcWithValues);

        try {
            funcWithValues.setPointX(1, -1);  // Попытка изменить x на корректное значение
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при установке x: " + e.getMessage());
        }

        funcWithValues.setPointY(1, 10);  // Корректное изменение значения y

        System.out.println("После изменения:");
        printPoints(funcWithValues);
    }

    // Вспомогательный метод для печати всех точек функции через getPoint()
    private static void printPoints(TabulatedFunction function) {
        for (int i = 0; i < function.getPointsCount(); i++) {
            System.out.println("Точка " + i + ": " + function.getPoint(i));
        }
    }
}
