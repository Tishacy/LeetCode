package search;

import java.util.ArrayList;
import java.util.List;

/**
 * 93. 复原IP地址
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * 有效的 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效的 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效的 IP 地址。
 *
 * 示例 1：
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 *
 * 示例 2：
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 *
 * 示例 3：
 * 输入：s = "1111"
 * 输出：["1.1.1.1"]
 *
 * 示例 4：
 * 输入：s = "010010"
 * 输出：["0.10.0.10","0.100.1.0"]
 *
 * 示例 5：
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 *
 * 提示：
 * 0 <= s.length <= 3000
 * s 仅由数字组成
 */
public class RestoreIpAddress_93 {

    /**
     * 思路：
     *  对于排列组合问题，如果是确定个数的元素进行排列组合，那么可以使用
     *  确定数量层的 for 循环。此题中，其实就是 3 个点在给定字符串的字符间隔
     *  的组合问题，因此可以使用 3 重循环的到所有可能的 IP 组合，并筛选出
     *  合法的 IP 地址。
     *
     *  此题目获取所有的排列组合问题，也可以使用 回溯法（DFS）进行解决。
     *  回溯函数的关键变量有两个：当前路径 和 路径集合
     *  回溯函数的任务是：
     *      DFS：在当前路径下，找出下一层的节点，加入到路径中，成为新路径继续搜索。
     *      回溯：当获取到一个完整的路径之后，路径回溯到上一个状态，继续搜索。
     * @param args
     */
    public static void main(String[] args) {
        RestoreIpAddress_93 solution = new RestoreIpAddress_93();
        String s = "101023";
        List<String> ips = solution.restoreIpAddresses(s);
        System.out.println("[Solution] IPs: " + ips);
    }

    public List<String> restoreIpAddresses(String s) {
        List<String> combinatios = new ArrayList<>();
        if (s == null || s.length() == 0 || s.length() > 12) {
            return combinatios;
        }
        List<String> path = new ArrayList<>();
        backTracking(path, combinatios, s);
        return combinatios;
    }

    private void backTracking(List<String> path, List<String> combinatios, String s) {
        String curStr = String.join("", path);
        // 获取到了一个完整的路径，添加到 combinations 中
        if (curStr.length() == s.length() && path.size() == 4) {
            combinatios.add(String.join(".", path));
            return;
        }
        // IP 中最多只有 4 个段落，因此如果当前 path 中已经有 4 个段落
        // 而 s 中仍然剩余其他的字符，那就废弃掉这一条路景
        if (curStr.length() < s.length() && path.size() == 4) {
            return;
        }
        // 在当前路径下，找出下一层的节点，加入到路径中，成为新路径继续搜索。
        int start = curStr.length();
        for (int len=1; len<=Math.min(3, s.length()-curStr.length()); len++) {
            int end = start + len;
            String nextIpPiece = s.substring(start, end);
            if (!isValidIpPiece(nextIpPiece)) {
                continue;
            }
            path.add(nextIpPiece);
            backTracking(path, combinatios, s);
            // 当获取到一个完整的路径之后，路径回溯到上一个状态，继续搜索。
            path.remove(path.size() - 1);
        }
    }

    private boolean isValidIpPiece(String ipPiece) {
        if (ipPiece.length() > 1 && ipPiece.charAt(0) == '0') {
            return false;
        }
        int num = Integer.parseInt(ipPiece);
        return (num >= 0 && num <= 255);
    }
}
