/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 *
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q
 * as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 *
 * 思路: 从根节点找到两个节点，形成两条路径。
 * 在两条路径中，搜索第一个出现在两条路径中的元素
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> path0 = new ArrayList<>();
        findPath(root, p, path0);

        List<TreeNode> path1 = new ArrayList<>();
        findPath(root, q, path1);

        if (path0.size() > path1.size()) {
            List<TreeNode> tmp = path0;
            path0 = path1;
            path1 = tmp;
        }
        Set<Integer> set = path1.stream().map(m -> m.val).collect(Collectors.toSet());
        for (int i = path0.size() - 1; i >= 0; i--) {
            if (set.contains(path0.get(i).val)) {
                return path0.get(i);
            }
        }
        return null;
    }

    private boolean findPath(TreeNode root, TreeNode target, List<TreeNode> path) {
        if (root == null) {
            return false;
        }
        path.add(root);
        if (root.val == target.val) {
            return true;
        }
        boolean find = findPath(root.left, target, path) || findPath(root.right, target, path);
        if (!find) {
            path.remove(path.size() - 1);
        }
        return find;
    }
}