package sort;

import javax.lang.model.type.ArrayType;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * 排序算法
 * - 冒泡排序 bubbleSort
 * - 选择排序 selectSort
 * - 插入排序 insertSort
 * - 归并排序 mergeSort
 * - 快速排序 quickSort
 * - 堆排序 heapSort
 * - 计数排序 countSort
 */
public class Sort {
    public static void main(String[] args) {
        int[] nums = { 3, 2, 3, 1, 2, 4, 5, 5, 6 };
//        bubbleSort(nums);
//        selectSort(nums);
//        insertSort(nums);
//        quickSort(nums, 0, nums.length-1);
//        heapSort(nums);
        countSort(nums);
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(mergeSort(nums, 0, nums.length-1)));
    }

    /**
     * bubble sort 冒泡排序（原地排序）
     * - 其核心思想是比较一个元素和它后面那个元素的大小，并把大的元素放后边
     * - 先写内循环，内循环要做的就是从 0 位置开始遍历数据，并以此将大的元素冒泡
     *   为什么内循环的右边界是 len-1-i？
     *       因为内循环操作的两个元素的索引值分别为 j 和 j+1，为了保证 j+1 不
     *       越界，因此需要 len-1。而且，因为每次内循环一遍之后，就会排好一个数
     *       因此，后面的就不用再冒泡了，右边界为 len-1-i
     * - 再写外循环，外循环控制的是要走几遍冒泡的内循环，一共有 len 个数，当有
     *   len-1 个数已经通过内循环排好序的时候，最后一个数就不需要再排序了，因此，
     *   一共需要跑 len-1 次内循环
     * - 时间复杂度：O(N²)
     * - 空间复杂度：原地排序，O(1)
     * @param nums 需要排序的数组
     */
    public static void bubbleSort(int[] nums) {
        int len = nums.length;
        for (int i=0; i<len-1; i++) {
            for (int j = 0; j<len-1-i; j++) {
                if (nums[j] > nums[j+1]) {
                    swap(nums, j, j+1);
                }
            }
        }
    }

    /**
     * selection sort 选择排序（原地排序）
     * - 其核心思想为每次都找到数组中的最小值放到数组的开始位置
     * - 先写内循环，一个标准的找寻数组最小值的过程，找到最小值之后，将最小值与数组
     *   的起始位置进行交换，即把最小值放到数组的开头，然后让数组范围减1，即让内循环
     *   的左边界等于 i+1
     * - 再写外循环，一共需要跑动 len-1 次内循环，因为 len-1 个数字排好序之后，剩下
     *   那个数组就不用再排序了，因此外循环的边界为 [0, len-1)
     * - 时间复杂度：O(N²)
     * - 空间复杂度：原地排序，O(1)
     * @param nums 需要排序的数组
     */
    public static void selectSort(int[] nums) {
        int len = nums.length;
        for (int i=0; i<len-1; i++) {
            int minIndex = i;
            for (int j=i+1; j<len; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            swap(nums, i, minIndex);
        }
    }

    /**
     * insertion sort 插入排序
     * - 其核心思想是抽扑克牌排序的原理，挑一张牌跟它前面的那些牌进行比较，把牌插到其应该在的位置上
     * - 先写内循环，抽出一张牌，依次遍历它之前的牌：
     *     - 如果比抽出的牌面大，就把这张牌向后移动一下，腾出一个空位
     *     - 如果遇到比抽出牌面小的，就找到了这张牌需要插入的空位了，这个空位为这个较小的牌的后一位，跳出循环
     *   循环结束后，把抽出的那张牌插入到空位上
     * - 再写外循环，从第二张牌开始往前插入，一直到最后一张牌，因此外循环边界为 [1, len-1]
     * - 空间复杂度：原地排序，O(1)
     * @param nums 需要排序的数组
     */
    public static void insertSort(int[] nums) {
        int len = nums.length;
        for (int i=1; i<len; i++) {
            int key = nums[i];
            int j = i - 1;
            while (j >= 0) {
                if (nums[j] >= key) {
                    swap(nums, j, j+1);
                    j--;
                } else {
                    break;
                }
            }
            nums[j+1] = key;
        }
    }

    /**
     * merge sort 归并排序（异地排序）
     * - 归并排序的核心在于分而治之，核心步骤为归并函数(merge)，即通过双指针将两个有序数组合并成为一个有序数组
     * - 思路为：
     *     - 如果数组需要归并排序的长度为 1, 那就直接把这部分的数组内容返回出去，不用排序
     *     - 如果数组需要归并排序的长度大于1
     *         - 先归并排序数组左半部分，得到排好序的左边的数组
     *         - 再归并排序数组右半部分，得到排好序的右边的数组
     *         - 最后将排好序的左边数组和排好序的右边数组合并起来
     * - 时间复杂度：O(NlogN)
     *     - 分：通过二分法将数组分成长度为 1 的子数组，O(logN)
     *     - 治：通过 merge 函数合并两个有序数组，O(N)
     *     - 总时间复杂度：O(NlogN)
     * - 空间复杂度：O(N)
     *     - 每次 merge 会建立一个长度为 N 的辅助空间，O(N)
     *     - 递归树的深度为 logN，但是由于每层递归的辅助空间会被释放，因此这部分
     *       空间复杂度不会进行叠加，因此无论递归多少次，一直都是 O(N) 的空间复杂度
     * @param nums 需要排序的数组
     * @param left 需要排序部分的左边界，且需要排序的部分包含左边界
     * @param right 需要排序部分的右边界，且需要排序的部分包含右边界
     * @return 归并排序后的新数组
     */
    public static int[] mergeSort(int[] nums, int left, int right) {
        if (left < right) {
            // int mid = (right + left) / 2;  // 这样不好，有可能会出现 left + right 数值越界问题
            int mid = (right - left + 1) / 2 + left;
            int[] leftNums = mergeSort(nums, left, mid-1);
            int[] rightNums = mergeSort(nums, mid, right);
            return merge(leftNums, rightNums);
        }
        return Arrays.copyOfRange(nums, left, right+1);
    }

    /**
     * 归并函数
     * - 通过双指针合并两个有序数组
     * @param leftNums 第一个有序数组
     * @param rightNums 第二个有序数组
     * @return 合并后的有序数组
     */
    public static int[] merge(int[] leftNums, int[] rightNums) {
        int leftLen = leftNums.length, rightLen = rightNums.length;
        int i = 0, j = 0, k = 0;
        int[] mergeRes = new int[leftLen + rightLen];
        while (i < leftLen && j < rightLen) {
            if (leftNums[i] < rightNums[j]) {
                mergeRes[k++] = leftNums[i++];
            } else {
                mergeRes[k++] = rightNums[j++];
            }
        }
        if (i >= leftLen) {
            while (j < rightLen) {
                mergeRes[k++] = rightNums[j++];
            }
        } else {
            while (i < leftLen) {
                mergeRes[k++] = leftNums[i++];
            }
        }
        return mergeRes;
    }

    /**
     * quick sort 快速排序（原位排序）
     * - 快速排序的核心在于分而治之，核心函数为划分函数（partition），即随机找一个基准 pivot，把数组中
     *   小于基准的数都放到基准左边，大于基准的数都放到基准右边。
     *   首先通过 partition 函数找到一个基准，然后以基准为界，快排左边，快排右边，
     *   这样递归进行，就可以给整个数组排序。
     * - 时间复杂度：O(N²) 或 O(NlogN)
     *     - 分：
     *         - 如果不随机化基准的话，分的最坏的时间复杂度为 O(N)，这种情况下，相当于每次基准把数组划分
     *           称为 1, N-1 的区间，共需要分 N 次
     *         - 如果随机化基准，分的期望时间复杂度为 O(logN)
     *     - 治：
     *         - partition 函数需要遍历一遍数组，时间复杂度为 O(N)
     *     - 因此，如果不随机化基准，总体最坏的时间复杂度为 O(N²)，而随机化基准后，总体期望的时间复杂度
     *       为 O(NlogN)
     * - 空间复杂度：O(1)
     *     - 快排是原地排序算法，不消耗辅助空间，因此空间复杂度为 O(1)
     * @param nums 需要排序的数组
     * @param left 需要排序部分的左边界，且需要排序的部分包含左边界
     * @param right 需要排序部分的右边界，且需要排序的部分包含右边界
     */
    public static void quickSort(int[] nums, int left, int right) {
        if (left < right) {
            int pivot = partition(nums, left, right);
            quickSort(nums, left, pivot - 1);
            quickSort(nums, pivot + 1, right);
        }
    }

    /**
     * 划分函数，随机找一个基准 pivot，把数组中小于基准的数都放到基准左边，
     * 大于基准的数都放到基准右边。此时，基准的位置为数组按自小到大排序后基准
     * 应该在的位置。
     * @param nums 需要划分的数组
     * @param left 需要划分部分的左边界，且需要划分的部分包含左边界
     * @param right 需要划分部分的右边界，且需要划分的部分包含右边界
     * @return 基准应该在的位置
     */
    public static int partition(int[] nums, int left, int right) {
        int rand = new Random().nextInt(right - left + 1) + left;
        swap(nums, left, rand);
        int pivot = left;
        int j = left;
        for (int i=left+1; i<=right; i++) {
            if (nums[i] < nums[pivot]) {
                j++;
                swap(nums, j, i);
            }
        }
        swap(nums, j, left);
        return j;
    }

    /**
     * heap sort 堆排序
     * - 其核心思想为构建一个小顶堆（java.util 中的 PriorityQueue 优先队列默认
     *   会构建一个小顶堆），然后每次从堆顶取出一个元素放入原数组中
     * - 时间复杂度：O(NlogN)
     *     - 构建一个小顶堆：每次添加一个值，会有 logN 的时间复杂度用于节点的上浮下沉
     *       共添加 N 个值，时间复杂度为 O(NlogN)
     *     - 每次从小顶堆上取出堆顶：每次取出一个堆顶，就会维护小顶堆而进行节点的上浮下沉，
     *       共取出 N 个值，时间复杂度为 O(NlogN)
     *     - 因此，总体时间复杂度为 O(NlogN)
     * - 空间复杂度：O(N)
     *     - 需要构造一个含有 N 个元素的小顶堆的辅助空间，空间复杂度为 O(N)
     * @param nums 需要排序的数组
     */
    public static void heapSort(int[] nums) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            queue.add(num);
        }
        int i = 0;
        while (queue.size() > 0) {
            nums[i++] = queue.poll();
        }
    }

    /**
     * count sort 计数排序（原地排序）
     * - 类似于桶排序，其核心思想是桶本身是有顺序的，因此可以把数字全放到各自的桶中，
     *   然后按照桶的顺序，依次输出数字
     *     - 首先，获取到数组中的最大值，用来的到桶的规模大小
     *     - 遍历数组，将数字放到其对应的桶当中，每个桶的索引为数字值，桶中的数字为这个数字
     *       出现的频率
     *     - 按照桶的顺序依次遍历桶，按照桶中数字出现的频率依次把桶所代表的的数字放到
     *       需要排序的数组中。
     * - 时间复杂度：O(N)
     *     - 获取数组最大值的时间复杂度为 O(N)
     *     - 将数组中的数字依次放入对应的桶中，时间复杂度为 O(N)
     *     - 依次将桶中的数字按照桶的顺序输出，时间复杂度为 O(N)
     * - 空间复杂度：O(max(N))
     *     - 需要构造一个规模为 max(N) + 1 的桶作为辅助空间，空间复杂度为 O(max(N))
     * @param nums 需要排序的数组
     */
    public static void countSort(int[] nums) {
        int maxVal = getMaxVal(nums);
        int bucketLen = maxVal + 1;
        int[] bucket = new int[bucketLen];
        for (int num : nums) {
            bucket[num]++;
        }
        int k = 0;
        for (int i=0; i<bucketLen; i++) {
            while (bucket[i] > 0) {
                nums[k++] = i;
                bucket[i]--;
            }
        }
    }

    /**
     * 获取给定数组的最大值
     * @param nums 给定数组
     * @return 数组中的最大值
     */
    public static int getMaxVal(int[] nums) {
        int maxIndex = 0;
        int len = nums.length;
        for (int i=1; i<len; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        return nums[maxIndex];
    }


    /**
     * 交换数组中两个位置的元素
     * @param nums 数组
     * @param i 需要交换位置的第一个索引值
     * @param j 需要交换位置的第二个索引值
     */
    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
