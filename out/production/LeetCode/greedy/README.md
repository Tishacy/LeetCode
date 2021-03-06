# 贪心算法思路总结

- 贪心算法是什么？
    - 在问题求解时，总是做在当前看来是最好的选择。
    - 也就是不直接求全局最优解，而是每一步都求其局部最优解。
    - 每一步都是局部最优解，并不一定能保证最后可以达到全局最优解，但是对于某些问题来说，可以达到全局最优解，所以需要会判断哪些问题可以用贪心算法来解决。
- 什么时候选择使用贪心算法？
    - 贪心算法首先解决的是最优化问题，所以当遇到问题是求最大、最小、最多、最少之类的问题时，看看是否可以用贪心算法来解决
    - 对于分数背包问题，可以使用贪心算法解决，而 0-1 背包问题，则无法用贪心解决，而是使用动态规划解决。
- 贪心算法的核心是什么？
    - 核心是知道要贪什么，即找到目标问题与给定内容的对应关系，比如：分数背包问题中，目标是拿的总价最多，对应单个商品其实就是单价最高，因此需要贪单价最高的商品。
    - 知道贪什么之后，就可以按照所贪的东西对给定的内容进行排序，贪值高的排前面。
    - 对排好序的内容进行遍历，逐个解决问题。
- 以下题目用到了贪心算法：
    - 455\. 分发饼干: [`AssignCookies_455.java`](AssignCookies_455.java)
    - 435\. 无重叠区间: [`NonOverlappingIntervals_435.java`](NonOverlappingIntervals_435.java)
    - 452\. 用最少数量的箭引爆气球: [`MinimumNumberOfArrowsToBurstBalloons_452.java`](MinimumNumberOfArrowsToBurstBalloons_452.java)
    - 406\. 根据身高重建队列: [`QueueReconstructionByHeight_406.java`](QueueReconstructionByHeight_406.java)