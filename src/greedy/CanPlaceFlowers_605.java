package greedy;

/**
 * 605. 种花问题
 * 假设你有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花卉不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
 * 给定一个花坛（表示为一个数组包含0和1，其中0表示没种植花，1表示种植了花），和一个数 n 。能否在不打破种植规则的情况下种入 n 朵花？能则返回True，不能则返回False。
 *
 * 示例 1:
 * 输入: flowerbed = [1,0,0,0,1], n = 1
 * 输出: True
 *
 * 示例 2:
 * 输入: flowerbed = [1,0,0,0,1], n = 2
 * 输出: False
 *
 * 注意:
 * 数组内已种好的花不会违反种植规则。
 * 输入的数组长度范围为 [1, 20000]。
 * n 是非负整数，且不会超过输入数组的大小。
 */
public class CanPlaceFlowers_605 {
    public static void main(String[] args) {
        CanPlaceFlowers_605 solution = new CanPlaceFlowers_605();
        System.out.println(solution.calcPlaceFlowers(new int[] {1, 0, 0, 0, 1}, 1));
        System.out.println(solution.calcPlaceFlowers(new int[] {1, 0, 0, 0, 1}, 2));
    }

    /**
     * 思路：
     * - 遍历花坛的位置
     *     - 如果为空位，如果它前面和后面都是空位，那就可以把花插到这个空位
     *     - 如果不是空位，就换下一个
     */
    public boolean calcPlaceFlowers(int[] flowerbed, int n) {
        // 为了让头部和尾部也可以判断前后，给花坛前后补两个 0
        int len = flowerbed.length;
        int[] flowerbedCopy = new int[len + 2];
        for (int i=1; i<=len; i++) {
            flowerbedCopy[i] = flowerbed[i-1];
        }

        // 遍历花坛并插花
        int count = 0;
        for (int i=1; i<=len; i++) {
            if (flowerbedCopy[i] == 0 && flowerbedCopy[i-1] == 0 && flowerbedCopy[i+1] == 0) {
                flowerbedCopy[i] = 1;    // 给这个位置插花
                count++;
            }
            if (count >= n) {
                return true;
            }
        }
        return false;
    }
}
