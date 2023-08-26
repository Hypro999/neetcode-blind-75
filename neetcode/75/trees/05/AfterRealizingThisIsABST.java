// Didn't read the problem statement carefully enough, never realized that this was a BST.
public class AfterRealizingThisIsABST {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // The reason this answer works is because we are given a BST and because
        // the input contraints say that both p and q are a part of the tree.
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;
    }
}
