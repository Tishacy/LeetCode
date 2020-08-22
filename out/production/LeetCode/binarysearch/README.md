# 二分查找思路总结

- 二分查找问题使用场景：
    - 一般给定一个有序数组，并从中找寻一个值（或一个区间）时可以使用二分查找方法
        - 如果这种问题使用指针或者双指针的方法，会有 O(N) 的时间复杂度，但是使用二分查找，可以使时间复杂度降低到 O(logN)。
- 二分查找的问题分类：
    - 找寻一个值的位置，其算法基本模板为：
      ```Java
      class Solution {
          public int searchValue(int[] nums, int value) {
              // 搜索空间为闭区间 [0, nums.length-1]
              int low = 0, high = nums.length - 1; 
              while (low <= high) {	// 注意点 1
                  int mid = low + (high - low) / 2;
                  if (nums[mid] == value) {
                      return mid;		// 注意点 2
                  } else if (nums[mid] < value) {
                      low = mid + 1;
                  } else {
                      high = mid - 1;
                  }
              }
              return -1;
          }
      }
      ```
      
      -   注意点1：对于找寻一个值的时候，搜索空间只剩下一个元素的时候仍然需要进行判断，因此，需要`while (low <= high)`，而不是`while (low < high)`
      -   注意点2：对于找寻一个值，只要判断出找到了目标元素，就可以直接返回其索引值。
      
    - 找寻一个值的边界（左边界或右边界），其算法基本模板为：
    
        ```java
        class Solution {
            /* 找某值的左边界 */
            public int searchLeftBound(int[] nums, int value) {
                // 搜索空间为闭区间 [0, nums.length - 1]
                int low = 0, high = nums.length - 1;
                while (low <= high) {	// 注意点 1
                    int mid = low + (high - low) / 2;
                    if (nums[mid] == value) {
                        // 注意点 2
                        high = mid - 1;
                    } else if (nums[mid] < value) {
                        low = mid + 1;
                    } else {
                        high = mid -1;
                    }
                }
                // 注意点 3
                // 循环结束时，low 所在的索引就是左边界
        		if (low < nums.length && nums[low] == value) {
                    return low;
                }
                return -1;
            }
            
            /* 找某值的右边界 */
            public int searchRightBound(int[] nums, int value) {
                // 搜索空间为闭区间 [0, nums.length - 1]
                int low = 0, high = nums.length - 1;
                while (low <= high) {	// 注意点 1
                    int mid = low + (high - low) / 2;
                    if (nums[mid] == value) {
                        // 注意点 2
                        low = mid + 1;
                    } else if (nums[mid] < value) {
                        low = mid + 1;
                    } else {
                        high = mid -1;
                    }
                }
                // 注意点 3
                // 循环结束时，high 所在的索引就是右边界
        		if (high >= 0 && nums[high] == value) {
                    return high;
                }
                return -1;
            }
        }
        ```
    
        -   注意点 1：这里的 `while (low <= high)` 还是 `while (low < high)`，是由注意点 决定的。
            -   注意点 2 中如果是`low = mid` 或`high = mid`，那么注意点 1就必须是`while (low < high)`，否则会陷入死循环
            -   注意点 2 中如果是`low = mid + 1` 或`high = mid - 1`，那么注意点 1就必须是`while (low <= high)`，否则会漏掉一次循环
        -   注意点 2：因为是查找边界，因此这里不会直接返回结果，而是一直去缩小搜索空间的范围：
            -   如果是查找左边界，那就不能动`low`，而是将 `high = mid - 1`
            -   如果是查找右边界，那就不能动`high`，而是将 `low = mid + 1`
        -   注意点 3：循环退出后，由于在循环体内并没有返回任何结果，因此必须要在这里进行判断返回。
            -   如果是查找左边界，由于循环体使用的是`while (low <= high)`，因此循环结束是，`low = high + 1`，所以要保证 `low` 不越界且其值等于目标元素，此时`low`就是左边界的索引，返回`low`。
            -   如果是查找右边界，同理，`high = low - 1`，所以要保证`high >= 0`，此时`high`就是右边界的索引，然后返回`high`。

-   各种情况的题目有：
    -   查找有序数组中的一个值：
        -   704\. 基本的二分查找问题: [`BinarySearch_704.java`](./BinarySearch_704.java)
        -   69\. 求 x 的开方: [`SquareX_69.java`](./SquareX_69.java)
        -   540\. 有序数组中的单一元素: [`SingleElementinASortedArray_540.java`](./SingleElementinASortedArray_540.java)
    -   查询有序数组中的某一值的边界：
        -   744\. 寻找比目标字母大的最小字母: [`FindSmallestLetterGreaterThanTarget_744.java`](./FindSmallestLetterGreaterThanTarget_744.java)
        -   153\. 寻找旋转排序数组中的最小值: [`FindMinimumInRotatedSortedArray_153.java`](./FindMinimumInRotatedSortedArray_153.java)
        -   34\. 在排序数组中查找元素的第一个和最后一个位置: [`FindFirstAndLastPositionOfElementInSortedArray_34.java`](./FindFirstAndLastPositionOfElementInSortedArray_34.java)

