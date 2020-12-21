package leetcode.jianzhi_offer;

import java.util.LinkedList;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/21 14:00
 * @Description: 剑指 Offer 06. 从尾到头打印链表 | 难度：简单 | 标签：链表
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 * <p>
 * 示例 1：
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 *  
 * 限制：
 * 0 <= 链表长度 <= 10000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class SolutionOffer06 {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.6 MB , 在所有 Java 提交中击败了 98.28% 的用户
     *
     * @param head
     * @return
     */
    public int[] reversePrint(ListNode head) {
        ListNode index = head;
        int count = 0;
        while (index != null) {
            count++;
            index = index.next;
        }
        int[] result = new int[count];
        index = head;
        while (index != null) {
            result[--count] = index.val;
            index = index.next;
        }
        return result;
    }

    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 38.04% 的用户
     * 内存消耗： 39 MB , 在所有 Java 提交中击败了 80.98% 的用户
     *
     * @param head
     * @return
     */
    public int[] reversePrint_linkedList(ListNode head) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        ListNode index = head;
        while (index != null) {
            linkedList.addFirst(index.val);
            index = index.next;
        }
        int[] result = new int[linkedList.size()];
        int count = 0;
        for (int i : linkedList) {
            result[count++] = i;
        }
        return result;
    }
}
