package Lab2.functions;

import java.util.Arrays;

public class TabulatedFunction {
    private FunctionPoint[] points;
    private int pointCount;

    public TabulatedFunction(double leftX, double rightX, int pointsCount) {

        points = new FunctionPoint[pointsCount];
        pointCount = pointsCount;
        double step = (rightX - leftX) / (pointsCount - 1);

        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + i * step;
            points[i] = new FunctionPoint(x, 0.0);
        }
    }

    public TabulatedFunction(double leftX, double rightX, double[] values) {
        int pointsCount = values.length;

        points = new FunctionPoint[pointsCount];
        pointCount = pointsCount;
        double step = (rightX - leftX) / (pointsCount - 1);

        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + i * step;
            points[i] = new FunctionPoint(x, values[i]);
        }
    }

    public int getPointsCount() {
        return pointCount;
    }

    public FunctionPoint getPoint(int index) {
        return points[index];
    }

    public void deletePoint(int index) {
        System.arraycopy(points, index + 1, points, index, pointCount - index - 1);
        points[--pointCount] = null;
    }

    public void addPoint(FunctionPoint point) {
        if (pointCount == points.length) {
            points = Arrays.copyOf(points, points.length * 2);
        }

        int insertIndex = 0;
        while (insertIndex < pointCount && points[insertIndex].getX() < point.getX()) {
            insertIndex++;
        }

        System.arraycopy(points, insertIndex, points, insertIndex + 1, pointCount - insertIndex);
        points[insertIndex] = point;
        pointCount++;
    }

    public double getPointX(int index) {
        return points[index].getX();
    }

    public void setPointX(int index, double x) {
        points[index].setX(x);
    }

    public double getPointY(int index) {
        return points[index].getY();
    }

    public void setPointY(int index, double y) {
        points[index].setY(y);
    }

    public double getLeftDomainBorder() {
        return points[0].getX();
    }

    public double getRightDomainBorder() {
        return points[pointCount - 1].getX();
    }


    public double getFunctionValue(double x) {
        if (x < getLeftDomainBorder() || x > getRightDomainBorder()) {
            return Double.NaN;
        }

        for (FunctionPoint point : points) {
            if (point != null && point.getX() == x) {
                return point.getY();
            }
        }

        for (int i = 0; i < pointCount - 1; i++) {
            FunctionPoint p1 = points[i];
            FunctionPoint p2 = points[i + 1];

            if (p1 != null && p2 != null) {
                double x1 = p1.getX();
                double y1 = p1.getY();
                double x2 = p2.getX();
                double y2 = p2.getY();

                if (x > x1 && x < x2) {
                    return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
                }
            }
        }
        return Double.NaN;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TabulatedFunction:\n");
        for (int i = 0; i < pointCount; i++) {
            sb.append(points[i].toString()).append("\n");
        }
        return sb.toString();
    }
}
