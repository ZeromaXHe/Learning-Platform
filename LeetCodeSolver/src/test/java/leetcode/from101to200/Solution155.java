package leetcode.from101to200;

import java.util.LinkedList;

/**
 * @Author: zhuxi
 * @Time: 2022/1/24 19:20
 * @Description: 155. 最小栈 | 难度：简单 | 标签：栈、设计
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 * <p>
 * 示例:
 * <p>
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * <p>
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 * <p>
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 * <p>
 * 提示：
 * pop、top 和 getMin 操作总是在 非空栈 上调用。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/min-stack
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution155 {
    /**
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 99.04% 的用户
     * 内存消耗： 40.3 MB , 在所有 Java 提交中击败了 17.30% 的用户
     * 通过测试用例： 31 / 31
     */
    class MinStack {
        LinkedList<Integer> stack;
        LinkedList<Integer> min;

        public MinStack() {
            stack = new LinkedList<>();
            min = new LinkedList<>();
        }

        public void push(int val) {
            stack.push(val);
            if (min.isEmpty() || val <= min.peek()) {
                min.push(val);
            }
        }

        public void pop() {
            int pop = stack.pop();
            if (pop == min.peek()) {
                min.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min.peek();
        }
    }

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
}
