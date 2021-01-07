package offer;

/**
 * 剑指 Offer 10- I. 斐波那契数列
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。斐波那契数列的定义如下：
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 示例 1：
 * 输入：n = 2
 * 输出：1
 *
 * 示例 2：
 * 输入：n = 5
 * 输出：5
 *
 * 提示：
 * 0 <= n <= 100
 */
public class Fibonacci_10 {

    public static void main(String[] args) {
        int[] ns = {2, 5, 100};
        Fibonacci_10 solution = new Fibonacci_10();
        for (int n : ns) {
            System.out.printf("[Solution] Fibonacci number of %s: %s%n", n, solution.fib(n));
        }
    }

    public int fib(int n) {
        if (n < 2) {
            return n;
        }
        int first = 0, second = 1;
        for (int i=2; i<=n; i++) {
            int fib = (first + second) % 1000000007;
            first = second;
            second = fib;
        }
        return second;
    }
}
