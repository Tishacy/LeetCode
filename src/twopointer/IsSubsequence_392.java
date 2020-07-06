package twopointer;

/**
 * 392. 判断子序列
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 *
 * 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。
 *
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 *
 * 示例 1:
 * s = "abc", t = "ahbgdc"
 *
 * 返回 true.
 *
 * 示例 2:
 * s = "axc", t = "ahbgdc"
 *
 * 返回 false.
 */
public class IsSubsequence_392 {
    /**
     * 思路：
     * - 判断子序列问题，可以使用双指针解决，有两个字符串（src 和 target）,
     *   判断 src 是否是 target 的子序列
     * - 起始：两个指针（i, j）分别指向两个字符串（src, target）的头部
     * - 指针移动规则：
     *     - 如果两个指针值相等，src 和 target 的指针都往后移动一步
     *     - 如果两个指针不相等，target 的指针向后移动一步
     * - 循环终止条件
     *     - 循环需要保证循环内部的 charAt(index) 方法不会越界，
     *     因此需要 i < src.length() && j < target.length()
     * - 最终输出指针 i 是否等于 src.length()
     */
    public static void main(String[] args) {
        IsSubsequence_392 solution = new IsSubsequence_392();
        String s = "abc", t = "ahbgdc";
        System.out.println(solution.isSubsequence(s, t));
    }

    public boolean isSubsequence(String s, String t) {
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == s.length();
    }
}
