package Lab2.task3.functions;

import Lab2.task2.functions.FunctionPoint;

import java.util.Arrays;

public class TabulatedFunction {
    private final FunctionPoint[] points;

    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        if (pointsCount < 2 || leftX >= rightX) {
            throw new IllegalArgumentException("Некорректные границы или количество точек.");
        }

        points = new FunctionPoint[pointsCount];
        double step = (rightX - leftX) / (pointsCount - 1);

        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + i * step;
            points[i] = new FunctionPoint(x, 0.0);
        }
    }

    public TabulatedFunction(double leftX, double rightX, double[] values) {
        int pointsCount = values.length;
        if (pointsCount < 2 || leftX >= rightX) {
            throw new IllegalArgumentException("Некорректные границы или количество точек.");
        }

        points = new FunctionPoint[pointsCount];
        double step = (rightX - leftX) / (pointsCount - 1);

        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + i * step;
            points[i] = new FunctionPoint(x, values[i]);
        }
    }

    public FunctionPoint[] getPoints() {
        return Arrays.copyOf(points, points.length);
    }

    public FunctionPoint getPoint(int index) {
        if (index < 0 || index >= points.length) {
            throw new IndexOutOfBoundsException("Некорректный индекс.");
        }
        return points[index];
    }

    public void setPoint(int index, FunctionPoint point) {
        if (index < 0 || index >= points.length) {
            throw new IndexOutOfBoundsException("Некорректный индекс.");
        }

        if ((index > 0 && point.getX() <= points[index - 1].getX()) ||
            (index < points.length - 1 && point.getX() >= points[index + 1].getX())) {
            throw new IllegalArgumentException("Нарушение порядка точек по x.");
        }

        points[index] = point;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TabulatedFunction:\n");
        for (FunctionPoint point : points) {
            sb.append(point.toString()).append("\n");
        }
        return sb.toString();
    }
}
