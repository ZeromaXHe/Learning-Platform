package leetcode.jianzhi_offer_II;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer II 024. 反转链表 | 难度：简单 | 标签：递归、链表
 * 给定单链表的头节点 head ，请反转链表，并返回反转后的链表的头节点。
 * <p>
 * 示例 1：
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 * <p>
 * 示例 2：
 * 输入：head = [1,2]
 * 输出：[2,1]
 * <p>
 * 示例 3：
 * 输入：head = []
 * 输出：[]
 * <p>
 * 提示：
 * 链表中节点的数目范围是 [0, 5000]
 * -5000 <= Node.val <= 5000
 * <p>
 * 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
 * <p>
 * 注意：本题与主站 206 题相同： https://leetcode-cn.com/problems/reverse-linked-list/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/UHnkqh
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/19 16:04
 */
public class SolutionOfferII24 {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：40.1 MB, 在所有 Java 提交中击败了 94.55% 的用户
     * 通过测试用例：28 / 28
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode();
        ListNode p = head;
        while (p != null) {
            ListNode next = dummy.next;
            dummy.next = p;
            p = p.next;
            dummy.next.next = next;
        }
        return dummy.next;
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
