/*
Problem:
Given an array of numbers, find all unique triplets (sets of 3 numbers) that add up to 0.

Approach:
First sort the array so we can use two pointers. For each number as the first of the
triplet, use two pointers (one at the start after it, one at the end) to find the other
two. If the sum is too small, move the left pointer right. If too big, move the right
pointer left. Skip duplicate values to avoid repeating the same triplet.

Why this works:
Sorting lets us use the two-pointer trick: because the array is ordered, moving a
pointer one way always changes the sum in a predictable direction. That makes it much
faster than checking every triple.

Time Complexity:
O(n^2) sorting is O(n log n) and the two-pointer scan is O(n^2).

Space Complexity:
O(1) extra space, not counting the output list.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }
}
