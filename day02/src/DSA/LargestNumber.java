package DSA;

import java.util.*;

public class LargestNumber {
    public static int large(int[] arr) {
        int largest = arr[0];
        for(int i=1;i<arr.length;i++){
            if(arr[i] > largest) largest = arr[i];
        }

        return largest;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();

        int[] arr = new int[size];

        for(int i=0;i<size;i++){
            arr[i] = sc.nextInt();
        }

        int ans = large(arr);
        System.out.println(ans);

        

    }
}
