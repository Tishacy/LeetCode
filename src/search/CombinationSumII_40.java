package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 40. 组合总和 II
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。
 *
 * 示例 1:
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 *
 * 示例 2:
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 */
public class CombinationSumII_40 {

    /**
     * 思路：获取所有求和的解决方案，使用 DFS + 回溯解决
     * 每个数字都是一个节点，每个数字可以连通到自身以及其之后的数字，形成一个有向图
     * 找出所有节点的和为 target 的路径集合
     * @param args
     */
    public static void main(String[] args) {
        CombinationSumII_40 solution = new CombinationSumII_40();
        int[] candidates = {10,1,2,7,6,1,5};
        int target = 8;
        List<List<Integer>> combinations = solution.combinationSum(candidates, target);
        System.out.println("[Solution] Combination sum: " + combinations);
    }

    private List<List<Integer>> combinations = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[candidates.length];
        backtracking(candidates, 0, path, visited, target);
        return combinations;
    }

    private void backtracking(int[] candidates, int i, List<Integer> path, boolean[] visited, int target) {
        if (target == 0) {
            combinations.add(new ArrayList<>(path));
            return;
        }
        for (int j=i; j<candidates.length; j++) {
            if (j > i && candidates[j] == candidates[j - 1]) {
                continue;
            }
            if (candidates[j] <= target) {
                path.add(candidates[j]);
                visited[j] = true;
                backtracking(candidates, j + 1, path, visited, target - candidates[j]);
                visited[j] = false;
                path.remove(path.size() - 1);
            }
        }
    }
}
