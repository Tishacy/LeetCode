package dp;

/**
 * 376. 摆动序列
 * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。第一个差（如果存在的话）可能是正数或负数。少于两个元素的序列也是摆动序列。
 * 例如， [1,7,4,9,2,5] 是一个摆动序列，因为差值 (6,-3,5,-7,3) 是正负交替出现的。相反, [1,4,7,2,5] 和 [1,7,4,5,5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
 * 给定一个整数序列，返回作为摆动序列的最长子序列的长度。 通过从原始序列中删除一些（也可以不删除）元素来获得子序列，剩下的元素保持其原始顺序。
 *
 * 示例 1:
 * 输入: [1,7,4,9,2,5]
 * 输出: 6
 * 解释: 整个序列均为摆动序列。
 *
 * 示例 2:
 * 输入: [1,17,5,10,13,15,10,5,16,8]
 * 输出: 7
 * 解释: 这个序列包含几个长度为 7 摆动序列，其中一个可为[1,17,10,13,10,16,8]。
 *
 * 示例 3:
 * 输入: [1,2,3,4,5,6,7,8,9]
 * 输出: 2
 *
 * 进阶:
 * 你能否用 O(n) 时间复杂度完成此题?
 */
public class WiggleSubsequence_376 {

    public static void main(String[] args) {
        WiggleSubsequence_376 solution = new WiggleSubsequence_376();

        int[] nums = {102,101,20,91,156,78,75,142,69,81,46,142,113,163,190,178,13,36,134,54};
        System.out.println("[Solution] Wiggle max length: " + solution.wiggleMaxLength(nums));
        System.out.println("[Solution] Wiggle max length: " + solution.wiggleMaxLengthGreedy(nums));
    }


    /**
     * 思路：为了让摆动序列的长度尽可能长，那么只需要统计给定序列中有多少个转折即可。
     * 状态转移函数为:
     * 如果呈上升趋势，那么转折的数目为：上次从波谷转折时的转折次数 + 1
     * 如果呈下降趋势，那么转折的数目为：上次从波峰转折时的转折次数 + 1
     *
     * @param nums
     * @return
     */
    public int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int up = 1;
        int down = 1;
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[i - 1]) {
                down = up + 1;
            } else if (nums[i] > nums[i - 1]) {
                up = down + 1;
            }
        }
        return Math.max(up, down);
    }

    /**
     * 贪心：统计给定序列中有多少个转折。
     * @param nums
     * @return
     */
    public int wiggleMaxLengthGreedy(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        int preDiff = nums[1] - nums[0];
        int maxLength = (preDiff == 0) ? 1 : 2;
        for (int i = 2; i < n; i++) {
            int curDiff = nums[i] - nums[i - 1];
            if (preDiff >= 0 && curDiff < 0 || preDiff <= 0 && curDiff > 0) {
                maxLength++;
                preDiff = curDiff;
            }
        }
        return maxLength;
    }
}
