package search;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 200. 岛的数量
 *
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 *  示例 1：
 * 输入：grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * 输出：1
 * 示例 2：
 *
 * 输入：grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * 输出：3
 *  
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 */
public class NumOfIslands {

    /**
     * 思路：矩阵某元素的连通性，可类比图的连通性，
     * 可以使用 DFS 或 BFS 来解决
     * @param args
     */
    public static void main(String[] args) {
        char[][] grid = {{'1','1','0','0','0'}, {'1','1','0','0','0'}, {'0','0','1','0','0' }, {'0','0','0','1','1'}};
        NumOfIslands solution = new NumOfIslands();
        int numIslands = solution.numIslands(grid);
        System.out.println("[Solution] Num of islands: " + numIslands);
    }

    private int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int numIslands(char[][] grid) {
        int numIslands = 0;
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                boolean isIsland = traverseIsland(grid, i, j);
                if (isIsland) {
                    numIslands++;
                }
            }
        }
        return numIslands;
    }

    /**
     * 遍历一个岛：遍历矩阵中(r,c)位置连通的所有元素
     * 如果(r,c)位置为'0'，说明不是岛，或者是已被遍历过的岛，返回 false
     * 反之就遍历这个岛
     */
    public boolean traverseIsland(char[][] grid, int r, int c) {
        if (grid[r][c] == '0') {
            return false;
        }
        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[] {r, c});
        while (!stack.isEmpty()) {
            int[] curPos = stack.pop();
            if (curPos[0] < 0 || curPos[0] >= grid.length || curPos[1] < 0 || curPos[1] >= grid[0].length) {
                continue;
            }
            if (grid[curPos[0]][curPos[1]] == '0') {
                continue;
            }
            grid[curPos[0]][curPos[1]] = '0';
            for (int[] direction : directions) {
                stack.push(new int[] {curPos[0] + direction[0], curPos[1] + direction[1]});
            }
        }
        return true;
    }
}
