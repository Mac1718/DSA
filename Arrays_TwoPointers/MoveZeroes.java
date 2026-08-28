/*
Problem:
Move all the zeroes in an array to the end, keeping the order of the other numbers the same.
Do it in place.

Approach:
Walk through the array with one pointer that tracks where the next non-zero should go.
Whenever we see a non-zero, we place it at that position and move the pointer forward.
After going through everything, fill the remaining positions from that pointer to the end
with zeroes.

Why this works:
The "write" pointer only ever points at already-handled spots, so we never overwrite a
number we still need. All non-zeroes end up packed at the front in order.

Time Complexity:
O(n) a single pass plus a small fill at the end.

Space Complexity:
O(1) we modify the array in place.
*/

class MoveZeroes {
    public void moveZeroes(int[] nums) {
        int write = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[write] = nums[i];
                write++;
            }
        }
        for (int i = write; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}
