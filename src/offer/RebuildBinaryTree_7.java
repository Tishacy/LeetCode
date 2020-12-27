package offer;

import javax.swing.tree.TreeNode;
import java.util.*;

/**
 * 剑指 Offer 07. 重建二叉树
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如，给出
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 限制：
 * 0 <= 节点个数 <= 5000
 */
public class RebuildBinaryTree_7 {

    public static void main(String[] args) {
        RebuildBinaryTree_7 solution = new RebuildBinaryTree_7();
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode root = solution.buildTree(preorder, inorder);
        System.out.println("[Solution] " + solution.layerTraverse(root));
    }

    private Map<Integer, Integer> inorderIndexMap = new HashMap<>();

    /**
     * 思路：根据 preorder 找到根节点，然后根据 inorder 找到中序遍历中根节点的位置，
     * 就可以从 inorder 中找到根节点左子树和右子树的范围，然后再递归的找左子树的根节点，
     * 和右子树的根节点。
     * @param preorder 前序遍历数组
     * @param inorder 中序遍历数组
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 构建 inorder 中节点索引的哈希表，方便后面寻找根节点在 inorder 中的位置
        int len = preorder.length;
        for (int i=0; i<len; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        // 调用递归方法重建二叉树
        return getSubTreeRoot(preorder, 0, len-1, inorder, 0, len-1);
    }

    /**
     * 寻找该子树的根节点，并递归寻找该子树的左左子树的根节点，
     * 和该子树的右子树的根节点
     * @param preorder 二叉树的前序遍历数组
     * @param ps 子树在前序遍历中的起始位置索引
     * @param pe 子树在前序遍历中的终止位置索引
     * @param inorder 二叉树的中序遍历数组
     * @param is 子树在中序遍历中的起始位置索引
     * @param ie 子树在中序遍历中的终止位置索引
     * @return 该子树的根节点
     */
    public TreeNode getSubTreeRoot(int[] preorder, int ps, int pe, int[] inorder, int is, int ie) {
        if (is > ie) {
            return null;
        }
        int rootVal = preorder[ps];
        TreeNode root = new TreeNode(rootVal);
        if (is == ie) {
            // 如果该子树的范围中只有一个节点，即没有孩子节点
            // 那么这个节点就是根节点，直接返回它
        } else {
            // 递归寻找该子树的左左子树的根节点，
            // 和该子树的右子树的根节点
            int inorderRootIndex = inorderIndexMap.get(rootVal);
            int leftTreeSize = inorderRootIndex - is;
            int rightTreeSize = ie - inorderRootIndex;
            root.left = getSubTreeRoot(preorder, ps+1, ps+leftTreeSize, inorder, inorderRootIndex-leftTreeSize, inorderRootIndex-1);
            root.right = getSubTreeRoot(preorder, ps+leftTreeSize+1, pe, inorder, inorderRootIndex+1, inorderRootIndex+rightTreeSize);
        }
        return root;
    }

    public class TreeNode {
        Integer val;
        TreeNode left;
        TreeNode right;
        public TreeNode(Integer val) {
            this.val = val;
        }
    }

    /**
     * 层次遍历二叉树，验证结果时用。
     * @param root 二叉树的根节点
     * @return 层次遍历数组
     */
    public List<Integer> layerTraverse(TreeNode root) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        List<Integer> values = new ArrayList<>();
        queue.add(root);
        while (queue.size() > 0) {
            TreeNode node = queue.poll();
            values.add(node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return values;
    }
}
