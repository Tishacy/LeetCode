package offer;

/**
 * 剑指 Offer 04. 二维数组中的查找
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 * 示例:
 * 现有矩阵 matrix 如下：
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 *
 * 给定 target = 5，返回 true。
 * 给定 target = 20，返回 false。
 *
 * 限制：
 * 0 <= n <= 1000
 * 0 <= m <= 1000
 */
public class FindNmberIn2DArray_4 {

    public static void main(String[] args) {
        FindNmberIn2DArray_4 solution = new FindNmberIn2DArray_4();
        int[][] matrix = {
            {1,   4,  7, 11, 15},
            {2,   5,  8, 12, 19},
            {3,   6,  9, 16, 22},
            {10, 13, 14, 17, 24},
            {18, 21, 23, 26, 30}
        };
        System.out.println("[Solution] 5 exists: " + solution.findNumberIn2DArray(matrix, 5));
        System.out.println("[Solution] 20 exists: " + solution.findNumberIn2DArray(matrix, 20));
    }

    /**
     * 思路：首先选取数组右上角数字。如果该数字等于目标元素，则查找过程结束；
     *  如果该数字小于目标数字，则剔除该数字所在行，即向该数字下方的区域寻找；
     *  如果该数字大于目标数字，则剔除该数字所在列，即向该数字左边的区域寻找。
     * @param matrix 给定矩阵
     * @param target 目标数字
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int nRows = matrix.length, nCols = matrix[0].length;
        int row = 0, col = nCols-1;
        while (row >= 0 && row < nRows && col >= 0 && col < nCols) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                row++;
            } else{
                col--;
            }
        }
        return false;
    }
}
