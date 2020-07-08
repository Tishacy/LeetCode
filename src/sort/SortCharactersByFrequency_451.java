package sort;

import java.util.*;

/**
 * 451. 根据字符出现频率排序
 * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 *
 * 示例 1:
 *
 * 输入:
 * "tree"
 *
 * 输出:
 * "eert"
 *
 * 解释:
 * 'e'出现两次，'r'和't'都只出现一次。
 * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
 * 示例 2:
 *
 * 输入:
 * "cccaaa"
 *
 * 输出:
 * "cccaaa"
 *
 * 解释:
 * 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
 * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
 * 示例 3:
 *
 * 输入:
 * "Aabb"
 *
 * 输出:
 * "bbAa"
 *
 * 解释:
 * 此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
 * 注意'A'和'a'被认为是两种不同的字符。
 */
public class SortCharactersByFrequency_451 {
    /**
     * 思路：
     * - 选择对应的算法：
     *     - 要一个排序算法，如果要按频率进行排序，可以选用桶排序，较易理解。
     *     - 其他排序方法，如堆排序也可以解决。
     */
    public static void main(String[] args) {
        SortCharactersByFrequency_451 solution = new SortCharactersByFrequency_451();
        String s = "tree";
        System.out.println(solution.frequencySortWithHeap(s));
    }

    /**
     * 思路1：桶排序
     * - 以什么为桶是关键：对于桶排序，之所以可以成功排序，是因为桶本身就是有序的，因此，
     *   需要对什么大小进行排序，桶的索引值就应该是什么值。比如：
     *     - 数组按数值大小进行排序，桶的索引值就应该是数值本身
     *     - 数组按字符出现频率大小进行排序，桶的索引值就应该是字符出现的频数
     *   这道题目是按字符出现的频率进行排序，因此桶的索引应当为字符出现的频数。
     * - 流程：
     *     - 首先，对于数组中的每个字符，记录其出现的频数。
     *     - 然后根据频数的最高值，构建（频数最高值）个桶，依次将字符放入对应频数的桶中
     *     - 然后，因为要取频率前 K 高的元素，所以从最后一个桶开始遍历取出元素
     *     - 最后，返回所有取出的元素
     * - 时间复杂度: O(N)
     * - 空间复杂度:
     *     - 需要 Map 统计字符出现频率  O(K) K 为不同字符的个数，K <= N
     *     - 需要 N+1 个桶作 O(N)
     *     - 总体空间复杂度为 O(K+N) -> O(N)
     */
    public String frequencySort(String s) {
        // 统计每个字符出现的次数
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        // 构建桶
        List<Character>[] buckets = new ArrayList[s.length() + 1];
        for (int i=0; i<buckets.length; i++) {
            buckets[i] = new ArrayList();
        }
        for (Character c : map.keySet()) {
            int freq = map.get(c);
            buckets[freq].add(c);
        }
        // 从频率最高的桶开始取出元素
        char[] charArr = new char[s.length()];
        int k = 0;
        for (int i=s.length(); i>=0; i--) {
            List<Character> bucket = buckets[i];
            int freq = i;
            for (int j=0; j<bucket.size(); j++) {
                for (int m=0; m<freq; m++) {
                    charArr[k++] = bucket.get(j);
                }
            }
        }
        return new String(charArr);
    }

    /**
     * 思路2：堆排序
     * - 因为是降序排列，所以采用大顶堆
     * - 在堆排序中，是根据什么大小排序的，就要根据什么大小来把数值加入到堆中，比如：
     *     - 如果是按数值大小进行排序，那么就把数值较大的那个放入堆中
     *     - 如果是按字符出现的频率排序，那么就把数值出现次数更多的那个字符放入堆中
     * - 流程：
     *     - 首先，统计每个字符出现的次数
     *     - 然后，构建一个大顶堆，java 里用 PriorityQueue，优先队列的比较规则是频数大的优先
     *     - 遍历每个字符（各个字符不同），把字符放到堆中
     *     - 最后，然后把堆里的字符依次取出来即可
     * - 时间复杂度：O(NlogN)
     * - 空间复杂度：O(N)
     */
    public String frequencySortWithHeap(String s) {
        // 统计每个字符出现的次数
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        // 按频率大小构建一个大顶堆
        PriorityQueue<Character> queue = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));
        for (Character c : map.keySet()) {
            queue.add(c);
        }
        // 取出所有的字符
        char[] charArr = new char[s.length()];
        int k = 0;
        while (!queue.isEmpty()) {
            Character c = queue.poll();
            int freq = map.get(c);
            for (int i=0; i<freq; i++) {
                charArr[k++] = c;
            }
        }
        return new String(charArr);
    }
}
