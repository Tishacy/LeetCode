package search;

import java.util.*;

/**
 * 417. 太平洋大西洋水流问题
 * 给定一个 m x n 的非负整数矩阵来表示一片大陆上各个单元格的高度。“太平洋”处于大陆的左边界和上边界，而“大西洋”处于大陆的右边界和下边界。
 * 规定水流只能按照上、下、左、右四个方向流动，且只能从高到低或者在同等高度上流动。
 * 请找出那些水流既可以流动到“太平洋”，又能流动到“大西洋”的陆地单元的坐标。
 *
 * 提示：
 * 输出坐标的顺序不重要
 * m 和 n 都小于150
 *
 * 示例：
 * 给定下面的 5x5 矩阵:
 *   太平洋 ~   ~   ~   ~   ~
 *        ~  1   2   2   3  (5) *
 *        ~  3   2   3  (4) (4) *
 *        ~  2   4  (5)  3   1  *
 *        ~ (6) (7)  1   4   5  *
 *        ~ (5)  1   1   2   4  *
 *           *   *   *   *   * 大西洋
 *
 * 返回:
 * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (上图中带括号的单元).
 */
public class PacificAtlanticWaterFlow_417 {

    /**
     * 思路：
     *  从矩阵的边界进行连通性搜索（dfs），即水从每一个边界节点开始逆高度流动
     *  凡是搜索到的节点都是水可以留到边界的。
     *  上边界和左边界搜索的时候，将搜索到的节点添加到 canReachPacific 中，即这些节点的水可以流到太平洋
     *  下边界和右边界搜索的时候，将搜索到的节点添加到 canReachAtlantic 中，即这些节点的水可以流到大西洋
     *  然后遍历所有节点，找出既在 canReachPacific 又在 canReachAtlantic 中
     *  就找到了既可以流动到“太平洋”，又能流动到“大西洋”的陆地单元的坐标。
     * @param args
     */
    public static void main(String[] args) {
        int[][] matrix = {{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        PacificAtlanticWaterFlow_417 solution = new PacificAtlanticWaterFlow_417();
        List<List<Integer>> result = solution.pacificAtlantic(matrix);
        System.out.println("[Solution] Result: " + result.toString());
    }

    private int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    private Set<List<Integer>> canReachPacific = new HashSet<>();
    private Set<List<Integer>> canReachAtlantic = new HashSet<>();

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return null;
        }
        List<List<Integer>> result = new ArrayList<>();
        int numRows = matrix.length;
        int numCols = matrix[0].length;
        // 左边界和右边界搜索
        for (int i=0; i<numRows; i++) {
            dfs(matrix, i, 0, canReachPacific);
            dfs(matrix, i, numCols-1, canReachAtlantic);
        }
        // 上边界和下边界搜索
        for (int i=0; i<numCols; i++) {
            dfs(matrix, 0, i, canReachPacific);
            dfs(matrix, numRows-1, i, canReachAtlantic);
        }
        // 找出既在 canReachPacific 又在 canReachAtlantic 中的坐标
        for (int i=0; i<numRows; i++) {
            for (int j=0; j<numCols; j++) {
                List<Integer> coord = Arrays.asList(i, j);
                if (canReachPacific.contains(coord) && canReachAtlantic.contains(coord)) {
                    result.add(coord);
                }
            }
        }
        return result;
    }

    private void dfs(int[][] matrix, int r, int c, Set<List<Integer>> reachSet) {
        List<Integer> coord = Arrays.asList(r, c);
        if (r < 0 || r >= matrix.length || c < 0 || c >= matrix[0].length || reachSet.contains(coord)) {
            return;
        }
        reachSet.add(coord);
        for (int[] d : directions) {
            int nextRow = r + d[0], nextCol = c + d[1];
            List<Integer> nextCoord = Arrays.asList(nextRow, nextCol);
            // 不越界且逆高度连通的节点可以继续 dfs
            if (nextRow < 0 || nextRow >= matrix.length || nextCol < 0 || nextCol >= matrix[0].length ||
                matrix[nextRow][nextCol] >= matrix[r][c]) {
                dfs(matrix, nextRow, nextCol, reachSet);
            }
        }
    }
}
