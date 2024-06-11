class TreeNode {

    int val;

    TreeNode left;

    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class SolutionNaive {

    static class ReportingPair {

        boolean seen;

        TreeNode lca;

        public ReportingPair(boolean seen, TreeNode lca) {
            this.seen = seen;
            this.lca = lca;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return lca(root, p.val, q.val).lca;
    }

    private ReportingPair lca(TreeNode root, int p, int q) {
        if (root == null) {
            return new ReportingPair(false, null);
        }

        boolean selfSeen = (root.val == p || root.val == q);

        ReportingPair left = lca(root.left, p, q);
        if (left.lca != null) {
            return left;
        }
        if (left.seen && selfSeen) {
            return new ReportingPair(true, root);
        }

        ReportingPair right = lca(root.right, p, q);
        if (right.lca != null) {
            return right;
        }
        if (right.seen && selfSeen) {
            return new ReportingPair(true, root);
        }
        if (right.seen && left.seen) {
            return new ReportingPair(true, root);
        }

        return new ReportingPair(selfSeen || left.seen || right.seen, null);
    }
}
