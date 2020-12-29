package dp;

import java.util.Arrays;

/**
 * 213. 打家劫舍II
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，能够偷窃到的最高金额。
 *
 * 示例 1：
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 *
 * 示例 2：
 * 输入：nums = [1,2,3,1]
 * 输出：4
 * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 示例 3：
 * 输入：nums = [0]
 * 输出：0
 *
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 */
public class HouseRobberII_213 {

    public static void main(String[] args) {
        HouseRobberII_213 solution = new HouseRobberII_213();
        int[] nums1 = {2, 3, 2};
        int[] nums2 = {1, 2, 3, 1};
        System.out.println("[Solution] " + Arrays.toString(nums1) + ": " + solution.rob(nums1));
        System.out.println("[Solution] " + Arrays.toString(nums2) + ": " + solution.rob(nums2));
    }

    /**
     * 思路：动态规划 + 滚动数组
     * 该题目的思路与 {@link HouseRobber_198} 几乎一样，只不过多了一点就是，首和尾不能同时被偷。
     * 时间复杂度: O(N)
     * 空间复杂度: O(1)
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        // 保证不同时偷窃首与尾
        return Math.max(rob(nums, 0, n-2), rob(nums, 1, n-1));
    }

    public int rob(int[] nums, int s, int e) {
        if (s == e) {
            return nums[s];
        }
        int first = nums[s], second = Math.max(nums[s], nums[s+1]);
        for (int i=s+2; i<=e; i++) {
            int res = Math.max(first + nums[i], second);
            first = second;
            second = res;
        }
        return second;
    }
}
