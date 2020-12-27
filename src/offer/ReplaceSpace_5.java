package offer;

/**
 * 剑指 Offer 05. 替换空格
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 *
 * 示例 1：
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 *
 * 限制：
 * 0 <= s 的长度 <= 10000
 */
public class ReplaceSpace_5 {
    public static void main(String[] args) {
        ReplaceSpace_5 solution = new ReplaceSpace_5();
        String s = "We are happy.";
        String s1 = " We are happy.";
        String s2 = " We are happy.  ";
        String s3 = "We     are happy.";
        System.out.println("[Solution] " + s + ": " + solution.replaceSpace(s));
        System.out.println("[Solution] " + s1 + ": " + solution.replaceSpace(s1));
        System.out.println("[Solution] " + s2 + ": " + solution.replaceSpace(s2));
        System.out.println("[Solution] " + s3 + ": " + solution.replaceSpace(s3));
        System.out.println("[Solution] " + s3 + ": " + solution.trimAndReplace(s3));
    }

    /**
     * 将字符串中的空格替换成 "%20"
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                stringBuilder.append("%20");
            } else {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 将字符串前后的空格去掉，其余的空格替换成 "%20"，
     * 并且连续的空格只能替换为一个 "%20"
     * @param s
     * @return
     */
    public String trimAndReplace(String s) {
        char[] trimCharArr = s.trim().toCharArray();
        int trimStrLen = trimCharArr.length;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0; i<trimStrLen; i++) {
            if (trimCharArr[i] == ' ') {
                if (i > 0 && trimCharArr[i-1] == ' ') {
                    continue;
                }
                stringBuilder.append("%20");
            } else {
                stringBuilder.append(trimCharArr[i]);
            }
        }
        return stringBuilder.toString();
    }
}
