package leetcode.chengxuyuan_mianshi_jingdian;

/**
 * @Author: zhuxi
 * @Time: 2022/5/5 10:09
 * @Description: 面试题 02.05. 链表求和 | 难度：中等 | 标签：递归、链表、数学
 * 给定两个用链表表示的整数，每个节点包含一个数位。
 * <p>
 * 这些数位是反向存放的，也就是个位排在链表首部。
 * <p>
 * 编写函数对这两个整数求和，并用链表形式返回结果。
 * <p>
 * 示例：
 * 输入：(7 -> 1 -> 6) + (5 -> 9 -> 2)，即617 + 295
 * 输出：2 -> 1 -> 9，即912
 * <p>
 * 进阶：思考一下，假设这些数位是正向存放的，又该如何解决呢?
 * <p>
 * 示例：
 * 输入：(6 -> 1 -> 7) + (2 -> 9 -> 5)，即617 + 295
 * 输出：9 -> 1 -> 2，即912
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-lists-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution02_05 {
    /**
     * Definition for singly-linked list.
     * | public class ListNode {
     * |     int val;
     * |     ListNode next;
     * |     ListNode(int x) { val = x; }
     * | }
     * <p>
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 41.2 MB , 在所有 Java 提交中击败了 75.82% 的用户
     * 通过测试用例： 1563 / 1563
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode ptr1 = l1;
        ListNode ptr2 = l2;
        ListNode ptr = dummy;
        int carry = 0;
        while (ptr1 != null && ptr2 != null) {
            int sum = ptr1.val + ptr2.val + carry;
            ptr.next = new ListNode(sum % 10);
            carry = sum / 10;
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
            ptr = ptr.next;
        }
        while (ptr1 != null) {
            int sum = ptr1.val + carry;
            ptr.next = new ListNode(sum % 10);
            carry = sum / 10;
            ptr1 = ptr1.next;
            ptr = ptr.next;
        }
        while (ptr2 != null) {
            int sum = ptr2.val + carry;
            ptr.next = new ListNode(sum % 10);
            carry = sum / 10;
            ptr2 = ptr2.next;
            ptr = ptr.next;
        }
        if (carry > 0) {
            ptr.next = new ListNode(carry);
        }
        return dummy.next;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
