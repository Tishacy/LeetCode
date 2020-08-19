package binarysearch;

/**
 * 704. 二分查找
 *
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 *
 * 示例 1:
 * 输入: nums = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 解释: 9 出现在 nums 中并且下标为 4
 *
 * 示例 2:
 * 输入: nums = [-1,0,3,5,9,12], target = 2
 * 输出: -1
 * 解释: 2 不存在 nums 中因此返回 -1
 *  
 * 提示：
 * 你可以假设 nums 中的所有元素是不重复的。
 * n 将在 [1, 10000]之间。
 * nums 的每个元素都将在 [-9999, 9999]之间。
 */
public class BinarySearch_704 {
    public static void main(String[] args) {
        int[] list = {1, 1, 2, 2, 3, 4, 4, 4, 4, 4, 5, 6, 7, 8, 9};
        int[] targets = {2, 3, 4, 10};
        for (int target : targets) {
            System.out.print(binarySearch(list, target) + " ");
            System.out.print(binarySearchLeft(list, target) + " ");
            System.out.print(binarySearchLeftConcise(list, target) + " ");
            System.out.print(binarySearchRight(list, target) + " ");
            System.out.print(binarySearchRightConcise(list, target) + " ");
            System.out.println();
        }
    }

    /**
     * 基本的二分查找
     * @param list 给定数组
     * @param value 目标值
     * @return 目标值所载的 key
     */
    public static int binarySearch(int[] list, int value) {
        int low = 0, high = list.length-1;
        // low == high 时，还有一个镉目标元素，也要进行判断
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (list[mid] == value) {
                return mid;
            } else if (list[mid] < value) {
                low = mid + 1;
            } else if (list[mid] > value) {
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 在一个有重复元素的数组中查找 key 的最左位置
     *  此时，二分查找最终返回的不应该是 mid 的位置，而是 low 的位置（左边位置）
     *  因此，所需要的修改就是：
     *      1. 因为 list[mid] == value 的时候，并不是直接跳出循环 return 出 mid，
     *         而是继续循环下去，所以不能让循环体中的 low == high，否则会 low == mid == high == value,
     *         然后无限循环下去
     *      2. 当 list[mid] == value 的时候，因为需要搜索的是 key 的左边界，
     *         此时，key 的左边界所在的范围是 [low, mid]，因此不能动 low 的值，
     *         而是将 high = mid，继续搜索
     *      3. 当循环体跳出时，low == high, 但是无法保证 low 对应的值是否是 value 值，
     *         有可能根本找不到 value 值。因此不能直接输出 low，而是判断此时 low 值是否
     *         等于 value 值，再输出结果。
     * @param list 给定数组
     * @param value 目标值
     * @return 目标值所载的 key 的左边界
     */
    public static int binarySearchLeft(int[] list, int value) {
        int low = 0, high = list.length - 1;
        // 不可以让 low == high，否则会无限循环下去
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (list[mid] < value) {
                low = mid + 1;
            } else if (list[mid] > value) {
                high = mid - 1;
            } else {
                // 当相等的时候，不是直接输出结果，而是将 high = mid
                high = mid;
            }
        }
        if (list[low] == value) {
            return low;
        } else {
            return -1;
        }
    }


    /**
     * 在一个有重复元素的数组中查找 key 的最左位置 (精简一下代码)
     * @param list 给定数组
     * @param value 目标值
     * @return 目标值所载的 key 的左边界
     */
    public static int binarySearchLeftConcise(int[] list, int value) {
        int low = 0, high = list.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (list[mid] < value) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return (list[low] == value)? low : -1;
    }

    /**
     * 在一个有重复元素的数组中查找 key 的最右位置
     *  与找最左位置几乎一样，只不过有两个区别：
     *      1. 当 list[mid] == value 的时候，此时要找的是 key 的右边界，因此不能直接输出
     *         而 key 的右边界是在 [mid, high] 区间范围内，因此需要将 low = mid，
     *         而不能动 high
     *      2. mid 的计算方法从 low + (high - low) / 2，变成了 low + (high - low) / 2 + 1，
     *         多了个 + 1 操作。原因是当 low 和 high 只相差 1 的时候，前者计算的 mid 为 low 的值，
     *         而后者计算的 mid 为 high 的值，比如：
     *             low = 2, high = 3
     *             计算方法1: mid = low + (high - low) / 2 = 2
     *             计算方法2: mid = low + (high - low) / 2 + 1 = 3
     *         而当 list[mid] == value 时的语句是 low = mid，因此，当 low < high 且只相差 1，
     *         并且 list[mid] == value 的时候，如果采用第一种计算方法，那么就会 low = mid => low = low，
     *         没有任何变化，从而 low 与 high 将永远相差 1，陷入死循环。所以需要采用第二种方式计算 mid。
     * @param list 给定数组
     * @param value 目标值
     * @return 目标值所载的 key 的右边界
     */
    public static int binarySearchRight(int[] list, int value) {
        int low = 0, high = list.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2 + 1;
            if (list[mid] < value) {
                low = mid + 1;
            } else if (list[mid] > value) {
                high = mid - 1;
            } else {
                low = mid;
            }
        }
        if (list[high] == value) {
            return high;
        } else {
            return -1;
        }
    }

    /**
     * 在一个有重复元素的数组中查找 key 的最右位置（精简版）
     * @param list 给定数组
     * @param value 目标值
     * @return 目标值所载的 key 的右边界
     */
    public static int binarySearchRightConcise(int[] list, int value) {
        int low = 0, high = list.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2 + 1;
            if (list[mid] > value) {
                high = mid - 1;
            } else {
                low = mid;
            }
        }
        return (list[high] == value)? high : -1;
    }
}
