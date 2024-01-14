package algorithms;

import java.util.Stack;
import java.util.function.Consumer;

import structures.TreeNode;


public class PostOrderTraversal<T> implements TraversalStrategy<T> {

    private Stack<TreeNode<T>> orderedStack;

    private Stack<TreeNode<T>> traversalStack;

    public PostOrderTraversal() {
        this.orderedStack = new Stack<>();
        this.traversalStack = new Stack<>();
    }

    public void traverse(TreeNode<T> root, Consumer<T> action) {
        if (root == null) {
            return;
        }
        traversalStack.push(root);
        while (!traversalStack.isEmpty()) {
            TreeNode<T> top = traversalStack.pop();
            orderedStack.push(top);
            if (top.left != null) {
                traversalStack.push(top.left);
            }
            if (top.right != null) {
                traversalStack.push(top.right);
            }
        }
        while (!orderedStack.isEmpty()) {
            TreeNode<T> top = orderedStack.pop();
            action.accept(top.value);
        }
    }
}
