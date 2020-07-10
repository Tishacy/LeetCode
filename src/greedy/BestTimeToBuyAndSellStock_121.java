package greedy;

/**
 * 121. 买卖股票的最佳时机
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 *
 * 注意：你不能在买入股票前卖出股票。
 * 示例 1:
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 *
 * 示例 2:
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 */
public class BestTimeToBuyAndSellStock_121 {
    public static void main(String[] args) {
        BestTimeToBuyAndSellStock_121 solution = new BestTimeToBuyAndSellStock_121();
        int[] prices = {7,1,5,3,6,4};
        int maxProfit = solution.maxProfit(prices);
        System.out.println(maxProfit);
    }

    /**
     * 思路
     * - 这道题是买卖股票获取最高收益，并且只可以交易（买卖）一次，采用骑驴找马的策略，
     *   其实是一种滑动窗口思想，即在遍历过程中，存储当前已有的最小值（骑驴），然后
     *   向后找尽可能的最大收益（找马），一旦发现有比已存最小值还小的就换驴，然后继续
     *   找马。
     * - 时间复杂度: O(N)，只需要遍历一次数组
     * - 空间复杂度: O(1)
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int soFarMin = prices[0];
        int maxProfit = 0;
        for (int i=1; i<prices.length; i++) {
            if (prices[i] < soFarMin) {
                soFarMin = prices[i];  // 骑驴
            } else {
                maxProfit = Math.max(maxProfit, prices[i] - soFarMin);
            }
        }
        return maxProfit;
    }
}
