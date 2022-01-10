package leetcode.first100;

/**
 * @Author: zhuxi
 * @Time: 2022/1/10 14:23
 * @Description: 19. 删除链表的倒数第 N 个结点 | 难度：中等 | 标签：链表、双指针
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * <p>
 * 示例 1：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * <p>
 * 示例 2：
 * 输入：head = [1], n = 1
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 * <p>
 * 提示：
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 * <p>
 * 进阶：你能尝试使用一趟扫描实现吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution19 {
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
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.4 MB , 在所有 Java 提交中击败了 52.58% 的用户
     * 通过测试用例： 208 / 208
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode beforeDel = dummy;
        ListNode end = head;
        while (n > 0) {
            end = end.next;
            n--;
        }
        while (end != null) {
            beforeDel = beforeDel.next;
            end = end.next;
        }
        beforeDel.next = beforeDel.next.next;
        return dummy.next;
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
