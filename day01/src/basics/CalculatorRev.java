package basics;

import java.util.Scanner;

public class CalculatorRev {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();

        int ch = sc.next().charAt(0);

        switch (ch){

            case '+' :
                System.out.println(a+b);
                break;

            case '-' :
                System.out.println(a-b);
                break;

            case '*' :
                System.out.println(a*b);
                break;

            case '/' :
                if(b!=0){
                    System.out.println(a/b);
                }else {
                    System.out.println("Divide by zero exception");

                }
                break;

            default:
                System.out.println("Invalid choice");
        }
    }
}
