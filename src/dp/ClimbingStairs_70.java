package dp;

/**
 * 70. 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 *
 *  示例 2：
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 */
public class ClimbingStairs_70 {

    public static void main(String[] args) {
        ClimbingStairs_70 solution = new ClimbingStairs_70();
        System.out.println("[Solution] n = 1: " + solution.climbStairs(1));
        System.out.println("[Solution] n = 2: " + solution.climbStairs(2));
        System.out.println("[Solution] n = 3: " + solution.climbStairs(3));
        System.out.println("[Solution] n = 30: " + solution.climbStairs(30));

        System.out.println("[Solution] n = 1: " + solution.climbStairs2(1));
        System.out.println("[Solution] n = 2: " + solution.climbStairs2(2));
        System.out.println("[Solution] n = 3: " + solution.climbStairs2(3));
        System.out.println("[Solution] n = 30: " + solution.climbStairs2(30));
    }

    /**
     * 方法 1：动态规划
     * 动态规划数组 dp[] 数组存储的是爬到第 n 个台阶的方法数
     * 当 n = 0 时（因为 n 是正整数，所以不会出现 n = 0，这里做假设用），共有 1 种方法
     * 当 n = 1 时，共有 1 种方法
     * 当 n > 1 时，当前台阶要么从上一个台阶踏1步过来，要么从前两个台阶踏2步过来，因此：
     *          n方法数 = n-1方法数 + n-2方法数，状态方程为：
     *          dp[i] = dp[i-1] + dp[i-2],  i 为第 i 个台阶
     *          边界条件是：dp[0] = 1, dp[1] = 1
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     * @param n
     * @return
     */
    private int climbStairs(int n) {
        if (n <= 1) {
            return n;
        }
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i=2; i<=n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    /**
     * 方法 2：动态规划 + 滚动数组
     * 由方法 1 可知，当前状态只跟它的前两个状态有关系，因此可以使用滚动数组
     * 方式来解决，这样不需要一个 dp[]，空间复杂度降低为 O(1)
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     * @param n
     * @return
     */
    private int climbStairs2(int n) {
        if (n <= 1) {
            return 1;
        }
        int first = 1, second = 1;
        for (int i=2; i<=n; i++) {
            int res = first + second;
            first = second;
            second = res;
        }
        return second;
    }
}
