package sort;

import java.util.*;

/**
 * 347. 前 K 个高频元素
 *
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 *
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *  
 *
 * 提示：
 *
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
 * 你可以按任意顺序返回答案。
 */
public class TopKFrequentElements_347 {

    /**
     * 思路：
     * - 选择对应的算法：
     *     - 对于 TopK 问题，首先是要一个排序算法，如果要按频率进行排序，可以选用桶排序，较易理解。
     *     - 并且，题目中有提示时间复杂度要优于 O(NlogN)，桶排序是 O(N)算法，堆排序对于 topK
     *       问题的时间复杂度为 O(NlogK)，因此堆排序和桶排序都可以解决。
     */
    public static void main(String[] args) {
        TopKFrequentElements_347 solution = new TopKFrequentElements_347();
        int[] nums = {4,1,-1,2,-1,2,3};
        int k = 2;
        System.out.println(Arrays.toString(solution.topKFrequentWithHeap(nums, k)));
    }

    /**
     * 思路1：桶排序
     * - 以什么为桶是关键：对于桶排序，之所以可以成功排序，是因为桶本身就是有序的，因此，
     *   需要对什么大小进行排序，桶的索引值就应该是什么值。比如：
     *     - 数组按数值大小进行排序，桶的索引值就应该是数值本身
     *     - 数组按数字出现频率大小进行排序，桶的索引值就应该是数字出现的频数
     *   这道题目是按数字出现的频率进行排序，因此桶的索引应当为数字出现的频数。
     * - 流程：
     *     - 首先，对于数组中的每个数字，记录其出现的频数。
     *     - 然后根据频数的最高值，构建（频数最高值）个桶，依次将数字放入对应频数的桶中
     *     - 然后，因为要取频率前 K 高的元素，所以从最后一个桶开始遍历取出元素
     *         - 如果已取出的元素数目 + 当前桶里的元素数 <= K，那么就把桶里的元素都取出来
     *         - 如果已取出的元素数目 + 当前桶里的元素书 > K, 那么就把桶里的部分元素取出来，取出的个数为（K - 已取出的元素数目）
     *     - 最后，返回所有取出的元素
     * - 时间复杂度: O(N)
     * - 空间复杂度:
     *     - 需要 Map 统计数字出现频率  O(K) K 为不同数字的个数，K <= N
     *     - 需要 N+1 个桶作 O(N)
     *     - 总体空间复杂度为 O(K+N) -> O(N)
     */
    public int[] topKFrequent(int[] nums, int k) {
        // 统计每个数字出现的频数
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // 将数字放到对应的频数桶中
        ArrayList<Integer>[] buckets = new ArrayList[nums.length+1];    // 因为元素可能出现的最高的频数也就是数组的长度，即数组中全是这个元素
        for (int num : map.keySet()) {
            int freq = map.get(num);
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(num);
        }
        // 从频数最大的桶开始取出数字
        ArrayList<Integer> topKFrequentRes = new ArrayList<>();
        for (int i=nums.length; i>=0 && topKFrequentRes.size() < k; i--) {
            ArrayList bucket = buckets[i];
            if (bucket == null) {
                continue;
            }
            if ((topKFrequentRes.size() + bucket.size()) <= k) {
                topKFrequentRes.addAll(bucket);
            } else {
                topKFrequentRes.addAll(bucket.subList(0, k - topKFrequentRes.size()));
            }
        }
        // 将 topKList 变为 array
        int[] res = new int[topKFrequentRes.size()];
        for (int i=0; i<res.length; i++) {
            res[i] = topKFrequentRes.get(i);
        }
        return res;
    }

    /**
     * 思路2：
     * - 堆排序解决 topK 问题的核心是维护一个大小为 K 的堆，求前 K 大，采用小顶堆，
     *   求前 K 小，采用大顶堆
     * - 在堆排序中，是根据什么大小排序的，就要根据什么大小来把数值加入到堆中，比如：
     *     - 如果是按数值大小进行排序，那么就把数值较大的那个放入堆中
     *     - 如果是按数值出现的频率排序，那么就把数值出现次数更多的那个数值放入堆中
     * - 流程：
     *     - 首先，统计每个数字出现的次数
     *     - 然后，构建一个小顶堆，java 里用 PriorityQueue，优先队列的比较规则是 频数大的 > 频数小的
     *     - 遍历每个数字（各个数字不同）：
     *         - 如果当前堆中元素个数 < k: 就把数字放到堆中
     *         - 如果当前堆中元素个数 >=k: （关键！）把堆顶取出来，把当前数字和堆顶数字
     *             出现的频数作对比，并把出现次数多的那个放到堆顶。
     *     - 现在，这个最小堆里就是数组中频率前 K 大的数字，然后把堆里的数字依次取出来即可
     * - 时间复杂度：O(NlogK)  K 为不同数字的个数，K <= N
     * - 空间复杂度：O(K) K 为不同数字的个数，K <= N
     */
    public int[] topKFrequentWithHeap(int[] nums, int k) {
        // 统计每个数字出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // 构建一个大小为 K 的小顶堆，堆的比较规则是频数大的优先
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> map.get(a) - map.get(b));
        for (Integer num : map.keySet()) {
            if (queue.size() < k) {
                queue.add(num);
            } else {
                if (map.get(num) > map.get(queue.peek())) {
                    queue.poll();
                    queue.add(num);
                }
            }
        }
        // 把堆里的数字依次取出来即可
        int[] topK = new int[queue.size()];
        int i = 0;
        while (!queue.isEmpty()) {
            topK[i++] = queue.poll();
        }
        return topK;
    }
}
