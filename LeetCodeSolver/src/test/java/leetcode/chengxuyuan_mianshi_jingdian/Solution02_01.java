package leetcode.chengxuyuan_mianshi_jingdian;

/**
 * @Author: zhuxi
 * @Time: 2022/4/29 13:45
 * @Description: 面试题 02.01. 移除重复节点 | 难度：简单 | 标签：哈希表、链表、双指针
 * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
 * <p>
 * 示例1:
 * 输入：[1, 2, 3, 3, 2, 1]
 * 输出：[1, 2, 3]
 * <p>
 * 示例2:
 * 输入：[1, 1, 1, 1, 2]
 * 输出：[1, 2]
 * <p>
 * 提示：
 * 链表长度在[0, 20000]范围内。
 * 链表元素在[0, 20000]范围内。
 * <p>
 * 进阶：
 * 如果不得使用临时缓冲区，该怎么解决？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicate-node-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution02_01 {
    /**
     * Definition for singly-linked list.
     * | public class ListNode {
     * |     int val;
     * |     ListNode next;
     * |     ListNode(int x) { val = x; }
     * | }
     * <p>
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 44.4 MB , 在所有 Java 提交中击败了 5.06% 的用户
     * 通过测试用例： 35 / 35
     */
    public ListNode removeDuplicateNodes(ListNode head) {
        boolean[] visited = new boolean[20001];
        if (head == null) {
            return head;
        }
        visited[head.val] = true;
        ListNode pre = head;
        ListNode ptr = head.next;
        while (ptr != null) {
            if (visited[ptr.val]) {
                pre.next = ptr.next;
            } else {
                pre = ptr;
                visited[ptr.val] = true;
            }
            ptr = ptr.next;
        }
        return head;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
