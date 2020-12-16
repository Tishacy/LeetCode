package search;

/**
 * 79. 单词搜索
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中.
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 * 示例:
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 *
 * 提示：
 * board 和 word 中只包含大写和小写英文字母。
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 1 <= word.length <= 10^3
 */
public class WordSearch_79 {

    /**
     * 思路：
     *  在一个二维矩阵中找一条确定的路径，需要遍历所有的路径，直到找到目标路径为止。
     *  而可以通过 DFS + 回溯的方法遍历所有可能的路径。
     *
     * 对于回溯函数，统一使用 curPos 尚未添加到 path 中的状态。
     * 回溯函数的作用就是判断当前位置的节点是否加入到 path 中
     *      如果达到最终的目标，直接返回目标结果
     *      如果不能加入到路径中，直接剪枝返回
     *      如果可以加入路径中，将当前节点加入到路径中，找该节点的下一批节点，然后继续搜索，搜索完成后回溯上一个状态。
     * @param args
     */
    public static void main(String[] args) {
        WordSearch_79 solution = new WordSearch_79();
        char[][] board = {
            {'A','B','C','E'},
            {'S','F','C','S'},
            {'A','D','E','E'}
        };
        String word = "SEECCBA";
        boolean exist = solution.exist(board, word);
        System.out.println("[Solution] Exist: " + exist);
    }

    private int[][] directions = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    public boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0) {
            return true;
        }
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                if (backTracking(board, i, j, 0, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean backTracking(char[][] board, int r, int c, int pathLen, String word) {
        if (pathLen == word.length()) {
            return true;
        }
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] == '-') {
            return false;
        }
        char curValidChar = word.charAt(pathLen);
        if (board[r][c] != curValidChar) {
            return false;
        }
        char temp = board[r][c];
        board[r][c] = '-';
        for (int[] d : directions) {
            if (backTracking(board, r+d[0], c+d[1], pathLen+1, word)) {
                return true;
            }
        }
        board[r][c] = temp;
        return false;
    }

}
