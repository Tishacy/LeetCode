package greedy;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 455. 分发饼干
 *
 * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。对每个孩子 i ，都有一个胃口值 gi ，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j ，都有一个尺寸 sj 。如果 sj >= gi ，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
 *
 * 注意：
 * 你可以假设胃口值为正。
 * 一个小朋友最多只能拥有一块饼干。
 *
 * 示例 1:
 * 输入: [1,2,3], [1,1]
 * 输出: 1
 * 解释:
 * 你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
 * 虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
 * 所以你应该输出1。
 *
 * 示例 2:
 * 输入: [1,2], [1,2,3]
 * 输出: 2
 * 解释:
 * 你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。
 * 你拥有的饼干数量和尺寸都足以让所有孩子满足。
 * 所以你应该输出2.
 */
public class AssignCookies_455 {
    public static void main(String[] args) {
        AssignCookies_455 solution = new AssignCookies_455();
        int[] children = {1, 2, 3};
        int[] cookies = {1, 1};
        System.out.println(solution.findContentChildren(children, cookies));
    }

    /**
     * 思路：
     * - 这是一个求最值的问题，可以看看贪心算法是否能解决
     * - 贪什么：因为要喂饱最多的孩子，因此先用最小的饼干喂胃口小的。
     * - 流程：
     *     - 贪什么就按什么排序，所以按饼干大小给饼干排序，按胃口大小给孩子排序
     *     - 初始化已喂饱的孩子数量为 0
     *     - 双指针遍历孩子和饼干
     *         - 如果这个饼干喂得饱这个孩子
     *             - 就换下一个孩子，换下一个饼干，然后已喂饱的孩子数量 + 1
     *         - 如果这个饼干喂不饱这个孩子
     *             - 这个饼干连胃口最小的孩子都喂不饱，其他人更喂不饱了，把这个饼干丢掉，换下一个饼干
     *     - 输出已喂饱的孩子数量
     * - 时间复杂度：O(NlogN)
     *     - 排序：O(NlogN)
     *     - 贪心循环：O(N)
     * - 空间复杂度：O(1)
     * @param g 孩子的胃口值数组
     * @param s 饼干的当饱值数组
     * @return 最多可以喂饱的孩子数量
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int numContentChildren = 0;
        int i = 0, j = 0;
        while (i < g.length && j < s.length) {
            if (g[i] <= s[j]) {
                i++;
                j++;
                numContentChildren++;
            } else {
                j++;
            }
        }
        return numContentChildren;
    }
}
