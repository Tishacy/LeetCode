package divideconquer;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 241. 为运算表达式设计优先级
 *
 * 给定一个含有数字和运算符的字符串，为表达式添加括号，改变其运算优先级以求出不同的结果。你需要给出所有可能的组合的结果。有效的运算符号包含 +, - 以及 * 。
 *
 * 示例 1:
 * 输入: "2-1-1"
 * 输出: [0, 2]
 * 解释:
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 *
 * 示例 2:
 * 输入: "2*3-4*5"
 * 输出: [-34, -14, -10, -10, 10]
 * 解释:
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 */
public class DifferentWaysToAddParentheses_241 {
    /**
     * 思路：
     *  - 字符串的组合问题，可以采用分而治之思想解决，通过记忆化递归实现
     *  - 本问题的思路是，通过操作符号（+ - *）将字符串分割为两部分，递归求解左右两部分的答案，
     *    然后将两部分的答案再进行运算。
     *    其中，字符串中的每个操作符号都需要一遍递归，会有大量的重复计算，因此使用 HashMap 做
     *    记忆化处理，记录已经求解过的答案。
     *  - 注意特殊情况：输入的字符串为空，和只有一个数字而没有操作符号的情况
     * @param args
     */
    public static void main(String[] args) {
        String input = "2*3-4*5";
//        String input = "";
//        String input = "123";
//        String input = "2-1-1";
        List<Integer> res = diffWaysToCompare(input);
        System.out.println(res.toString());
    }

    private static Map<String, List<Integer>> map = new HashMap<>();

    public static List<Integer> diffWaysToCompare(String input) {
        int len = input.length();
        // input 为空字符串
        if (len == 0) {
            return new ArrayList<>();
        }
        // 通过 HashMap 进行记忆化
        if (map.containsKey(input)) {
            return map.get(input);
        }
        // input 中只有一个数字
        int num = 0;
        int i=0;
        for (; i<len; i++) {
            if (!isOperator(input.charAt(i))) {
                num = num * 10 + (input.charAt(i) - '0');
            } else {
                break;
            }
        }
        if (i == len) {
            List<Integer> res = new ArrayList<>();
            res.add(num);
            map.put(input, res);
            return res;
        }
        // input 中有多个数字和操作符
        // 分而治之
        List<Integer> res = new ArrayList<>();
        for (int m=0; m<len; m++) {
            if (isOperator(input.charAt(m))) {
                char op = input.charAt(m);
                List<Integer> leftRes = diffWaysToCompare(input.substring(0, m));
                List<Integer> rightRes = diffWaysToCompare(input.substring(m+1, len));
                for (int n1 : leftRes) {
                    for (int n2 : rightRes) {
                        res.add(calculate(n1, op, n2));
                    }
                }
            }
        }
        map.put(input, res);
        return res;
    }

    public static int calculate(int n1, char op, int n2) {
        switch (op) {
            case '+':
                return n1 + n2;
            case '-':
                return n1 - n2;
            case '*':
                return n1 * n2;
            default:
                return -1;
        }
    }

    public static boolean isOperator(char op) {
        return (op == '+' || op == '-' || op == '*');
    }
}
