package twopointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 524. 通过删除字母匹配到字典里最长单词
 * 给定一个字符串和一个字符串字典，找到字典里面最长的字符串，该字符串可以通过删除给定字符串的某些字符来得到。如果答案不止一个，返回长度最长且字典顺序最小的字符串。如果答案不存在，则返回空字符串。
 *
 * 示例 1:
 *
 * 输入:
 * s = "abpcplea", d = ["ale","apple","monkey","plea"]
 *
 * 输出:
 * "apple"
 * 示例 2:
 *
 * 输入:
 * s = "abpcplea", d = ["a","b","c"]
 *
 * 输出:
 * "a"
 * 说明:
 *
 * 所有输入的字符串只包含小写字母。
 * 字典的大小不会超过 1000。
 * 所有输入的字符串长度不会超过 1000。
 */
public class LongestWordInDictionaryThroughDeleting_524 {
    /**
     * 思路：
     * - 首先需要一个判断是否为子序列的函数 isSubsequence，参考 IsSubsequence.java
     * - 起始条件：设定最长子序列为一个空串 ""
     * - 遍历 d 中的每一个单词
     *     - 如果这个单词是一个子序列
     *         - 如果这个单词的长度大于当前最长子序列的长度
     *             - 就把最长子序列换成这个单词
     *         - 如果这个单词的长度等于当前最长子序列的长度
     *             - 就比较这个单词和当前最长子序列的字典序，如果当前这个单词的字典序更小
     *                 - 就把最长子序列换成这个单词
     *  - 返回最长子序列
     */
    public static void main(String[] args) {
        LongestWordInDictionaryThroughDeleting_524 solution = new LongestWordInDictionaryThroughDeleting_524();
        List<String> d = Arrays.asList("a","b","c");
        String s = "abpcplea";
        System.out.println(solution.findLongestWord(s, d));
    }

    public String findLongestWord(String s, List<String> d) {
        String longestWord = "";
        for (String word : d) {
            if (isSubsequence(s, word)) {
                if (word.length() > longestWord.length()) {
                    longestWord = word;
                } else if (word.length() == longestWord.length() && word.compareTo(longestWord) < 0) {
                    longestWord = word;
                } else {
                    continue;
                }
            }
        }
        return longestWord;
    }

    /**
     * 判断子序列
     * 判断字符串 t 是否是 字符串 s 的子序列
     * @param s 字符串
     * @param t 字符串
     * @return boolean
     */
    public boolean isSubsequence(String s, String t) {
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                j++;
            }
            i++;
        }
        return j == t.length();
    }
}
