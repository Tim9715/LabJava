package Lab1.myfirstpackage;

public class MyFirstPackage {
    private int firstNumber;
    private int secondNumber;

    public MyFirstPackage(int var1, int var2) {
        this.firstNumber = var1;
        this.secondNumber = var2;
    }

    public int getFirstNumber() {
        return this.firstNumber;
    }

    public void setFirstNumber(int var1) {
        this.firstNumber = var1;
    }

    public int getSecondNumber() {
        return this.secondNumber;
    }

    public void setSecondNumber(int var1) {
        this.secondNumber = var1;
    }

    public int performOperation() {
        return this.firstNumber * this.secondNumber;
    }
}
