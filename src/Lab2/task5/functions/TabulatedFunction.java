package Lab2.task5.functions;

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

    public int getPointsCount() {
        return points.length;
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

        if (index > 0 && point.getX() <= points[index - 1].getX() ||
            index < points.length - 1 && point.getX() >= points[index + 1].getX()) {
            throw new IllegalArgumentException("Нарушение порядка точек по x.");
        }

        points[index] = point;
    }

    public double getPointX(int index) {
        if (index < 0 || index >= points.length) {
            throw new IndexOutOfBoundsException("Некорректный индекс.");
        }
        return points[index].getX();
    }

    public void setPointX(int index, double x) {
        if (index < 0 || index >= points.length) {
            throw new IndexOutOfBoundsException("Некорректный индекс.");
        }

        if (index > 0 && x <= points[index - 1].getX() ||
            index < points.length - 1 && x >= points[index + 1].getX()) {
            throw new IllegalArgumentException("Нарушение порядка точек по x.");
        }

        points[index].setX(x);
    }

    public double getPointY(int index) {
        if (index < 0 || index >= points.length) {
            throw new IndexOutOfBoundsException("Некорректный индекс.");
        }
        return points[index].getY();
    }

    public void setPointY(int index, double y) {
        if (index < 0 || index >= points.length) {
            throw new IndexOutOfBoundsException("Некорректный индекс.");
        }
        points[index].setY(y);
    }

    public double getLeftDomainBorder() {
        return points[0].getX();
    }

    public double getRightDomainBorder() {
        return points[points.length - 1].getX();
    }

    public double getFunctionValue(double x) {
        if (x < getLeftDomainBorder() || x > getRightDomainBorder()) {
            return Double.NaN;
        }

        for (FunctionPoint point : points) {
            if (point.getX() == x) {
                return point.getY();
            }
        }

        for (int i = 0; i < points.length - 1; i++) {
            double x1 = points[i].getX();
            double y1 = points[i].getY();
            double x2 = points[i + 1].getX();
            double y2 = points[i + 1].getY();

            if (x > x1 && x < x2) {
                return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
            }
        }

        return Double.NaN;
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
