package leetcode.chengxuyuan_mianshi_jingdian;

/**
 * @Author: zhuxi
 * @Time: 2022/5/5 11:11
 * @Description: 面试题 02.08. 环路检测 | 难度：中等 | 标签：哈希表、链表、双指针
 * 给定一个链表，如果它是有环链表，实现一个算法返回环路的开头节点。若环不存在，请返回 null。
 * <p>
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。
 * 注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * <p>
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：tail connects to node index 1
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * <p>
 * 示例 2：
 * 输入：head = [1,2], pos = 0
 * 输出：tail connects to node index 0
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * <p>
 * 示例 3：
 * 输入：head = [1], pos = -1
 * 输出：no cycle
 * 解释：链表中没有环。
 * <p>
 * 进阶：
 * 你是否可以不用额外空间解决此题？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution02_08 {
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
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 42 MB , 在所有 Java 提交中击败了 26.65% 的用户
     * 通过测试用例： 16 / 16
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode p2 = head.next;
        ListNode p1 = head;
        while (p1 != p2 && p2 != null) {
            p1 = p1.next;
            p2 = p2.next == null ? null : p2.next.next;
        }
        if (p2 == null) {
            return null;
        }
        p1 = head;
        p2 = p2.next;
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
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
