package leetcode.chengxuyuan_mianshi_jingdian;

/**
 * @Author: zhuxi
 * @Time: 2022/5/5 10:57
 * @Description: 面试题 02.07. 链表相交 | 难度：简单 | 标签：哈希表、链表、双指针
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
 * <p>
 * 图示两个链表在节点 c1 开始相交：
 * <p>
 * 题目数据 保证 整个链式结构中不存在环。
 * <p>
 * 注意，函数返回结果后，链表必须 保持其原始结构 。
 * <p>
 * 示例 1：
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Intersected at '8'
 * 解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。
 * 从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。
 * 在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 * <p>
 * 示例 2：
 * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * 输出：Intersected at '2'
 * 解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。
 * 从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。
 * 在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
 * <p>
 * 示例 3：
 * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * 输出：null
 * 解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。
 * 由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
 * 这两个链表不相交，因此返回 null 。
 * <p>
 * 提示：
 * listA 中节点数目为 m
 * listB 中节点数目为 n
 * 0 <= m, n <= 3 * 104
 * 1 <= Node.val <= 105
 * 0 <= skipA <= m
 * 0 <= skipB <= n
 * 如果 listA 和 listB 没有交点，intersectVal 为 0
 * 如果 listA 和 listB 有交点，intersectVal == listA[skipA + 1] == listB[skipB + 1]
 * <p>
 * 进阶：你能否设计一个时间复杂度 O(n) 、仅用 O(1) 内存的解决方案？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-linked-lists-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution02_07 {
    /**
     * Definition for singly-linked list.
     * | public class ListNode {
     * |     int val;
     * |     ListNode next;
     * |     ListNode(int x) {
     * |         val = x;
     * |         next = null;
     * |     }
     * | }
     * <p>
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 97.42% 的用户
     * 内存消耗： 44.5 MB , 在所有 Java 提交中击败了 10.04% 的用户
     * 通过测试用例： 45 / 45
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode ptr1 = headA;
        ListNode ptr2 = headB;
        while (ptr1 != ptr2) {
            ptr1 = ptr1 == null ? headB : ptr1.next;
            ptr2 = ptr2 == null ? headA : ptr2.next;
        }
        return ptr1;
    }

    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 97.42% 的用户
     * 内存消耗： 44.2 MB , 在所有 Java 提交中击败了 49.50% 的用户
     * 通过测试用例： 45 / 45
     * <p>
     * 其实不需要引入 finish1 和 finish2，因为这样最后会 null == null，也是正常退出 while 循环。
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode_unnecessaryBoolean(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode ptr1 = headA;
        ListNode ptr2 = headB;
        boolean finish1 = false;
        boolean finish2 = false;
        while (ptr1 != ptr2) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
            if (ptr1 == null) {
                if (finish1) {
                    return null;
                }
                finish1 = true;
                ptr1 = headB;
            }
            if (ptr2 == null) {
                if (finish2) {
                    return null;
                }
                finish2 = true;
                ptr2 = headA;
            }
        }
        return ptr1;
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
