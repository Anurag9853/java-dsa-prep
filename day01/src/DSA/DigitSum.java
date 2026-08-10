package DSA;

import java.util.Scanner;

public class DigitSum {
    static int sumOfDigits(int n) {
        // code here
        int sum = 0;

        while(n>0){
            int rem = n%10;
            sum+=rem;
            n/=10;
        }

        return sum;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int ans = sumOfDigits(n);
        System.out.println(ans);
    }
}

