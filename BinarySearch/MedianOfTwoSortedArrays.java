/*
Problem:
Given two sorted arrays nums1 and nums2, return the median of the two arrays
combined. The median is the middle value (or the average of the two middle
values) when everything is sorted together. We want to do it efficiently,
without actually merging the arrays.

Approach (the binary-search partition idea):
The key observation is that the median splits the combined data into two
equal halves: the left half (smaller numbers) and the right half (larger
numbers). If we can cut the data so that the left half and right half have
the same total size, the median is determined by the largest number on the
left and the smallest number on the right.

We always do the binary search on the smaller array, because it keeps the
search fast and avoids edge cases. Suppose we pick a "cut" in the smaller
array at some index. That cut automatically decides the cut in the larger
array, because the two left parts together must hold exactly half of all
elements. So:
  - leftPart = elements of small array before its cut
  - rightPart = elements of small array from its cut onward
  - the cut in the big array is chosen so both left sides add up to the
    correct total size.
  - let L1 = biggest on small's left, R1 = smallest on small's right
  - let L2 = biggest on big's left,   R2 = smallest on big's right

We then compare:
  - If L1 <= R2 and L2 <= R1, the cut is correct and we have the answer.
    For an odd total length the median is max(L1, L2). For an even total
    length it is (max(L1, L2) + min(R1, R2)) / 2.0.
  - If L1 > R2, our cut in the small array is too far to the right, so we
    move right bound down.
  - Otherwise L2 > R1, the cut is too far left, so we move left bound up.

Why this works:
By moving the cut based on the comparisons, we are shrinking the search
space toward the exact point where every value on the left is smaller than
every value on the right. At that point the partition is correct and the
median falls right between the two sides. Since we binary search the
smaller array, we only spend O(log(min(n, m))) steps.

Time Complexity:
O(log(min(n, m)))
Space Complexity:
O(1)
*/
class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Make sure nums1 is the smaller array.
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int n = nums1.length;
        int m = nums2.length;
        int totalLeft = (n + m + 1) / 2; // size of the left half

        int left = 0;
        int right = n;

        while (left <= right) {
            int cut1 = (left + right) / 2;     // cut in the smaller array
            int cut2 = totalLeft - cut1;       // cut in the larger array

            // Values on each side. Use sentinels for out-of-bounds.
            int L1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1 - 1];
            int R1 = (cut1 == n) ? Integer.MAX_VALUE : nums1[cut1];
            int L2 = (cut2 == 0) ? Integer.MIN_VALUE : nums2[cut2 - 1];
            int R2 = (cut2 == m) ? Integer.MAX_VALUE : nums2[cut2];

            if (L1 <= R2 && L2 <= R1) {
                // Correct partition found.
                if ((n + m) % 2 == 0) {
                    return (Math.max(L1, L2) + Math.min(R1, R2)) / 2.0;
                } else {
                    return (double) Math.max(L1, L2);
                }
            } else if (L1 > R2) {
                // Small array cut is too far right.
                right = cut1 - 1;
            } else {
                // Small array cut is too far left.
                left = cut1 + 1;
            }
        }

        return 0.0; // should never reach here with valid inputs
    }
}
