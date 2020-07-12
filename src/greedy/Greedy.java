package greedy;

import java.util.*;

/**
 * 经典贪心算法问题
 * - 找零问题
 * - 分数背包问题
 * - 字符串数字拼接问题
 * - 活动选择问题
 */
public class Greedy {
    public static void main(String[] args) {
        testOfChangeProblem();
        testOfFractionalPackage();
        testOfConcatNumbers();
        testOfManageActivities();
    }

    /**
     * - 问题：找零问题
     *   假设老板需要找零 n 元钱，纸币的面额有 100 50 20 5 1
     *   假设每种纸币数量不限，如何找零使得找钱的纸币数目最少
     *
     * - 示例：
     *   [Input] n = 357
     *   [Output] [[3, 1, 0, 1, 2], 0]
     *   [Description] 需要 3 张 100, 1 张 50, 1 张 5 块，2 张 1 块
     *
     * - 贪心思路：
     *   为了找钱的纸币数目最少，需要尽量找面额较大的钱币
     *
     * - 流程：
     *     贪什么就按什么排序，因此按纸币面额降序排列
     *     遍历不同面额的纸币
     *         计算用这种面额的纸币可以找回的最大数量，如果还没找开，
     *         换下一个面额继续找
     *
     * - 时间复杂度：O(MlogM) M 为纸币的面额种类数
     * - 空间复杂度：O(M)
     * @param n 需要找零的钱数
     */
    public static Object[] changeProblem(int n) {
        int[] cash = {100, 50, 20, 5, 1};
        int[] count = new int[cash.length];
        for (int i=0; i<cash.length; i++) {
            count[i] = n / cash[i];
            n %= cash[i];
        }
        return new Object[] {count, n};
    }

    /**
     * 找零问题测试
     */
    public static void testOfChangeProblem() {
        System.out.println(">>> 找零问题: ");
        int n = 357;
        Object[] changeProblemRes = Greedy.changeProblem(n);
        System.out.println(String.format("\t n = %d\n\t res = %s", n, Arrays.deepToString(changeProblemRes)));
    }


    /**
     * - 问题：分数背包问题
     *   我们有一堆物品 S={a1, a2, ..., an} 每一个物品 ai 都有一个总价值 vi 和 一个总重量 wi.
     *   现在有一个背包，这个背包的容量为 W ,现在要将这些物品在不超出背包容量的情况下选择性的放入背包,
     *   使得背包里面物品的价值最大，里面的物品可以进行分割，即可以只选取一个物品 ai 的一部分
     *
     * - 示例：
     *   [Input] s = [[60, 10], [100, 20], [120, 30]], w = 50
     *   [Output] [[1, 1, 0.66667], 240]
     *   [Description] 总价值为 60 的全拿走，总价值为 100 的全拿走，总价值为 120 的拿走 2/3，
     *       拿走的总价值为 240
     *
     * - 贪心策略：
     *   贪的是物品的单价，由于可以拿走物品的一部分，因此单价越高的拿的越多，最后总价值就一定越高。
     *
     * - 流程：
     *     记录排序前物品的顺序
     *     贪什么就按什么排序，此处贪的是单价，因此按单价降序排列
     *     依次遍历每个物品，
     *         如果当前背包容量 >= 物品的总重量，把物品全部拿走
     *         如果当前背包容量 < 物品总重量，把物品部分拿走
     *     最后返回每种商品拿了多少
     *
     * - 时间复杂度：O(NlogN)
     *     - 排序：O(NlogN)
     *     - 贪心遍历：O(N)
     * - 空间复杂度：O(N)
     *     需要记录排序前原物品的顺序和最后结果的大小为 N 的辅助列表
     */
    public static Object[] fractionalPackage(int[][] s, int w) {
        // 记录排序前物品的顺序
        Map<int[], Integer> map = new HashMap<>();
        for (int i=0; i<s.length; i++) {
            map.put(s[i], i);
        }

        // 因此按单价降序排
        Arrays.sort(s, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0]/o2[1] - o1[0]/o1[1];
            }
        });

        // 贪心遍历
        float[] res = new float[s.length];
        float value = 0;
        for (int i=0; i<s.length; i++) {
            if (w >= s[i][1]) {
                res[map.get(s[i])] += 1.;
                value += s[i][0];
                w -= s[i][1];
            } else {
                res[map.get(s[i])] += 1.0 * w / s[i][1];
                value += 1.0 * w / s[i][1] * s[i][0];
                w = 0;
                break;
            }
        }
        return new Object[] {res, value};
    }

    /**
     * 分数背包问题测试
     */
    public static void testOfFractionalPackage() {
        System.out.println(">>> 分数背包问题: ");
        int[][] s = {{60, 10}, {100, 20}, {120, 30}};
        int w = 50;
        Object[] fractionalPackageRes = Greedy.fractionalPackage(s, w);
        System.out.println(String.format("\t s = %s, w = %d \n\t res = %s",
                Arrays.deepToString(s),
                w, Arrays.deepToString(fractionalPackageRes)));
    }

    /**
     * - 问题：字符串数字拼接问题
     *   有 n 个非负整数，将其按照字符串拼接的方式拼接成一个整数，如何拼接可以使
     *   拼接得到的整数最大
     *
     * - 示例：
     *   [Input] [32, 94, 128, 1286, 6, 71]
     *   [Output] 94716321286128
     *
     * - 思路：贪的是两个数字字符串拼接后的字典序
     * - 时间复杂度：O(NlogN)
     * - 空间复杂度：O(N)
     */
    public static String concatNumbers(Integer[] nums) {
        Arrays.sort(nums, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                String s1 = String.valueOf(o1);
                String s2 = String.valueOf(o2);
                return ((s2 + s1).compareTo(s1 + s2));
            }
        });
        StringBuffer str = new StringBuffer();
        for (int num : nums) {
            str.append(String.valueOf(num));
        }
        return str.toString();
    }

    /**
     * 数字拼接问题测试
     */
    public static void testOfConcatNumbers() {
        System.out.println(">>> 字符串数字拼接问题: ");
        Integer[] nums = {32, 94, 128, 1286, 6, 71};
        String res = concatNumbers(nums);
        System.out.println(String.format("\tnums = %s\n\tres = %s", Arrays.toString(nums), res));
    }

    /**
     * - 问题活动选择问题：
     *   假设有 n 个活动，这些活动要占用同一片场地，而场地在某个时刻只能供一个活动使用。
     *   每个活动都有开始时间 si 和结束 ei 时间，表示活动在 [si, ei) 区间占用场地。
     *   问安排哪些活动可以使得安排活动数最多？
     *
     * - 示例
     *   [Input] [[1, 4], [3, 5], [12, 16], [5, 7], [3, 9], [5, 9],
     *            [6, 10], [8, 11], [8, 12], [2, 14], [0, 6]]
     *   [Output] [[0, 6], [6, 10], [12, 16]]
     *
     * - 贪心策略：贪活动的时间，活动的结束事件越早，它后面空出来的时间越多，
     *   就有更多的时间来安排更多的活动
     *
     * - 时间复杂度：O(NlogN)
     * - 空间复杂度：O(N)
     */
    public static Object[] manageActivities(int[][] activities) {
        Arrays.sort(activities, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        List<int[]> res = new ArrayList<>();
        res.add(activities[0]);
        int[] lastAct = activities[0];
        int count = 0;
        for (int i=1; i<activities.length; i++) {
            int[] act = activities[i];
            if (act[0] < lastAct[1]) {
                continue;
            } else {
                res.add(act);
                count++;
                lastAct = act;
            }
        }
        return res.toArray();
    }

    /**
     * 活动选择问题测试
     */
    public static void testOfManageActivities() {
        System.out.println(">>> 活动选择问题: ");
        int[][] activities = {{1, 4}, {3, 5}, {12, 16}, {5, 7}, {3, 9}, {5, 9},
                              {6, 10}, {8, 11}, {8, 12}, {2, 14}, {0, 6}};
        Object[] res = manageActivities(activities);
        System.out.println(String.format("\tactivities = %s\n\tres = %s", Arrays.deepToString(activities), Arrays.deepToString(res)));
    }
}
