package offer;

import java.util.LinkedList;
import java.util.List;

/**
 * 剑指 Offer 09. 用两个栈实现队列
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 *
 * 示例 1：
 * 输入：
 * ["CQueue","appendTail","deleteHead","deleteHead"]
 * [[],[3],[],[]]
 * 输出：[null,null,3,-1]
 *
 * 示例 2：
 * 输入：
 * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
 * [[],[],[5],[2],[],[]]
 * 输出：[null,-1,null,null,5,2]
 *
 * 提示：
 * 1 <= values <= 10000
 * 最多会对 appendTail、deleteHead 进行 10000 次调用
 */
public class CQueue_9 {

    /**
     * 思路：用两个栈分别为栈1 和 栈2
     *  当有新元素加入队列的时候，将元素放入栈1
     *  当需要取出元素的时候，
     *      考虑栈1有元素，栈2为空的情况：由于队列的 FIFO (先进先出) 性质，此时应该出队的元素在栈1的栈底，
     *          因此，将栈1中的元素依次 pop 出来，push 到栈2中，这样应该出队的元素就在栈2的栈顶了，
     *          将栈2栈顶的元素 pop 出来即可。
     *      考虑栈1有元素（或为空），栈2也有元素的情况：直接从栈2 pop 出栈顶的元素即可。直到栈2为空，再按上面那种情况进行操作。
     *      考虑栈1为空，栈2也为空的情况：队列中没有元素，返回 -1
     * @param args
     */
    public static void main(String[] args) {
        CQueue_9 queue = new CQueue_9();
        System.out.println(queue.deleteHead());
        queue.appendTail(5);
        queue.appendTail(2);
        System.out.println(queue.deleteHead());;
        System.out.println(queue.deleteHead());
    }

    private LinkedList<Integer> stack1;
    private LinkedList<Integer> stack2;

    public CQueue_9() {
        stack1 = new LinkedList<>();
        stack2 = new LinkedList<>();
    }

    public void appendTail(int value) {
        stack1.push(value);
    }

    public int deleteHead() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        if (stack2.isEmpty()) {
            return -1;
        }
        return stack2.pop();
    }
}
