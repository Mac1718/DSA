/*
Problem:
Given an array of numbers and a target, find the two indices whose values add up to the target.
You can assume there is exactly one such pair.

Approach:
We use a hash map to remember numbers we have already seen. As we walk through the
array, we check if the number we need (target - current) is already in the map.
If it is, we found our pair. If not, we store the current number with its index.

Why this works:
Instead of checking every possible pair (which is slow), we keep a lookup table of
seen numbers. That way each step is just a quick map check.

Time Complexity:
O(n) because we go through the array once.

Space Complexity:
O(n) for the hash map in the worst case.
*/

import java.util.HashMap;

class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i];
            if (seen.containsKey(need)) {
                return new int[] { seen.get(need), i };
            }
            seen.put(nums[i], i);
        }
        return new int[] {};
    }
}
