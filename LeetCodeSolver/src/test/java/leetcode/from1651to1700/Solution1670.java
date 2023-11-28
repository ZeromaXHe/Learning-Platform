package leetcode.from1651to1700;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author zhuxi
 * @apiNote 1670. 设计前中后队列 | 难度：中等 | 标签：设计、队列、数组、链表、数据流
 * 请你设计一个队列，支持在前，中，后三个位置的 push 和 pop 操作。
 * <p>
 * 请你完成 FrontMiddleBack 类：
 * <p>
 * FrontMiddleBack() 初始化队列。
 * void pushFront(int val) 将 val 添加到队列的 最前面 。
 * void pushMiddle(int val) 将 val 添加到队列的 正中间 。
 * void pushBack(int val) 将 val 添加到队里的 最后面 。
 * int popFront() 将 最前面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 * int popMiddle() 将 正中间 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 * int popBack() 将 最后面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 * 请注意当有 两个 中间位置的时候，选择靠前面的位置进行操作。比方说：
 * <p>
 * 将 6 添加到 [1, 2, 3, 4, 5] 的中间位置，结果数组为 [1, 2, 6, 3, 4, 5] 。
 * 从 [1, 2, 3, 4, 5, 6] 的中间位置弹出元素，返回 3 ，数组变为 [1, 2, 4, 5, 6] 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * ["FrontMiddleBackQueue", "pushFront", "pushBack", "pushMiddle", "pushMiddle", "popFront", "popMiddle", "popMiddle", "popBack", "popFront"]
 * [[], [1], [2], [3], [4], [], [], [], [], []]
 * 输出：
 * [null, null, null, null, null, 1, 3, 4, 2, -1]
 * <p>
 * 解释：
 * FrontMiddleBackQueue q = new FrontMiddleBackQueue();
 * q.pushFront(1);   // [1]
 * q.pushBack(2);    // [1, 2]
 * q.pushMiddle(3);  // [1, 3, 2]
 * q.pushMiddle(4);  // [1, 4, 3, 2]
 * q.popFront();     // 返回 1 -> [4, 3, 2]
 * q.popMiddle();    // 返回 3 -> [4, 2]
 * q.popMiddle();    // 返回 4 -> [2]
 * q.popBack();      // 返回 2 -> []
 * q.popFront();     // 返回 -1 -> [] （队列为空）
 * <p>
 * 提示：
 * 1 <= val <= 109
 * 最多调用 1000 次 pushFront， pushMiddle， pushBack， popFront， popMiddle 和 popBack 。
 * @implNote
 * @since 2023/11/28 9:52
 */
public class Solution1670 {
    @Test
    public void testFrontMiddleBackQueue() {
        FrontMiddleBackQueue q = new FrontMiddleBackQueue();
        q.pushFront(1);   // [1]
        q.pushBack(2);    // [1, 2]
        q.pushMiddle(3);  // [1, 3, 2]
        q.pushMiddle(4);  // [1, 4, 3, 2]
        Assert.assertEquals(1, q.popFront());     // 返回 1 -> [4, 3, 2]
        Assert.assertEquals(3, q.popMiddle());    // 返回 3 -> [4, 2]
        Assert.assertEquals(4, q.popMiddle());    // 返回 4 -> [2]
        Assert.assertEquals(2, q.popBack());      // 返回 2 -> []
        Assert.assertEquals(-1, q.popFront());     // 返回 -1 -> [] （队列为空）

        FrontMiddleBackQueue q2 = new FrontMiddleBackQueue();
        q2.pushFront(888438);   // [888438]
        q2.pushMiddle(772690);    // [772690, 888438]
        q2.pushMiddle(375192);  // [772690, 375192, 888438]
        q2.pushFront(411268);  // [411268, 772690, 375192, 888438]
        q2.pushFront(885613);  // [885613, 411268, 772690, 375192, 888438]
        q2.pushMiddle(508187);  // [885613, 411268, 508187, 772690, 375192, 888438]
        Assert.assertEquals(508187, q2.popMiddle());     // 返回 508187 -> [885613, 411268, 772690, 375192, 888438]
        Assert.assertEquals(772690, q2.popMiddle());    // 返回 772690 -> [885613, 411268, 375192, 888438]
        q2.pushMiddle(111498);  // [885613, 411268, 111498, 375192, 888438]
        q2.pushMiddle(296008);  // [885613, 411268, 296008, 111498, 375192, 888438]
        Assert.assertEquals(885613, q2.popFront());     // 返回 885613 -> [411268, 296008, 111498, 375192, 888438]
    }

    /**
     * 用双端队列估计会实现简单一点
     * <p>
     * 执行用时分布 6 ms
     * 击败 80.50% 使用 Java 的用户
     * 消耗内存分布 43.23 MB
     * 击败 38.37% 使用 Java 的用户
     */
    class FrontMiddleBackQueue {
        Node front = new Node();
        Node back = new Node();
        Node mid = front;
        int size = 0;

        public FrontMiddleBackQueue() {
            back.pre = front;
            front.next = back;
        }

        public void pushFront(int val) {
            Node node = new Node();
            node.val = val;
            node.pre = front;
            node.next = front.next;
            front.next = node;
            node.next.pre = node;
            size++;
            if (size % 2 == 0) {
                mid = mid.pre;
            } else if (size == 1) {
                mid = mid.next;
            }
        }

        public void pushMiddle(int val) {
            Node node = new Node();
            node.val = val;
            if (size % 2 == 0) {
                node.pre = mid;
                node.next = mid.next;
                mid.next = node;
                node.next.pre = node;
            } else {
                node.pre = mid.pre;
                node.next = mid;
                mid.pre = node;
                node.pre.next = node;
            }
            mid = node;
            size++;
        }

        public void pushBack(int val) {
            Node node = new Node();
            node.val = val;
            node.next = back;
            node.pre = back.pre;
            back.pre = node;
            node.pre.next = node;
            size++;
            if (size % 2 == 1) {
                mid = mid.next;
            }
        }

        public int popFront() {
            if (size == 0) {
                return -1;
            }
            Node node = front.next;
            node.next.pre = front;
            front.next = node.next;
            size--;
            if (size == 0) {
                mid = front;
            } else if (size == 1) {
                mid = front.next;
            } else if (size % 2 == 1) {
                mid = mid.next;
            }
            node.pre = null;
            node.next = null;
            return node.val;
        }

        public int popMiddle() {
            if (size == 0) {
                return -1;
            }
            Node node = mid;
            node.next.pre = node.pre;
            node.pre.next = node.next;
            size--;
            if (size % 2 == 1) {
                mid = mid.next;
            } else {
                mid = mid.pre;
            }
            node.pre = null;
            node.next = null;
            return node.val;
        }

        public int popBack() {
            if (size == 0) {
                return -1;
            }
            Node node = back.pre;
            back.pre = node.pre;
            node.pre.next = back;
            size--;
            if (size % 2 == 0) {
                mid = mid.pre;
            }
            node.pre = null;
            node.next = null;
            return node.val;
        }
    }

    class Node {
        int val;
        Node pre;
        Node next;
    }
/**
 * Your FrontMiddleBackQueue object will be instantiated and called as such:
 * FrontMiddleBackQueue obj = new FrontMiddleBackQueue();
 * obj.pushFront(val);
 * obj.pushMiddle(val);
 * obj.pushBack(val);
 * int param_4 = obj.popFront();
 * int param_5 = obj.popMiddle();
 * int param_6 = obj.popBack();
 */
}
