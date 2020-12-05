package search;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;


/**
 * 在一个 N × N 的方形网格中，每个单元格有两种状态：空（0）或者阻塞（1）。
 *
 * 一条从左上角到右下角、长度为 k 的畅通路径，由满足下述条件的单元格 C_1, C_2, ..., C_k 组成：
 *
 * 相邻单元格 C_i 和 C_{i+1} 在八个方向之一上连通（此时，C_i 和 C_{i+1} 不同且共享边或角）
 * C_1 位于 (0, 0)（即，值为 grid[0][0]）
 * C_k 位于 (N-1, N-1)（即，值为 grid[N-1][N-1]）
 * 如果 C_i 位于 (r, c)，则 grid[r][c] 为空（即，grid[r][c] == 0）
 * 返回这条从左上角到右下角的最短畅通路径的长度。如果不存在这样的路径，返回 -1 。
 *
 *
 * 输入：[[0,1],
 *       [1,0]]
 * 输出：2
 *
 * 输入：[[0,0,0],
 *       [1,1,0],
 *       [1,1,0]]
 * 输出：4
 */
public class ShortestPathInBinaryMatrix_1091 {
    public static void main(String[] args) {
        ShortestPathInBinaryMatrix_1091 solution = new ShortestPathInBinaryMatrix_1091();
        int[][] grid = {{0,0,0}, {1,1,0}, {1,1,0}};
//        int[][] grid = {{0,1}, {1,0}};
//        int[][] grid = {{1,0,0},{1,1,0},{1,1,0}};
        int shortestLen = solution.shortestPathBinaryMatrix(grid);
        System.out.println("[Solution] Shortest path length: " + shortestLen);
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0 || grid[0][0] == 1 || grid[grid.length - 1][grid[0].length - 1] == 1) {
            return -1;
        }
        int[][] directions = {{1,1}, {1,0}, {0,1}, {1,-1}, {-1,1}, {-1,0}, {0,-1}, {-1,-1}};
        Deque<Pair<Integer, Integer>> queue = new ArrayDeque<>();
        queue.add(new Pair<>(0, 0));
        int shortestPathLen = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            shortestPathLen++;
            for (int i=0; i<size; i++) {
                Pair<Integer, Integer> curCoords = queue.poll();
                int curValue = grid[curCoords.x][curCoords.y];
                if (curValue == 1) {
                    continue;
                }
                if (curCoords.x == grid[0].length - 1 && curCoords.y == grid.length - 1) {
                    return shortestPathLen;
                }
                grid[curCoords.x][curCoords.y] = 1;
                for (int[] direction : directions) {
                    Pair<Integer, Integer> nextCoords = new Pair<>(curCoords.x + direction[0], curCoords.y + direction[1]);
                    if (isOutOfMatrix(grid, nextCoords)) {
                        continue;
                    }
                    queue.add(nextCoords);
                }
            }
        }
        return -1;
    }

    private boolean isOutOfMatrix(int[][] grid, Pair<Integer, Integer> coords) {
        return (coords.x < 0 || coords.x >= grid[0].length || coords.y < 0 || coords.y >= grid.length);
    }

    private class Pair<T, M> {
        public T x;
        public M y;

        public Pair(T x, M y) {
            this.x = x;
            this.y = y;
        }
    }

}
