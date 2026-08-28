/*
Problem:
Sort an array that only contains 0s, 1s, and 2s (the Dutch national flag colors) in place.

Approach:
Use three pointers: low keeps 0s at the front, high keeps 2s at the back, and mid walks
through the array. If mid sees a 0, swap it to low and move both forward. If it sees a 2,
swap it to high and pull high back (mid stays to recheck the swapped value). A 1 just means
move mid forward.

Why this works:
The low and high boundaries grow to enclose the already-sorted 0s and 2s, so everything
left of low is 0 and everything right of high is 2. What remains in the middle is all 1s.

Time Complexity:
O(n) each element is touched a constant number of times.

Space Complexity:
O(1) done in place with swaps.
*/

class SortColors {
    public void sortColors(int[] nums) {
        int low = 0;
        int mid = 0;
        int high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                int temp = nums[low];
                nums[low] = nums[mid];
                nums[mid] = temp;
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                int temp = nums[mid];
                nums[mid] = nums[high];
                nums[high] = temp;
                high--;
            }
        }
    }
}
