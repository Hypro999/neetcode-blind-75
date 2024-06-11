import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public int maxPathSum(TreeNode root) {
        int bestPathSumOverall = Integer.MIN_VALUE;
        Map<TreeNode, Integer> bestExtendablePathSums = new HashMap<>();

        // Prep a post-order traversal into orderedStack.
        // When popping from orderedStack, we will be doing a REVERSE post-order traversal.
        Stack<TreeNode> traversalStack = new Stack<>();
        Stack<TreeNode> orderedStack = new Stack<>();
        traversalStack.push(root);
        while (!traversalStack.isEmpty()) {
            TreeNode top = traversalStack.pop();
            orderedStack.push(top);
            if (top.right != null) {
                traversalStack.push(top.right);
            }
            if (top.left != null) {
                traversalStack.push(top.left);
            }
        }

        while (!orderedStack.isEmpty()) {
            TreeNode top = orderedStack.pop();
            int pathSumNodeOnly = top.val;
            int bestLeft = bestExtendablePathSums.getOrDefault(top.left, 0);
            int pathSumInclLeft = top.val + bestLeft;
            int bestRight = bestExtendablePathSums.getOrDefault(top.right, 0);
            int pathSumInclRight = top.val + bestRight;
            int pathSumInclBothSides = top.val + bestLeft + bestRight;
            int bestExtendablePathSum = Math.max(pathSumNodeOnly, Math.max(pathSumInclLeft, pathSumInclRight));
            bestExtendablePathSums.put(top, bestExtendablePathSum);
            int bestPathSum = Math.max(bestExtendablePathSum, pathSumInclBothSides);
            if (bestPathSum > bestPathSumOverall) {
                bestPathSumOverall = bestPathSum;
            }
        }

        return bestPathSumOverall;
    }
}