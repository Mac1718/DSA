import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static long findOptimalResources(int[] arr, int k) {
        int n = arr.length;
        
        // Edge case: If k is greater than the array length, return -1
        if (k > n) {
            return -1;
        }
        
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        long maxSum = -1;
        long currentSum = 0;
        
        for (int i = 0; i < n; i++) {
            // Add the current element to the frequency map
            freqMap.put(arr[i], freqMap.getOrDefault(arr[i], 0) + 1);
            currentSum += arr[i];
            
            // If the window size exceeds k, remove the leftmost element
            if (i >= k) {
                int leftElement = arr[i - k];
                currentSum -= leftElement;
                freqMap.put(leftElement, freqMap.get(leftElement) - 1);
                if (freqMap.get(leftElement) == 0) {
                    freqMap.remove(leftElement);
                }
            }
            
            // If the current window size is exactly k and all elements are unique
            if (i >= k - 1 && freqMap.size() == k) {
                maxSum = Math.max(maxSum, currentSum);
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: Size of the array
        System.out.print("Enter the size of the array: ");
        int n = scanner.nextInt();

        // Input: Array elements
        int[] arr = new int[n];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        // Input: Size of the subarray
        System.out.print("Enter the size of the subarray (k): ");
        int k = scanner.nextInt();

        // Call the method and print the result
        long result = findOptimalResources(arr, k);
        System.out.println("The maximum sum of any subarray of length " + k + " with all unique elements is: " + result);
    }
}
