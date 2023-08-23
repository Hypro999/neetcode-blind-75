import java.util.LinkedList;
import java.util.Queue;

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

public class MySolution {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode top = q.remove();
            if (isSameTree(top, subRoot)) {
                return true;
            }
            if (top.left != null) {
                q.add(top.left);
            }
            if (top.right != null) {
                q.add(top.right);
            }
        }
        return false;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null && q != null) {
            return false;
        }
        if (p != null && q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        // Too lazy to write non-recursive code.
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}