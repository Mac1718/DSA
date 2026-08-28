/*
Problem:
Given a sorted array of numbers and a target value, find the index of the target.
If the target is not present, return -1. The array is already sorted so we can
use binary search to find it quickly.

Approach:
Keep two pointers, left and right, marking the current search window.
Look at the middle element. If it equals the target, we are done.
If it is smaller than the target, the answer must be in the right half.
If it is larger, the answer must be in the left half.
Repeat until the window is empty.

Why this works:
Because the array is sorted, every comparison lets us throw away half of the
remaining elements. That is why we can find the answer in logarithmic time.

Time Complexity:
O(log n)
Space Complexity:
O(1)
*/
class BinarySearch {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}
