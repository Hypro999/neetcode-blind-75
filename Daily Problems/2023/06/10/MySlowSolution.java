/**
 * This solution results in a TLE for the following test case:
 * 4, 3, 782939544
 * Apparently O(maxSum) isn't a good enough solution.
 */
public class MySlowSolution {
    public int maxValue(int n, int index, int maxSum) {
        int currentSum = n;
        int promotionsPerformed = 0;
        while (true) {
            if (currentSum > maxSum) {
                break;
            }
            // Promote arr[i]:
            currentSum += 1;
            promotionsPerformed += 1;
            if (currentSum > maxSum) {
                // Undo.
                currentSum -= 1;
                promotionsPerformed -= 1;
                break;
            }
            // Promote dependencies for promoting arr[i] again:
            currentSum = currentSum + Math.min(promotionsPerformed, index) + Math.min(promotionsPerformed, n - 1 - index);
        }
        return promotionsPerformed + 1; // arr[i] = baseValue + promotionsPerformed. Where baseValue = 1.
    }
}