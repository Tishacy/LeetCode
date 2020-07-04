package twopointer;

import java.util.Arrays;

/**
 * 88. 合并两个有序数组
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 *
 * 说明:
 *
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *  
 * 示例:
 *
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * 输出: [1,2,2,3,5,6]
 */
public class MergeSortedArray_88 {
    /**
     * 思路：
     * - 对于有序数组，以及操作两个数的需求。可以使用双指针法
     * - 较为常见的方式，是两个指针从前往后遍历，并每一次把较小的值放入输出数组之中，并且该指针右移
     * - 指针移动规则：
     *     - 如果左指针值小于右指针值，就把左指针值放入输出数组中，并且左指针右移
     *     - 如果右指针值小于左指针值，就把右指针值放入输出数组中，并且右指针左移
     * - 循环终止条件：
     *     - 当左指针或右指针遍历到尾部之后的位置，只要有一个数组被遍历完成，就退出循环
     *     - 退出循环后，就把还有的那个数组剩余的值，追加到输出数组中
     * - 归并两个有序数组是归并排序的核心代码，需要熟练掌握
     */
    public static void main(String[] args) {
        MergeSortedArray_88 solution = new MergeSortedArray_88();
        int[] nums1 = {2, 0};
        int[] nums2 = {1};
        solution.merge(nums1, 1, nums2, 1);
        System.out.println(Arrays.toString(nums1));
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] nums1Copy = Arrays.copyOf(nums1, m);
        int left = 0, right = 0, resPointer = 0;
        while (left < m && right < n) {
            if (nums1Copy[left] <= nums2[right]) {
                nums1[resPointer++] = nums1Copy[left++];
            } else {
                nums1[resPointer++] = nums2[right++];
            }
        }
        if (left > m-1) {
            while (right < n) {
                nums1[resPointer++] = nums2[right++];
            }
        } else {
            while (left < m) {
                nums1[resPointer++] = nums1Copy[left++];
            }
        }
    }
}
