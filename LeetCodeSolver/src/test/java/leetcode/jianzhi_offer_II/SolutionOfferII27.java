package leetcode.jianzhi_offer_II;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer II 027. 回文链表 | 难度：简单 | 标签：栈、递归、链表、双指针
 * 给定一个链表的 头节点 head ，请判断其是否为回文链表。
 * <p>
 * 如果一个链表是回文，那么链表节点序列从前往后看和从后往前看是相同的。
 * <p>
 * 示例 1：
 * 输入: head = [1,2,3,3,2,1]
 * 输出: true
 * <p>
 * 示例 2：
 * 输入: head = [1,2]
 * 输出: false
 * <p>
 * 提示：
 * 链表 L 的长度范围为 [1, 105]
 * 0 <= node.val <= 9
 * <p>
 * 进阶：能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 * <p>
 * 注意：本题与主站 234 题相同：https://leetcode-cn.com/problems/palindrome-linked-list/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/aMhZSa
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/20 12:00
 */
public class SolutionOfferII27 {
    /**
     * 执行用时：3 ms, 在所有 Java 提交中击败了 99.37% 的用户
     * 内存消耗：57.8 MB, 在所有 Java 提交中击败了 31.99% 的用户
     * 通过测试用例：85 / 85
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
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
            if (p1.val != p2.val) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
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
