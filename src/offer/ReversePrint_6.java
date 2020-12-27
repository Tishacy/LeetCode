package offer;

import java.util.Arrays;

/**
 * 剑指 Offer 06. 从尾到头打印链表
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 *
 * 示例 1：
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 *
 * 限制：
 * 0 <= 链表长度 <= 10000
 */
public class ReversePrint_6 {

    public static void main(String[] args) {
        ReversePrint_6 solution = new ReversePrint_6();
        ListNode head = generateLinkedList(new int[] {1, 3, 2});
        int[] reverseValues = solution.reversePrint(head);
        System.out.println("[Solution] Reverse values: " + Arrays.toString(reverseValues));
    }

    /**
     * 从尾到头生成链表中的数值数组
     * @param head
     * @return
     */
    public int[] reversePrint(ListNode head) {
        int len = 0;
        ListNode curNode = head;
        while (curNode != null) {
            curNode = curNode.next;
            len++;
        }
        int[] reverseVals = new int[len];
        curNode = head;
        for (int i=0; i<reverseVals.length; i++) {
            reverseVals[len-1-i] = curNode.val;
            curNode = curNode.next;
        }
        return reverseVals;
    }

    /**
     * 链表节点
     */
    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 根据给定数组生成链表
     * @param vals
     * @return
     */
    public static ListNode generateLinkedList(int[] vals) {
        if (vals == null || vals.length == 0) {
            return null;
        }
        ListNode head = new ListNode(vals[0]);
        ListNode curNode = head;
        for (int i=1; i<vals.length; i++) {
            ListNode node = new ListNode(vals[i]);
            curNode.next = node;
            curNode = node;
        }
        curNode.next = null;
        return head;
    }
}
