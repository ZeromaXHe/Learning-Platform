package leetcode.jianzhi_offer_II;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer II 026. 重排链表 | 难度：中等 | 标签：栈、递归、链表、双指针
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 * <p>
 *  L0 → L1 → … → Ln-1 → Ln 
 * 请将其重新排列后变为：
 * <p>
 * L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
 * <p>
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 示例 1:
 * 输入: head = [1,2,3,4]
 * 输出: [1,4,2,3]
 * <p>
 * 示例 2:
 * 输入: head = [1,2,3,4,5]
 * 输出: [1,5,2,4,3]
 * <p>
 * 提示：
 * 链表的长度范围为 [1, 5 * 104]
 * 1 <= node.val <= 1000
 * <p>
 * 注意：本题与主站 143 题相同：https://leetcode-cn.com/problems/reorder-list/ 
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/LGjMqU
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/20 10:59
 */
public class SolutionOfferII26 {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了 99.62% 的用户
     * 内存消耗：44.6 MB, 在所有 Java 提交中击败了 30.95% 的用户
     * 通过测试用例：12 / 12
     *
     * @param head
     */
    public void reorderList(ListNode head) {
        ListNode dummy1 = new ListNode();
        dummy1.next = head;
        ListNode p1 = dummy1;
        ListNode p2 = dummy1;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
        }
        ListNode dummy2 = new ListNode();
        p2 = p1.next;
        p1.next = null;
        // 反转 p1 后面的链表
        while (p2 != null) {
            ListNode next = dummy2.next;
            dummy2.next = p2;
            p2 = p2.next;
            dummy2.next.next = next;
        }
        p1 = dummy1.next;
        p2 = dummy2.next;
        while (p2 != null) {
            ListNode next = p1.next;
            p1.next = p2;
            p2 = p2.next;
            p1.next.next = next;
            p1 = next;
        }
    }

    public class ListNode {
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
