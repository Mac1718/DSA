/*
Problem:
You are given an array where each value is the height of a vertical line. Find two lines
that, together with the x-axis, form a container holding the most water.

Approach:
Use two pointers, one at the far left and one at the far right. The area is the distance
between them times the smaller height. After measuring, move the pointer at the smaller
height inward, because moving the taller one can never give a bigger area.

Why this works:
The width is largest at the start. We always drop the side that limits the height, so we
never miss a possible larger container. It is safe to skip those positions.

Time Complexity:
O(n) since each pointer moves at most n times.

Space Complexity:
O(1) just a few variables.
*/

class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max = 0;

        while (left < right) {
            int h = Math.min(height[left], height[right]);
            int width = right - left;
            int area = h * width;
            if (area > max) {
                max = area;
            }
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }
}
