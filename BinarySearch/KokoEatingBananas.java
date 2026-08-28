/*
Problem:
Koko has a bunch of banana piles. She can choose an eating speed k (bananas
per hour). Each hour she eats k bananas from one pile, and any leftovers in
that pile are finished the next hour. She has h hours total. Find the
smallest k that lets her finish all piles in h hours or less.

Approach:
Notice that if she can finish at speed k, she can also finish at any larger
speed. So the valid speeds form a range, and we can binary search for the
smallest one. The search goes from 1 up to the largest pile (the slowest
she would ever need). For each candidate speed we count how many hours it
takes and compare with h.

Why this works:
Because faster speeds always take fewer or equal hours, the set of speeds
that work is a single contiguous range. Binary search finds the left edge
of that range, which is the minimum speed that is still fast enough.

Time Complexity:
O(n log m) where n is number of piles and m is the largest pile
Space Complexity:
O(1)
*/
class KokoEatingBananas {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = 1;

        // The biggest possible speed we might need is the largest pile.
        for (int i = 0; i < piles.length; i++) {
            if (piles[i] > right) {
                right = piles[i];
            }
        }

        int answer = right;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (canFinish(piles, mid, h)) {
                answer = mid;       // this speed works, try a smaller one
                right = mid - 1;
            } else {
                left = mid + 1;     // too slow, need a bigger speed
            }
        }

        return answer;
    }

    private boolean canFinish(int[] piles, int k, int h) {
        long hours = 0;
        for (int i = 0; i < piles.length; i++) {
            hours += (piles[i] + k - 1) / k; // ceil(pile / k)
        }
        return hours <= h;
    }
}
