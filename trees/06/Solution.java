import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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

class Solution {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();

        if (root == null) {
            return levels;
        }

        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        while (!que.isEmpty()) {
            List<Integer> currentLevel = new ArrayList<>();
            for (int sz = que.size(); sz > 0; sz--) {
                TreeNode front = que.remove();
                currentLevel.add(front.val);
                if (front.left != null) {
                    que.add(front.left);
                }
                if (front.right != null) {
                    que.add(front.right);
                }
            }
            levels.add(currentLevel);
        }

        return levels;
    }
}