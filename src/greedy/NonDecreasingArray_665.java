package greedy;

/**
 * 665. 非递减数列
 * 给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。
 * 我们是这样定义一个非递减数列的： 对于数组中所有的 i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。
 *
 * 示例 1:
 * 输入: nums = [4,2,3]
 * 输出: true
 * 解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
 *
 * 示例 2:
 * 输入: nums = [4,2,1]
 * 输出: false
 * 解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
 *  
 * 说明：
 * 1 <= n <= 10 ^ 4
 * - 10 ^ 5 <= nums[i] <= 10 ^ 5
 */
public class NonDecreasingArray_665 {
    public static void main(String[] args) {
        NonDecreasingArray_665 solution = new NonDecreasingArray_665();
        System.out.println(solution.checkPossibility(new int[] {4,1,5,6}));
        System.out.println(solution.checkPossibility(new int[] {4,1,2,3}));
        System.out.println(solution.checkPossibility(new int[] {4,4,2,3}));
    }

    /**
     * 思路：
     * - 遍历数组，如果遇到一个数比前一个数小，那么有两种情况
     *     - 上个数字突然变大，比如：[1, 2, 6, 3]，当前为 3, 上个数字突然变大
     *         - 那么就让上个数字等于这个数字，变为 [1, 2, 3, 3]
     *     - 这个数字突然变小，比如：[1, 2, 1, 3]，当前为 1，这个数字突然变小
     *         - 那么就让这个数字等于上个数字，变为 [1, 2, 2, 3]
     *     - 还有一种特殊情况是，如果当前数字是第 1 个数字（从 0 开始计数），
     *       无论这个数字是突然变小，还是上个数字突然变大，都可以用上个数字等于
     *       这个数字来解决：
     *         - 第 0 个数字突然增大，[4, 1, 2, 3]，将第 0 个数字换为第 1 个数字
     *           得到 [1, 1, 2, 3] 是非递减数列
     *         - 第 1 个数字突然减小，[4, 1, 5, 6]，将第 0 个数字环微第 1 个数字
     *           得到 [1, 1, 5, 6] 也是非递减数列
     * @param nums
     * @return
     */
    public boolean checkPossibility(int[] nums) {
        int count = 0;
        for (int i=1; i<nums.length; i++) {
            if (nums[i] < nums[i-1]) {
                // 当前为第 1 个数字 或者 上个数字突然变大
                if (i==1 || i-2 > 0 && nums[i] >= nums[i-2]) {
                    nums[i-1] = nums[i];
                    count++;
                }
                // 这个数字突然变小
                else {
                    nums[i] = nums[i-1];
                    count++;
                }
            }
        }
        return count <= 1;
    }
}