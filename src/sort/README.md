# 排序算法思路总结

- 基本的排序算法（[Sort.java](Sort.java)）需要熟练掌握，按时间复杂度由高到低为：
    - O(N²)：冒泡排序、选择排序、插入排序，这三个都是原地排序，都是比较型排序。
    - O(NlogN)：归并排序、快速排序、堆排序，其中，快排是原地排序，归并排序和堆排序都是非原地排序，三者都是比较型排序。
        > 原地排序的概念是：在排序过程中不申请多余的存储空间，只利用原来存储待排数据的存储空间进行比较和交换的数据排序。因此，虽然堆排序最后也可以把堆中的元素放入原数组而不返回新数组，但是其仍然申请了一个堆的辅助空间，因此不算原地排序。
    - O(N)：计数排序、基数排序、桶排序，这三个都是非原地排序，而且都是非比较型排序，以空间换时间。计数排序和基数排序其实是桶排序的一种特殊情况。
- 排序算法主要在两种情况下会用到：
    - 排序问题：无论是按照数值进行排序，还是按数值出现的频率排序，还是按字符字典序排序，只要涉及到排序，都可以使用排序算法。
        - 对于按照数值排序，倾向于用快排、归并排序、堆排序、桶排序这几个排序算法。
            - 经典排序算法: [`Sort.java`](Sort.java)
            - 75\. 颜色分类: [`SortColors_75.java`](SortColors_75.java)
        - 对于按照数值出现的频率、字符字典序等排序，倾向于用堆排序，桶排序这两种。
            - 451\. 根据字符出现频率排序: [`SortCharactersByFrequency_451.java`](SortCharactersByFrequency_451.java)
    - top K 问题，有两种思路：
        - 思路1：排序后再取相应的 top K。
        - 思路2：利用堆排序，维护一个元素个数为 K 的大顶堆或小顶堆（取前 K 大用小顶堆，取前 K 小用大顶堆）。
        这种情况的题目有：
            - 215\. 数组中的第K个最大元素: [`KthLargestElementInAnArray_215.java`](KthLargestElementInAnArray_215.java)
            - 347\. 前 K 个高频元素: [`TopKFrequentElements_347.java`](TopKFrequentElements_347.java)
- 排序问题的核心是：看清楚问题中需要**按什么大小去比较**，是数值大小，还是出现频率、字典序等
    - 如果是用比较型排序算法，就替换成相应的数值去比较。
    - 如果是用桶排序这种非比较型排序算法，由于桶排序之所以可以进行排序是因为桶本身就是有次序的，因此，需要将桶本身的 key（或索引）换成需要比较的数值，比如频率、字典序等。
