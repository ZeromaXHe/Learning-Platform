package leetcode.jianzhi_offer;

/**
 * @Author: zhuxi
 * @Time: 2021/9/2 9:58
 * @Description: 剑指 Offer 22. 链表中倒数第k个节点 | 难度：简单 | 标签：链表、双指针
 * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
 * <p>
 * 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。
 * <p>
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 k = 2.
 * 返回链表 4->5.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class SolutionOffer22 {
    /**
     * Definition for singly-linked list.
     * |public class ListNode {
     * |    int val;
     * |    ListNode next;
     * |    ListNode(int x) { val = x; }
     * |}
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.2 MB , 在所有 Java 提交中击败了 64.42% 的用户
     * 通过测试用例： 208 / 208
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode last = head;
        for (int i = 0; i < k; i++) {
            if (last == null) {
                return null;
            }
            last = last.next;
        }
        ListNode ptr = head;
        while (last != null) {
            last = last.next;
            ptr = ptr.next;
        }
        return ptr;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
