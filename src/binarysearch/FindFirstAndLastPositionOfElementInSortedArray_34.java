package binarysearch;

import java.util.Arrays;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 *
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * 示例 1:
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 *
 * 示例 2:
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 */
public class FindFirstAndLastPositionOfElementInSortedArray_34 {
    /**
     * 思路：
     *  问题是查目标元素的左边界和右边界，就是用二分查找左边界与右边界即可
     *  可以将查找左边界和查找右边界合并为一个函数
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = new int[]{5,7,7,8,8,10};
        int target = 8;
        int[] res = searchRange(nums, target);
        System.out.println(Arrays.toString(res));
    }

    public static int[] searchRange(int[] nums, int target) {
        int leftBound = searchBound(nums, target, true);
        int rightBound = searchBound(nums, target, false);
        return new int[] {leftBound, rightBound};
    }

    public static int searchBound(int[] nums, int target, boolean left) {
        int n = nums.length;
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                if (left) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        if (left) {
            if (low < n && nums[low] == target) {
                return low;
            }
            return -1;
        } else {
            if (high >= 0 && nums[high] == target) {
                return high;
            }
            return -1;
        }
    }
}
