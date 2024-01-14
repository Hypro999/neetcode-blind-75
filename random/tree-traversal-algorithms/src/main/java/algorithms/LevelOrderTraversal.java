package algorithms;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

import structures.TreeNode;


public class LevelOrderTraversal<T> implements TraversalStrategy<T> {

    public void traverse(TreeNode<T> root, Consumer<T> action) {
        if (root == null) {
            return;
        }
        Queue<TreeNode<T>> queue = new LinkedList<TreeNode<T>>();
        queue.add(root);
        while (!queue.isEmpty()) {
            for (int sz = queue.size(); sz > 0; sz--) {
                TreeNode<T> top = queue.remove();
                action.accept(top.value);
                if (top.left != null) {
                    queue.add(top.left);
                }
                if (top.right != null) {
                    queue.add(top.right);
                }
            }
        }
    }
}
