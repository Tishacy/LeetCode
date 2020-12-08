package search;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 695. 岛的最大面积
 *  给定一个包含了一些 0 和 1 的非空二维数组 grid 。
 * 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)
 *
 * 示例 1:
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 对于上面这个给定矩阵应返回 6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。
 *
 * 示例 2:
 * [[0,0,0,0,0,0,0,0]]
 * 对于上面这个给定的矩阵, 返回 0。
 */
public class MaxAreaOfIsland_695 {
    /**
     * 思路：
     *  遍历数组中的每一个元素，计算该元素所能够**连通**的岛屿的面积
     *  并返回岛屿面积的最大值。
     *  因为是对矩阵元素连通性的计算，可以类似的使用图的连通性。如果一个图
     *  是连通的，那么通过图的遍历，一定能够访问到这个图的所有节点，因此，
     *  使用 DFS 或 BFS 都可以将所有连通的节点访问一遍。
     *  放在此题中，使用 DFS 或 BFS 都可以将矩阵中某元素连通的元素访问一遍，
     *  每访问一个元素就可以使岛的面积增加 1，直至所有连通的元素访问完，就得到
     *  了岛屿的面积。
     *
     * tip: 对于图来说，通常使用 Set 或 Map 来记录访问过的元素。但此题中
     * 是矩阵（二维数组），那么可以不额外使用空间，直接使用此数组记录已访问过的元素。
     * 对于已访问过的元素，将元素值置为 0.
     * @param args
     */
    public static void main(String[] args) {
        MaxAreaOfIsland_695 solution = new MaxAreaOfIsland_695();

        // 使用递归 DFS 获取岛屿最大面积
        int[][] grid = {{1,1,0,0,0}, {1,1,0,0,0}, {0,0,0,1,1}, {0,0,0,1,1}};
        System.out.println("[Solution] Max area with recursive DFS: " + solution.maxAreaOfIsland(grid, GET_ISLAND_AREA_METHOD.REC_DFS));

        // 使用stack的 DFS 获取岛屿最大面积
        grid = new int[][] {{1,1,0,0,0}, {1,1,0,0,0}, {0,0,0,1,1}, {0,0,0,1,1}};
        System.out.println("[Solution] Max area with stack DFS: " + solution.maxAreaOfIsland(grid, GET_ISLAND_AREA_METHOD.STACK_DFS));

        // 使用queue的 BFS 获取岛屿最大面积
        grid = new int[][] {{1,1,0,0,0}, {1,1,0,0,0}, {0,0,0,1,1}, {0,0,0,1,1}};
        System.out.println("[Solution] Max area with queue BFS: " + solution.maxAreaOfIsland(grid, GET_ISLAND_AREA_METHOD.QUEUE_BFS));
    }

    private int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private interface GET_ISLAND_AREA_METHOD {
        int REC_DFS = 0;
        int STACK_DFS = 1;
        int QUEUE_BFS = 2;
    }

    public int maxAreaOfIsland(int[][] grid, int getIslandAreaMethod) {
        int maxArea = 0;
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                // 使用递归的 DFS 计算岛屿面积
                if (getIslandAreaMethod == GET_ISLAND_AREA_METHOD.REC_DFS) {
                    maxArea = Math.max(maxArea, getIslandAreaWithRecDFS(grid, i, j));
                } else if (getIslandAreaMethod == GET_ISLAND_AREA_METHOD.STACK_DFS) {
                    maxArea = Math.max(maxArea, getIslandAreaWithStackDFS(grid, i, j));
                } else if (getIslandAreaMethod == GET_ISLAND_AREA_METHOD.QUEUE_BFS){
                    maxArea = Math.max(maxArea, getIslandAreaWithBFS(grid, i, j));
                }
            }
        }
        return maxArea;
    }

    /**
     * 使用递归 DFS 获取岛屿面积
     */
     private int getIslandAreaWithRecDFS(int[][] grid, int r, int c) {
         // r, c 越界
         if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) {
             return 0;
         }
         if (grid[r][c] == 0) {
             return 0;
         }
         grid[r][c] = 0;
         int area = 1;
         for (int[] direction : directions) {
             area += getIslandAreaWithRecDFS(grid, r + direction[0], c + direction[1]);
         }
         return area;
     }

    /**
     * 使用栈的 DFS 获取岛屿面积
     */
     private int getIslandAreaWithStackDFS(int[][] grid, int r, int c) {
         Deque<int[]> stack = new ArrayDeque<>();
         stack.push(new int[]{r, c});
         int area = 0;
         while (!stack.isEmpty()) {
              int[] curPos = stack.pop();
             if (curPos[0] < 0 || curPos[0] >= grid.length || curPos[1] < 0 || curPos[1] >= grid[0].length) {
                 continue;
             }
             if (grid[curPos[0]][curPos[1]] == 0) {
                 continue;
             }
             area++;
             grid[curPos[0]][curPos[1]] = 0;
             for (int[] direction : directions) {
                 stack.push(new int[] {curPos[0] + direction[0], curPos[1] + direction[1]});
             }
         }
         return area;
     }

    /**
     * 使用队列的 BFS 获取岛屿面积
     */
    private int getIslandAreaWithBFS(int[][] grid, int r, int c) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{r, c});
        int area = 0;
        while (!queue.isEmpty()) {
            int[] curPos = queue.poll();
            if (curPos[0] < 0 || curPos[0] >= grid.length || curPos[1] < 0 || curPos[1] >= grid[0].length) {
                continue;
            }
            if (grid[curPos[0]][curPos[1]] == 0) {
                continue;
            }
            area++;
            grid[curPos[0]][curPos[1]] = 0;
            for (int[] direction : directions) {
                queue.add(new int[] {curPos[0] + direction[0], curPos[1] + direction[1]});
            }
        }
        return area;
    }
}
