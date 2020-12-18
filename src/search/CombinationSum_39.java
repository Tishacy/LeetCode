package search;

import java.util.*;

/**
 * 39. 组合总和
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 *
 * 示例 1：
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 *   [7],
 *   [2,2,3]
 * ]
 *
 * 示例 2：
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *
 * 提示：
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 */
public class CombinationSum_39 {

    /**
     * 思路：获取所有求和的解决方案，使用 DFS + 回溯解决
     * 每个数字都是一个节点，每个数字可以连通到自身以及其之后的数字，形成一个有向图
     * 找出所有节点的和为 target 的路径集合
     * @param args
     */
    public static void main(String[] args) {
        CombinationSum_39 solution = new CombinationSum_39();
        int[] candidates = {2, 3, 5};
        int target = 8;
        List<List<Integer>> combinations = solution.combinationSum(candidates, target);
        System.out.println("[Solution] Combination sum: " + combinations);
    }

    private List<List<Integer>> combinations = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> path = new ArrayList<>();
        backTracking(candidates, 0, path, target);
        return combinations;
    }

    private void backTracking(int[] candidates, int i, List<Integer> path, int target) {
        if (target == 0) {
            combinations.add(new ArrayList<>(path));
            return;
        }
        for (int j=i; j<candidates.length; j++) {
            if (candidates[j] <= target) {
                path.add(candidates[j]);
                backTracking(candidates, j, path, target - candidates[j]);
                path.remove(path.size() - 1);
            }
        }
    }
}
