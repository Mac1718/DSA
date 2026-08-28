/*
Problem:
Given heights of bars, compute how much rainwater can be trapped between them after it rains.

Approach:
For any bar, the water it can hold is limited by the tallest bar to its left and the
tallest bar to its right. The trapped water at that bar is the smaller of those two
limits minus the bar's own height. We precompute the max-from-left and max-from-right
for every position, then add up the water.

Why this works:
Water sits in the lowest spot bounded by higher walls on both sides. The smaller of the
two side maxima is the true water level at that point.

Time Complexity:
O(n) we scan the array a few times.

Space Complexity:
O(n) for the two helper arrays.
*/

class TrappingRainWater {
    public int trap(int[] height) {
        if (height.length == 0) {
            return 0;
        }

        int n = height.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int total = 0;
        for (int i = 0; i < n; i++) {
            int water = Math.min(leftMax[i], rightMax[i]) - height[i];
            if (water > 0) {
                total += water;
            }
        }
        return total;
    }
}
