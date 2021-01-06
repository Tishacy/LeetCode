package dp;

/**
 * 343. 整数拆分
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 *
 * 示例 1:
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 *
 * 示例 2:
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 */
public class IntegerBreak_343 {

    /**
     * 思路：此题与经典的剪绳子问题几乎一样。
     * 设对于正整数n，其拆分整数的最大乘积为 f(n)，那么：
     *  f(n) = max(1 * f(n-1), 2 * f(n-2), 3 * f(n-3), ..., n-1 * f(1))
     * 因此，可以将一个原问题拆分成相似子问题，所以可以用动态规划解决。
     * 有 dp 数组，dp[i]表示：正整数 i 所能拆分整数的最大乘积，其状态转移函数为：
     *      当 i = 1 时，无法继续拆分，dp[i] = 1;
     *      当 i = 2 时，拆分成 1 × 1，dp[i] = 1;
     *      当 i > 2 时，有两种拆分方案：
     *          （1）将 i 拆分成 k 和 i-k, 其中 i-k 不继续拆分，获得乘积为 k*(i-k)
     *          （2）将 i 拆分成 k 和 i-k，其中 i-k 可以继续拆分，获得乘积为 k*f(i-k)
     *          所以对于拆分方案 k，应当去这两种拆分方案的最大值，即：
     *              dp[i(k)] = max(k * (i-k), k * dp(i-k)), 其中 k 可以取 [1, i-1]
     *          那么对于所有的拆分方案 k，有：
     *              dp[i] = max(dk[i(k)])  k ∈ [1, i-1]
     *          也可以表述称：
     *              dp[i] = max(dp[i], max(k * (i-k), k * dp(i-k))) k ∈ [1, i-1]
     * @param args
     */
    public static void main(String[] args) {
        int n = 10;
        IntegerBreak_343 solution = new IntegerBreak_343();
        int res = solution.integerBreak(n);
        System.out.println(String.format("[Solution] %s: %s", n, res));
    }

    private int integerBreak(int n) {
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 1;
        for (int i=3; i<=n; i++) {
            for (int k=1; k<i; k++) {
                dp[i] = Math.max(dp[i], Math.max(k * (i-k), k * dp[i-k]));
            }
        }
        return dp[n];
    }
}
