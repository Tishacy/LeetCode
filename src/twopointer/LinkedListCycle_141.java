package twopointer;

/**
 * 141. 环形链表
 * 给定一个链表，判断链表中是否有环。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 *
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 * 示例 2：
 * 输入：head = [1,2], pos = 0
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 *
 * 示例 3：
 * 输入：head = [1], pos = -1
 * 输出：false
 * 解释：链表中没有环。
 *
 * 进阶：
 * 你能用 O(1)（即，常量）内存解决此问题吗？
 */
public class LinkedListCycle_141 {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args) {
        LinkedListCycle_141 solution = new LinkedListCycle_141();
        int[] arr = { 1, 2 };
        ListNode head = solution.buildLinkedList(arr, 0);
        System.out.println(solution.hasCycle(head));
    }

    /**
     * 判断链表是否有环
     * @param head 链表头节点
     *
     * 思路：
     * - 链表成环问题可以使用快慢指针来解决，即：
     *     两个指针，一个慢指针，一个快指针
     * - 指针移动规则：
     *     遍历链表，快慢指针一开始都指向链表头
     *     慢指针一次走一个元素，快指针一次走两个元素
     * - 循环终止条件：
     *     - 如果没有环，那一定有一个指针可以走到尾部，尾节点,next == null
     *     - 如果有环，那快指针一定可以经过环并追上慢指针
     * - 时间复杂度：O(N)，最多遍历一遍链表长度
     * - 空间复杂度: O(1)，只用了两个指针
     */
    public boolean hasCycle(ListNode head) {
        // 注意判断链表是否为空
        if (head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (slow != null && fast != null && fast.next != null) {
            // 在判断中，slow != null 和 fast != null 是保证两个指针都没走到尾部
            // fast.next != null 是为了保证快指针可以一次走两步，即保证 fast = fast.next.next
            // 的正常运行，不会出现由于 fast.next 为 nulL 而使得 fast.next.next 报出
            // NullPointerException 异常
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /**
     * 构建一个可能有环的链表
     * @param arr 数值数组
     * @param pos 链表尾部指向的位置，如果为 -1 就则不成环
     * @return
     */
    public ListNode buildLinkedList(int[] arr, int pos) {
        ListNode head = new ListNode(arr[0]);
        ListNode p = head;
        for (int i=1; i<arr.length; i++) {
            p.next = new ListNode(arr[i]);
            p = p.next;
        }
        if (pos != -1) {
            ListNode q = head;
            while (pos > 0) {
                q = q.next;
                pos--;
            }
            p.next = q;
        }
        return head;
    }
}
