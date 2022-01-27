package leetcode.from5551to5600;

/**
 * @Author: ZeromaXHe
 * @Time: 2020/11/28 22:43
 * @Description: 5558. 合并两个链表
 * 给你两个链表 list1 和 list2 ，它们包含的元素分别为 n 个和 m 个。
 * <p>
 * 请你将 list1 中第 a 个节点到第 b 个节点删除，并将list2 接在被删除节点的位置。
 * <p>
 * 下图中蓝色边和节点展示了操作后的结果：
 * <p>
 * 请你返回结果链表的头指针。
 * <p>
 * 示例 1：
 * 输入：list1 = [0,1,2,3,4,5], a = 3, b = 4, list2 = [1000000,1000001,1000002]
 * 输出：[0,1,2,1000000,1000001,1000002,5]
 * 解释：我们删除 list1 中第三和第四个节点，并将 list2 接在该位置。上图中蓝色的边和节点为答案链表。
 * <p>
 * 示例 2：
 * 输入：list1 = [0,1,2,3,4,5,6], a = 2, b = 5, list2 = [1000000,1000001,1000002,1000003,1000004]
 * 输出：[0,1,1000000,1000001,1000002,1000003,1000004,6]
 * 解释：上图中蓝色的边和节点为答案链表。
 * <p>
 * 提示：
 * 3 <= list1.length <= 104
 * 1 <= a <= b < list1.length - 1
 * 1 <= list2.length <= 104
 * @Modified By: ZeromaXHe
 */
public class Solution5558 {
    /**
     * 60 / 60 个通过测试用例
     * 状态：通过
     * 执行用时: 1 ms
     * 内存消耗: 42.7 MB
     *
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = list1;
        ListNode index = list1;
        ListNode pre = dummyHead;
        for (int i = 0; i < a; i++) {
            // 这里写成pre = index可以吗？
            pre = pre.next;
            index = index.next;
        }
        pre.next = list2;

        ListNode end = list2;
        while (end.next != null) {
            end = end.next;
        }
        for (int i = a; i <= b; i++) {
            index = index.next;
        }
        end.next = index;

        return dummyHead.next;
    }
}

class ListNode {
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
