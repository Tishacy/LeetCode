package dp;

/**
 * 413. 等差数列划分（数组中等差递增子区间的个数）
 * 如果一个数列至少有三个元素，并且任意两个相邻元素之差相同，则称该数列为等差数列。
 * 例如，以下数列为等差数列:
 * 1, 3, 5, 7, 9
 * 7, 7, 7, 7
 * 3, -1, -5, -9
 *
 * 以下数列不是等差数列。
 * 1, 1, 2, 5, 7
 *
 * 数组 A 包含 N 个数，且索引从0开始。数组 A 的一个子数组划分为数组 (P, Q)，P 与 Q 是整数且满足 0<=P<Q<N 。
 * 如果满足以下条件，则称子数组(P, Q)为等差数组：
 * 元素 A[P], A[p + 1], ..., A[Q - 1], A[Q] 是等差的。并且 P + 1 < Q 。
 * 函数要返回数组 A 中所有为等差数组的子数组个数。
 *
 * 示例:
 * A = [1, 2, 3, 4]
 * 返回: 3, A 中有三个子等差数组: [1, 2, 3], [2, 3, 4] 以及自身 [1, 2, 3, 4]。
 */
public class ArithmeticSlices_413 {
    /**
     * 思路：动态规划
     * 核心：dp[i] 表示以 A[i] 为结尾的等差递增子区间的个数。
     * 状态转移：
     *      当 A[i] - A[i-1] == A[i-1] - A[i-2]，那么 [A[i-2], A[i-1], A[i]] 构成一个等差递增子区间。而且在以 A[i-1] 为结尾的递增子区间的后面再加上一个 A[i]，一样可以构成新的递增子区间。
     *      dp[2] = 1
     *          [0, 1, 2]
     *      dp[3] = dp[2] + 1 = 2
     *          [0, 1, 2, 3], // [0, 1, 2] 之后加一个 3
     *          [1, 2, 3]     // 新的递增子区间
     *      dp[4] = dp[3] + 1 = 3
     *          [0, 1, 2, 3, 4], // [0, 1, 2, 3] 之后加一个 4
     *          [1, 2, 3, 4],    // [1, 2, 3] 之后加一个 4
     *          [2, 3, 4]        // 新的递增子区间
     *      综上，在 A[i] - A[i-1] == A[i-1] - A[i-2] 时，dp[i] = dp[i-1] + 1。
     * 结果返回：
     *      因为递增子区间不一定以最后一个元素为结尾，可以是任意一个元素结尾，因此需要返回 dp 数组累加的结果。
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 3, 4};
        ArithmeticSlices_413 solution = new ArithmeticSlices_413();
        System.out.println("[Solution] Number of arithmetic slices: " + solution.numberOfArithmeticSlices(nums));
    }

    public int numberOfArithmeticSlices(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        int[] dp = new int[n];
        for (int i=2; i<n; i++) {
            if (A[i] - A[i-1] == A[i-1] - A[i-2]) {
                dp[i] = dp[i-1] + 1;
            }
        }
        int count = 0;
        for (int cnt:dp) {
            count += cnt;
        }
        return count;
    }
}
