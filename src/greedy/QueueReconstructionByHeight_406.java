package greedy;

import java.util.*;

/**
 * 406. 根据身高重建队列
 * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
 *
 * 注意：
 * 总人数少于1100人。
 *
 * 示例
 *
 * 输入:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 *
 * 输出:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 */
public class QueueReconstructionByHeight_406 {
    public static void main(String[] args) {
        QueueReconstructionByHeight_406 solution = new QueueReconstructionByHeight_406();
        int[][] unorderedQueue = {
                {7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}
        };
        int[][] reconstructedQueue = solution.reconstructQueue(unorderedQueue);
        System.out.println(Arrays.deepToString(reconstructedQueue));
    }

    /**
     * 思路：
     * - 看着题解理解了一会才明白，但是不知道这个思路是怎么通过题意分析出来的，
     *   而且也不像是贪心算法，首先，这不是一个最优化问题，其次也没看出来贪什么，
     *   也说不上用的什么算法，就是排序然后插入新数组。
     * - 这个的解题思路是这样的
     *     - 身高一样的，按照 k 升序排列，因为 k 代表他前面有多少个比他高（或一样高）
     *       的人，所以身高一样的人重建后的顺序肯定是按 k 升序排列。
     *     - 身高不一样的人，按照 h 降序排列，因为 k 是代表他前面有多少个比他高（或一样高）的人，
     *       就是一个人只看得到比他高或者一样高的人，比他低的人他看不到，所以，优先重建
     *       较高的人，把高的人插入队列后，再让较低的人去插空排队。所以要按照身高 h 降序排列。
     * - 流程：
     *     - 将乱掉的队列按照身高 h 降序，k 升序进行二重排序
     *     - 遍历排序好的数组，从高到矮插入到新的队列中，每个人插入的位置就是新队列的 k 值。
     * - 时间复杂度：O(N²) 或 O(NlogN)
     *     - 排序：O(NlogN)
     *     - 遍历插入新队列: O(N²) 或 O(N)
     *         - 如果是 ArrayList 插入元素，插入本身要 O(k) 其中 k 是当前输出队列的元素个数
     *           一共要插入 N 个元素，所以是 O(N²)
     *         - 如果是 LinkList 插入元素，插入本身是 O(1)，一共插入 N 个元素，所以是 O(N)
     * - 空间复杂度: O(N)
     *     - 辅助的新队列，O(N)
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                return (o1[0] == o2[0])? o1[1] - o2[1] : o2[0] - o1[0];
            }
        });
        List<int[]> queue = new LinkedList<>();
        for (int[] pers : people) {
            queue.add(pers[1], pers);
        }
        return queue.toArray(new int[queue.size()][]);
    }
}
