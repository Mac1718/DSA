/*
Problem:
Given daily stock prices, find the biggest profit you can make by buying on one day and
selling on a later day. If no profit is possible, return 0.

Approach:
Keep track of the lowest price seen so far as we walk through the array. Each day, compute
the profit if we sold at today's price after buying at that lowest price, and remember the
best profit we have found.

Why this works:
The best deal is buying at the cheapest point before selling at a high point. By always
using the running minimum as our buy price, we check every possible profitable sale in
one pass.

Time Complexity:
O(n) a single pass through the prices.

Space Complexity:
O(1) only a couple of variables.
*/

class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        int minPrice = prices[0];
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                int profit = prices[i] - minPrice;
                if (profit > maxProfit) {
                    maxProfit = profit;
                }
            }
        }
        return maxProfit;
    }
}
