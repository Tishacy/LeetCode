package offer;

/**
 * 剑指 Offer 10- II. 青蛙跳台阶问题
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 示例 1：
 * 输入：n = 2
 * 输出：2
 *
 * 示例 2：
 * 输入：n = 7
 * 输出：21
 *
 * 示例 3：
 * 输入：n = 0
 * 输出：1
 *
 * 提示：
 * 0 <= n <= 100
 */
public class NumWays_10 {

    public static void main(String[] args) {
        int[] ns = {0, 2, 7, 100};
        NumWays_10 solution = new NumWays_10();
        for (int n : ns) {
            System.out.printf("[Solution] Number of Ways of %s: %s%n", n, solution.numWays(n));
        }
    }

    /**
     * 思路：对于每一级台阶，可以通过上一级台阶过来，也可以通过上上级台阶过来。
     * 设 dp[i] 为第 i 级台阶的跳法数目，那么有状态转移函数：
     *  dp[i] = dp[i-1] + dp[i-2]   i >= 2
     *  dp[i] = 1    i < 2
     * @param n
     * @return
     */
    public int numWays(int n) {
        if (n < 2) {
            return 1;
        }
        int[] dp = new int[n+1];
        dp[0] = 1; dp[1] = 1;
        for (int i=2; i<=n; i++) {
            dp[i] = (dp[i-1] + dp[i-2]) % 1000000007;
        }
        return dp[n];
    }

}
