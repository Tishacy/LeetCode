package offer;

/**
 * 面试 3: 找出数组中重复的数字。
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，
 * 但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 *
 * 示例 1：
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 *
 * 输出：2 或 3
 *
 * 限制：
 * 2 <= n <= 100000
 */
public class FindRepeatNumber_3 {
    public static void main(String[] args) {
        FindRepeatNumber_3 solution = new FindRepeatNumber_3();
        int[] nums = {2, 3, 1, 0, 2, 5, 3};
        System.out.println("Solution 1: " + solution.findRepeatNumber1(nums));
        System.out.println("Solution 2: " + solution.findRepeatNumber2(nums));
    }

    /**
     * 方法1：哈希表
     * 时间复杂度: O(N)
     * 空间复杂度: O(N)
     * @param nums
     * @return
     */
    public int findRepeatNumber1(int[] nums) {
        boolean[] repeat = new boolean[nums.length];
        for (int num : nums) {
            if (repeat[num]) {
                return num;
            }
            repeat[num] = true;
        }
        return -1;
    }

    /**
     * 方法2：原地排序
     * 思路：将数字放到它应该在的位置，如果它应该在的位置上已经有一个跟它一样的数字了，
     * 那就返回这个数字。
     * 时间复杂度: O(N)
     * 空间复杂度: O(1)
     * @param nums
     * @return
     */
    public int findRepeatNumber2(int[] nums) {
        for (int i=0; i<nums.length; i++) {
            // while 该数字没有在它应在的位置
            while (i != nums[i]) {
                // 如果他应该在的位置上已经有一个跟他一样的数字了
                // 就返回这个数字
                if (nums[nums[i]] == nums[i]) {
                    return nums[i];
                }
                // 反之就把它他放到他应该在的位置
                swap(nums, i, nums[i]);
            }
        }
        return -1;
    }

    /**
     * 交换 nums 中 i 和 j 位置的元素
     * @param nums
     * @param i
     * @param j
     */
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
