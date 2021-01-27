package dp;

/**
 * 300. 最长递增子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *
 * 示例 1：
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 *
 * 示例 2：
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 *
 * 示例 3：
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 *
 * 提示：
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 */
public class LongestIncreasingSubsequence_300 {

    /**
     * 思路：
     *  在一些动态规划题目中，我们可以判断dp数组中当前状态
     *  是由哪些前面的状态转移过来，比如斐波那契数列的当前状态
     *  由其前两个状态转移过来，二维矩阵路径数的某位置状态可以由
     *  其周围某些方向的位置转移过来等。对于这些动态规划的题目，
     *  往往可以以O(N)的复杂度进行解决，因为找哪些状态转移的步骤
     *  是通过推理找到的。但是，有些题目，无法直接从逻辑推理出当前状态
     *  可以由哪些状态转移而来，只能将其之前的状态进行一个一个的判断，
     *  判断出能转移的那些状态，然后再计算当前状态的结果，因此需要 O(N^2)
     *  的复杂度。最长上升序列就是这种题目。
     *
     *  设有dp数组，dp[i]表示以第 i 个数组结尾的最长上升子序列的长度，那么
     *  状态转移函数为：
     *      当 i = 0 时，dp[i] = 1，因为以第 1 个位置为结尾的最长上升子序列数为 1
     *      当 i > 1 时，dp[i] = Max(dp[j]) + 1, 其中，0<=j<i，且 nums[i] > nums[j],
     *  也就是说需要遍历 i 之前的所有状态中满足上升序列条件的那些状态，因为要找出
     *  最长的长度，因此需要取 Max(dp[j])，再加 1.
     * @param args
     */
    public static void main(String[] args) {
        LongestIncreasingSubsequence_300 solution = new LongestIncreasingSubsequence_300();
        int[] nums = {10,9,2,5,3,7,101,18};
        int[] nums2 = {0,1,0,3,2,3};
        int maxLength = solution.lengthOfLIS(nums);
        int maxLength2 = solution.lengthOfLIS(nums2);
        System.out.println("[Solution] length of LIS: " + maxLength);
        System.out.println("[Solution] length of LIS: " + maxLength2);
    }

    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 1;
        int maxLength = 1;
        for (int i=0; i<n; i++) {
            dp[i] = 1;
            for (int j=0; j<i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }
        return maxLength;
    }
}
