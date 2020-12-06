package search;

import java.util.*;

/**
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * 示例 1:
 *
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 * 示例 2:
 *
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 */
public class PerfectSquares_279 {
    /**
     * 思路：
     *   将 [0, n] 个数字变成图中的节点
     *   两个数字之间如果相差一个”完全平方数字“, 节点之间就有一条边
     *   图中任意一个节点到 0 节点的路径，就是一个解决方案
     *   比如：13 -> 0 的路径为 13 -(4)-> 9 -(9)-> 0
     *   题目实际上就是在求从 n 节点 到 0 节点的 **最短路径**
     *   可以使用 BFS 解决
     * @param args
     */
    public static void main(String[] args) {
//        int n = 12;
        int n = 13;
        PerfectSquares_279 solution = new PerfectSquares_279();
        int numSquares = solution.numSquares(n);
        System.out.println("[Solution] num of squares: " + numSquares);
    }

    public int numSquares(int n) {
        List<Integer> squares = getSquares(n);
        Deque<Integer> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        int shortestPathLen = 0;
        queue.add(n);

        while (!queue.isEmpty()) {
            shortestPathLen++;
            int size = queue.size();
            for (int i=0; i<size; i++) {
                int curNum = queue.poll();
                if (curNum == 0) {
                    return shortestPathLen - 1;
                }
                if (visited.contains(curNum)) {
                    continue;
                }
                visited.add(curNum);
                for (Integer square : squares) {
                    int next = curNum - square;
                    queue.add(next);
                }
            }
        }
        return -1;
    }

    private List<Integer> getSquares(int n) {
        List<Integer> squares = new ArrayList<>();
        for (int i=1; i<=Math.sqrt(n); i++) {
            if (i * i <= n) {
                squares.add(i*i);
            }
        }
        return squares;
    }
}
