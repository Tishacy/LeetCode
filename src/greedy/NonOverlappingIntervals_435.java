package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 435. 无重叠区间
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 *
 * 注意:
 * 可以认为区间的终点总是大于它的起点。
 * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 *
 * 示例 1:
 * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
 * 输出: 1
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 *
 * 示例 2:
 * 输入: [ [1,2], [1,2], [1,2] ]
 * 输出: 2
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 *
 * 示例 3:
 * 输入: [ [1,2], [2,3] ]
 * 输出: 0
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 */
public class NonOverlappingIntervals_435 {
    public static void main(String[] args) {
        NonOverlappingIntervals_435 solution = new NonOverlappingIntervals_435();
        int[][] intervels = {
                {0, 2}, {1, 3}, {2, 4}, {3, 5}, {4, 6}
        };
        System.out.println(solution.eraseOverlapIntervals(intervels));
    }

    /**
     * 思路：
     * - 这个问题与活动选择问题几乎是一样的，采用贪心算法解决
     * - 为什么跟活动选择问题一样？
     *     - 因为找到需要移除区间的最小数量，其实就是找到最多的互不重叠的子列表，
     *       而每个子列表可以看成是一个活动的起止时间，就变成了活动选择。
     * - 贪心算法贪什么？
     *     - 与活动选择问题类似，要安排最多的互不挤占的活动，就需要贪活动的结束时间，
     *       因为活动结束的越早，留给其他活动的时间就越多，就可以安排越多的活动。因此，
     *       要找到最多的互不重叠的子列表，贪的是子列表的最后一个数值，子列表的最后一个
     *       数值越小，留给其他子列表的空间就越多，就可以放更多的子列表。
     * - 流程：
     *     - 根据子列表的最后一个数值，将列表从小到大进行排序
     *     - 指针指向第一个子列表
     *     - 然后遍历后面的子列表
     *         - 如果子列表的第一个值 >= 指针所指的子列表的第二个值，说明不重叠，不重叠
     *           的子列表数目 + 1
     *         - 如果重叠了，这个子列表就扔掉，就换下一个子列表
     * - 时间复杂度：O(NlogN)
     *     - 排序：java 默认用快排 O(NlogN)
     *     - 贪心循环：O(N)
     * - 空间复杂度：O(1)，排序为原地排序，其他只是用了指针操作
     *
     * @param intervals
     * @return 需要移除区间的最小数量
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int[] p = intervals[0];
        int count = 1;
        for (int i=1; i<intervals.length; i++) {
            if (intervals[i][0] < p[1]) { // 重叠
                continue;
            }
            p = intervals[i];
            count++;
        }
        return intervals.length - count;
    }
}
