package leetcode.jianzhi_offer_II;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer II 022. 链表中环的入口节点 | 难度：中等 | 标签：哈希表、链表、双指针
 * 给定一个链表，返回链表开始入环的第一个节点。 从链表的头节点开始沿着 next 指针进入环的第一个节点为环的入口节点。如果链表无环，则返回 null。
 * <p>
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * 注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
 * <p>
 * 说明：不允许修改给定的链表。
 * <p>
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：返回索引为 1 的链表节点
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * <p>
 * 示例 2：
 * 输入：head = [1,2], pos = 0
 * 输出：返回索引为 0 的链表节点
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * <p>
 * 示例 3：
 * 输入：head = [1], pos = -1
 * 输出：返回 null
 * 解释：链表中没有环。
 * <p>
 * 提示：
 * 链表中节点的数目范围在范围 [0, 104] 内
 * -105 <= Node.val <= 105
 * pos 的值为 -1 或者链表中的一个有效索引
 * <p>
 * 进阶：是否可以使用 O(1) 空间解决此题？
 * <p>
 * 注意：本题与主站 142 题相同： https://leetcode-cn.com/problems/linked-list-cycle-ii/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/c32eOV
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/19 14:57
 */
public class SolutionOfferII22 {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：42.7 MB, 在所有 Java 提交中击败了 18.64% 的用户
     * 通过测试用例：16 / 16
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode quick = head.next;
        while (slow != quick) {
            if (quick == null) {
                return null;
            }
            slow = slow.next;
            quick = quick.next;
            if (quick == null) {
                return null;
            }
            quick = quick.next;
            if (quick == null) {
                return null;
            }
        }
        slow = head;
        quick = quick.next;
        while (slow != quick) {
            slow = slow.next;
            quick = quick.next;
        }
        return slow;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
