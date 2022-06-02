package leetcode.from401to450;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author: zhuxi
 * @Time: 2022/6/2 13:49
 * @Description: 445. 两数相加 II | 难度：中等 | 标签：
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * <p>
 * 示例1：
 * 输入：l1 = [7,2,4,3], l2 = [5,6,4]
 * 输出：[7,8,0,7]
 * <p>
 * 示例2：
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[8,0,7]
 * <p>
 * 示例3：
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * <p>
 * 提示：
 * 链表的长度范围为 [1, 100]
 * 0 <= node.val <= 9
 * 输入数据保证链表代表的数字无前导 0
 * <p>
 * 进阶：如果输入链表不能翻转该如何解决？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/add-two-numbers-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution445 {
    /**
     * Definition for singly-linked list.
     * | public class ListNode {
     * |     int val;
     * |     ListNode next;
     * |     ListNode() {}
     * |     ListNode(int val) { this.val = val; }
     * |     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * | }
     * <p>
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 70.74% 的用户
     * 内存消耗： 41.7 MB , 在所有 Java 提交中击败了 8.60% 的用户
     * 通过测试用例： 1563 / 1563
     * <p>
     * 官方题解，ArrayDeque 这个类还是第一次看到
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> stack1 = new ArrayDeque<>();
        Deque<Integer> stack2 = new ArrayDeque<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        int carry = 0;
        ListNode ans = null;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
            int a = stack1.isEmpty() ? 0 : stack1.pop();
            int b = stack2.isEmpty() ? 0 : stack2.pop();
            int cur = a + b + carry;
            carry = cur / 10;
            cur %= 10;
            ListNode curnode = new ListNode(cur);
            curnode.next = ans;
            ans = curnode;
        }
        return ans;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
