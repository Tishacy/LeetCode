package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 279. 完全平方数
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * 示例 1:
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 *
 * 示例 2:
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 */
public class PerfectSquares_279 {

    /**
     * 思路：设对于正整数 n，其可组成和的最少完全平方数的个数为 f(n)，那么
     * f(n) = min(f(n-1) + 1, f(n-4) + 1, f(n-9) + 1, ..., f(n-k) + 1)
     * 问题可以转化为最优子问题，因此可以使用动态规划。
     * 设有 dp数组，dp[i] 表示：正整数 i 可以拆分的完全平方数的最少个数，状态转移函数为：
     *    当 i=1 时，dp[i] = 1
     *    当 i>=2 时，dp[i] = min(dp[i], dp[i-k] + 1), k 为 [0, i] 间的所有完全平方数
     * @param args
     */
    public static void main(String[] args) {
        int n = 12;
        PerfectSquares_279 solution = new PerfectSquares_279();
        int numSquares = solution.numSquares(n);
        System.out.println("[Solution] Number of squares: " + numSquares);
    }

    public int numSquares(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 1;
        List<Integer> squares = getSquares(n);
        for (int i=2; i<=n; i++) {
            for (int square : squares) {
                if (square > i) {
                    break;
                }
                dp[i] = Math.min(dp[i], dp[i-square] + 1);
            }
        }
        return dp[n];
    }

    private List<Integer> getSquares(int n) {
        List<Integer> squares = new ArrayList<>();
        for (int i=1; i<=Math.sqrt(n); i++) {
            squares.add(i * i);
        }
        return squares;
    }
}
