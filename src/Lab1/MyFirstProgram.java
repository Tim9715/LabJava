package Lab1;

import Lab1.myfirstpackage.*;

public class MyFirstProgram {
    public static void main(String[] args) {
        MyFirstPackage o = new MyFirstPackage(0, 0);
        int i, j;
        for (i = 1; i <= 8; i++) {
            for (j = 1; j <= 8; j++) {
                o.setFirstNumber(i);
                o.setSecondNumber(j);
                System.out.print(o.performOperation());
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}