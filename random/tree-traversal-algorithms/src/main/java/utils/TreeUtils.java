package utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import structures.TreeNode;


public class TreeUtils {

    public static <T> List<T> serialize(TreeNode<T> root) {
        if (root == null) {
            return null;
        }
        List<T> serializedTree = new ArrayList<>();

        Queue<TreeNode<T>> que = new LinkedList<TreeNode<T>>();
        que.add(root);

        while (!que.isEmpty()) {
            for (int sz = que.size(); sz > 0; sz--) {
                TreeNode<T> node = que.remove();
                if (node == null) {
                    serializedTree.add(null);
                    continue;
                }
                serializedTree.add(node.value);
                que.add(node.left);
                que.add(node.right);
            }
        }

        return serializedTree;
    }

    public static <T> TreeNode<T> deserialize(List<T> serializedTree) {
        int sz;
        if (serializedTree == null || (sz = serializedTree.size()) == 0) {
            return null;
        }

        T val = serializedTree.get(0);
        TreeNode<T> root = val == null ? null : new TreeNode<>(val);

        Queue<TreeNode<T>> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int childCursor = 1;
        while (childCursor < sz) {
            TreeNode<T> parent = null;
            while ( (parent = nodeQueue.remove()) == null)
                ;

            val = serializedTree.get(childCursor);
            parent.left = val == null ? null : new TreeNode<>(val);
            nodeQueue.add(parent.left);
            childCursor++;
            if (! (childCursor < sz)) {
                break;
            }

            val = serializedTree.get(childCursor);
            parent.right = val == null ? null : new TreeNode<>(val);
            nodeQueue.add(parent.right);
            childCursor++;
        }

        return root;
    }
}
