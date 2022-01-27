package leetcode.from101to150;

/**
 * @Author: zhuxi
 * @Time: 2022/1/21 18:33
 * @Description: 142. 环形链表 II | 难度：中等 | 标签：哈希表、链表、双指针
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * <p>
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * <p>
 * 不允许修改 链表。
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
 * 进阶：你是否可以使用 O(1) 空间解决此题？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution142 {
    /**
     * Definition for singly-linked list.
     * | class ListNode {
     * |     int val;
     * |     ListNode next;
     * |     ListNode(int x) {
     * |         val = x;
     * |         next = null;
     * |     }
     * | }
     * <p>
     * 参考题解下别人的回复：
     * <p>
     * f为快指针走的步数，s为慢指针的步数，b为一圈的步数
     * 根据：
     * f = 2s （快指针每次2步，路程刚好2倍）
     * f = s + nb (相遇时，刚好多走了n圈）
     * 推出：s = nb
     * 从head结点走到入环点需要走 ： a + nb， 而slow已经走了nb，那么slow再走a步就是入环点了。
     * 如何知道slow刚好走了a步？ 从head开始，和slow指针一起走，相遇时刚好就是a步
     * <p>
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.2 MB , 在所有 Java 提交中击败了 91.41% 的用户
     * 通过测试用例： 16 / 16
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null) {
            fast = fast.next;
            if (fast == null) {
                break;
            }
            fast = fast.next;
            slow = slow.next;
            if (slow == fast) {
                ListNode ptr = head;
                while (slow != ptr) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
