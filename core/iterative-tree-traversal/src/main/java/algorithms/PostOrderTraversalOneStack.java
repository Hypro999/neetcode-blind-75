package algorithms;

import structures.TreeNode;

import java.util.Stack;
import java.util.function.Consumer;

public class PostOrderTraversalOneStack<T> implements TraversalStrategy<T> {

    Stack<TreeNode<T>> stack;

    public PostOrderTraversalOneStack() {
        this.stack = new Stack<>();
    }

    @Override
    public void traverse(TreeNode<T> root, Consumer<T> action) {
        exahustLeft(root);
        while (!stack.isEmpty()) {
            TreeNode<T> top = stack.pop();
            if (!stack.isEmpty() && stack.peek().equals(top.right)) {
                TreeNode<T> right = stack.pop();
                stack.push(top);
                exahustLeft(right);
                continue;
            }
            action.accept(top.value);
        }
    }

    private void exahustLeft(TreeNode<T> root) {
        while (root != null) {
            if (root.right != null) {
                stack.push(root.right);
            }
            stack.push(root);
            root = root.left;
        }
    }
}
