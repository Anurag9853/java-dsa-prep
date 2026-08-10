package basics;

import java.util.Scanner;

public class swap {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();

        int temp = a;
        a = b;
        b= temp;

        System.out.println("A"+a);
        System.out.println("B"+b);

    }
}
