package search;

import java.util.*;

/**
 * 基本的搜索算法，包括广度优先搜索（BFS）和深度优先搜搜（DFS）
 *
 * 对于树数据结构：
 * - 层次遍历属于 BFS，即从根节点一层一层遍历
 * - 前、中、后序遍历属于 DFS，即一条路走到头，走不通再回溯
 *
 * 对于无权图数据结构：
 * - BFS 需要用到一个队列（若干为加权图，则为优先队列）与标记set
 * - DFS 需要用到一个栈与标记set
 *
 * 从代码实现上来说，BFS 和 DFS 的唯一区别在于将 辅助队列 换成了 辅助栈，
 * 其余部分是完全一样的
 */
public class BasicSearch {
    public static void main(String[] args) {
        // 定义示例图结构
        Character[] vexChars = {'A', 'B', 'C', 'D', 'E', 'F'};
        List<Character> vexs = new ArrayList<>(Arrays.asList(vexChars));
        int[][] arc = {
                //   A  B  C  D  E  F
                {0, 1, 1, 0, 0, 0},
                {1, 0, 1, 1, 0, 0},
                {1, 1, 0, 1, 1, 0},
                {0, 1, 1, 0, 1, 1},
                {0, 0, 1, 1, 0, 0},
                {0, 0, 0, 1, 0, 0}
        };
        Graph graph = new Graph(vexs, arc);

        // 测试 BFS 和 DFS
        List<Character> visited = breadthFirstSearch(graph, 0);
        System.out.println("广度优先搜索路径：" + visited.toString());
        List<Character> visited2 = depthFirstSearch(graph, 0);
        System.out.println("深度优先搜索路径：" + visited2.toString());

        // 最短路径查询 map
        Map<Character, Character> shortestPathMap = shortestPathMap(graph, 0);
        System.out.println("最短路径查询map：" + shortestPathMap.toString());
        // 找到最短路径
        int start = 4, end = 5;
        List<Character> shortestPath = shortestPath(graph, start, end);
        System.out.println(graph.vexs.get(start) + " -> " + graph.vexs.get(end) + " 的最短路径为：" + shortestPath.toString());
    }

    /**
     * 使用邻接矩阵定义图
     */
    public static class Graph {
        public List<Character> vexs;
        public int[][] arc;

        public Graph() {
        }

        public Graph(List<Character> vexs, int[][] arc) {
            this.vexs = vexs;
            this.arc = arc;
        }
    }

    /**
     * 广度优先搜索
     * - 需要的关键辅助数据结构：队列 和 标记set
     * - 核心是一层一层遍历
     *
     * @param graph 给定图
     * @param j     起始节点下标
     * @return 广度优先搜索路径
     */
    public static List<Character> breadthFirstSearch(Graph graph, int j) {
        // 准备队列 和 标记set，并用 paths 数组记录结果
        Deque<Character> queue = new ArrayDeque<>();
        queue.add(graph.vexs.get(j));
        Set<Character> visited = new HashSet<>();
        visited.add(graph.vexs.get(j));
        List<Character> paths = new ArrayList<>();

        while (!queue.isEmpty()) {
            // 取出队首的元素
            Character vertex = queue.poll();
            paths.add(vertex);

            // 找出取出元素的邻接元素，如果它们没有被访问过，就并将他们放到队列和标记set中
            int vertexIndex = graph.vexs.indexOf(vertex);
            int[] neighborFlags = graph.arc[vertexIndex];
            for (int i = 0; i < neighborFlags.length; i++) {
                Character neighborVertex = graph.vexs.get(i);
                if (neighborFlags[i] == 1 && !visited.contains(neighborVertex)) {
                    queue.add(neighborVertex);
                    visited.add(neighborVertex);
                }
            }
        }
        return paths;
    }

    /**
     * 深度优先搜索
     * - 需要的关键辅助数据结构：栈 和 标记set
     * - 核心是一条路走到头，然后回溯
     *
     * @param graph 给定图
     * @param j     起始节点下标
     * @return 深度优先搜索的路径
     */
    public static List<Character> depthFirstSearch(Graph graph, int j) {
        // 准备栈 和 标记set，并用 paths 数组记录结果
        Deque<Character> stack = new ArrayDeque<>();
        stack.push(graph.vexs.get(j));
        Set<Character> visited = new HashSet<>();
        visited.add(graph.vexs.get(j));
        List<Character> paths = new ArrayList<>();

        while (!stack.isEmpty()) {
            // 取出栈顶的元素，放入路径数组中
            Character vertex = stack.pop();
            paths.add(vertex);

            // 找出取出元素的邻接元素，如果它们没有被访问过，就把它们放入栈和 标记set 中
            int vertexIndex = graph.vexs.indexOf(vertex);
            int[] vertexFlags = graph.arc[vertexIndex];
            for (int i = 0; i < vertexFlags.length; i++) {
                Character neighborVertex = graph.vexs.get(i);
                if (vertexFlags[i] == 1 && !visited.contains(neighborVertex)) {
                    stack.push(neighborVertex);
                    visited.add(neighborVertex);
                }
            }
        }
        return paths;
    }


    /**
     * 最短路径查询表
     * - 只需要将 BFS 中返回的 路径列表 变成 (每个元素的上一个元素) 的表即可
     *
     * @param graph 给定图
     * @param j     起始节点下标
     * @return 最短路径查询表
     */
    public static Map<Character, Character> shortestPathMap(Graph graph, int j) {
        // 准备队列 和 标记set，并用 paths 数组记录结果
        Deque<Character> queue = new ArrayDeque<>();
        queue.add(graph.vexs.get(j));
        Set<Character> visited = new HashSet<>();
        visited.add(graph.vexs.get(j));
        // 将 路径列表 替换为 路经查询表
        Map<Character, Character> map = new HashMap<>();
        map.put(graph.vexs.get(j), null);

        while (!queue.isEmpty()) {
            // 取出队首的元素
            Character vertex = queue.poll();

            // 找出取出元素的邻接元素，如果它们没有被访问过，就并将他们放到队列和标记set中
            int vertexIndex = graph.vexs.indexOf(vertex);
            int[] neighborFlags = graph.arc[vertexIndex];
            for (int i = 0; i < neighborFlags.length; i++) {
                Character neighborVertex = graph.vexs.get(i);
                if (neighborFlags[i] == 1 && !visited.contains(neighborVertex)) {
                    queue.add(neighborVertex);
                    visited.add(neighborVertex);
                    map.put(neighborVertex, vertex);
                }
            }
        }
        return map;
    }

    /**
     * 无权无向图的最短路径
     * @param graph 给定的无权无向图
     * @param start 起始节点下标
     * @param end   终止节点下标
     * @return 从起始节点到终止节点在给定图中的最短路径
     */
    public static List<Character> shortestPath(Graph graph, int start, int end) {
        Map<Character, Character> shortestPathMap = shortestPathMap(graph, start);
        Character vertex = graph.vexs.get(end);
        List<Character> shortestPath = new ArrayList<>();
        while (vertex != null) {
            shortestPath.add(vertex);
            vertex = shortestPathMap.get(vertex);
        }
        Collections.reverse(shortestPath);
        return shortestPath;
    }
}