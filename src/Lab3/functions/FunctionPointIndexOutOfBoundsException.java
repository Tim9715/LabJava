package Lab3.functions;

public class FunctionPointIndexOutOfBoundsException extends IndexOutOfBoundsException {
    public FunctionPointIndexOutOfBoundsException() {
        super("Индекс точки находится за пределами допустимого диапазона.");
    }

    public FunctionPointIndexOutOfBoundsException(String message) {
        super(message);
    }
}