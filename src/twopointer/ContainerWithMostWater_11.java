package twopointer;

/**
 * 11. 盛最多水的容器
 *
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 *
 * 示例：
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ContainerWithMostWater_11 {
    /**
     * 思路：
     * - 双指针：由于给了一个固定顺序的数组，并且需要操作两个数来解决问题，因此采用双指针法。
     * - 初始化：左右两个指针分别指向头和尾
     * - 指针移动条件：
     *     - 由于要使得构成的长方形面积最大，而指针往中间移动的时候，底边长度一定会减小，因此
     *       只能移动值较小的指针，保留当前较大的指针。
     * - 循环终止条件：
     *     - 当左右指针重合的时候，底边边长为 0，这时就不用判断了，面积一定为 0，退出循环
     */
    public static void main(String[] args) {
        ContainerWithMostWater_11 solution = new ContainerWithMostWater_11();
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(solution.maxArea(height));
    }

    /**
     * 最大化面积
     * @param height 高度数组
     * @return 可以构成的最大面积
     */
    public int maxArea(int[] height) {
        int left = 0, right = height.length-1;
        int ans = 0;
        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left);
            ans = Math.max(ans, area);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return ans;
    }
}
