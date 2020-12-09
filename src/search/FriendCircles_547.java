package search;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 547. 朋友圈
 * 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。
 * 给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。你必须输出所有学生中的已知的朋友圈总数。
 *
 * 示例 1:
 * 输入：
 * [[1,1,0],
 *  [1,1,0],
 *  [0,0,1]]
 * 输出：2
 * 解释：已知学生 0 和学生 1 互为朋友，他们在一个朋友圈。
 * 第2个学生自己在一个朋友圈。所以返回 2 。
 *
 * 示例 2：
 * 输入：
 * [[1,1,0],
 *  [1,1,1],
 *  [0,1,1]]
 * 输出：1
 * 解释：已知学生 0 和学生 1 互为朋友，学生 1 和学生 2 互为朋友，所以学生 0 和学生 2 也是朋友，所以他们三个在一个朋友圈，返回 1 。
 *  
 * 提示：
 * 1 <= N <= 200
 * M[i][i] == 1
 * M[i][j] == M[j][i]
 */
public class FriendCircles_547 {
    /**
     * 思路:
     *  所给的矩阵为一个图的邻接矩阵。根据该邻接矩阵可以通过 DFS 或 BFS 来
     *  获得图中有多少个不连通的子图，即朋友圈的数量
     * @param args
     */
    public static void main(String[] args) {
        int[][] M = {{1,1,0}, {1,1,1}, {0,1,1}};
        FriendCircles_547 solution = new FriendCircles_547();
        int numCircles = solution.findCircleNum(M);
        System.out.println("[Solution] Num of circles: " + numCircles);
    }

    public int findCircleNum(int[][] M) {
        int numCircles = 0;
        // 对每一个学生进行 dfs
        int n = M.length;
        boolean[] visited = new boolean[n];
        for (int i=0; i<n; i++) {
            if (!visited[i]) {
                dfs2(M, i, visited);
                numCircles++;
            }
        }
        return numCircles;
    }

    /**
     * 从第 i 个节点开始深度优先搜索（递归）图 M，
     * 将搜索到的节点添加到 visited 中
     * @param M
     * @param i
     * @param visited
     */
    private void dfs(int[][] M, int i, boolean[] visited) {
        if (i < 0 || i >= M.length || visited[i]) {
            return;
        }
        visited[i] = true;
        // 找第 i 个学生的朋友
        for (int j=0; j<M.length; j++) {
            if (M[i][j] == 1) {
                dfs(M, j, visited);
            }
        }
    }

    /**
     * 从第 i 个节点开始深度优先搜索（非递归）图 M，
     * 将搜索到的节点添加到 visited 中
     * @param M
     * @param i
     * @param visited
     */
    private void dfs2(int[][] M, int i, boolean[] visited) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(i);
        while (!queue.isEmpty()) {
            Integer curNodeIndex = queue.poll();
            if (curNodeIndex < 0 || curNodeIndex >= M.length || visited[curNodeIndex]) {
                return;
            }
            visited[curNodeIndex] = true;
            for (int j=0; j<M.length; j++) {
                if (M[curNodeIndex][j] == 1 && !visited[j]) {
                    queue.add(j);
                }
            }
        }
    }
}
