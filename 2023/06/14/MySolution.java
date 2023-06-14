class TreeNode {
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
 
public class MySolution {
    int minimumDifference;

    private static final int MIN = 0;

    private static final int MAX = 1;

    public int getMinimumDifference(TreeNode root) {
        setInitialMinimumDifference(root);
        getMinimumDifferenceRecursively(root);
        return minimumDifference;
    }

    private void setInitialMinimumDifference(TreeNode root) {
        if (root.left != null) {
            minimumDifference = Math.abs(root.val - root.left.val);
        } else {
            minimumDifference = Math.abs(root.val - root.right.val);
        }
    }

    /**
     * @param root The tree to search for the minimumDifference in. Not null.
     * @returns int[min, max] for that tree.
     */
    private int[] getMinimumDifferenceRecursively(TreeNode root) {
        int[] result = new int[]{root.val, root.val};
        if (root.left != null) {
            int[] leftResult = getMinimumDifferenceRecursively(root.left);
            result[MIN] = leftResult[MIN];
            int difference = Math.abs(root.val - leftResult[MAX]);
            if (difference < minimumDifference) {
                minimumDifference = difference;
            }
        }
        if (root.right != null) {
            int[] rightResult = getMinimumDifferenceRecursively(root.right);
            result[MAX] = rightResult[MAX];
            int difference = Math.abs(root.val - rightResult[MIN]);
            if (difference < minimumDifference) {
                minimumDifference = difference;
            }
        }
        return result;
    }
}