package basics;

import java.util.Scanner;

public class StartPattern {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();

        for(int i=1;i<=h;i++){
            for (int j=1;j<=i;j++){
                System.out.print('*');
            }
            System.out.println();

        }

    }
}
