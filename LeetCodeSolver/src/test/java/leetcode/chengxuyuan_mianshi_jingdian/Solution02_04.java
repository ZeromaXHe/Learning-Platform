package leetcode.chengxuyuan_mianshi_jingdian;

/**
 * @Author: zhuxi
 * @Time: 2022/4/29 14:32
 * @Description: 面试题 02.04. 分割链表 | 难度：中等 | 标签：
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * <p>
 * 你不需要 保留 每个分区中各节点的初始相对位置。
 * <p>
 * 示例 1：
 * 输入：head = [1,4,3,2,5,2], x = 3
 * 输出：[1,2,2,4,3,5]
 * <p>
 * 示例 2：
 * 输入：head = [2,1], x = 2
 * 输出：[1,2]
 * <p>
 * 提示：
 * 链表中节点的数目在范围 [0, 200] 内
 * -100 <= Node.val <= 100
 * -200 <= x <= 200
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-list-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution02_04 {
    /**
     * Definition for singly-linked list.
     * | public class ListNode {
     * |     int val;
     * |     ListNode next;
     * |     ListNode(int x) { val = x; }
     * | }
     * <p>
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 40.9 MB , 在所有 Java 提交中击败了 35.08% 的用户
     * 通过测试用例： 166 / 166
     */
    public ListNode partition(ListNode head, int x) {
        ListNode dummyBig = new ListNode(-101);
        ListNode dummySmall = new ListNode(-101);
        dummySmall.next = head;
        ListNode pre = dummySmall;
        ListNode ptr = head;
        while (ptr != null) {
            if (ptr.val < x) {
                ptr = ptr.next;
                pre = pre.next;
            } else {
                pre.next = ptr.next;
                ptr.next = dummyBig.next;
                dummyBig.next = ptr;
                ptr = pre.next;
            }
        }
        pre.next = dummyBig.next;
        return dummySmall.next;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
