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
     *      1. 当 list[mid] == value 的时候，因为需要搜索的是 key 的左边界，
     *         此时，key 的左边界所在的范围是 [low, mid]，因此不能动 low 的值，
     *         而是将 high = mid - 1，继续搜索
     *      3. 当循环体跳出时，low == high, 但是无法保证 low 对应的值是否是 value 值，
     *         有可能根本找不到 value 值。因此不能直接输出 low，而是判断此时 low 值是否
     *         等于 value 值，再输出结果。
     * @param list 给定数组
     * @param value 目标值
     * @return 目标值所载的 key 的左边界
     */
    public static int binarySearchLeft(int[] list, int value) {
        int low = 0, high = list.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (list[mid] < value) {
                low = mid + 1;
            } else if (list[mid] > value) {
                high = mid - 1;
            } else {
                // 当相等的时候，不是直接输出结果，而是将 high = mid - 1
                high = mid - 1;
            }
        }
        // 循环跳出时，此时 low 指向的就是左边界位置，high 指向的是左边界左边的位置
        if (low < list.length && list[low] == value) {
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
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (list[mid] < value) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return (low < list.length && list[low] == value)? low : -1;
    }

    /**
     * 在一个有重复元素的数组中查找 key 的最右位置
     *  与找最左位置几乎一样，只不过有个区别：
     *      1. 当 list[mid] == value 的时候，此时要找的是 key 的右边界，因此不能直接输出
     *         而 key 的右边界是在 [mid, high] 区间范围内，因此需要将 low = mid + 1
     *         而不能动 high
     * @param list 给定数组
     * @param value 目标值
     * @return 目标值所载的 key 的右边界
     */
    public static int binarySearchRight(int[] list, int value) {
        int low = 0, high = list.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (list[mid] < value) {
                low = mid + 1;
            } else if (list[mid] > value) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        // 循环跳出时，此时 high 指向的就是右边界位置，low 指向的是右边界右边的位置
        if (high > 0 && list[high] == value) {
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
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (list[mid] > value) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return (high > 0 && list[high] == value)? high : -1;
    }
}
