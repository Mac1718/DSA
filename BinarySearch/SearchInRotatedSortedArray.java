/*
Problem:
We have a sorted array that has been rotated at some point (for example
[4,5,6,7,0,1,2]). Given a target value, find its index, or return -1.

Approach:
At every step we still compare the middle element with the target.
The tricky part is that the array is not fully sorted. So before deciding
which half to throw away, we first figure out which half is sorted.
If the left half is sorted, we check whether the target lies inside it.
If yes, search there; otherwise search the other half. If the right half
is sorted we do the same check on it.

Why this works:
Even though the whole array is rotated, one of the two halves around the
middle is always fully sorted. That sorted half lets us know for sure
whether the target can be there or not, so we always discard the half
that cannot contain it.

Time Complexity:
O(log n)
Space Complexity:
O(1)
*/
class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // Check which half is sorted.
            if (nums[left] <= nums[mid]) {
                // Left half is sorted.
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // Right half is sorted.
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }
}
