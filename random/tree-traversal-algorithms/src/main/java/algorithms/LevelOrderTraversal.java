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
        Queue<TreeNode<T>> que = new LinkedList<TreeNode<T>>();
        que.add(root);
        while (!que.isEmpty()) {
            for (int sz = que.size(); sz > 0; sz--) {
                TreeNode<T> node = que.remove();
                if (node == null) {
                    action.accept(null);
                    continue;
                }
                action.accept(node.value);
                que.add(node.left);
                que.add(node.right);
            }
        }
    }
}
