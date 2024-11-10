package Lab4.functions;

public class InappropriateFunctionPointException extends Exception {
    public InappropriateFunctionPointException() {
        super("Попытка добавления или изменения точки функции выполнена некорректно.");
    }

    public InappropriateFunctionPointException(String message) {
        super(message);
    }
}
