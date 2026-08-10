package DSA;

import java.util.Scanner;

class Solution {
    static int reverseDigits(int n) {

        int reverse = 0;

        while(n>0){
            int rem = n%10;
            reverse = reverse * 10 + rem;
            n/=10;
        }

        return reverse;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int ans = reverseDigits(n);
        System.out.println(ans);

    }
}
