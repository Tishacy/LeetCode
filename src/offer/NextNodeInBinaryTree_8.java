package offer;

import javax.swing.tree.TreeNode;
import java.util.*;

/**
 * 剑指 Offer 08. 二叉树的下一个节点
 * 给定一棵二叉树和其中的一个节点，如何找出中序遍历序列的下一个节点？树中的节点除了有两个分别指向左、右子节点
 * 的指针，还有一个指向父节点的指针。
 *
 * 示例：
 *          a
 *       /    \
 *      b     c
 *     / \   / |
 *    d  e  f  g
 *      / |
 *     h  i
 *
 * a 的下个节点: f
 * e 的下个节点: i
 * i 的下个节点: a
 * h 的下个节点: e
 */
public class NextNodeInBinaryTree_8 {

    /**
     * 思路：对于一个二叉树的节点:
     *      如果它有右子树，那么中序遍历的下一个节点就是其右子树的最左子节点。
     *      如果它没有右子树
     *          如果它是父亲的左子节点，那么中序遍历的下一个元素就是它的父节点。
     *          如果它是父亲的右子节点，那么找出它父节点中是左子节点的那个节点的父节点。
     * @param args
     */
    public static void main(String[] args) {
        NextNodeInBinaryTree_8 solution = new NextNodeInBinaryTree_8();
        Map<Character, TreeNode<Character>> nodeMap = new HashMap<>();
        for (char c='a'; c<='i'; c++) {
            nodeMap.put(c, new TreeNode<>(c));
        }
        makeConn(nodeMap.get('a'), nodeMap.get('b'), nodeMap.get('c'));
        makeConn(nodeMap.get('b'), nodeMap.get('d'), nodeMap.get('e'));
        makeConn(nodeMap.get('e'), nodeMap.get('h'), nodeMap.get('i'));
        makeConn(nodeMap.get('c'), nodeMap.get('f'), nodeMap.get('g'));
        System.out.println("[Solution] a's next node: " + solution.nextNodeInBinaryTree(nodeMap.get('a')).val);
        System.out.println("[Solution] e's next node: " + solution.nextNodeInBinaryTree(nodeMap.get('e')).val);
        System.out.println("[Solution] i's next node: " + solution.nextNodeInBinaryTree(nodeMap.get('i')).val);
        System.out.println("[Solution] h's next node: " + solution.nextNodeInBinaryTree(nodeMap.get('h')).val);
    }

    public TreeNode nextNodeInBinaryTree(TreeNode node) {
        if (null != node.right) {
            // 如果它有右子树，那么中序遍历的下一个节点就是其右子树的最左子节点。
            TreeNode p = node.right;
            while (null != p.left) {
                p = p.left;
            }
            return p;
        }
        if (node.parent != null && node.parent.left == node) {
            // 如果它是父亲的左子节点，那么中序遍历的下一个元素就是它的父节点。
            return node.parent;
        } else {
            // 如果它是父亲的右子节点，那么找出它父节点中，是左子节点的那个节点的父节点。
            TreeNode p = node.parent;
            while (null != p) {
                if (p.parent != null && p.parent.left == p) {
                    return p.parent;
                }
                p = p.parent;
            }
        }
        return null;
    }

    private static void makeConn(TreeNode root, TreeNode left, TreeNode right) {
        root.left = left;
        root.right = right;
        left.parent = root;
        right.parent = root;
    }


    private static class TreeNode<T> {
        TreeNode left;
        TreeNode right;
        TreeNode parent;
        T val;

        public TreeNode(T val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "left=" + left +
                    ", right=" + right +
                    ", val=" + val +
                    '}';
        }
    }
}
