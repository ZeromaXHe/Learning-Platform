package leetcode.first100;

/**
 * @Author: zhuxi
 * @Time: 2021/3/18 9:48
 * @Description: 92.反转链表II | 难度：中等 | 标签：链表
 * 给你单链表的头节点 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 * <p>
 * 示例 1：
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 * <p>
 * 示例 2：
 * 输入：head = [5], left = 1, right = 1
 * 输出：[5]
 * <p>
 * 提示：
 * 链表中节点数目为 n
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution92 {
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
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36 MB , 在所有 Java 提交中击败了 53.58% 的用户
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) {
            return head;
        }
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode beforePtr = dummy;
        ListNode afterPtr = dummy;
        int count = 0;
        while (count < left - 1) {
            beforePtr = beforePtr.next;
            afterPtr = afterPtr.next;
            count++;
        }
        while (count <= right) {
            afterPtr = afterPtr.next;
            count++;
        }
        ListNode end = beforePtr.next;
        ListNode ptr = beforePtr.next;
        beforePtr.next = null;
        ListNode temp;
        ListNode after = ptr.next;
        while (ptr != afterPtr) {
            temp = beforePtr.next;
            after = ptr.next;
            beforePtr.next = ptr;
            ptr.next = temp;
            ptr = after;
        }
        end.next = afterPtr;
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
}
