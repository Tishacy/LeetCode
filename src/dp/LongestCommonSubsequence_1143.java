package dp;

/**
 * 1143. 最长公共子序列
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
 * 若这两个字符串没有公共子序列，则返回 0。
 *
 * 示例 1:
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace"，它的长度为 3。
 *
 * 示例 2:
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc"，它的长度为 3。
 *
 * 示例 3:
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0。
 *
 * 提示:
 * 1 <= text1.length <= 1000
 * 1 <= text2.length <= 1000
 * 输入的字符串只含有小写英文字符。
 */
public class LongestCommonSubsequence_1143 {
    /**
     * 思路：两个字符串的子序列问题，通常用二维动态规划解决。
     * 定义二维 dp 数组，dp[i][j] 表示以第 text1[i] 结尾的字符串，和以第 text2[j]
     * 结尾的字符串的最长公共子序列的长度，并且设 i=0 (或 j=0 )时表示空串，则状态转移函数为：
     *  当 i=0 或者 j=0 时, dp[i][j] = 0，因为空串与其他任意字符串的公共子序列长度都为 0
     *  当 i>=1 或 j>=1 时，
     *      如果 text1[i] = text2[j]，dp[i][j] = dp[i-1][j-1] + 1，即在已有的最长公共子序列中添加该字符
     *      如果 text1[i] != text2[j]，dp[i][j] = Max(dp[i][j-1], dp[i-1][j], dp[i-1][j-1])，即
     *          最长公共子序列的长度是能够转移到该状态的最长公共子序列长度的最大值。
     * @param args
     */
    public static void main(String[] args) {
        LongestCommonSubsequence_1143 solution = new LongestCommonSubsequence_1143();
        String text1 = "abc";
        String text2 = "def";
        System.out.println("[Solution] LCS: " + solution.longestCommonSubsequence(text1, text2));
    }

    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null) {
            return 0;
        }
        char[] charArr1 = text1.toCharArray();
        char[] charArr2 = text2.toCharArray();
        int m = charArr1.length;
        int n = charArr2.length;
        int[][] dp = new int[m+1][n+1];
        for (int i=1; i<=m; i++) {
            for (int j=1; j<=n; j++) {
                if (charArr1[i-1] == charArr2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[m][n];
    }
}
