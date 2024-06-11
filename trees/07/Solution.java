class TreeNode {

    int val;

    TreeNode left;

    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    /**
     * @param root
     *            Not null.
     */
    private boolean isValidBST(TreeNode root, Integer min, Integer max) {
        if (min != null && root.val <= min) {
            return false;
        }
        if (max != null && root.val >= max) {
            return false;
        }
        if (root.left != null) {
            if (!isValidBST(root.left, min, root.val)) {
                return false;
            }
        }
        if (root.right != null) {
            if (!isValidBST(root.right, root.val, max)) {
                return false;
            }
        }
        return true;
    }
}