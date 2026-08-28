/*
Problem:
You are given a list of intervals [start, end]. Merge any intervals that overlap or touch
each other, and return the combined list.

Approach:
Sort the intervals by their start time. Then walk through them, keeping the current merged
interval. If the next interval starts at or before the end of the current one, they
overlap, so extend the end. Otherwise, the current one is finished and we start a new one.

Why this works:
After sorting by start, any interval that could merge with the current one must come right
after it. So a single pass is enough to combine everything correctly.

Time Complexity:
O(n log n) because of the sort; the merge pass is O(n).

Space Complexity:
O(n) for the output in the worst case.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new StartComparator());

        List<int[]> result = new ArrayList<>();
        int[] current = intervals[0];
        result.add(current);

        for (int i = 1; i < intervals.length; i++) {
            int[] next = intervals[i];
            if (next[0] <= current[1]) {
                current[1] = Math.max(current[1], next[1]);
            } else {
                current = next;
                result.add(current);
            }
        }

        return result.toArray(new int[result.size()][]);
    }
}

class StartComparator implements Comparator<int[]> {
    public int compare(int[] a, int[] b) {
        if (a[0] < b[0]) {
            return -1;
        }
        if (a[0] > b[0]) {
            return 1;
        }
        return 0;
    }
}
