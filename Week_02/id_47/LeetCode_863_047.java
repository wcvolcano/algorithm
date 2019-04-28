/**
 * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
 *
 * We are given a binary tree (with root node root), a target node, and an integer value K.
 * Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned
 * in any order.
 *
 * 思路说明:
 * 1. 从根节点找到到目标节点的路径
 * 2.   a) 从目标节点往下K层都满足
 *      b) 目标节点的父界节点往另一个方向的K-1层都满足
 *      c) 依次内推
 */
class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<TreeNode> path = new ArrayList<>();
        findPath(root, target, path);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < path.size() - 1; ++i) {
            TreeNode cur = path.get(i);
            int dis = K - (path.size()  - 1-i);
            if (dis < 0) { }
            else if (dis == 0) {
                result.add(cur.val);
            } else if (cur.left != path.get(i + 1)) {
                findDistance(cur.left, result, dis - 1);
            } else if (cur.right != path.get(i + 1)) {
                findDistance(cur.right, result, dis - 1);
            }
        }
        findDistance(target, result, K);
        return result;
    }

    private void findDistance(TreeNode root, List<Integer> all, int k) {
        if (root == null) {
            return;
        }
        if (k <= 0) {
            all.add(root.val);
            return;
        }
        findDistance(root.left, all, k - 1);
        findDistance(root.right, all, k - 1);
    }

    private boolean findPath(TreeNode root, TreeNode target, List<TreeNode> path) {
        if (root == null) {
            return false;
        }
        if (root == target) {
            path.add(root);
            return true;
        }
        path.add(root);
        boolean find = findPath(root.left, target, path) || findPath(root.right, target, path);
        if (find) {
            return true;
        } else {
            path.remove(path.size() - 1);
            return false;
        }
    }
}