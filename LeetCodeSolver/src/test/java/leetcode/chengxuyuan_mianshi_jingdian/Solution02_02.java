package leetcode.chengxuyuan_mianshi_jingdian;

/**
 * @Author: zhuxi
 * @Time: 2022/4/29 14:16
 * @Description: 面试题 02.02. 返回倒数第 k 个节点 | 难度：简单 | 标签：链表、双指针
 * 实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。
 * <p>
 * 注意：本题相对原题稍作改动
 * <p>
 * 示例：
 * 输入： 1->2->3->4->5 和 k = 2
 * 输出： 4
 * <p>
 * 说明：
 * 给定的 k 保证是有效的。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-node-from-end-of-list-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution02_02 {
    /**
     * Definition for singly-linked list.
     * | public class ListNode {
     * |     int val;
     * |     ListNode next;
     * |     ListNode(int x) { val = x; }
     * | }
     * <p>
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 39.1 MB , 在所有 Java 提交中击败了 53.29% 的用户
     * 通过测试用例： 208 / 208
     */
    public int kthToLast(ListNode head, int k) {
        ListNode ptr = head;
        ListNode nextK = head;
        for (int i = 0; i < k; i++) {
            nextK = nextK.next;
        }
        while (nextK != null) {
            nextK = nextK.next;
            ptr = ptr.next;
        }
        return ptr.val;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
