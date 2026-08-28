/*
Problem:
A sorted array has been rotated, and we want to find the smallest element
in it. For example, in [3,4,5,1,2] the minimum is 1.

Approach:
We use binary search. At each step, look at the middle element and the
rightmost element. If the middle is bigger than the right edge, the
rotation "drop" happened to the right of mid, so the minimum is in the
right half and we move left past mid. Otherwise the drop is at or to the
left of mid, so we move right to mid. The loop ends when left meets right,
which is the minimum.

Why this works:
The smallest element is the one right after the array "drops" from a high
value to a low value. Comparing mid with the right edge tells us whether
that drop happens in the left half or the right half, so we keep narrowing
down to the side that must contain the minimum.

Time Complexity:
O(log n)
Space Complexity:
O(1)
*/
class FindMinimumInRotatedSortedArray {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                // Minimum is in the right half.
                left = mid + 1;
            } else {
                // Minimum is in the left half (or at mid).
                right = mid;
            }
        }

        return nums[left];
    }
}
