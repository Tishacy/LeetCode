package dp;

import java.util.Arrays;

/**
 * 646. 最长数对链
 * 给出 n 个数对。 在每一个数对中，第一个数字总是比第二个数字小。
 * 现在，我们定义一种跟随关系，当且仅当 b < c 时，数对(c, d) 才可以跟在 (a, b) 后面。我们用这种形式来构造一个数对链。
 * 给定一个数对集合，找出能够形成的最长数对链的长度。你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。
 *
 * 示例：
 * 输入：[[1,2], [2,3], [3,4]]
 * 输出：2
 * 解释：最长的数对链是 [1,2] -> [3,4]
 *
 * 提示：
 * 给出数对的个数在 [1, 1000] 范围内。
 */
public class MaximumLengthOfPairChain_646 {

    public static void main(String[] args) {
        MaximumLengthOfPairChain_646 solution = new MaximumLengthOfPairChain_646();
        int[][] pairs = {{1, 2}, {2, 3}, {3, 4}};
        System.out.println("[Solution] Maximum length: " + solution.findLongestChainDp(pairs));
        System.out.println("[Solution] Maximum length: " + solution.findLongestChainGreedy(pairs));
    }

    /**
     * 思路 1：
     * 本题与最长递增子序列类似，只不过将字符比较大小换成了数对之间的比较大小，
     * 因此，可以通过dp解决。
     * 设有 dp 数组，dp[i] 表示以第 i 个数对结尾的最长数对链的长度，则状态
     * 转移函数为：
     * 当 i = 0 时，dp[i] = 1
     * 当 i > 1 时，dp[i] = Max(dp[j]) + 1, 其中遍历 j ∈ [0, i)，并且 pairs[i][0] > pairs[j][1]
     * <p>
     * 时间复杂度：O(N²) 2 层 for 循环
     * 空间复杂度: O(N) 1 个长度为 n 的 dp 数组
     *
     * @param pairs
     * @return
     */
    public int findLongestChainDp(int[][] pairs) {
        if (pairs == null || pairs.length == 0 || pairs[0].length == 0) {
            return 0;
        }
        int n = pairs.length;
        int[] dp = new int[n];
        dp[0] = 1;
        int maxLength = 0;
        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (pairs[i][0] > pairs[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }
        return maxLength;
    }

    /**
     * 思路 2:
     * 其实分析本题，可以看出这完全是一个活动选择问题（活动时间完全错开），只不过换了一种表述方式。
     * 思路可以参考 {@link greedy.Greedy} 中 `manageActivities` 部分。
     * 贪心思路为：为了使得获得的数对链尽可能的长，每次应当选择所有数对中 pair[1] 最小的那个。
     * <p>
     * 时间复杂度: O(NlogN) 排序需要 O(NlogN)，贪心遍历需要 O(N)
     * 空间复杂度: O(1)
     *
     * @param pairs
     * @return
     */
    public int findLongestChainGreedy(int[][] pairs) {
        if (pairs == null || pairs.length == 0 || pairs[0].length == 0) {
            return 0;
        }
        // pairs 按照 pair[1] 升序排列
        Arrays.sort(pairs, (p1, p2) -> p1[1] - p2[1]);
        int n = pairs.length;
        int[] curPair = pairs[0];
        int maxLength = 1;
        for (int i = 1; i < n; i++) {
            if (pairs[i][0] > curPair[1]) {
                maxLength++;
                curPair = pairs[i];
            }
        }
        return maxLength;
    }
}
