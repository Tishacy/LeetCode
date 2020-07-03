package twopointer;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 345. 反转字符串中的元音字母
 * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
 *
 * 示例 1:
 *
 * 输入: "hello"
 * 输出: "holle"
 * 示例 2:
 *
 * 输入: "leetcode"
 * 输出: "leotcede"
 * 说明:
 * 元音字母不包含字母"y"。
 */
public class ReverseVowelsofAString_345 {
    /**
     * 思路：
     * - 给定的条件为：
     *     - 一个字符串，可以看成一个有序的序列
     *     - 反转字母，需要操作两个元素才能实现
     *         - 注意关键词 反转
     * - 因此，可以使用双指针的方法来解决，两个指针从两头向中间移动，当左右两个指针都是元音字母的时候，就反转它们。
     * - 指针移动的条件为：
     *     - 当左指针不是元音的时候，左指针右移
     *     - 当右指针不是元音的时候，右指针左移
     *     - 当左右两个指针都是元音的时候，反转两个字母，然后左指针右移，右指针左移
     * - 循环终止条件：
     *     - 当左指针位置等于右指针位置的时候，只有一个元素，结束循环
     * - 时间复杂度：
     *     - 在字符串上使用双指针：O(N)
     *     - 判断是否为元音字母:
     *         - 通过列表循环进行查找: O(M) M 为元音个数
     *         - 通过 HashSet 进行搜索：O(1)
     */
    public static void main(String[] args) {
        ReverseVowelsofAString_345 solution = new ReverseVowelsofAString_345();
        System.out.println(solution.reverseVowels("hello"));
        System.out.println(solution.reverseVowels("leetcode"));
    }

    public String reverseVowels(String s) {
        HashSet<Character> vowelSet = new HashSet<>(
                Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')
        );
        char[] chars = s.toCharArray();
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            // 使用列表循环查找判断是否为元音 O(M)
            // boolean isLeftVowel = this.isVowel(s.charAt(left));
            // boolean isRightVowel = this.isVowel(s.charAt(right));
            // 使用 HashSet 搜索判断是否为元音 O(1)
            boolean isLeftVowel = vowelSet.contains(s.charAt(left));
            boolean isRightVowel = vowelSet.contains(s.charAt(right));

            if (!isLeftVowel) {
                left++;
            }
            if (!isRightVowel) {
                right--;
            }
            if (isLeftVowel && isRightVowel) {
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
                left++;
                right--;
            }
        }
        return new String(chars);
    }

    /**
     * 通过列表循环判断是否为元音
     * 时间复杂度：O(M)
     * @param c 传入的字符
     * @return
     */
    public boolean isVowel(char c) {
        char[] vowels = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        for (char vowel : vowels) {
            if (vowel == c) {
                return true;
            }
        }
        return false;
    }
}
