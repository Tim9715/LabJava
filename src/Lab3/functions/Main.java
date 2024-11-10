package Lab3.functions;

public class Main {
    public static void main(String[] args) {
        // Проверка на основе ArrayTabulatedFunction
        TabulatedFunction function = new ArrayTabulatedFunction(-3, 3, new double[]{5, 4, 1, 0, 1, 4, 5});

        // Проверка на основе LinkedListTabulatedFunction (для замены закомментируйте предыдущую строку)
        // TabulatedFunction function = new LinkedListTabulatedFunction(-3, 3, new double[]{5, 4, 1, 0, 1, 4, 5});

        System.out.println("Табулированные точки функции:");
        System.out.println(function);

        // Проверка значений функции в различных точках
        double[] testPoints = {-3, -2, -1.5, 0, 1.5, 2, 3};
        System.out.println("Значения функции в точках:");
        for (double x : testPoints) {
            System.out.printf("f(%.1f) = %.2f%n", x, function.getFunctionValue(x));
        }

        // Проверка на изменение значения точки
        System.out.println("\nИзменение ординаты точки с индексом 2 на 2:");
        try {
            function.setPointY(2, 2);
            System.out.println(function);
        } catch (FunctionPointIndexOutOfBoundsException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        // Проверка на добавление новой точки
        System.out.println("Добавление новой точки (-1.5, 2.25):");
        try {
            ((ArrayTabulatedFunction) function).addPoint(new FunctionPoint(-1.5, 2.25));
            System.out.println(function);
        } catch (InappropriateFunctionPointException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        // Проверка на удаление точки
        System.out.println("Удаление точки с индексом 1:");
        try {
            function.deletePoint(1);
            System.out.println(function);
        } catch (FunctionPointIndexOutOfBoundsException | IllegalStateException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        // Проверка исключения при создании объекта с некорректными параметрами
        System.out.println("Проверка исключения при создании с некорректными параметрами:");
        try {
            TabulatedFunction invalidFunction = new ArrayTabulatedFunction(3, -3, new double[]{5, 4, 1});
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        // Проверка выхода за границы индекса
        System.out.println("Проверка исключения при обращении к несуществующему индексу:");
        try {
            function.getPoint(-1);
        } catch (FunctionPointIndexOutOfBoundsException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        // Проверка на добавление точки с существующим значением x
        System.out.println("Добавление точки с уже существующим значением x:");
        try {
            ((ArrayTabulatedFunction) function).addPoint(new FunctionPoint(0, 2));
        } catch (InappropriateFunctionPointException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        // Проверка значений функции после изменений
        System.out.println("Значения функции после изменений:");
        for (double x : testPoints) {
            System.out.printf("f(%.1f) = %.2f%n", x, function.getFunctionValue(x));
        }
    }
}
