package search;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 130. 被围绕的区域
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 *
 * 示例:
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 *
 * 运行你的函数后，矩阵变为：
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 *
 * 解释:
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 */
public class SurroundedRegions_130 {

    /**
     * 思路：
     *  将边界上为 O 的节点进行连通性搜索（dfs）
     *  将搜索到的节点标记为 M
     *  标记为 M 的节点就是不被围绕的区域
     *  剩余的所有节点都用 X 填充
     * @param args
     */
    public static void main(String[] args) { 
        char[][] board = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        SurroundedRegions_130 solution = new SurroundedRegions_130();
        System.out.println("[Problem] board: ");
        printBoard(board);

        solution.solve(board);
        System.out.println("[Solution] Filled board: ");
        printBoard(board);
    }

    private int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public static void printBoard(char[][] board) {
        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }

    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }

        // 将边界上为 O 的节点进行连通性搜索（dfs）,
        // 将搜索到的节点标记为 M
        int numRows = board.length;
        int numCols = board[0].length;
        for (int i=0; i<numRows; i++) {
            dfs(board, i, 0);
            dfs(board, i, numCols - 1);
        }
        for (int i=0; i<numCols; i++) {
            dfs(board, 0, i);
            dfs(board, numRows-1, i);
        }
        // 标记为 M 的节点就是不被围绕的区域
        // 剩余的所有节点都用 X 填充
        for (int i=0; i<numRows; i++) {
            for (int j=0; j<numCols; j++) {
                if (board[i][j] != 'M') {
                    board[i][j] = 'X';
                } else {
                    board[i][j] = 'O';
                }
            }
        }
    }

    /**
     * 从 (r, c) 位置开始深度优先搜索（递归），
     * @param board
     * @param r
     * @param c
     */
    private void dfs(char[][] board, int r, int c) {
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != 'O') {
            return;
        }
        board[r][c] = 'M';
        for (int[] direction : directions) {
            dfs(board, r + direction[0], c + direction[1]);
        }
    }
}
