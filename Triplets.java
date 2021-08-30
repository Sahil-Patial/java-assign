import java.util.*;

class Triplets {

    static int countTriplets(int arr[], int n) {
        int[] freq = new int[100];

        for (int i = 0; i < n; i++) {
            freq[arr[i]]++;
        }
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (freq[arr[i] + arr[j]] > 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int n;
        Scanner s = new Scanner(System.in);
        System.out.println("Number of elements:");
        n = s.nextInt();

        int arr[] = new int[n];
        System.out.println("Enter elements:");

        for (int i = 0; i < n; i++) {
            arr[i] = s.nextInt();
        }

        System.out.print("Number of triplets are: " + countTriplets(arr, n));
    }
}
