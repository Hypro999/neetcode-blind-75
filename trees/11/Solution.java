import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

class TreeNode {

    int val;

    TreeNode left;

    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class Solution {

    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }

        StringBuilder serializedTreeBuilder = new StringBuilder();
        Queue<TreeNode> que = new LinkedList<TreeNode>();
        que.add(root);

        while (!que.isEmpty()) {
            for (int sz = que.size(); sz > 0; sz--) {
                TreeNode node = que.remove();
                if (node == null) {
                    serializedTreeBuilder.append("null");
                    serializedTreeBuilder.append(",");
                    continue;
                }
                serializedTreeBuilder.append(String.valueOf(node.val));
                serializedTreeBuilder.append(",");
                que.add(node.left);
                que.add(node.right);
            }
        }

        if (serializedTreeBuilder.charAt(serializedTreeBuilder.length() - 1) == ',') {
            serializedTreeBuilder.setLength(serializedTreeBuilder.length() - 1);
        }
        return serializedTreeBuilder.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) {
            return null;
        }

        List<Integer> parts =
                Arrays.asList(data.split(",")).stream()
                        .map(x -> x.equals("null") ? null : Integer.parseInt(x))
                        .collect(Collectors.toList());

        int sz;
        if (parts == null || (sz = parts.size()) == 0) {
            return null;
        }
        Integer val = parts.get(0);
        TreeNode root = val == null ? null : new TreeNode(val);

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int childCursor = 1;
        while (childCursor < sz) {
            TreeNode parent = null;
            while ((parent = nodeQueue.remove()) == null)
                ;

            val = parts.get(childCursor);
            parent.left = val == null ? null : new TreeNode(val);
            nodeQueue.add(parent.left);
            childCursor++;
            if (!(childCursor < sz)) {
                break;
            }

            val = parts.get(childCursor);
            parent.right = val == null ? null : new TreeNode(val);
            nodeQueue.add(parent.right);
            childCursor++;
        }

        return root;
    }
}
