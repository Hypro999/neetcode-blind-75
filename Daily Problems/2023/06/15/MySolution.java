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
    public int maxLevelSum(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int currentLevel = 0;
        int highestLevel = 1;
        int highestLevelSum = root.val;
        while (!q.isEmpty()) {
            currentLevel++;
            int width = q.size();
            int levelSum = 0;
            while (width > 0) {
                TreeNode front = q.remove();
                levelSum += front.val;
                if (front.left != null) {
                    q.add(front.left);
                }
                if (front.right != null) {
                    q.add(front.right);
                }
                width--;
            }
            if (levelSum > highestLevelSum) {
                highestLevelSum = levelSum;
                highestLevel = currentLevel;
            }
        }
        return highestLevel;
    }
}
