package dp;

import java.util.ArrayList;
import java.util.List;

/**
 * 62. 不同路径
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 *
 * 示例 1：
 * 输入：m = 3, n = 7
 * 输出：28
 *
 * 示例 2：
 * 输入：m = 3, n = 2
 * 输出：3
 * 解释：
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 *
 * 示例 3：
 * 输入：m = 7, n = 3
 * 输出：28
 *
 * 示例 4：
 * 输入：m = 3, n = 3
 * 输出：6
 *
 * 提示：
 * 1 <= m, n <= 100
 * 题目数据保证答案小于等于 2 * 109
 */
public class UniquePaths_62 {

    public static void main(String[] args) {
        UniquePaths_62 solution = new UniquePaths_62();
        int m = 7, n = 3;
        System.out.println("[Solution] (DFS) Num of paths: " + solution.uniquePaths1(m, n));
        System.out.println("[Solution] (DP) Num of paths: " + solution.uniquePaths2(m, n));
    }

    private List<Integer> paths = new ArrayList<>();
    private int[][] directions = {{0, 1}, {1, 0}};

    /**
     * 方法1：DFS 回溯（超过内存限制）
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths1(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        dfs(0, 0, m, n);
        return paths.size();
    }

    public void dfs(int i, int j, int m, int n) {
        if (i == m-1 && j == n-1) {
            paths.add(1);
        }
        for (int[] d : directions) {
            int[] nextPos = {i + d[0], j + d[1]};
            if (nextPos[0] >= 0 && nextPos[0] < m && nextPos[1] >= 0 && nextPos[1] < n) {
                dfs(nextPos[0], nextPos[1], m, n);
            }
        }
    }


    /**
     * 方法2: DP 动态规划
     * 思路:
     *    遍历二维矩阵，对于每一个元素，表示到(i,j)位置的路径数
     *    则状态转移方程为：
     *    dp[i][j] = 1,             if i==0, j==0
     *    dp[i][j] = dp[i-1][j],    if j=0, i>0 (第一列，只能从上边来)
     *    dp[i][j] = dp[i][j-1],    if i=0, j>0 (第一行，只能从左边来)
     *    dp[i][j] = dp[i-1][j] + dp[i][j-1]  other conditions (普通情况，当前路径数为从上边来的路径数+从左边来的路径数)
     *  时间复杂度：O(N²)，但因为使用了记忆矩阵，大大减小了重复计算次数，所以会比普通 dfs 快很多。
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths2(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        int[][] dp = new int[m][n];
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                } else if (i == 0 && j > 0) {
                    dp[i][j] = dp[i][j-1];
                } else if (i > 0 && j == 0) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = dp[i][j-1] + dp[i-1][j];
                }
            }
        }
        return dp[m-1][n-1];
    }
}
