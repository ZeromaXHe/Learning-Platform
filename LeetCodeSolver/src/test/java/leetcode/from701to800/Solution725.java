package leetcode.from701to800;

/**
 * @Author: zhuxi
 * @Time: 2021/9/22 9:56
 * @Description: 725. 分隔链表 | 难度：中等 | 标签：链表
 * 给你一个头结点为 head 的单链表和一个整数 k ，请你设计一个算法将链表分隔为 k 个连续的部分。
 * <p>
 * 每部分的长度应该尽可能的相等：任意两部分的长度差距不能超过 1 。这可能会导致有些部分为 null 。
 * <p>
 * 这 k 个部分应该按照在链表中出现的顺序排列，并且排在前面的部分的长度应该大于或等于排在后面的长度。
 * <p>
 * 返回一个由上述 k 部分组成的数组。
 * <p>
 * 示例 1：
 * 输入：head = [1,2,3], k = 5
 * 输出：[[1],[2],[3],[],[]]
 * 解释：
 * 第一个元素 output[0] 为 output[0].val = 1 ，output[0].next = null 。
 * 最后一个元素 output[4] 为 null ，但它作为 ListNode 的字符串表示是 [] 。
 * <p>
 * 示例 2：
 * 输入：head = [1,2,3,4,5,6,7,8,9,10], k = 3
 * 输出：[[1,2,3,4],[5,6,7],[8,9,10]]
 * 解释：
 * 输入被分成了几个连续的部分，并且每部分的长度相差不超过 1 。前面部分的长度大于等于后面部分的长度。
 * <p>
 * 提示：
 * 链表中节点的数目在范围 [0, 1000]
 * 0 <= Node.val <= 1000
 * 1 <= k <= 50
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/split-linked-list-in-parts
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution725 {
    /**
     * Definition for singly-linked list.
     * |public class ListNode {
     * |    int val;
     * |    ListNode next;
     * |    ListNode() {}
     * |    ListNode(int val) { this.val = val; }
     * |    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * |}
     * <p>
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.6 MB , 在所有 Java 提交中击败了 38.03% 的用户
     * 通过测试用例： 43 / 43
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode ptr = head;
        int count = 0;
        while (ptr != null) {
            ptr = ptr.next;
            count++;
        }
        ptr = head;
        int more = count % k;
        int div = count / k;
        ListNode pre = null;
        ListNode[] result = new ListNode[k];
        result[0] = head;
        for (int i = 0; i < more; i++) {
            for (int j = 0; j < div + 1; j++) {
                pre = ptr;
                ptr = ptr.next;
            }
            result[i + 1] = ptr;
            pre.next = null;
        }
        if (count < k) {
            return result;
        }
        for (int i = more; i < k - 1; i++) {
            for (int j = 0; j < div; j++) {
                pre = ptr;
                ptr = ptr.next;
            }
            result[i + 1] = ptr;
            pre.next = null;
        }
        return result;
    }


    static class ListNode {
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
