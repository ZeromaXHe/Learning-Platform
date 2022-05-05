package leetcode.chengxuyuan_mianshi_jingdian;

import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @Author: zhuxi
 * @Time: 2022/5/5 14:29
 * @Description: 面试题 03.05. 栈排序 | 难度：中等 | 标签：栈、设计、单调栈
 * 栈排序。 编写程序，对栈进行排序使最小元素位于栈顶。最多只能使用一个其他的临时栈存放数据，但不得将元素复制到别的数据结构（如数组）中。
 * 该栈支持如下操作：push、pop、peek 和 isEmpty。当栈为空时，peek 返回 -1。
 * <p>
 * 示例1:
 * 输入：
 * ["SortedStack", "push", "push", "peek", "pop", "peek"]
 * [[], [1], [2], [], [], []]
 * 输出：
 * [null,null,null,1,null,2]
 * <p>
 * 示例2:
 * 输入：
 * ["SortedStack", "pop", "pop", "push", "pop", "isEmpty"]
 * [[], [], [], [1], [], []]
 * 输出：
 * [null,null,null,null,null,true]
 * <p>
 * 说明:
 * 栈中的元素数目在[0, 5000]范围内。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-of-stacks-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution03_05 {
    /**
     * 执行用时： 13 ms , 在所有 Java 提交中击败了 89.68% 的用户
     * 内存消耗： 41.8 MB , 在所有 Java 提交中击败了 44.88% 的用户
     * 通过测试用例： 30 / 30
     * <p>
     * 直接用优先队列是真的秀，不过不太符合题意，这里权且贴一下耗时
     */
    class SortedStack_heap {
        PriorityQueue<Integer> queue;

        public SortedStack_heap() {
            queue = new PriorityQueue<>();
        }

        public void push(int val) {
            queue.add(val);
        }

        public void pop() {
            queue.poll();
        }

        public int peek() {
            return queue.isEmpty() ? -1 : queue.peek();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    /**
     * 执行用时： 86 ms , 在所有 Java 提交中击败了 81.28% 的用户
     * 内存消耗： 43.1 MB , 在所有 Java 提交中击败了 7.76% 的用户
     * 通过测试用例： 30 / 30
     * <p>
     * 如果将 辅助栈倒回原栈中 这一步提到 push 的开头，并且在 pop、peek 等方法都写该逻辑的话，耗时并不会减少，反而增加到了 107 ms
     */
    class SortedStack {
        private LinkedList<Integer> stack;
        private LinkedList<Integer> temp;

        public SortedStack() {
            stack = new LinkedList<>();
            temp = new LinkedList<>();
        }

        public void push(int val) {
            // 对比栈顶元素，并弹出栈顶
            while (!stack.isEmpty() && val > stack.peek()) {
                temp.push(stack.pop());
            }
            // 将val插入到合适位置
            stack.push(val);
            // 辅助栈倒回原栈中
            while (!temp.isEmpty()) {
                stack.push(temp.pop());
            }
        }

        public void pop() {
            if (!stack.isEmpty()) {
                stack.pop();
            }
        }

        public int peek() {
            if (stack.isEmpty()) {
                return -1;
            }
            return stack.peek();
        }

        public boolean isEmpty() {
            return stack.isEmpty();
        }
    }

/**
 * Your SortedStack object will be instantiated and called as such:
 * SortedStack obj = new SortedStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.isEmpty();
 */
}
