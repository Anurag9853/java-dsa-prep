package basics;

import java.util.Scanner;

public class check_even_or_odd {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();

        String s = a%2==0 ? "Even" : "Odd";
        System.out.println(s);
    }
}
