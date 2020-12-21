package search;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 */
public class Subsets_78 {

    /**
     * 思路：DFS + 回溯
     * @param args
     */
    public static void main(String[] args) {
        Subsets_78 solution = new Subsets_78();
        int[] nums = {1, 2, 3};
        List<List<Integer>> combinations = solution.subsets(nums);
        System.out.println("[Solution] Subsets: " + combinations);
    }

    private List<List<Integer>> combinations = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length == 0) {
            return combinations;
        }
        List<Integer> path = new ArrayList<>();
        backTracking(0, path, nums);
        return combinations;
    }

    private void backTracking(int startIndex, List<Integer> path, int[] nums) {
        combinations.add(new ArrayList(path));
        if (path.size() == nums.length) {
            return;
        }
        for (int i=startIndex; i<nums.length; i++) {
            path.add(nums[i]);
            backTracking(i+1, path, nums);
            path.remove(path.size() - 1);
        }
    }
}
