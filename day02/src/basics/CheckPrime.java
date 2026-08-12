package basics;

import java.util.Scanner;

public class CheckPrime {
    static boolean isPrime(int n){

        if(n<2) return false;

        for(int i=2;i*i<=n;i++){
            if(n%i==0) return false;
        }

        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        if(isPrime(n)) System.out.println("Prime number");
        else {
            System.out.println("Not prime");
        }
    }
}
