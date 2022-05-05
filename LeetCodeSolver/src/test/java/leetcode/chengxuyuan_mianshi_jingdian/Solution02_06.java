package leetcode.chengxuyuan_mianshi_jingdian;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2022/5/5 10:17
 * @Description: 面试题 02.06. 回文链表 | 难度：简单 | 标签：栈、递归、链表、双指针
 * 编写一个函数，检查输入的链表是否是回文的。
 * <p>
 * 示例 1：
 * 输入： 1->2
 * 输出： false
 * <p>
 * 示例 2：
 * 输入： 1->2->2->1
 * 输出： true
 * <p>
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-linked-list-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution02_06 {
    @Test
    public void isPalindromeTest() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        node1.next = node2;
        Assert.assertFalse(isPalindrome(node1));
    }

    /**
     * Definition for singly-linked list.
     * | public class ListNode {
     * |     int val;
     * |     ListNode next;
     * |     ListNode(int x) { val = x; }
     * | }
     * <p>
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 94.17% 的用户
     * 内存消耗： 43.8 MB , 在所有 Java 提交中击败了 70.49% 的用户
     * 通过测试用例： 26 / 26
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        ListNode dummy = new ListNode(-1);
        ListNode next = head;
        ListNode ptr1 = head;
        ListNode ptr2 = head.next;
        ListNode test2 = null;
        while (ptr2 != null) {
            // 头插法处理过半之前的节点
            ptr1 = next;
            next = ptr1.next;
            test2 = next;
            ptr1.next = dummy.next;
            dummy.next = ptr1;

            ptr2 = ptr2.next;
            if (ptr2 != null) {
                ptr2 = ptr2.next;
                test2 = next.next;
            }
        }

        ListNode test1 = dummy.next;

        while (test1 != null) {
            if (test1.val != test2.val) {
                return false;
            }
            test1 = test1.next;
            test2 = test2.next;
        }
        return true;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
