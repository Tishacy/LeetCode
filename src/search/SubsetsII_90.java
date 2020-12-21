package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 90. 子集 II
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 * 输入: [1,2,2]
 * 输出:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 */
public class SubsetsII_90 {

    /**
     * 思路：DFS + 回溯 + 剪枝
     *
     * 关键的剪枝：
     *          O
     *        / | \×
     *       1  2  2
     *      / \× \
     *     2  2  2
     *    /
     *   2
     * 剪枝目的：同一层，如果出现与前一个数字相同的，就剪枝。
     * 而同一条路径下，出现与前一个数字相同的，不需要剪枝。
     * @param args
     */
    public static void main(String[] args) {
        SubsetsII_90 solution = new SubsetsII_90();
        int[] nums = {1, 2, 2};
        List<List<Integer>> combinations = solution.subsetsWithDup(nums);
        System.out.println("[Solution] Subsets: " + combinations);
    }

    private List<List<Integer>> combinations = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // 为了剪枝中判断数字是否相同，需要先进行排序
        Arrays.sort(nums);
        if (nums == null || nums.length == 0) {
            return combinations;
        }
        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        backTracking(0, path, visited, nums);
        return combinations;
    }

    private void backTracking(int startIndex, List<Integer> path, boolean[] visited, int[] nums) {
        combinations.add(new ArrayList<Integer>(path));
        if (path.size() == nums.length) {
            return;
        }
        for (int i=startIndex; i<nums.length; i++) {
            // 关键的剪枝步骤：
            // nums[i] == nums[i-1]，即出现数字与前一个数字相同的情况。
            // !visited[i-1]，即上一个数字没有出现在当前路径中，那就说明是在同层的相同数字，需要剪枝。
            if (i > 0 && nums[i] == nums[i-1] && !visited[i-1]) {
                continue;
            }
            path.add(nums[i]);
            visited[i] = true;
            backTracking(i+1, path, visited, nums);
            path.remove(path.size() - 1);
            visited[i] = false;
        }
    }
}
