public class Solution {

    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int start = prices[0];
        for (int i = 1; i < prices.length; i++) {
            int end = prices[i];
            if (end < start) {
                start = end;
            } else {
                int profit = end - start;
                if (profit > maxProfit) {
                    maxProfit = profit;
                }
            }
        }
        return maxProfit;
    }
}
