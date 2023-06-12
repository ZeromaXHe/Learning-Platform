package leetcode.jianzhi_offer;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Objects;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer 59 - II. 队列的最大值 | 难度：中等 | 标签：设计、队列、单调队列
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 * <p>
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 * <p>
 * 示例 1：
 * 输入:
 * ["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
 * [[],[1],[2],[],[],[]]
 * 输出: [null,null,null,2,1,2]
 * <p>
 * 示例 2：
 * 输入:
 * ["MaxQueue","pop_front","max_value"]
 * [[],[],[]]
 * 输出: [null,-1,-1]
 * <p>
 * 限制：
 * 1 <= push_back,pop_front,max_value的总操作数 <= 10000
 * 1 <= value <= 10^5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/dui-lie-de-zui-da-zhi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/12 13:49
 */
public class SolutionOffer59_II {
    @Test
    public void testLinkedListIntegerEquals() {
        LinkedList<Integer> ll1 = new LinkedList<>();
        LinkedList<Integer> ll2 = new LinkedList<>();
        ll1.add(646);
        ll2.add(646);
        // 超过 127 的 Integer 的 == 不会相等
        Assert.assertFalse(ll1.peekFirst() == ll2.peekFirst());
    }

    /**
     * 执行用时：26 ms, 在所有 Java 提交中击败了 75.13% 的用户
     * 内存消耗：49 MB, 在所有 Java 提交中击败了 77.79% 的用户
     * 通过测试用例：35 / 35
     */
    class MaxQueue {
        LinkedList<Integer> queue;
        LinkedList<Integer> deque;

        public MaxQueue() {
            queue = new LinkedList<>();
            deque = new LinkedList<>();
        }

        public int max_value() {
            if (deque.isEmpty()) {
                return -1;
            } else {
                return deque.peekFirst();
            }
        }

        public void push_back(int value) {
            while (!deque.isEmpty() && value > deque.peekLast()) {
                deque.pollLast();
            }
            // deque 最后是最大值，相当于是递增队列
            deque.offerLast(value);
            queue.offer(value);
        }

        public int pop_front() {
            if (queue.isEmpty()) {
                return -1;
            } else {
                // 这里用 == 判等会出错
                if (Objects.equals(queue.peekFirst(), deque.peekFirst())) {
                    deque.pollFirst();
                }
                return queue.poll();
            }
        }
    }

/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue obj = new MaxQueue();
 * int param_1 = obj.max_value();
 * obj.push_back(value);
 * int param_3 = obj.pop_front();
 */
}
