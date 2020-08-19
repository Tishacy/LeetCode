package binarysearch;

/**
 * 744. 寻找比目标字母大的最小字母
 *
 * 给你一个排序后的字符列表 letters ，列表中只包含小写英文字母。另给出一个目标字母 target，请你寻找在这一有序列表里比目标字母大的最小字母。
 * 在比较时，字母是依序循环出现的。举个例子：
 * 如果目标字母 target = 'z' 并且字符列表为 letters = ['a', 'b']，则答案返回 'a'
 *  
 * 示例：
 * 输入:
 * letters = ["c", "f", "j"]
 * target = "a"
 * 输出: "c"
 *
 * 输入:
 * letters = ["c", "f", "j"]
 * target = "c"
 * 输出: "f"
 *
 * 输入:
 * letters = ["c", "f", "j"]
 * target = "d"
 * 输出: "f"
 *
 * 输入:
 * letters = ["c", "f", "j"]
 * target = "g"
 * 输出: "j"
 *
 * 输入:
 * letters = ["c", "f", "j"]
 * target = "j"
 * 输出: "c"
 *
 * 输入:
 * letters = ["c", "f", "j"]
 * target = "k"
 * 输出: "c"
 *  
 * 提示：
 * letters长度范围在[2, 10000]区间内。
 * letters 仅由小写字母组成，最少包含两个不同的字母。
 * 目标字母target 是一个小写字母。
 */
public class FindSmallestLetterGreaterThanTarget_744 {
    /**
     * 思路
     *  给定一个有序数组，找寻一个元素，可以使用二分查找法。
     *  问题需要的是比目标元素大的最小元素的位置，其实就是找目标元素的右边界，然后返回右边界右边的元素
     *  因此，这个问题是基础二分查找右边界的变种
     * @param args
     */
    public static void main(String[] args) {
        char[] letters = {'c', 'f', 'j'};
        char[] targets = {'a', 'c', 'd', 'g', 'j', 'k'};
        for (char target : targets) {
            System.out.println(nextGreatestLetter(letters, target));
        }
    }

    public static char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length;
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (letters[mid] == target) {
                // 因为是寻找右边界，所以右边界不动，左边界右移，来缩小查找范围
                low = mid + 1;
            } else if (letters[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        // 循环结束后，high + 1 = low, high 所在位置就是右边界位置，low 所在位置就是右边界的右侧位置
        // low 有可能越界，因此通过模运算实现循环搜索
        return letters[low % n];
    }
}
