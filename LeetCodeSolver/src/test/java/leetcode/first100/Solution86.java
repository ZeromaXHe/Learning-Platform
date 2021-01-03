package leetcode.first100;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/1/3 13:22
 * @Description: 86.分隔链表 | 难度：中等 | 标签：链表、双指针
 * 给你一个链表和一个特定值 x ，请你对链表进行分隔，使得所有小于 x 的节点都出现在大于或等于 x 的节点之前。
 * <p>
 * 你应当保留两个分区中每个节点的初始相对位置。
 * <p>
 * 示例：
 * 输入：head = 1->4->3->2->5->2, x = 3
 * 输出：1->2->2->4->3->5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: ZeromaXHe
 */
public class Solution86 {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 37.7 MB , 在所有 Java 提交中击败了 70.57% 的用户
     */
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode lessDummy = new ListNode(0);
        ListNode moreDummy = new ListNode(0);
        ListNode index = head;
        ListNode lessTail = lessDummy;
        ListNode moreTail = moreDummy;
        while (index != null) {
            if (index.val < x) {
                lessTail.next = index;
                lessTail = index;
            } else {
                moreTail.next = index;
                moreTail = index;
            }
            index = index.next;
        }
        lessTail.next = moreDummy.next;
        moreTail.next = null;
        return lessDummy.next;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
