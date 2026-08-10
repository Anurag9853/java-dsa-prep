package basics;

import java.util.Scanner;

public class Swap_without_variable {
    public static void main(String[] args) {

        int a = 10;
        int b= 11;

        a = a+b;
        b= a-b;
        a=a-b;

        System.out.println("a:" + a);
        System.out.println("b:" + b);
    }
}
