package algorithms;

import structures.TreeNode;

import java.util.Stack;
import java.util.function.Consumer;

/**
 * An implementation of {@link InOrderTraversal} that follows a similar pattern to {@link
 * PostOrderTraversalOneStack}.
 */
public class InOrderTraversalAlt<T> extends InOrderTraversal<T> {

    private Stack<TreeNode<T>> stack;

    public InOrderTraversalAlt() {
        this.stack = new Stack<>();
    }

    @Override
    public void traverse(TreeNode<T> root, Consumer<T> action) {
        exhaustLeft(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            action.accept(root.value);
            exhaustLeft(root.right);
        }
    }

    private void exhaustLeft(TreeNode<T> root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }
}
