package leetcode.chengxuyuan_mianshi_jingdian;

import java.util.LinkedList;

/**
 * @Author: zhuxi
 * @Time: 2022/5/5 14:20
 * @Description: 面试题 03.04. 化栈为队 | 难度：简单 | 标签：栈、设计、队列
 * 实现一个MyQueue类，该类用两个栈来实现一个队列。
 * <p>
 * 示例：
 * MyQueue queue = new MyQueue();
 * queue.push(1);
 * queue.push(2);
 * queue.peek();  // 返回 1
 * queue.pop();   // 返回 1
 * queue.empty(); // 返回 false
 * <p>
 * 说明：
 * 你只能使用标准的栈操作 -- 也就是只有 push to top, peek/pop from top, size 和 is empty 操作是合法的。
 * 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
 * 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-queue-using-stacks-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution03_04 {
    /**
     * 执行用时： 8 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.9 MB , 在所有 Java 提交中击败了 82.41% 的用户
     * 通过测试用例： 17 / 17
     */
    class MyQueue {
        LinkedList<Integer> stack1;
        LinkedList<Integer> stack2;

        /**
         * Initialize your data structure here.
         */
        public MyQueue() {
            stack1 = new LinkedList<>();
            stack2 = new LinkedList<>();
        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {
            stack1.push(x);
        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            return stack2.pop();
        }

        /**
         * Get the front element.
         */
        public int peek() {
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            return stack2.peek();
        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            return stack1.isEmpty() && stack2.isEmpty();
        }
    }

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
}
