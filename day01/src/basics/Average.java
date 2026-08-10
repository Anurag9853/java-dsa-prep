package basics;

import java.util.Scanner;

public class Average {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter count of number");
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Invalid");
            return;
        }

        int sum = 0;

        for(int i=0;i<n;i++){
            int num = sc.nextInt();
            sum += num;
        }

        double avg = (double)sum/n;

        System.out.println("sum of Numbers:" + sum);
        System.out.println("Average:" + avg);
    }
}
