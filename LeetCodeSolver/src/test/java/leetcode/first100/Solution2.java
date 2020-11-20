package leetcode.first100;

import java.util.LinkedList;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/11/20 17:40
 * @Description: 2.两数相加 | 难度：中等 | 标签：链表、数学
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution2 {
    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了 99.92% 的用户
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了 99.83% 的用户
     * <p>
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode dummyHead = new ListNode();
        ListNode lastInsert = dummyHead;
        while (l1 != null && l2 != null) {
            int val1 = l1.val;
            int val2 = l2.val;
            int sum = val1 + val2 + carry;
            ListNode insert = new ListNode(sum % 10);
            lastInsert.next = insert;
            lastInsert = insert;
            carry = sum / 10;
            l1 = l1.next;
            l2 = l2.next;
        }
        // 这个循环和下面l2的循环只会最多执行一个
        // 因为要么l1为空，要么l2为空，要么都为空
        // 否则上面的循环不会退出
        while (l1 != null) {
            int val1 = l1.val;
            int sum = val1 + carry;
            ListNode insert = new ListNode(sum % 10);
            lastInsert.next = insert;
            lastInsert = insert;
            carry = sum / 10;
            l1 = l1.next;
        }
        // 这个循环和上面l1的循环只会最多执行一个，原因同上
        while (l2 != null) {
            int val2 = l2.val;
            int sum = val2 + carry;
            ListNode insert = new ListNode(sum % 10);
            lastInsert.next = insert;
            lastInsert = insert;
            carry = sum / 10;
            l2 = l2.next;
        }
        if (carry != 0) {
            ListNode insert = new ListNode(carry);
            lastInsert.next = insert;
        }
        return dummyHead.next;
    }

    /**
     * 链表是逆序的…… 我说标签里为什么没有栈
     * 输出的链表是反的
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers_wrongAnswer(ListNode l1, ListNode l2) {
        LinkedList<Integer> stack1 = new LinkedList<>();
        LinkedList<Integer> stack2 = new LinkedList<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        int carry = 0;
        ListNode dummyHead = new ListNode();
        ListNode lastInsert = null;
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            int val1 = stack1.pop();
            int val2 = stack2.pop();
            int sum = val1 + val2 + carry;
            ListNode insert = new ListNode(sum % 10);
            insert.next = lastInsert;
            dummyHead.next = insert;
            lastInsert = insert;
            carry = sum / 10;
        }
        // 这个循环和下面stack2的循环只会最多执行一个
        // 因为要么stack1为空，要么stack2为空，要么都为空
        // 否则上面的循环不会退出
        while (!stack1.isEmpty()) {
            int val1 = stack1.pop();
            int sum = val1 + carry;
            ListNode insert = new ListNode(sum % 10);
            insert.next = lastInsert;
            dummyHead.next = insert;
            lastInsert = insert;
            carry = sum / 10;
        }
        // 这个循环和上面stack1的循环只会最多执行一个，原因同上
        while (!stack2.isEmpty()) {
            int val2 = stack2.pop();
            int sum = val2 + carry;
            ListNode insert = new ListNode(sum % 10);
            insert.next = lastInsert;
            dummyHead.next = insert;
            lastInsert = insert;
            carry = sum / 10;
        }
        if (carry != 0) {
            ListNode insert = new ListNode(carry);
            insert.next = lastInsert;
            dummyHead.next = insert;
        }
        return dummyHead.next;
    }
}

class ListNode {
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
