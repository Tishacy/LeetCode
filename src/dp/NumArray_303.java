package dp;

/**
 * 303. 区域和检索 - 数组不可变
 * 给定一个整数数组  nums，求出数组从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点。
 * 实现 NumArray 类：
 * NumArray(int[] nums) 使用数组 nums 初始化对象
 * int sumRange(int i, int j) 返回数组 nums 从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点（也就是 sum(nums[i], nums[i + 1], ... , nums[j])）
 *
 * 示例：
 * 输入：
 * ["NumArray", "sumRange", "sumRange", "sumRange"]
 * [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
 * 输出：
 * [null, 1, -1, -3]
 * 解释：
 * NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
 * numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
 * numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1))
 * numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
 *
 * 提示：
 * 0 <= nums.length <= 104
 * -105 <= nums[i] <= 105
 * 0 <= i <= j < nums.length
 * 最多调用 104 次 sumRange 方法
 */
public class NumArray_303 {

    /**
     * 思路：
     *  方法1：最简单的方法就是每次都把[i,j]区间的数加一遍。
     *     初始化：O(1)
     *     求和:O(N)
     *  方法2：因为题目当中说会初始化1次，多次求和，所以初始化可以用O(N)，但是每次求和尽量为O(1)
     *     初始化：O(N) 使用动态规划， dp 数组的第 i+1 个元素表示 nums[0:i] 的数字的求和。
     *     求和: O(1) 这样每次求和，只需要返回 dp[j+1] - dp[i]，就表示 [i,j] 区间内的和
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray_303 numArray = new NumArray_303(nums);
        System.out.println(numArray.sumRange(0,2));
        System.out.println(numArray.sumRange(2,5));
        System.out.println(numArray.sumRange(0,5));
    }

    private int[] sums;

    public NumArray_303(int[] nums) {
        int n = nums.length;
        sums = new int[n+1];
        for (int i=0; i<n; i++) {
            sums[i+1] = sums[i] + nums[i];
        }
    }

    public int sumRange(int i, int j) {
        return sums[j+1] - sums[i];
    }
}
