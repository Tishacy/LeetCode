package search;

import java.util.*;

/**
 * 77. 组合
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 * 示例 1:
 * 输入: n = 4, k = 2
 * 输出:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *
 * 示例 2:
 * 输入: n = 1, k = 1
 * 输出: [[1]]
 *
 * 限制条件:
 * 1 <= n <= 20
 * 1 <= k <= n
 */
public class Combinations_77 {

    /**
     * 思路同 `Permutations_46`，不过限定了路径长度为 k，而不是 nums 的长度。
     * @param args
     */
    public static void main(String[] args) {
        Combinations_77 solution = new Combinations_77();
        int n = 4, k = 2;
        List<List<Integer>> combinations = solution.combine(n, k);
        System.out.println("[Solution] Combinations: " + combinations);
    }

    private List<List<Integer>> combinations = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        for (int num = 1; num <= n; num++) {
            // 因为 [1,2] 和 [2,1] 是同一个结果，因此数字节点是无序的，所以用 set 结构
            Set<Integer> path = new HashSet<>(Arrays.asList(num));
            backTracking(num, path, n, k);
        }
        return combinations;
    }

    /**
     * DFS + 回溯
     * @param curNum 当前数字节点
     * @param path 当前路径（包含了当前数字节点），找到当前节点所连通的合格的其他节点，继续搜索
     * @param n 最大数字节点
     * @param k 路径长度
     */
    private void backTracking(int curNum, Set<Integer> path, int n, int k) {
        if (path.size() == k) {
            combinations.add(new ArrayList<>(path));
            return;
        }
        for (int nextNum = curNum+1; nextNum <= n; nextNum++) {
            if (!path.contains(nextNum)) {
                path.add(nextNum);
                backTracking(nextNum, path, n, k);
                path.remove(nextNum);
            }
        }
    }
}
