package greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 452. 用最少数量的箭引爆气球
 *
 * 在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。由于它是水平的，所以y坐标并不重要，因此只要知道开始和结束的x坐标就足够了。开始坐标总是小于结束坐标。平面内最多存在104个气球。
 * 一支弓箭可以沿着x轴从不同点完全垂直地射出。在坐标x处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
 *
 * Example:
 * 输入:
 * [[10,16], [2,8], [1,6], [7,12]]
 * 输出:
 * 2
 * 解释:
 * 对于该样例，我们可以在x = 6（射爆[2,8],[1,6]两个气球）和 x = 11（射爆另外两个气球）。
 */
public class MinimumNumberOfArrowsToBurstBalloons_452 {
    public static void main(String[] args) {
        MinimumNumberOfArrowsToBurstBalloons_452 solution = new MinimumNumberOfArrowsToBurstBalloons_452();
        int[][] points = {{10, 16}, {2, 8}, {1, 6}, {7,12}};
        System.out.println(solution.findMinArrowShots(points));
    }

    /**
     * 思路：
     * - 箭射穿气球，最多需要箭的数量是等于气球数的，就是一个气球一支箭，这样箭数最多，
     *   如果两个重叠的气球用一支箭射穿，而不是两支箭，那箭的数量就会相应减少，
     *   当用箭最少的时候，就是所有不重叠的气球都各用一支箭，而其他与之重叠的气球都
     *   随着其重叠气球的箭被射穿了。因此，问题就转换成了：有多少互不重叠的气球，
     *   因为，每个不重叠的气球都必须会有一个箭，因此问题是：这几个气球里到底有几个互不重叠
     *   的气球，求得是最大数。
     * - 这样，问题其实就是一个活动选择问题，只不过当两个气球的如果有一个边界相同的时候也
     *   算作是重叠
     * - 考虑用贪心算法，先按气球的右范围大小进行排序，然后贪心循环
     * - 时间复杂度：O(NlogN)
     *     - 排序: O(NlogN)
     *     - 贪心循环: O(N)
     * - 空间复杂度: O(1)
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }

        Arrays.sort(points, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int[] p = points[0];
        int count = 1;
        for (int i=1; i<points.length; i++) {
            if (points[i][0] <= p[1]) {
                continue;
            }
            p = points[i];
            count++;
        }
        return count;
    }
}
