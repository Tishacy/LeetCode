package greedy;

/**
 * 860. 柠檬水找零
 *
 * 在柠檬水摊上，每一杯柠檬水的售价为 5 美元。
 * 顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
 * 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
 * 注意，一开始你手头没有任何零钱。
 * 如果你能给每位顾客正确找零，返回 true ，否则返回 false 。
 *
 * 示例 1：
 * 输入：[5,5,5,10,20]
 * 输出：true
 * 解释：
 * 前 3 位顾客那里，我们按顺序收取 3 张 5 美元的钞票。
 * 第 4 位顾客那里，我们收取一张 10 美元的钞票，并返还 5 美元。
 * 第 5 位顾客那里，我们找还一张 10 美元的钞票和一张 5 美元的钞票。
 * 由于所有客户都得到了正确的找零，所以我们输出 true。
 *
 * 示例 2：
 * 输入：[5,5,10]
 * 输出：true
 *
 * 示例 3：
 * 输入：[10,10]
 * 输出：false
 *
 * 示例 4：
 * 输入：[5,5,10,10,20]
 * 输出：false
 * 解释：
 * 前 2 位顾客那里，我们按顺序收取 2 张 5 美元的钞票。
 * 对于接下来的 2 位顾客，我们收取一张 10 美元的钞票，然后返还 5 美元。
 * 对于最后一位顾客，我们无法退回 15 美元，因为我们现在只有两张 10 美元的钞票。
 * 由于不是每位顾客都得到了正确的找零，所以答案是 false。
 *  
 * 提示：
 * 0 <= bills.length <= 10000
 * bills[i] 不是 5 就是 10 或是 20 
 */
public class LemonadeChange_860 {
    public static void main(String[] args) {
        LemonadeChange_860 solution = new LemonadeChange_860();
        System.out.println(solution.lemonadeChange(new int[] {5,5,5,10,20}));
        System.out.println(solution.lemonadeChange(new int[] {5,5,10}));
        System.out.println(solution.lemonadeChange(new int[] {10,10}));
        System.out.println(solution.lemonadeChange(new int[] {5,5,10,10,20}));
    }

    /**
     * 思路：
     * - 经典的找零问题，用贪心算法解决。
     * - 对于找零问题，贪心策略是：
     *     如果有面额较大的钱，先找大的钱 10 美元，再找小的钱 5 美元
     *     因为对于任意一种需要找零的情况，都可以用 5 美元去找零，所以
     *     它的适用性最强，5 美元越多，就越可以去找零，因此找零的时候
     *     希望可以留着 5 美元，把面额大的钱先找回去。
     * - 流程：
     *     记录一下当前各类纸币的个数
     *     遍历每个顾客
     *         如果顾客给了 5 美元，那 5 美元的数量+1
     *         如果顾客给了 10 美元，此时需要找 5 美元
     *             如果现在还要 5 美元的钱，就找回去，5 美元的数量-1
     *             如果现在没有 5 美元，就找不开了，返回 false
     *         如果顾客给了 20 美元，此时需要找 15 美元
     *             如果现在有 10 美元和 5 美元，就找回去，10 美元数量-1，5 美元数量-1
     *             如果现在有 3 张或以上 5 美元，就找回去，5 美元数量-3
     *             其他情况，找不开了，返回 false
     *      遍历完了顾客，就相当于所有顾客都找开了钱，就返回 true
     * - 时间复杂度：O(N)
     * - 空间复杂度：O(1)，需要一个长度为 3 的辅助数组记录每种纸币的数量
     */
    public boolean lemonadeChange(int[] bills) {
        int[] cashes = new int[3];
        for (int i=0; i<bills.length; i++) {
            int bill = bills[i];
            if (bill == 5) {
                cashes[0]++;
            } else if (bill == 10) {
                if (cashes[0] == 0) return false;
                cashes[0]--;
                cashes[1]++;
            } else {
                if (cashes[0] > 0 && cashes[1] > 0) {
                    cashes[0]--;
                    cashes[1]--;
                    cashes[2]++;
                } else if (cashes[0] > 3) {
                    cashes[0] -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
