/*
Problem:
Given an array, return a new array where each position holds the product of all the other
numbers. Do it without using division.

Approach:
Build the answer in two passes. First pass goes left to right and stores, for each spot,
the product of everything to its left. Second pass goes right to left and multiplies in
the product of everything to the right. Together, each spot ends up with the product of
every other number.

Why this works:
A number's prefix (left product) times its suffix (right product) equals the product of
all numbers except itself. Keeping a running product avoids building full sub-arrays.

Time Complexity:
O(n) two linear passes.

Space Complexity:
O(1) extra, ignoring the output array.
*/

class ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];

        answer[0] = 1;
        for (int i = 1; i < n; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];
        }

        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            answer[i] = answer[i] * right;
            right = right * nums[i];
        }
        return answer;
    }
}
