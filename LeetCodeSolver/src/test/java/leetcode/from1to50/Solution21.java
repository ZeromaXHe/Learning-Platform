package leetcode.from1to50;

/**
 * @Author: zhuxi
 * @Time: 2022/1/10 14:44
 * @Description: 21. 合并两个有序链表 | 难度：简单 | 标签：递归、链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 * <p>
 * 示例 1：
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * <p>
 * 示例 2：
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 * <p>
 * 提示：
 * 两个链表的节点数目范围是 [0, 50]
 * -100 <= Node.val <= 100
 * l1 和 l2 均按 非递减顺序 排列
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution21 {
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
     * 内存消耗： 37.4 MB , 在所有 Java 提交中击败了 95.21% 的用户
     * 通过测试用例： 208 / 208
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list2 == null) {
            return list1;
        }
        if (list1 == null) {
            return list2;
        }
        ListNode dummy = new ListNode();
        ListNode node1 = list1;
        ListNode node2 = list2;
        ListNode end = dummy;
        while (node1 != null && node2 != null) {
            if (node1.val > node2.val) {
                end.next = node2;
                node2 = node2.next;
            } else {
                end.next = node1;
                node1 = node1.next;
            }
            end = end.next;
            end.next = null;
        }
        if (node1 != null) {
            end.next = node1;
        }
        if (node2 != null) {
            end.next = node2;
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
