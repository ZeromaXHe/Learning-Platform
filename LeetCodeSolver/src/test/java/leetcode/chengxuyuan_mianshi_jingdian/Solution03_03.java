package leetcode.chengxuyuan_mianshi_jingdian;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @Author: zhuxi
 * @Time: 2022/5/5 11:48
 * @Description: 面试题 03.03. 堆盘子 | 难度：中等 | 标签：栈、设计、链表
 * 堆盘子。设想有一堆盘子，堆太高可能会倒下来。因此，在现实生活中，盘子堆到一定高度时，我们就会另外堆一堆盘子。
 * 请实现数据结构SetOfStacks，模拟这种行为。SetOfStacks应该由多个栈组成，并且在前一个栈填满时新建一个栈。
 * 此外，SetOfStacks.push()和SetOfStacks.pop()应该与普通栈的操作方法相同（也就是说，pop()返回的值，应该跟只有一个栈时的情况一样）。
 * 进阶：实现一个popAt(int index)方法，根据指定的子栈，执行pop操作。
 * <p>
 * 当某个栈为空时，应当删除该栈。当栈中没有元素或不存在该栈时，pop，popAt 应返回 -1.
 * <p>
 * 示例1:
 * 输入：
 * ["StackOfPlates", "push", "push", "popAt", "pop", "pop"]
 * [[1], [1], [2], [1], [], []]
 * 输出：
 * [null, null, null, 2, 1, -1]
 * <p>
 * 示例2:
 * 输入：
 * ["StackOfPlates", "push", "push", "push", "popAt", "popAt", "popAt"]
 * [[2], [1], [2], [3], [0], [0], [0]]
 * 输出：
 * [null, null, null, null, 2, 1, 3]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/stack-of-plates-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution03_03 {
    @Test
    public void stackOfPlatesTest() {
        StackOfPlates stack = new StackOfPlates(1);
        stack.push(1);
        stack.push(2);
        Assert.assertEquals(2, stack.popAt(1));
        Assert.assertEquals(1, stack.pop());
        Assert.assertEquals(-1, stack.pop());

        stack = new StackOfPlates(0);
        stack.push(1);
        stack.push(2);
        Assert.assertEquals(-1, stack.popAt(1));
        Assert.assertEquals(-1, stack.pop());
    }

    /**
     * 执行用时： 8 ms , 在所有 Java 提交中击败了 99.72% 的用户
     * 内存消耗： 49.3 MB , 在所有 Java 提交中击败了 70.29% 的用户
     * 通过测试用例： 30 / 30
     */
    class StackOfPlates {
        private int cap;
        LinkedList<int[]> stack;

        public StackOfPlates(int cap) {
            this.cap = cap;
            stack = new LinkedList<>();
        }

        public void push(int val) {
            if (cap == 0) {
                return;
            }
            if (stack.isEmpty()) {
                int[] arrStack = new int[cap + 1];
                arrStack[0] = 1;
                arrStack[1] = val;
                stack.push(arrStack);
                return;
            }
            int[] topStack = stack.peek();
            if (topStack[0] == cap) {
                topStack = new int[cap + 1];
                stack.push(topStack);
            }
            topStack[++topStack[0]] = val;
        }

        public int pop() {
            if (cap == 0 || stack.isEmpty()) {
                return -1;
            }
            int[] topStack = stack.peek();
            if (topStack[0] == 1) {
                stack.pop();
            }
            return topStack[topStack[0]--];
        }

        public int popAt(int index) {
            if (cap == 0 || index < 0 || index >= stack.size()) {
                return -1;
            }
            Iterator<int[]> iterator = stack.iterator();
            int[] next = iterator.next();
            // LinkedList 每次 push 等于是头插法（addFirst） , 所以 index 和 LinkedList 的索引是反的
            for (int i = 0; i < stack.size() - index - 1; i++) {
                next = iterator.next();
            }
            if (next[0] == 1) {
                iterator.remove();
            }
            return next[next[0]--];
        }
    }

    /**
     * 执行用时： 10 ms , 在所有 Java 提交中击败了 63.88% 的用户
     * 内存消耗： 49.5 MB , 在所有 Java 提交中击败了 44.91% 的用户
     * 通过测试用例： 30 / 30
     * <p>
     * 因为有 cap 参数，所以使用 LinkedList<int[]> 会快一点。
     */
    class StackOfPlates_DoubleLayersLinkedList {
        private int cap;
        LinkedList<LinkedList<Integer>> stack;

        public StackOfPlates_DoubleLayersLinkedList(int cap) {
            this.cap = cap;
            stack = new LinkedList<>();
        }

        public void push(int val) {
            if (cap == 0) {
                return;
            }
            if (stack.isEmpty()) {
                stack.push(new LinkedList<>());
                stack.peek().push(val);
                return;
            }
            LinkedList<Integer> topStack = stack.peek();
            if (topStack.size() == cap) {
                topStack = new LinkedList<>();
                stack.push(topStack);
            }
            topStack.push(val);
        }

        public int pop() {
            if (cap == 0 || stack.isEmpty()) {
                return -1;
            }
            LinkedList<Integer> topStack = stack.peek();
            if (topStack.size() == 1) {
                stack.pop();
            }
            return topStack.pop();
        }

        public int popAt(int index) {
            if (cap == 0 || index < 0 || index >= stack.size()) {
                return -1;
            }
            Iterator<LinkedList<Integer>> iterator = stack.iterator();
            LinkedList<Integer> next = iterator.next();
            // LinkedList 每次 push 等于是头插法（addFirst） , 所以 index 和 LinkedList 的索引是反的
            for (int i = 0; i < stack.size() - index - 1; i++) {
                next = iterator.next();
            }
            if (next.size() == 1) {
                iterator.remove();
            }
            return next.pop();
        }
    }

/**
 * Your StackOfPlates object will be instantiated and called as such:
 * StackOfPlates obj = new StackOfPlates(cap);
 * obj.push(val);
 * int param_2 = obj.pop();
 * int param_3 = obj.popAt(index);
 */
}
