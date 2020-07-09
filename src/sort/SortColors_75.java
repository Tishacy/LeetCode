package sort;

import java.util.Arrays;

/**
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 *
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 *
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 *
 * 示例:
 *
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 * 进阶：
 *
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。（计数排序）
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？（三路快排）
 */
public class SortColors_75 {
    public static void main(String[] args) {
        SortColors_75 solution = new SortColors_75();
        int[] nums =  {2, 0, 2, 1, 2, 1, 2, 2, 2, 1, 1, 0};
        solution.sortColorsWithPartition(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 思路1:
     * - 利用桶排序(计数排序)
     * - 时间复杂度 O(N)，需要遍历两次
     * - 空间复杂度 O(K)，需要 3 个桶的辅助空间
     */
    public void sortColors(int[] nums) {
        int[] buckets = new int[3];
        for (int num : nums) {
            buckets[num]++;
        }
        int k = 0;
        for (int i=0; i<buckets.length; i++) {
            int freq = buckets[i];
            for (int j = 0; j < freq; j++) {
                nums[k++] = i;
            }
        }
    }

    /**
     * 思路2：
     * - 三路快排方法
     * - 时间复杂度 O(N)，只遍历一遍
     * - 空间复杂度 O(1)，需要 3 个指针
     */
    public void sortColorsWithPartition(int[] nums) {
        int left = 0, right = nums.length - 1;
        int cur = 0;
        while (cur <= right) {
            if (nums[cur] == 0) {
                swap(nums, cur++, left++);
            } else if (nums[cur] == 1) {
                cur++;
            } else {
                swap(nums, cur, right--);
            }
        }
    }

    /**
     * 交换数组中的两个数
     * @param nums 给定数组
     * @param i 需要交换的第一个数值的索引
     * @param j 需要交换的第二个数值的索引
     */
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
