package binarysearch;

/**
 * 69. x 的平方根
 *
 * 实现 int sqrt(int x) 函数。
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 *
 * 示例 1:
 * 输入: 4
 * 输出: 2
 *
 * 示例 2:
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 *      由于返回类型是整数，小数部分将被舍去。
 */
public class SquareX_69 {
    /**
     * 思路：
     *    由于 x 的开方值一定在 [0, x] 区间范围内，是一个有序数组，
     *    在一个有序数组中找寻一个数值，可以采用二分查找的方法。
     *
     *    二分查找注意点：
     *      - 搜索区间为闭区间，即 [low, high]
     *      - while (low < high) 还是 while (low <= high)
     *          循环体内，如果找到答案就返回，因此循环体本身的搜索区间大小为 1 的时候也要搜索，需要 low <= high
     *          如果循环体内一直不会返回结果，那么当搜索区间大小为 1 的时候，就不能继续搜索，因为继续搜索会无限循环下去
     *          因此，这里是找到结果就返回，所以就需要 low <= high
     *      - 循环结束时，没找到整数解，因此答案是小数解，并且因为循环结束是 log = high + 1, 因此取较小的那个值，
     *          即 high 值
     */
    public static void main(String[] args) {
        System.out.println(mySqrt(0));
        System.out.println(mySqrt(1));
        System.out.println(mySqrt(4));
        System.out.println(mySqrt(8));
        System.out.println(mySqrt(9));
        System.out.println(mySqrt(15));
        System.out.println(mySqrt(2147395599));
    }

    public static int mySqrt(int x) {
        if (x <= 1) {
            return x;
        }
        int low = 0, high = x;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            // 如果用 mid * mid 与 x 比较的话，容易出现数值越界问题
            // 因此使用 x / mid 与 mid 比较
            int sqrt = x / mid;
            if (mid == sqrt) {
                return mid;
            } else if (mid < sqrt) {
                // mid < sqrt => mid * mid < sqrt * mid = x
                // 即 mid < sqrt(x)
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return high;
    }
}
