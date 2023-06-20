package leetcode.jianzhi_offer_II;

import java.util.LinkedList;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer II 025. 链表中的两数相加 | 难度：中等 | 难度：栈、链表、数学
 * 给定两个 非空链表 l1和 l2 来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * <p>
 * 可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * <p>
 * 示例1：
 * 输入：l1 = [7,2,4,3], l2 = [5,6,4]
 * 输出：[7,8,0,7]
 * <p>
 * 示例2：
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[8,0,7]
 * <p>
 * 示例3：
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * <p>
 * 提示：
 * 链表的长度范围为 [1, 100]
 * 0 <= node.val <= 9
 * 输入数据保证链表代表的数字无前导 0
 * <p>
 * 进阶：如果输入链表不能修改该如何处理？换句话说，不能对列表中的节点进行翻转。
 * <p>
 * 注意：本题与主站 445 题相同：https://leetcode-cn.com/problems/add-two-numbers-ii/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/lMSNwu
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/20 10:05
 */
public class SolutionOfferII25 {
    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了 65.88% 的用户
     * 内存消耗：42.4 MB, 在所有 Java 提交中击败了 22.36% 的用户
     * 通过测试用例：1563 / 1563
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        LinkedList<Integer> stack1 = new LinkedList<>();
        LinkedList<Integer> stack2 = new LinkedList<>();
        ListNode ptr = l1;
        while (ptr != null) {
            stack1.push(ptr.val);
            ptr = ptr.next;
        }
        ptr = l2;
        while (ptr != null) {
            stack2.push(ptr.val);
            ptr = ptr.next;
        }
        ListNode dummy = new ListNode();
        int carry = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry > 0) {
            int sum = (stack1.isEmpty() ? 0 : stack1.pop()) + (stack2.isEmpty() ? 0 : stack2.pop()) + carry;
            carry = sum / 10;
            dummy.next = new ListNode(sum % 10, dummy.next);
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
