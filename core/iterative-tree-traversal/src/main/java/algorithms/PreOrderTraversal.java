package algorithms;

import java.util.Stack;
import java.util.function.Consumer;

import structures.TreeNode;


public class PreOrderTraversal<T> implements TraversalStrategy<T> {

    private Stack<TreeNode<T>> stack;
    
    public PreOrderTraversal() {
        this.stack = new Stack<>();
    }

    public void traverse(TreeNode<T> root, Consumer<T> action) {
        if (root == null) {
            return;
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode<T> top = stack.pop();
            action.accept(top.value);
            if (top.right != null) {
                stack.push(top.right);
            }
            if (top.left != null) {
                stack.push(top.left);
            }
        }
    }
}
