package DSA;


import java.util.Scanner;

public class MoveZeroes {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // WRITE YOUR MAIN LOGIC HERE
        int zero = 0;

        for(int i=0;i<n;i++){
            if(arr[i] != 0){
                int temp = arr[i];
                arr[i] = arr[zero];
                arr[zero] = temp;
                zero++;
            }
        }


        // Print the array
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
