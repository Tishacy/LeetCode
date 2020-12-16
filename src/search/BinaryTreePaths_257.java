package search;

import java.util.ArrayList;
import java.util.List;

/**
 * 257. 二叉树的所有路径
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 输入:
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * 输出: ["1->2->5", "1->3"]
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 */
public class BinaryTreePaths_257 {

    /**
     * 思路:
     *  找出二叉树的所有路径，使用 DFS + 回溯 解决
     *
     * 回溯算法：在当前的路径下（尚未包含当前节点）
     *      如果当前节点不合法（为空），剪枝返回
     *      如果当前节点已经是叶子节点，证明走完了一整条路径，将该路径加入到路径集合中
     *      反之，添加当前节点到路径中，继续搜索左子树和右子树，搜索完后回溯上一个状态
     * @param args
     */
    public static void main(String[] args) {
        BinaryTreePaths_257 solution = new BinaryTreePaths_257();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);

        List<String> combinations = solution.binaryTreePaths(root);
        System.out.println("[Solution]: Binary tree paths: " + combinations);
    }

    private List<String> combinations = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return combinations;
        }
        List<Integer> path = new ArrayList<>();
        backTracking(root, path);
        return combinations;
    }

    private void backTracking(TreeNode curNode, List<Integer> path) {
        if (curNode == null) {
            return;
        }
        if (curNode.left == null && curNode.right == null) {
            StringBuilder pathStr = new StringBuilder();
            for (Integer val : path) {
                pathStr.append(val + "->");
            }
            pathStr.append(curNode.val);
            combinations.add(pathStr.toString());
            return;
        }
        path.add(curNode.val);
        backTracking(curNode.left, path);
        backTracking(curNode.right, path);
        path.remove(path.size() - 1);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
