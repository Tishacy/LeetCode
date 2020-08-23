package divideconquer;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 95. 不同的二叉搜索树 II
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
 * <p>
 * 示例：
 * 输入：3
 * 输出：
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * 解释：
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 * 1         3     3      2      1
 *  \       /     /      / \      \
 *   3     2     1      1   3      2
 *  /     /       \                 \
 * 2     1         2                 3
 *
 * 提示：
 * 0 <= n <= 8
 */
public class UniqueBinarySearchTreesII_95 {
    /**
     * 思路：
     * 组合问题可以使用分治思想 top-down 递归解决
     * 定义一个函数，用于生成数值范围在 [start, end] 的所有 BST 集合
     *
     * @param args
     */
    public static void main(String[] args) {
        int n = 8;
        List<TreeNode> trees = generateTrees(n);
        for (TreeNode root : trees) {
            System.out.println(levelTraverse(root).toString());
        }
    }

    /**
     * Definition for a binary tree node.
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * LeetCode 上给的函数，用于生成 [1, n] 的所有 BST 的（根节点）集合
     */
    public static List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return generateTrees(1, n);
    }

    /**
     * 本函数的目的是为了生成 [start, end] 的所有 BST 的（根节点）集合
     * 遍历 [start, end] 的每一个数，让每一个数作为根节点，递归左边与右边，
     * 递归的结果就是：
     * 左边是左半部分可以生成的所有 BST 子树的集合
     * 右边是右半部分可以生成的所有 BST 子树的集合
     * 让左右 BST 子树集合进行组合，就可以得到所有可能产生的 BST
     * 递归退出条件为 当树没有节点的时候就可以退出
     */
    public static List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> trees = new ArrayList<>();

        // 递归退出条件：没有节点
        if (start > end) {
            trees.add(null);
            return trees;
        }

        // 遍历 [start, end] 的每一个数，让每一个数作为根节点，递归左边与右边
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftSubTrees = generateTrees(start, i - 1);
            List<TreeNode> rightSubTrees = generateTrees(i + 1, end);

            // 让左右 BST 子树集合进行组合，就可以得到所有可能产生的 BST
            for (TreeNode leftSubTree : leftSubTrees) {
                for (TreeNode rightSubTree : rightSubTrees) {
                    TreeNode root = new TreeNode(i, leftSubTree, rightSubTree);
                    trees.add(root);
                }
            }
        }
        return trees;
    }

    /**
     * 层次遍历二叉树
     * @param root 二叉树的根节点
     */
    public static List<Integer> levelTraverse(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        // 一个循环队列，这个队列里存的是每一层的节点
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            res.add(node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return res;
    }
}
