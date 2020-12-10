package search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 示例:
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 */
public class LetterCombinationsOfAPhoneNumber_17 {

    /**
     * 思路：
     *  对于排列组合问题，如果是确定个数的数字排列组合，完全可以使用多重
     *  for 循环解决，比如规定只有 2 个数字所对应的字母进行排列组合，就可以用
     *  两重循环遍历所有的可能并输出即可，同理，如果有 n 个数字所对应的字母
     *  进行排列组合，就可以用 n-for 循环遍历所有的可能。但是，因为 n 是不确定的，
     *  因此，编程时是无法确定使用多少层循环的，因此需要使用回溯的方法。
     *
     *  回溯是 DFS 的一种，与普通的 DFS 找到确定的某个结果就返回不同，排列组合
     *  问题不是找到某个结果的路径，而是要找到所有的可能的结果，因此在找到某个结果
     *  之后，不应当直接返回，而是应当回溯上一个状态，继续搜索，直到所有的路径走完为止。
     *
     *  在回溯的算法中，重点需要 2 个关键变量：路径变量 和 路径的集合变量
     *
     * @param args
     */
    public static void main(String[] args) {
        String digits = "23";
        LetterCombinationsOfAPhoneNumber_17 solution = new LetterCombinationsOfAPhoneNumber_17();
        List<String> combinations = solution.letterCombinations(digits);
        System.out.println("[Solution] Combinations: " + combinations.toString());
    }

    private String[] keys = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return combinations;
        }
        StringBuilder path = new StringBuilder();
        backTracking(path, combinations, digits);
        return combinations;
    }

    /**
     * 回溯方法
     * @param path
     * @param combinations
     * @param digits
     */
    private void backTracking(StringBuilder path, List<String> combinations, String digits) {
        // 如果路径长度已经与 digits 的长度相等了
        // 一条完整的路径已经走到底了，将这条完整的路径添加到组合中，就返回了
        if (path.length() == digits.length()) {
            combinations.add(path.toString());
            return;
        }
        // 取出下一个数字所对应的字母列表
        // 添加到路径中，递归回溯当前路径
        // 当递归函数返回的时候，是将一条完整的路径走完的时候，这是要需要回溯到上一个状态
        int number = digits.charAt(path.length()) - '0';
        char[] letters = keys[number].toCharArray();
        for (char letter : letters) {
            path.append(letter);
            backTracking(path, combinations, digits);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
