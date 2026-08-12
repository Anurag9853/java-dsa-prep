package basics;

import java.util.Scanner;

import static java.lang.Math.pow;

public class ArmstrongNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int count = 0;
        int temp = n;
        while(temp > 0){
            count++;
            temp /= 10;
        }
        int temp1 = n;
        int NewNum = 0;

        while(temp1>0){
            int rem = temp1%10;
            NewNum = (int) (NewNum + pow(rem, count));
            temp1 /= 10;

        }

        if(n==NewNum) System.out.println("Armstrong");
        else {
            System.out.println("Not Armstrong");
        }

    }
}
