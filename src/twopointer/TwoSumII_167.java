package twopointer;

import java.util.Arrays;

/**
 * 167. 两数之和 II - 输入有序数组
 * 给定一个已按照升序排列的有序数组，找到两个数使得它们相加之和等于目标数。
 *
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 *
 * 说明:
 *
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * 示例:
 *
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2。
 */
public class TwoSumII_167 {
    /**
     * 思路：
     * - 题目给了下面两个条件：
     *     - 给定了一个数组，并且是有序的
     *     - 通过操作两个数，来判断是否得到结果
     * - 因此，为了利用有序这个关键条件，可以考虑使用双指针，从数组的两头向中间移动，直到达到要求为止。
     * - 指针移动的条件为：
     *     - 如果左右指针值之和 > 目标值，就把右指针往左移动
     *     - 如果左右指针值之和 < 目标值，就把左指针往右移动
     * - 循环终止条件为：
     *     - 如果找得到目标值：当左右指针值之和 == 目标值，退出循环，得到结果
     *     - 如果找不到目标值：因为不能重复使用相同的元素，且 index1 < index2，所以当 左指针==右指针 时，就要退出循环
     */
    public static void main(String[] args) {
        TwoSumII_167 solution = new TwoSumII_167();
        // An example
        int[] numbers = {2, 7, 11, 15};
        int target = 9;
        int[] result = solution.twoSum(numbers, target);
        System.out.println(Arrays.toString(result));
    }

    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        int[] result = new int[2];
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum > target) {
                right--;
            } else if (sum < target) {
                left++;
            } else {
                result[0] = left + 1;
                result[1] = right + 1;
                break;
            }
        }
        return result;
    }
}
