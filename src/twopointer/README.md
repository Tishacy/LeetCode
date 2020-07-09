# 双指针算法思路总结

- 有三种情况，可以第一时间反映出来用双指针：
    - 有一个已经排好序的数组（字符串也可以看做是一个有序数组），并且需要操作这个数组中的两个元素来判断是否获取结果。这个时候可以用双指针，一个指头，一个指尾，然后向中间移动。本来用暴力搜索的时间复杂度为 O(N²)，利用数组有序这个条件，使用双指针，可以把时间复杂度降到 O(N)。这种情况的题目有：
        - 167\. 两数之和 II - 输入有序数组: [`TwoSumII_167.java`](TwoSumII_167.java) 
        - 633\. 平方数之和: [`SumOfSquareNumbers_633.java`](SumOfSquareNumbers_633.java)
        - 345\. 反转字符串中的元音字母: [`ReverseVowelsofAString_345.java`](ReverseVowelsofAString_345.java)
        - 680\. 验证回文字符串 Ⅱ: [`ValidPalindromeII_680.java`](ValidPalindromeII_680.java)
    - 有两个数组、链表、字符串等，需要比较两者之间的规则，需要用到双指针。比如合并两个有序数组，判断一个字符串是否为另一个字符串的子序列或子串等。这种情况的题目有：
        - 88\. 合并两个有序数组: [`MergeSortedArray_88.java`](MergeSortedArray_88.java)
        - 392\. 判断子序列: [`IsSubsequence_392.java`](IsSubsequence_392.java)
        - 524\. 通过删除字母匹配到字典里最长单词: [`LongestWordInDictionaryThroughDeleting_524.java`](LongestWordInDictionaryThroughDeleting_524.java)
    - 判断链表成环问题，可以采用快慢指针，快指针一定可以绕过环再追上慢指针。这种情况的题目有：
        - 141\. 环形链表: [`LinkedListCycle_141.java`](LinkedListCycle_141.java)
        