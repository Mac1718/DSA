/*
Problem:
Given a sorted array and a target, return the starting and ending index of
the target (both inclusive). If the target is not found, return [-1, -1].
For example, in [5,7,7,8,8,10] with target 8, the answer is [3, 4].

Approach:
We do two binary searches. The first search finds the leftmost occurrence:
when we find the target, instead of stopping we keep searching to the left
to see if there is an earlier one. The second search finds the rightmost
occurrence by keeping to the right when we hit the target.

Why this works:
Normal binary search can land anywhere on a run of equal values. By always
moving left when we find a match (for the first index) and always moving
right (for the last index), we push the search toward the boundary of the
matching range, which gives us the exact first and last positions.

Time Complexity:
O(log n)
Space Complexity:
O(1)
*/
class FindFirstAndLastPosition {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};

        result[0] = findFirst(nums, target);
        result[1] = findLast(nums, target);

        return result;
    }

    private int findFirst(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int index = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                index = mid;
                right = mid - 1; // keep looking on the left
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return index;
    }

    private int findLast(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int index = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                index = mid;
                left = mid + 1; // keep looking on the right
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return index;
    }
}
