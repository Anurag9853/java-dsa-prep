package basics;

import org.w3c.dom.css.CSSStyleDeclaration;

import java.util.Scanner;

public class CelToFer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int temp = sc.nextInt();

        double ans = (9.0/5.0 * temp) + 32;

        System.out.println(ans);
    }
}
