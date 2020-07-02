package twopointer;

/**
 * 633. 平方数之和
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a^2 + b^2 = c。
 *
 * 示例1:
 *
 * 输入: 5
 * 输出: True
 * 解释: 1 * 1 + 2 * 2 = 5
 *  
 *
 * 示例2:
 *
 * 输入: 3
 * 输出: False
 */
public class SumOfSquareNumbers_633 {
    /**
     * 思路：
     * - 题目给了下面两个条件：
     *     - 需要通过操作两个数来判断是否得到结果
     *     - 这两个数肯定是小于等于 sqrt(c) 的，这就相当于是给了一个 [0-sqrt(c)] 的有序整数数组
     *         - 一般 a 和 b 都是是小于 sqrt(c) 的
     *         - 极端情况下，a 和 b 某一个数会等于 sqrt(c), 如：a=0, b=1, c=1
     *         - 为什么是小于等于 sqrt(c) 而不是 c?
     *             - 首先，由于最终条件为 a^2 + b^2 = c，c 为一个定值，所以 a 越小，b 越大，
     *               极端情况下，a 为 0，b 为最大值，此时，b^2 = c, 因此，b = sqrt(c)，所以
     *               a 和 b 的取值边界为 [0-sqrt(c)]
     *             - 其次，理论上小于等于 c 的话，也可以得到最终结果，但是可能会出现数值越界的问题。
     *               因为 c 的取值范围为 int，但是 c^2 就可能超过 int 范围，导致比较出错，
     *               而 sqrt(c)^2 == c，因此其取值范围仍为 int，不会出现数值越界问题。
     *               因此，必须要使用 sqrt(c) 作为取值边界。
     * - 由于给了有序数组，并且还是操作两个数得到结果，因此采用双指针法解决，
     *   两个指针分别从两头向中间移动，直到达到要求。
     * - 指针移动条件：
     *     - 如果 a^2 + b^2 < c，右指针向左移
     *     - 如果 a^2 + b^2 > c，左指针向右移
     * - 循环终止条件：
     *     - 如果找到了 a^2 + b^2 == c，返回 True
     *     - 由于是两个不同的整数，所以 a < b，当 a == b 时，跳出循环，返回 False
     *         - 注意！通过网站测试用例发现，c = 2 时，返回结果为 true，
     *           即 a = 1, b = 1 时满足条件，所以当 a == b 时也要进行平方和判断
     *           因此这里的终止条件应该改为：a <= b
     */
    public static void main(String[] args) {
        SumOfSquareNumbers_633 solution = new SumOfSquareNumbers_633();
        // Examples
        System.out.println(solution.judgeSquareSum(5));
        System.out.println(solution.judgeSquareSum(3));
        System.out.println(solution.judgeSquareSum(2));
        System.out.println(solution.judgeSquareSum(1000000));
    }

    public boolean judgeSquareSum(int c) {
        int a = 0;
        int b = (int)Math.sqrt(c);
        while (a <= b) {
            int sumOfSquare = a * a + b * b;
            if (sumOfSquare > c) {
                b--;
            } else if (sumOfSquare < c) {
                a++;
            } else {
                return true;
            }
        }
        return false;
    }
}
