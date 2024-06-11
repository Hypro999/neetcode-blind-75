//@formatter:off
package utils;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import structures.TreeNode;


public class TreeUtilsTests {

    @Test
    public void testDeserialize_empty() {
        List<Integer> serializedTree = List.of();
        TreeNode<Integer> root = TreeUtils.deserialize(serializedTree);
        assertNull(root);
    }

    @Test
    public void testDeserialize_null() {
        List<Integer> serializedTree = null;
        TreeNode<Integer> root = TreeUtils.deserialize(serializedTree);
        assertNull(root);
    }

    @Test
    public void testDeserialize_nullNode() {
        List<Integer> serializedTree = Arrays.asList(new Integer[]{null});
        TreeNode<Integer> root = TreeUtils.deserialize(serializedTree);
        assertNull(root);
    }

    @Test
    public void testDeserialize_singleNodeTree() {
        List<Integer> wantSerializedTree = Arrays.asList(new Integer[]{1, null, null});
        TreeNode<Integer> root = TreeUtils.deserialize(wantSerializedTree);
        List<Integer> gotSerializedTree = TreeUtils.serialize(root);
        assertIterableEquals(wantSerializedTree, gotSerializedTree);
    }

    @Test
    public void testDeserialize_singleLeftChildTree() {
        List<Integer> wantSerializedTree = Arrays.asList(new Integer[]{1, 2, null, null, null});
        TreeNode<Integer> root = TreeUtils.deserialize(wantSerializedTree);
        List<Integer> gotSerializedTree = TreeUtils.serialize(root);
        assertIterableEquals(wantSerializedTree, gotSerializedTree);
    }

    @Test
    public void testDeserialize_singleRightChildTree() {
        List<Integer> wantSerializedTree = Arrays.asList(new Integer[]{1, null, 3, null, null});
        TreeNode<Integer> root = TreeUtils.deserialize(wantSerializedTree);
        List<Integer> gotSerializedTree = TreeUtils.serialize(root);
        assertIterableEquals(wantSerializedTree, gotSerializedTree);
    }

    @Test
    public void testDeserialize_simpleLeftAndRightChild() {
        List<Integer> wantSerializedTree = Arrays.asList(new Integer[]{1, 2, 3, null, null, null, null});
        TreeNode<Integer> root = TreeUtils.deserialize(wantSerializedTree);
        List<Integer> gotSerializedTree = TreeUtils.serialize(root);
        assertIterableEquals(wantSerializedTree, gotSerializedTree);
    }

    @Test
    public void testDeserialize_complexTree() {
        List<Integer> wantSerializedTree = Arrays.asList(new Integer[]{1, 2, 3, null, 4, 5, 6, 7, 8, null, null, 9, null, null, null, null, null, null, 10, null, null});
        TreeNode<Integer> root = TreeUtils.deserialize(wantSerializedTree);
        List<Integer> gotSerializedTree = TreeUtils.serialize(root);
        assertIterableEquals(wantSerializedTree, gotSerializedTree);
    }
}
