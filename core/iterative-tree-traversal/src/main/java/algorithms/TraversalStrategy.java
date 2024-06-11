package algorithms;

import java.util.function.Consumer;

import structures.TreeNode;


public interface TraversalStrategy<T> {

    public void traverse(TreeNode<T> root, Consumer<T> action);
}
