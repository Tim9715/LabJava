package Lab4.functions;

public interface TabulatedFunction {
    int getPointsCount();
    FunctionPoint getPoint(int index);
    void setPoint(int index, FunctionPoint point);
    double getPointX(int index);
    void setPointX(int index, double x) throws InappropriateFunctionPointException;
    double getPointY(int index);
    void setPointY(int index, double y);
    void deletePoint(int index);
    double getLeftDomainBorder();
    double getRightDomainBorder();
    double getFunctionValue(double x);
}