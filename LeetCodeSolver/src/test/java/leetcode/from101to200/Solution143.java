package leetcode.from101to200;

import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2022/1/21 18:48
 * @Description: 143. 重排链表 | 难度：中等 | 标签：栈、递归、链表、双指针
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 * <p>
 * L0 → L1 → … → Ln - 1 → Ln
 * 请将其重新排列后变为：
 * <p>
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 示例 1：
 * 输入：head = [1,2,3,4]
 * 输出：[1,4,2,3]
 * <p>
 * 示例 2：
 * 输入：head = [1,2,3,4,5]
 * 输出：[1,5,2,4,3]
 * <p>
 * 提示：
 * 链表的长度范围为 [1, 5 * 104]
 * 1 <= node.val <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reorder-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution143 {
    @Test
    public void reorderListTest() {
        ListNode node1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        reorderList(node1);
        System.out.println(node1);
    }

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
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 99.92% 的用户
     * 内存消耗： 41.2 MB , 在所有 Java 提交中击败了 30.47% 的用户
     * 通过测试用例： 12 / 12
     *
     * @param head
     */
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        // 分割前半和后半部分
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast.next != null) {
                fast = fast.next;
            }
        }
        // 颠倒后半部分
        ListNode from = slow.next;
        fast = slow.next;
        slow.next = null;
        while (from != null) {
            ListNode next = from.next;
            from.next = slow.next;
            slow.next = from;
            from = next;
        }

//        System.out.println(head);

        // 将后半部分插入前半部分
        from = slow.next;
        ListNode to = head;
        slow.next = null;

        while (from != fast) {
            slow = from.next;
            ListNode nextTo = to.next;
            to.next = from;
            from.next = nextTo;
            from = slow;
            to = nextTo;
        }
        if (to.next != null) {
            fast.next = to.next;
        }
        to.next = fast;
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

        @Override
        public String toString() {
            return "" + val + ", " + next;
        }
    }
}
