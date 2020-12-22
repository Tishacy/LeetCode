package search;


import java.util.ArrayList;
import java.util.List;
import java.util.prefs.BackingStoreException;

/**
 * 131. 分割回文串
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * 返回 s 所有可能的分割方案。
 *
 * 示例:
 * 输入: "aab"
 * 输出:
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 */
public class AnotherPalindromePartitioning_131 {

    /**
     * 思路：与 {@link RestoreIpAddress_93} 思路相同
     * @param args
     */
    public static void main(String[] args) {
        AnotherPalindromePartitioning_131 solution = new AnotherPalindromePartitioning_131();
        String s = "aab";
        List<List<String>> partitions = solution.partition(s);
        System.out.println("[Solution] Partitions: " + partitions);
    }

    private List<List<String>> partitions = new ArrayList<>();

    public List<List<String>> partition(String s) {
        if (s == null || s.length() == 0) {
            return partitions;
        }
        List<String> partitionPath = new ArrayList<>();
        backTracking(partitionPath, s);
        return partitions;
    }

    /**
     * 当前 path 已经包含当前状态的子串，需要寻找下一个是回文串的子串，加入到path中继续搜索
     * @param path 当前路径的子串集合
     * @param s
     */
    private void backTracking(List<String> path, String s) {
        String pathStr = String.join("", path);
        if (pathStr.length() == s.length()) {
            partitions.add(new ArrayList<>(path));
            return;
        }
        int start = pathStr.length();
        for (int len=1; len<=s.length() - start; len++) {
            int end = start + len;
            String curStr = s.substring(start, end);
            if (!isPalindrome(curStr)) {
                continue;
            }
            path.add(curStr);
            backTracking(path, s);
            path.remove(path.size() - 1);
        }
    }

    private boolean isPalindrome(String s) {
        int mid = s.length() / 2;
        for (int i=0; i<mid; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
