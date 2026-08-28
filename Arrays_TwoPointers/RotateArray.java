/*
Problem:
Rotate an array to the right by k steps. For example, [1,2,3,4,5] rotated by 2 becomes
[4,5,1,2,3]. Do it in place.

Approach:
First handle k bigger than the array length by taking k % n. The trick is to reverse
three parts: reverse the whole array, then reverse the first k elements, then reverse the
rest. The reversals shuffle everything into the rotated position.

Why this works:
Reversing the full array puts the last k items at the front but backwards, and the rest at
the back backwards. Reversing each part fixes their order, giving the final rotation.

Time Complexity:
O(n) we reverse the array a constant number of times.

Space Complexity:
O(1) done in place.
*/

class RotateArray {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) {
            return;
        }
        k = k % n;

        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
