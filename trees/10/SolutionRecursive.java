public class SolutionRecursive {

    private class Result {

        int bestPathSum;

        int bestExtendablePathSum;

        Result(int bestPathSum, int bestExtendablePathSum) {
            this.bestPathSum = bestPathSum;
            this.bestExtendablePathSum = bestExtendablePathSum;
        }
    }

    public int maxPathSum(TreeNode root) {
        return maxPathSumRecursive(root).bestPathSum;
    }

    private Result maxPathSumRecursive(TreeNode root) {
        Result leftResult = root.left == null ? null : maxPathSumRecursive(root.left);
        Result rightResult = root.right == null ? null : maxPathSumRecursive(root.right);
        int bestExtendablePathSum = max(
                        root.val,
                        leftResult == null
                            ? Integer.MIN_VALUE
                            : root.val + leftResult.bestExtendablePathSum,
                        rightResult == null
                            ? Integer.MIN_VALUE
                            : root.val + rightResult.bestExtendablePathSum
                );
        int bestPathSum = max(
                        bestExtendablePathSum,
                        root.val
                                + (leftResult == null
                                    ? 0
                                    : leftResult.bestExtendablePathSum)
                                + (rightResult == null
                                    ? 0
                                    : rightResult.bestExtendablePathSum),
                        leftResult == null
                            ? Integer.MIN_VALUE
                            : leftResult.bestPathSum,
                        rightResult == null
                            ? Integer.MIN_VALUE
                            : rightResult.bestPathSum
                );
        return new Result(bestPathSum, bestExtendablePathSum);
    }

    private int max(int ...vals) {
        int maxVal = vals[0];
        for (int i = 0; i < vals.length; i++) {
            if (vals[i] > maxVal) {
                maxVal = vals[i];
            }
        }
        return maxVal;
    }
}