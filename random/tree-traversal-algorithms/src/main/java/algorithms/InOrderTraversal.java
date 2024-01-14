package algorithms;

import java.util.Stack;
import java.util.function.Consumer;

import structures.TreeNode;


public class InOrderTraversal<T> implements TraversalStrategy<T> {

    private Stack<TreeNode<T>> leftSeen;
    
    public InOrderTraversal() {
        this.leftSeen = new Stack<>();
    }

    public void traverse(TreeNode<T> root, Consumer<T> action) {
        if (root == null) {
            return;
        }
        leftSeen.push(root);
        TreeNode<T> lookAhead = root.left;
        while (!leftSeen.isEmpty()) {
            if (lookAhead == null) {
                lookAhead = leftSeen.pop();
                action.accept(lookAhead.value);
                lookAhead = lookAhead.right;
            }
            // ↑ may affect ↓
            if (lookAhead != null) {
                leftSeen.push(lookAhead);
                lookAhead = lookAhead.left;
            }
        }
    }
}
