package leetcode.first100;

import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/3/27 14:51
 * @Description: 61.旋转链表 | 难度：中等 | 标签：链表、双指针
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 * <p>
 * 示例 1：
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 * <p>
 * 示例 2：
 * 输入：head = [0,1,2], k = 4
 * 输出：[2,0,1]
 * <p>
 * 提示：
 * 链表中节点的数目在范围 [0, 500] 内
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 109
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution61 {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     * <p>
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 51.22% 的用户
     * 内存消耗： 38.1 MB , 在所有 Java 提交中击败了 7.68% 的用户
     * <p>
     * 自己的思路还是和数组旋转的思路一样的，反转后再在两边反转。
     * 题解的思路还是妙一点，直接组成环，然后在合适的地方断开，利用了链表的特性。
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        int count = 0;
        ListNode dummy = new ListNode(-101, null);
        ListNode ptr = head;
        ListNode pre = dummy;
        while (ptr != null) {
            pre = ptr;
            ptr = ptr.next;
            pre.next = dummy.next;
            dummy.next = pre;
            count++;
        }
        int toBeRotate = k % count;
        if (toBeRotate == 0) {
            ptr = dummy.next;
            dummy.next = null;
            while (ptr != null) {
                pre = ptr;
                ptr = ptr.next;
                pre.next = dummy.next;
                dummy.next = pre;
                count++;
            }
            return dummy.next;
        }
        ptr = dummy.next;
        ListNode beforeEnd = dummy.next;
        dummy.next = null;
        while (toBeRotate > 0) {
            pre = ptr;
            ptr = ptr.next;
            pre.next = dummy.next;
            dummy.next = pre;
            toBeRotate--;
        }
        while (ptr != null) {
            pre = ptr;
            ptr = ptr.next;
            pre.next = beforeEnd.next;
            beforeEnd.next = pre;
        }
        return dummy.next;
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

    @Test
    public void rotateRightTest() {
        ListNode second = new ListNode(2, null);
        ListNode head = new ListNode(1, second);
        rotateRight(head, 1);
    }
}
