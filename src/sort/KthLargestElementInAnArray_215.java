package sort;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * 215. 数组中的第K个最大元素
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 *
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 */
public class KthLargestElementInAnArray_215 {

    public static void main(String[] args) {
        KthLargestElementInAnArray_215 solution = new KthLargestElementInAnArray_215();
        int[] nums = { 3, 2, 3, 1, 2, 4, 5, 5, 6 };
        int k = 4;
        System.out.println(solution.findKthLargest(nums, k));
        System.out.println(solution.findKthLargestWithMinHeap(nums, k));
        System.out.println(solution.findKthLargestWithPartition(nums, k));
    }

    /**
     * 思路1：
     * - 先将数组排序，然后取第 K 个最大的元素
     * - 时间复杂度：
     *     - 因为 java 默认使用快排，所以时间复杂度为 O(NlogN)
     * - 空间复杂度：
     *     - 因为 java 使用的排序是原地排序，不创建其他新的空间，所以空间复杂度为 O(N)
     */
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    /**
     * 思路2：
     * - 维护一个共有 K 个元素的最小堆，遍历整个数组，依次把数插入到最小堆里，
     *   插完一遍之后，这个堆里就维护了数组中的前 K 大元素，而堆顶的元素是这个
     *   堆里面最小的那个，因此也就是这个数组里的第 K 大元素
     * - java 中 PriorityQueue 是优先队列，默认会得到一个最小堆，由于优先队列
     *   会自动扩容，因此当队列中的元素大于 K 时，需要把堆顶元素取出来跟需要添加的数
     *   进行比较，并把较大的那个添加到堆中（因为要维护的是数组的前 K 大的数）
     * - 时间复杂度：
     *     - 遍历整个数组需要 O(N) 的复杂度，而每插入一个元素进入堆中，需要
     *       维护堆为最小堆，一些元素需要上浮下沉等操作，并且这个最小堆中共有 K
     *       个元素，因此时间复杂度为 O(logK)
     *     - 因此，总体时间复杂度为 O(NlogK)
     * - 空间复杂度：
     *     - 需要维护一个大小为 K 的最小堆，复杂度为 O(K)
     */
    public int findKthLargestWithMinHeap(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        for (int num : nums) {
            if (queue.size() < k) {
                queue.offer(num);
            } else {
                int val = queue.poll();
                if (num > val) {
                    queue.offer(num);
                } else {
                    queue.offer(val);
                }
            }
        }
        return queue.peek();
    }

    /**
     * 思路3：
     * - 对第一种思路进行优化，其实不用将整个数组进行自小到大排序，然后再取数组中的
     *   [len-k] 的位置，而是只要找到最终位置应该在 [len-k] 的位置的那个元素即可。
     *   而快排中的 partition 函数就是一个可以将一个元素放置在其排序后的最终位置上
     *   的一个函数，因此可以利用它。
     * - 快速选择法，利用快排中的 partition 函数
     *     - partition 函数会在给定数组中的随机挑选一个元素放置在其应有的位置上，
     *       即其左边全是比它小的数，右边全是比它大的数
     *     - 通过将给定数组利用 partition 函数随机的到一个元素的最终位置 p ，然后判断
     *       这个最终位置与 [len-k] 的位置哪个大
     *         - 如果 [len-k] 更大一些，就从[p+1, 结束] 的区间内继续 partition 下去
     *         - 如果 p 更大一些，就从 [开始, p-1] 的区间内继续 partition 下去
     *         - 如果 p == len-k，那就找到了这个元素，将 nums[p] 这个值返回。
     * - 时间复杂度：
     *     - O(N) partition 函数(随机化基准后)的算法复杂度为 O(N)
     * - 空间复杂度：
     *     - O(logN) 递归栈空间的期望时间复杂度为 O(logN)
     */
    public int findKthLargestWithPartition(int[] nums, int k) {
        int len = nums.length;
        int p = partition(nums, 0, len-1);
        while (true) {
            if (p < len - k) {
                p = partition(nums, p+1, len-1);
            } else if (p > len-k) {
                p = partition(nums, 0, p-1);
            } else {
                return nums[p];
            }
        }
    }

    public int partition(int[] nums, int left, int right) {
        int rand = new Random().nextInt(right - left + 1) + left;
        swap(nums, left, rand);
        int pivot = nums[left];
        int j = left;
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] < pivot) {
                j++;
                swap(nums, i, j);
            }
        }
        swap(nums, left, j);
        return j;
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
