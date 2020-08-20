package binarysearch;

/**
 * 153. 寻找旋转排序数组中的最小值
 *
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 请找出其中最小的元素。
 *
 * 你可以假设数组中不存在重复元素。
 *
 * 示例 1:
 * 输入: [3,4,5,1,2]
 * 输出: 1
 *
 * 示例 2:
 * 输入: [4,5,6,7,0,1,2]
 * 输出: 0
 */
public class FindMinimumInRotatedSortedArray_153 {
    /**
     * 思路：
     *      一个部分有序的数组，找一个元素，可以用二分法，关键是
     *      找到二分后取舍左右部分的思路
     *      这里是将中点 mid 与 high 指针对应的值相对比
     *          如果 mid = high，旋转点在左边
     *          如果 mid < high，旋转点在左边
     *          如果 mid > high，旋转点在右边
     *       其实本质上是在找一个区间的左边界，所以是二分查找左边界的变种
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = new int[]{3,4,5,1,2};
        int[] arr2 = new int[]{4,5,6,7,0,1,2};
        System.out.println(findMin(arr));
        System.out.println(findMin(arr2));
    }

    public static int findMin(int[] nums) {
        int low = 0, high = nums.length-1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > nums[high]) {
                low = mid + 1;
            } else if (nums[mid] < nums[high]){
                high = mid - 1;
            } else {
                // 因为找的是目标区间的左边界，因此不能动 low，
                // 而是修改 high
                high = mid - 1;
            }
        }
        return nums[low];
    }
}
