import java.util.ArrayList;
import java.util.List;

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

public class Solution {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        List<Integer> preorderList = new ArrayList<>();
        for (int i = 0; i < preorder.length; i++) {
            preorderList.add(preorder[i]);
        }

        List<Integer> inorderList = new ArrayList<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderList.add(inorder[i]);
        }

        return buildTree(preorderList, inorderList);
    }

    // With lists, it's easier to create slices (logical-views) over a larger array.
    // For an even faster solution, just stick to using the original array and passing
    // around start and end indicies.
    public TreeNode buildTree(List<Integer> preorder, List<Integer> inorder) {
        if (preorder.size() == 0) {
            return null;
        }
        if (preorder.size() == 1) {
            return new TreeNode(preorder.get(0), null, null);
        }

        int rootVal = preorder.get(0);
        int partitionPoint = 0;
        while (inorder.get(partitionPoint) != rootVal) {
            partitionPoint++;
        }

        TreeNode root = new TreeNode(rootVal, null, null);
        root.left = buildTree(preorder.subList(1, partitionPoint + 1),
                inorder.subList(0, partitionPoint));
        root.right = buildTree(preorder.subList(partitionPoint + 1, preorder.size()),
                inorder.subList(partitionPoint + 1, inorder.size()));
        return root;
    }
}