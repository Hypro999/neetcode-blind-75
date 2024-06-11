//@formatter:off
package algorithms;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import structures.TreeNode;
import utils.TreeUtils;


// Note: This test relies on TreeUtils to work properly first.
public class PreOrderTraversalTests {

    @Test
    public void testNull() {
        TraversalStrategy<Integer> engine = new PreOrderTraversal<>();
        TreeNode<Integer> root = null;
        List<Integer> want = Collections.emptyList();
        List<Integer> got = new ArrayList<>();
        engine.traverse(root, val -> got.add(val));
        assertIterableEquals(want, got);
    }

    @Test
    public void testSingleNodeTree() {
        TraversalStrategy<Integer> engine = new PreOrderTraversal<>();
        TreeNode<Integer> root = new TreeNode<Integer>(1);
        List<Integer> want = List.of(1);
        List<Integer> got = new ArrayList<>();
        engine.traverse(root, val -> got.add(val));
        assertIterableEquals(want, got);
    }

    @Test
    public void testSingleLeftChildTree() {
        TraversalStrategy<Integer> engine = new PreOrderTraversal<>();
        TreeNode<Integer> root = TreeUtils.deserialize(Arrays.asList(new Integer[]{1, 2, null, null, null}));
        List<Integer> want = List.of(1, 2);
        List<Integer> got = new ArrayList<>();
        engine.traverse(root, val -> got.add(val));
        assertIterableEquals(want, got);
    }

    @Test
    public void testSingleRightChildTree() {
        TraversalStrategy<Integer> engine = new PreOrderTraversal<>();
        TreeNode<Integer> root = TreeUtils.deserialize(Arrays.asList(new Integer[]{1, null, 3, null, null}));
        List<Integer> want = List.of(1, 3);
        List<Integer> got = new ArrayList<>();
        engine.traverse(root, val -> got.add(val));
        assertIterableEquals(want, got);
    }

    @Test
    public void testSimpleLeftAndRightChildTree() {
        TraversalStrategy<Integer> engine = new PreOrderTraversal<>();
        TreeNode<Integer> root = TreeUtils.deserialize(Arrays.asList(new Integer[]{1, 2, 3, null, null, null, null}));
        List<Integer> want = List.of(1, 2, 3);
        List<Integer> got = new ArrayList<>();
        engine.traverse(root, val -> got.add(val));
        assertIterableEquals(want, got);
    }

    @Test
    public void testComplexTree() {
        TraversalStrategy<Integer> engine = new PreOrderTraversal<>();
        TreeNode<Integer> root = TreeUtils.deserialize(Arrays.asList(new Integer[]{1, 2, 3, null, 4, 5, 6, 7, 8, null, null, 9, null, null, null, null, null, null, 10, null, null}));
        List<Integer> want = List.of(1, 2, 4, 7, 8, 3, 5, 6, 9, 10);
        List<Integer> got = new ArrayList<>();
        engine.traverse(root, val -> got.add(val));
        assertIterableEquals(want, got);
    }
}
