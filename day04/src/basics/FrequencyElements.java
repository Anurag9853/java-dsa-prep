package basics;

public class FrequencyElements {

    static void countFrequency(int[] arr) {
        // YOUR CODE HERE

        int[] freq = new int[101];

        for(int x : arr){
            freq[x]++;
        }

        for(int i=0;i<freq.length;i++){
            if(freq[i] > 0){
                System.out.println(i + " " + freq[i] );
            }
        }

    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 2, 3, 1, 2, 4};

        countFrequency(arr);
    }
}