package dp;

/**
 * 64. 最小路径和
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 *
 * 示例 1：
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 *
 * 示例 2：
 * 输入：grid = [[1,2,3],[4,5,6]]
 * 输出：12
 *
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 100
 */
public class MinPathSum_64 {

    public static void main(String[] args) {
        MinPathSum_64 solution = new MinPathSum_64();
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println("[Solution] Min path sum: "  + solution.minPathSum(grid));
    }

    /**
     * 思路:
     *  遍历二维矩阵，对于每一个元素，表示到(i,j)位置的最小路径和
     *  则状态转移方程为：
     *      dp[i][j] = + grid[i][j],             if i==0, j==0
     *      dp[i][j] = dp[i-1][j] + grid[i][j],    if j=0, i>0 (第一列，只能从上边来)
     *      dp[i][j] = dp[i][j-1] + grid[i][j],    if i=0, j>0 (第一行，只能从左边来)
     *      dp[i][j] = Min(dp[i-1][j] + dp[i][j-1]) + grid[i][j]  other conditions
     *          (普通情况，当前最小路径和为从 上边来的最小路径和 与 从左边来的最小路径和 的最小值 + 当前值)
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        if (null == grid || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;

        // dp 矩阵中的(i,j)位置元素表示：到达坐标(i,j)的最小路径和
        int[][] dp = new int[m][n];
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                } else if (i == 0 && j > 0) {
                    // 第一行，只能从左边移动过来，其最小路径和为左边元素的最小路径和+本元素值
                    dp[i][j] = dp[i][j-1] + grid[i][j];
                } else if (j == 0 && i > 0) {
                    // 第一列，只能从上方移动过来，其最小路径和为上边元素的最小路径和+本元素值
                    dp[i][j] = dp[i-1][j] + grid[i][j];
                } else {
                    // 其他情况，可以从左边或上边移动过来，其最小路径和为
                    // min(上边元素的最小路径和, 左边元素的最小路径和) + 本元素值
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
                }
            }
        }
        return dp[m-1][n-1];
    }
}
