import java.util.Stack;

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

    private static class StackEntry {
        TreeNode node;
        boolean leftSeen;

        StackEntry(TreeNode node) {
            this.node = node;
        }
    }

    // Solve with a simple in-order traversal, stopping at node k.
    public int kthSmallest(TreeNode root, int k) {
        Stack<StackEntry> stk = new Stack<>();
        stk.push(new StackEntry(root));
        while (!stk.isEmpty()) {
            StackEntry top = stk.pop();
            if (!top.leftSeen && top.node.left != null) {
                top.leftSeen = true;
                stk.push(top);
                stk.push(new StackEntry(top.node.left));
            } else {
                k--;
                if (k == 0) {
                    return top.node.val;
                }
                if (top.node.right != null) {
                    stk.push(new StackEntry(top.node.right));
                }
            }
        }
        return -1;
    }
}