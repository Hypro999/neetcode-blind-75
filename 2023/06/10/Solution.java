// It took me longer than I'd like to admit to get the formula right.
// Binary search was the easy part.
class Solution {
    public int maxValue(int n, int index, int maxSum) {
        int lower = 1;
        int upper = maxSum;

        int checkpoint = lower;
        int x = (lower + upper) / 2;
        while (lower <= upper) {
            long s = sum(n, index, x);
            if (s > maxSum) {
                upper = x - 1;
            } else if (s < maxSum) {
                checkpoint = x;
                lower = x + 1;
            } else {
                return x;
            }
            x = (lower + upper) / 2;
        }

        return checkpoint;
    }

    /**
     * Sum if arr[i] = x.
     */
    private long sum(int n, int i, int x) {
        long leftSum = halfSum(i, x);
        System.out.println(leftSum);
        long rightSum = halfSum(n - 1 - i, x);
        System.out.println(rightSum);
        return rightSum + leftSum - x;
    }

    // inclusive.
    private long halfSum(long i, long x) {
        if (x > i) {
            return (2 * x - i) * (i + 1) / 2;
        }
        return (x * (x + 1)) / 2 + (i - x + 1);
    }
}