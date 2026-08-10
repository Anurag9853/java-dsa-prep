package basics;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("Menu:");
            System.out.println("1. Addition (+)\n" +
                    "2. Subtraction (-)\n" +
                    "3. Multiplication (*)\n" +
                    "4. Division (/)\n" +
                    "5. Exit");


            System.out.println("Choose from the Menu");
            int choice = sc.nextInt();

            if (choice == 5) {
                System.out.println("Exiting calculator");
                break;
            }
            if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice");
                continue;
            }


            System.out.println("Enter 1st Num:");
            int num1 = sc.nextInt();
            System.out.println("Enter 2nd Num:");
            int num2 = sc.nextInt();

            if (choice == 4 && num2 == 0) {
                System.out.println("Invalid divisor");
                return;

            }


            int cal = switch (choice) {

                case 1 -> num1 + num2;
                case 2 -> num1 - num2;
                case 3 -> num1 * num2;
                case 4 -> num1 / num2;
                default -> -1;


            };
            System.out.println("result: " + cal);

        }
    }
}
