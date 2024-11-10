package Lab3.functions;

public class LinkedListTabulatedFunction {
    private static class FunctionNode {
        private FunctionPoint data;
        private FunctionNode next;
        private FunctionNode prev;

        public FunctionNode(FunctionPoint data) {
            this.data = data;
        }
    }

    private FunctionNode head;
    private int size;
    private FunctionNode lastAccessedNode;
    private int lastAccessedIndex;

    // Конструктор по количеству точек
    public LinkedListTabulatedFunction(double leftX, double rightX, int pointsCount) {
        if (leftX >= rightX) {
            throw new IllegalArgumentException("Левая граница больше или равна правой.");
        }
        if (pointsCount < 2) {
            throw new IllegalArgumentException("Количество точек меньше двух.");
        }

        head = new FunctionNode(null);
        head.next = head;
        head.prev = head;
        size = 0;

        double step = (rightX - leftX) / (pointsCount - 1);
        for (int i = 0; i < pointsCount; i++) {
            addNodeToTail(new FunctionPoint(leftX + i * step, 0.0));
        }
    }

    // Конструктор по массиву значений
    public LinkedListTabulatedFunction(double leftX, double rightX, double[] values) {
        if (leftX >= rightX) {
            throw new IllegalArgumentException("Левая граница больше или равна правой.");
        }
        if (values.length < 2) {
            throw new IllegalArgumentException("Количество точек меньше двух.");
        }

        head = new FunctionNode(null);
        head.next = head;
        head.prev = head;
        size = 0;

        double step = (rightX - leftX) / (values.length - 1);
        for (int i = 0; i < values.length; i++) {
            addNodeToTail(new FunctionPoint(leftX + i * step, values[i]));
        }
    }

    // Методы для работы с точками

    public int getPointsCount() {
        return size;
    }

    public FunctionPoint getPoint(int index) {
        return getNodeByIndex(index).data;
    }

    public void setPoint(int index, FunctionPoint point) throws InappropriateFunctionPointException {
        FunctionNode node = getNodeByIndex(index);
        if (index > 0 && point.getX() <= getNodeByIndex(index - 1).data.getX()) {
            throw new InappropriateFunctionPointException("x должен быть больше, чем у предыдущей точки.");
        }
        if (index < size - 1 && point.getX() >= getNodeByIndex(index + 1).data.getX()) {
            throw new InappropriateFunctionPointException("x должен быть меньше, чем у следующей точки.");
        }
        node.data = point;
    }

    public double getPointX(int index) {
        return getNodeByIndex(index).data.getX();
    }

    public void setPointX(int index, double x) throws InappropriateFunctionPointException {
        FunctionNode node = getNodeByIndex(index);
        if (index > 0 && x <= getNodeByIndex(index - 1).data.getX()) {
            throw new InappropriateFunctionPointException("Новый x меньше x предыдущей точки.");
        }
        if (index < size - 1 && x >= getNodeByIndex(index + 1).data.getX()) {
            throw new InappropriateFunctionPointException("Новый x больше x следующей точки.");
        }
        node.data.setX(x);
    }

    public double getPointY(int index) {
        return getNodeByIndex(index).data.getY();
    }

    public void setPointY(int index, double y) {
        getNodeByIndex(index).data.setY(y);
    }

    public void deletePoint(int index) {
        if (size < 3) {
            throw new IllegalStateException("Список не может содержать менее трех точек.");
        }
        deleteNodeByIndex(index);
    }

    public double getLeftDomainBorder() {
        return head.next.data.getX();
    }

    public double getRightDomainBorder() {
        return head.prev.data.getX();
    }

    public double getFunctionValue(double x) {
        if (x < getLeftDomainBorder() || x > getRightDomainBorder()) {
            return Double.NaN;
        }

        FunctionNode current = head.next;
        while (current != head) {
            if (current.data.getX() == x) {
                return current.data.getY();
            }
            if (current.next != head && current.next.data.getX() > x) {
                FunctionPoint p1 = current.data;
                FunctionPoint p2 = current.next.data;
                return p1.getY() + (p2.getY() - p1.getY()) * (x - p1.getX()) / (p2.getX() - p1.getX());
            }
            current = current.next;
        }
        return Double.NaN;
    }

    // Вспомогательные методы для работы со списком

    private FunctionNode getNodeByIndex(int index) {
        if (index < 0 || index >= size) {
            throw new FunctionPointIndexOutOfBoundsException("Индекс вне границ: " + index);
        }

        if (lastAccessedNode != null && lastAccessedIndex <= index) {
            FunctionNode current = lastAccessedNode;
            for (int i = lastAccessedIndex; i < index; i++) {
                current = current.next;
            }
            lastAccessedNode = current;
            lastAccessedIndex = index;
            return current;
        } else {
            FunctionNode current = head.next;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            lastAccessedNode = current;
            lastAccessedIndex = index;
            return current;
        }
    }

    private void addNodeToTail(FunctionPoint point) {
        FunctionNode newNode = new FunctionNode(point);
        newNode.prev = head.prev;
        newNode.next = head;
        head.prev.next = newNode;
        head.prev = newNode;
        size++;
    }

    private void deleteNodeByIndex(int index) {
        FunctionNode nodeToDelete = getNodeByIndex(index);
        nodeToDelete.prev.next = nodeToDelete.next;
        nodeToDelete.next.prev = nodeToDelete.prev;
        size--;
    }
}
