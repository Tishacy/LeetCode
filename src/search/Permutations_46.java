package search;

import java.util.*;

/**
 * 46. 全排列
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 *  示例:
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 */
public class Permutations_46 {

    /**
     * 思路：
     *  可以将给定数组想象为一个无向图，每个数字节点都与其他所有数字相连。
     *         1
     *        / \
     *       2 - 3
     *  那么本题实际上就是找，以每个节点为起点的所有路径的集合，可以使用 DFS + 回溯
     *  回溯算法的关键参数为 当前节点变量 和 当前路径变量，他们的关系有两种：
     *      1. 当前路径变量已经包含了当前节点变量，需要找到当前节点所能连通的合格节点继续搜索
     *      2. 当前路径变量尚未包含当前节点变量，需要判断当前节点是否合格，如果合格就加入到路径中，
     *          继续搜索该节点所连通的节点（不需判断是否合格）
     * @param args
     */
    public static void main(String[] args) {
        Permutations_46 solution = new Permutations_46();
        int[] nums = {1, 2, 3};
        List<List<Integer>> permutations1 = solution.permute(nums, 1);
        List<List<Integer>> permutations2 = solution.permute(nums, 2);
        System.out.println("[Solution] Permutations 1: " + permutations1);
        System.out.println("[Solution] Permutations 2: " + permutations2);
    }

    private List<List<Integer>> permutations1 = new ArrayList<>();
    private List<List<Integer>> permutations2 = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums, int method) {
        if (nums == null || nums.length == 0) {
            if (method == 1) {
                return permutations1;
            }
            return permutations2;
        }

        if (method == 1) {
            for (int num : nums) {
                List<Integer> path = new ArrayList<>(Arrays.asList(num));
                Set<Integer> visited = new HashSet<>(Arrays.asList(num));
                backTracking1(num, path, visited, nums);
            }
            return permutations1;
        } else {
            for (int num : nums) {
                List<Integer> path = new ArrayList<>();
                Set<Integer> visited = new HashSet<>();
                backTracking2(num, path, visited, nums);
            }
            return permutations2;
        }
    }

    /**
     * DFS + 回溯算法 1
     * @param curNum 当前节点数字
     * @param path 当前路径变量，且已经包含了当前节点数字，需要找到当前数字所能连通的合格节点继续搜索
     * @param visited 已访问过的数字集合
     * @param nums 数字列表
     */
    private void backTracking1(int curNum, List<Integer> path, Set<Integer> visited, int[] nums) {
        // 已经获取了一条完整的路径，添加到 permutations 中
        if (path.size() == nums.length) {
            permutations1.add(new ArrayList<>(path));
            return;
        }
        // 找到当前数字所能连通的合格的节点继续搜索
        for (int nextNum : nums) {
            if (nextNum != curNum && !visited.contains(nextNum)) {
                path.add(nextNum);
                visited.add(nextNum);
                backTracking1(nextNum, path, visited, nums);
                path.remove(path.size() - 1);
                visited.remove(nextNum);
            }
        }
    }

    /**
     * DFS + 回溯算法 2
     * @param curNum 当前节点数字
     * @param path 当前路径变量尚未包含当前节点变量，需要判断当前节点是否合格，如果合格就加入到路径中，
     *             继续搜索该节点所连通的节点（不需判断是否合格）
     * @param visited 已访问过的数字集合
     * @param nums 数字列表
     */
    private void backTracking2(int curNum, List<Integer> path, Set<Integer> visited, int[] nums) {
        // 由于 path 中尚未包含当前数字节点，因此需要判断距离完成路径还有一步之遥的状态
        // 当还有一步之遥的时候，如果当前数字是合格的，那么就得到了完整的一条路径，添加到路径集合中
        if (path.size() + 1 == nums.length && !visited.contains(curNum)) {
            List<Integer> completePath = new ArrayList<>(path);
            completePath.add(curNum);
            permutations2.add(completePath);
            return;
        }
        // 判断当前数字节点是否合格，不合格就剪枝
        if (visited.contains(curNum)) {
            return;
        }
        path.add(curNum);
        visited.add(curNum);
        for (int nextNum : nums) {
            if (nextNum != curNum) {
                backTracking2(nextNum, path, visited, nums);
            }
        }
        path.remove(path.size() - 1);
        visited.remove(curNum);
    }
}
