package structures;

public class TreeNode<t> {

    public t value;

    public TreeNode<t> left;

    public TreeNode<t> right;

    public TreeNode(t value) {
        this.value = value;
    }

    public TreeNode(t value, TreeNode<t> left, TreeNode<t> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
