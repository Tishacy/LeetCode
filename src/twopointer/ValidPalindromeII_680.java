package twopointer;

/**
 * 680. 验证回文字符串 Ⅱ
 *
 * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
 *
 * 示例 1:
 *
 * 输入: "aba"
 * 输出: True
 * 示例 2:
 *
 * 输入: "abca"
 * 输出: True
 * 解释: 你可以删除c字符。
 * 注意:
 *
 * 字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。
 */
public class ValidPalindromeII_680 {
    /**
     * 思路：
     * - 题目给了下面条件：
     *     - 给了一个字符串，相当于是一个有序序列
     *     - 判断是否回文，即左右每个字符是否相等，需要操作两个元素
     * - 因此，可以采用双指针解决，左右指针从两头开始向中间移动
     * - 移动条件为：
     *     - 当左右指针的值相同，表明该两个字母回文，左指针右移，右指针左移
     *     - 当左右指针不同时，表明要删除一个元素看看，由于只能删除一个元素，所以这个步骤只会走一遍
     *         - 删除左指针所在字符，判断 [左指针+1, 右指针] 的字符串是否为回文字符串
     *         - 删除右指针所在字符，判断 [左指针，右指针-1] 的字符串是否为回文字符串
     *         - 如果上面两个都不是回文字符串，那么就跳出循环，返回 false
     *         - 如果上面两个只要有一个是回文字符串，那就跳出循环，返回 true
     * - 循环终止条件：
     *     - 如果一个元素都不删，当左指针不小于右指针的时候，就可以跳出循环，返回 true
     *     - 如果删除一个元素
     *         - 循环中间只要发现不满足删除一个元素为回文字符串，就返回 false
     *         - 如果删除一个元素发现子串为回文字符串，就返回 true
     * - 时间复杂度：
     *     - 双指针循环字符串：O(N)
     *     - 判断子串为回文字符串：O(N)
     *         - 但是这个判断最多只会执行一次
     *     - 因此，整体时间复杂度为 O(N)
     */
    public static void main(String[] args) {
        ValidPalindromeII_680 solution = new ValidPalindromeII_680();
        System.out.println(solution.validPalindrome("aba"));
        System.out.println(solution.validPalindrome("acdbba"));
    }

    public boolean validPalindrome(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (chars[left] == chars[right]) {
                left++;
                right--;
            } else {
                boolean isPalindromeDeleteLeft = this.isPalindrome(chars, left+1, right);
                boolean isPalindromeDeleteRight = this.isPalindrome(chars, left, right-1);
                if (!isPalindromeDeleteLeft && !isPalindromeDeleteRight) {
                    return false;
                }
                return true;
            }
        }
        return true;
    }

    public boolean isPalindrome(char[] chars, int left, int right) {
        while (left < right) {
            if (chars[left] == chars[right]) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }
}
