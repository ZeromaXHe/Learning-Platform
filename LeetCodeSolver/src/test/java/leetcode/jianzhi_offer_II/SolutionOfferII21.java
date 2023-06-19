package leetcode.jianzhi_offer_II;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer II 021. 删除链表的倒数第 n 个结点 | 难度：中等 | 标签：链表、双指针
 * 给定一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
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
 * 进阶：能尝试使用一趟扫描实现吗？
 * <p>
 * 注意：本题与主站 19 题相同： https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/SLwz0R
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/19 14:46
 */
public class SolutionOfferII21 {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：39.5 MB, 在所有 Java 提交中击败了 63.82% 的用户
     * 通过测试用例：208 / 208
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode post = dummy;
        for (int i = 0; i < n; i++) {
            post = post.next;
        }
        ListNode rmv = dummy;
        ListNode pre = null;
        while (post != null) {
            post = post.next;
            pre = rmv;
            rmv = rmv.next;
        }
        pre.next = rmv.next;
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
