class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


# We can take advantage of the following input contraints:
# 1. All of the nodes are unique
# 2. Both p and q are guaranteed to exist in the graph
#
# The LCA has the property that:
# 1. If the LCA is either p or q, then it must have the other as a descendant
# 2. If the LCA is neither p nor q, then p most be present on one side and q on the other side
class Solution:

    def lowestCommonAncestor(
        self, root: TreeNode, p: TreeNode, q: TreeNode
    ) -> "TreeNode":
        if root is None:
            return None
        if root == p or root == q:
            return root
        left = self.lowestCommonAncestor(root.left, p, q)
        right = self.lowestCommonAncestor(root.right, p, q)
        if left is not None and right is not None:
            # We p and q on opposite ends.
            return root
        # Otherwise p and q are on the same side and we already
        # found the LCA and just need to bubble it up.
        if left is None:
            return right
        return left
