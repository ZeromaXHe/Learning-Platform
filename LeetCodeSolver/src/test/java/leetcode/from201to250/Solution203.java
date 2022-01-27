package leetcode.from201to250;

/**
 * @Author: zhuxi
 * @Time: 2022/1/27 18:32
 * @Description: 203. 移除链表元素 | 难度：简单 | 标签：递归、链表
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 * <p>
 * 示例 1：
 * 输入：head = [1,2,6,3,4,5,6], val = 6
 * 输出：[1,2,3,4,5]
 * <p>
 * 示例 2：
 * 输入：head = [], val = 1
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：head = [7,7,7,7], val = 7
 * 输出：[]
 * <p>
 * 提示：
 * 列表中的节点数目在范围 [0, 104] 内
 * 1 <= Node.val <= 50
 * 0 <= val <= 50
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-linked-list-elements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution203 {
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
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 94.80% 的用户
     * 内存消耗： 42.5 MB , 在所有 Java 提交中击败了 5.06% 的用户
     * 通过测试用例： 66 / 66
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode pre = dummy;
        ListNode ptr = dummy.next;
        while (ptr != null) {
            if (ptr.val == val) {
                pre.next = ptr.next;
            } else {
                pre = pre.next;
            }
            ptr = ptr.next;
        }
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
