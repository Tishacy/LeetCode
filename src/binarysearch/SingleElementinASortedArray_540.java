package binarysearch;

/**
 * 540. 有序数组中的单一元素
 *
 * 给定一个只包含整数的有序数组，每个元素都会出现两次，唯有一个数只会出现一次，找出这个数。
 *
 * 示例 1:
 * 输入: [1,1,2,3,3,4,4,8,8]
 * 输出: 2
 *
 * 示例 2:
 * 输入: [3,3,7,7,10,11,11]
 * 输出: 10
 * 注意: 您的方案应该在 O(log n)时间复杂度和 O(1)空间复杂度中运行。
 */
public class SingleElementinASortedArray_540 {
    /**
     * 思路：
     *  在有序数组中找一个元素，可以用二分查找法
     *  找到中点后，保证中点的索引为偶数
     *      如果中点跟它右边如果相同，那么目标元素就在右半段
     *      如果不相同，则目标元素左半端
     *  注意：给定列表中只有一个元素
     */
    public static void main(String[] args) {
        int[] arr = new int[] {1,1,2,3,3,4,4,8,8};
        int[] arr2 = new int[] {3,3,7,7,10,11,11};
        System.out.println(singleNonDuplicate(arr));
        System.out.println(singleNonDuplicate(arr2));
    }

    public static int singleNonDuplicate(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            mid = (mid % 2 == 1)? mid-1 : mid;
            if (nums[mid] == nums[mid + 1]) {
                low = mid + 2;
            } else {
                high = mid;
            }
        }
        // 搜索空间只有 1 个元素 low == mid == high
        return nums[low];
    }

}
