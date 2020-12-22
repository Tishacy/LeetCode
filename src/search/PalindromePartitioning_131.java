package search;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
public class PalindromePartitioning_131 {

    /**
     * 思路：分割方案其实就是是找出所有的 [1, s.len-1] 中数字的所有组合方案，
     * 并找到所分割的字符全部是回文串的分割方式，放到最终的结果中。
     * @param args
     */
    public static void main(String[] args) {
        PalindromePartitioning_131 solution = new PalindromePartitioning_131();
        String s = "aab";
        List<List<String>> partitions = solution.partition(s);
        System.out.println("[Solution] Partitions: " + partitions);
    }

    private List<List<String>> partitions = new ArrayList<>();

    public List<List<String>> partition(String s) {
        if (s == null || s.length() == 0) {
            return partitions;
        }
        List<Integer> partitionPath = new ArrayList<>();
        int[] nums = new int[s.length() - 1];
        for (int i=0; i<nums.length; i++) {
            nums[i] = i+1;
        }
        backTracking(0, partitionPath, s, nums);
        return partitions;
    }

   private void backTracking(int curIndex, List<Integer> path, String s, int[] nums) {
        List<String> pathStrList = getPathList(s, path);
        if (isAllPalindrome(pathStrList)) {
            partitions.add(pathStrList);
        }
        if (path.size() == nums.length) {
            return;
        }
        for (int i=curIndex; i<nums.length; i++) {
            path.add(nums[i]);
            backTracking(i+1, path, s, nums);
            path.remove(path.size() - 1);
        }
    }

    private List<String> getPathList(String s, List<Integer> path) {
        List<String> pathStrList = new ArrayList<>();
        if (path.size() == 0) {
            pathStrList.add(s);
            return pathStrList;
        }
        pathStrList.add(s.substring(0, path.get(0)));
        for (int i=0; i<path.size()-1; i++) {
            pathStrList.add(s.substring(path.get(i), path.get(i+1)));
        }
        pathStrList.add(s.substring(path.get(path.size() - 1)));
        return pathStrList;
    }

    private boolean isPalindrome(String s) {
        for (int i=0; i<s.length()/2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    private boolean isAllPalindrome(List<String> pathStrList) {
        for (String str : pathStrList) {
            if (!isPalindrome(str)) {
                return false;
            }
        }
        return true;
    }
}
