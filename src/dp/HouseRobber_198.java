package dp;

import java.util.Arrays;

/**
 * 198. 打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 * 示例 1：
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *     偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 示例 2：
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *
 * 提示：
 * 0 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 */
public class HouseRobber_198 {

    public static void main(String[] args) {
        HouseRobber_198 solution = new HouseRobber_198();
        int[] nums = {5, 6};
        int[] nums1 = {1, 2, 3, 1};
        int[] nums2 = {2, 7, 9, 3, 1};
        System.out.println("[Solution] " + Arrays.toString(nums) + ": " + solution.rob(nums));
        System.out.println("[Solution] " + Arrays.toString(nums1) + ": " + solution.rob(nums1));
        System.out.println("[Solution] " + Arrays.toString(nums2) + ": " + solution.rob(nums2));

        System.out.println("[Solution] " + Arrays.toString(nums) + ": " + solution.rob2(nums));
        System.out.println("[Solution] " + Arrays.toString(nums1) + ": " + solution.rob2(nums1));
        System.out.println("[Solution] " + Arrays.toString(nums2) + ": " + solution.rob2(nums2));
    }

    /**
     * 方法 1：动态规划
     * 对于第 i 个房屋，小偷有两个选择：
     *    - 偷这家：那么就不能偷上一家，因此偷窃的最高金额 = 偷窃 i-2 家的最高金额 + 当前第 i 家的金额
     *    - 不偷这家：那么偷窃的最高金额 = 偷窃 i-1 家的最高金额
     * 那么，对于第 i 个房屋，所偷窃的最高金额就是上面这两种选择所能偷到金额的最大值。
     * 因此得到状态转移方程：
     *      dp[i] = Max(dp[i-2] + nums[i], dp[i-1])
     *  边界条件为：
     *      dp[0] = nums[0]  （只有 1 个房屋，那最高金额就是偷了他）
     *      dp[1] = Max(nums[0], nums[1])  （只有 2 个房屋，那最高金额就是去偷钱多的那家）
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     * @param nums
     * @return
     */
    private int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i=2; i<n; i++) {
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
        }
        return dp[n-1];
    }

    /**
     * 方法 2：动态规划 + 滚动数组
     * 由方法 1 可知，当前状态只与前两个状态有关，因此可以用滚动数组将空间复杂度降低为 O(1)
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     * @param nums
     * @return
     */
    private int rob2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int first = nums[0], second = Math.max(nums[0], nums[1]);
        for (int i=2; i<nums.length; i++) {
            int res = Math.max(first + nums[i], second);
            first = second;
            second = res;
        }
        return second;
    }
}
