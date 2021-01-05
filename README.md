# LeetCode

LeetCode刷题记录，记录代码和做题思路。

## 算法
- [双指针](src/twopointer)
    - 167\. 两数之和 II - 输入有序数组: [`TwoSumII_167.java`](src/twopointer/TwoSumII_167.java) 
    - 633\. 平方数之和: [`SumOfSquareNumbers_633.java`](src/twopointer/SumOfSquareNumbers_633.java)
    - 345\. 反转字符串中的元音字母: [`ReverseVowelsofAString_345.java`](src/twopointer/ReverseVowelsofAString_345.java)
    - 680\. 验证回文字符串 Ⅱ: [`ValidPalindromeII_680.java`](src/twopointer/ValidPalindromeII_680.java)
    - 88\. 合并两个有序数组: [`MergeSortedArray_88.java`](src/twopointer/MergeSortedArray_88.java)
    - 141\. 环形链表: [`LinkedListCycle_141.java`](src/twopointer/LinkedListCycle_141.java)
    - 392\. 判断子序列: [`IsSubsequence_392.java`](src/twopointer/IsSubsequence_392.java)
    - 524\. 通过删除字母匹配到字典里最长单词: [`LongestWordInDictionaryThroughDeleting_524.java`](src/twopointer/LongestWordInDictionaryThroughDeleting_524.java)
    - 11\. 盛最多水的容器: [`ContainerWithMostWater_11.java`](src/twopointer/ContainerWithMostWater_11.java)
- [排序](src/sort)
    - 经典排序算法: [`Sort.java`](src/sort/Sort.java)
        - 冒泡排序: `bubbleSort`
        - 选择排序: `selectSort`
        - 插入排序: `insertSort`
        - 归并排序: `mergeSort`
        - 快速排序: `quickSort`
        - 堆排序: `heapSort`
        - 计数排序: `countSort`
    - 215\. 数组中的第K个最大元素: [`KthLargestElementInAnArray_215.java`](src/sort/KthLargestElementInAnArray_215.java)
    - 347\. 前 K 个高频元素: [`TopKFrequentElements_347.java`](src/sort/TopKFrequentElements_347.java)
    - 451\. 根据字符出现频率排序: [`SortCharactersByFrequency_451.java`](src/sort/SortCharactersByFrequency_451.java)
    - 75\. 颜色分类: [`SortColors_75.java`](src/sort/SortColors_75.java)
- [贪心算法](src/greedy)
    - 经典贪心问题: [`Greedy.java`](src/greedy/Greedy.java)
    - 455\. 分发饼干: [`AssignCookies_455.java`](src/greedy/AssignCookies_455.java)
    - 435\. 无重叠区间: [`NonOverlappingIntervals_435.java`](src/greedy/NonOverlappingIntervals_435.java)
    - 452\. 用最少数量的箭引爆气球: [`MinimumNumberOfArrowsToBurstBalloons_452.java`](src/greedy/MinimumNumberOfArrowsToBurstBalloons_452.java)
    - 406\. 根据身高重建队列: [`QueueReconstructionByHeight_406.java`](src/greedy/QueueReconstructionByHeight_406.java)
    - 121\. 买卖股票的最佳时机: [`BestTimeToBuyAndSellStock_121.java`](src/greedy/BestTimeToBuyAndSellStock_121.java)
    - 122\. 买卖股票的最佳时机 II: [`BestTimeToBuyAndSellStockII_122.java`](src/greedy/BestTimeToBuyAndSellStockII_122.java)
    - 605\. 种花问题: [`CanPlaceFlowers_605.java`](src/greedy/CanPlaceFlowers_605.java)
    - 860\. 柠檬水找零: [`LemonadeChange_860.java`](src/greedy/LemonadeChange_860.java)
    - 874\. 模拟行走机器人: 
        - OOP: [`WalkingRobotSimulationOOP_874.java`](src/greedy/WalkingRobotSimulationOOP_874.java)
        - 面向过程: [`WalkingRobotSimulation_874.java`](src/greedy/WalkingRobotSimulation_874.java)
    - 665\. 非递减数列: [`NonDecreasingArray_665.java`](src/greedy/NonDecreasingArray_665.java)
    
- [二分查找](src/binarysearch)
    - 704\. 基本的二分查找问题: [`BinarySearch_704.java`](src/binarysearch/BinarySearch_704.java)
    - 69\. 求 x 的开方: [`SquareX_69.java`](src/binarysearch/SquareX_69.java)
    - 744\. 寻找比目标字母大的最小字母: [`FindSmallestLetterGreaterThanTarget_744.java`](src/binarysearch/FindSmallestLetterGreaterThanTarget_744.java)
    - 540\. 有序数组中的单一元素: [`SingleElementinASortedArray_540.java`](src/binarysearch/SingleElementinASortedArray_540.java)
    - 153\. 寻找旋转排序数组中的最小值: [`FindMinimumInRotatedSortedArray_153.java`](src/binarysearch/FindMinimumInRotatedSortedArray_153.java)
    - 34\. 在排序数组中查找元素的第一个和最后一个位置: [`FindFirstAndLastPositionOfElementInSortedArray_34.java`](src/binarysearch/FindFirstAndLastPositionOfElementInSortedArray_34.java)

- [分而治之](src/divideconquer)
    - 241\. 为运算表达式设计优先级: [`DifferentWaysToAddParentheses_241.java`](src/divideconquer/DifferentWaysToAddParentheses_241.java)
    - 95\. 不同的二叉搜索树 II: [`UniqueBinarySearchTreesII_95.java`](src/divideconquer/UniqueBinarySearchTreesII_95.java)

- [搜索](src/search)
    - BasicSearch: [`BasicSearch.java`](src/search/BasicSearch.java)
        - 广度优先搜索（BFS）算法：`breadthFirstSearch`
        - 深度优先搜索（DFS）算法：`depthFirstSearch`
        - 最短路径算法：`shortestPath`
    - BFS: 图的遍历与最短路径问题
        - 1091\. 二进制矩阵中的最短路径: [`ShortestPathInBinaryMatrix_1091.java`](src/search/ShortestPathInBinaryMatrix_1091.java)
        - 279\. 完全平方数: [`PerfectSquares_279.java`](src/search/PerfectSquares_279.java)
        - 127\. 单词接龙: [`WordLadder_127.java`](src/search/WordLadder_127.java)
    - DFS: 图的连通性问题（存在一条连通的路径问题）
        - 695\. 岛的最大面积: [`MaxAreaOfIsland_695.java`](src/search/MaxAreaOfIsland_695.java)
        - 200\. 岛屿数量: [`NumOfIslands.java`](src/search/NumOfIslands.java)
        - 547\. 朋友圈: [`FriendCircles_547.java`](src/search/FriendCircles_547.java)
        - 130\. 被围绕的区域: [`SurroundedRegions_130.java`](src/search/SurroundedRegions_130.java)
        - 417\. 太平洋大西洋水流问题: [`PacificAtlanticWaterFlow_417.java`](src/search/PacificAtlanticWaterFlow_417.java)
    - DFS + 回溯: 排列组合、所有路经集合、某一确定路径是否存在问题
        - 17\. 电话号码的字母组合: [`LetterCombinationsOfAPhoneNumber_17.java`](src/search/LetterCombinationsOfAPhoneNumber_17.java)
        - 93\. 复原IP地址: [`RestoreIpAddress_93.java`](src/search/RestoreIpAddress_93.java)
        - 79\. 单词搜索: [`WordSearch_79.java`](src/search/WordSearch_79.java)
        - 257\. 二叉树的所有路径: [`BinaryTreePaths_257.java`](src/search/BinaryTreePaths_257.java)
        - 46\. 全排列: [`Permutations_46.java`](src/search/Permutations_46.java)
        - 77\. 组合: [`Combinations_77.java`](src/search/Combinations_77.java)
        - 39\. 组合总和: [`CombinationSum_39.java`](src/search/CombinationSum_39.java)
        - 78\. 子集: [`Subsets_78.java`](src/search/Subsets_78.java)
        - 90\. 子集II: [`SubsetsII_78.java`](src/search/SubsetsII_90.java)
        - 131\. 分割回文串: [`PalindromePartitioning_131.java`](src/search/PalindromePartitioning_131.java)
        - 131\. 分割回文串(另一种思路): [`AnotherPalindromePartitioning_131.java`](src/search/AnotherPalindromePartitioning_131.java)

- [动态规划](src/dp)
    - 斐波那契数据列：
        - 70\. 爬楼梯: [`ClimbingStairs_70.java`](src/dp/ClimbingStairs_70.java)
        - 198\. 打家劫舍: [`HouseRobber_198.java`](src/dp/HouseRobber_198.java)
        - 213\. 打家劫舍II: [`HouseRobberII_213.java`](src/dp/HouseRobberII_213.java)
        - 62\. 不同路径: [`UniquePaths_62.java`](src/dp/UniquePaths_62.java)
        - 64\. 最小路径和: [`MinPathSum_64.java`](src/dp/MinPathSum_64.java)
        - 303\. 区域和检索-数组不可变: [`NumArray_303.java`](src/dp/NumArray_303.java)
        - 413\. 等差数列划分: [`ArithmeticSlices_413.java`](src/dp/ArithmeticSlices_413.java)

## 剑指 Offer

- 面试 2. 实现 Singleton 模式: [`SingletonPattern_2.java`](src/offer/SingletonPattern_2.java)
- 面试 3. 数组中重复的数字: [`FindRepeatNumber_3.java`](src/offer/FindRepeatNumber_3.java)
- 面试 4. 二维数组中的查找: [`FindNmberIn2DArray_4.java`](src/offer/FindNmberIn2DArray_4.java)
- 面试 5. 替换空格: [`ReplaceSpace_5.java`](src/offer/ReplaceSpace_5.java)
- 面试 6. 从尾到头打印链表: [`ReversePrint_6.java`](src/offer/ReversePrint_6.java)
- 面试 7. 重建二叉树: [`RebuildBinaryTree_7.java`](src/offer/RebuildBinaryTree_7.java)
- 面试 8. 二叉树的下一个节点: [`NextNodeInBinaryTree_8.java`](src/offer/NextNodeInBinaryTree_8.java)
