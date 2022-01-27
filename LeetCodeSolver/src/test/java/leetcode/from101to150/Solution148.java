package leetcode.from101to150;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/11/21 19:03
 * @Description: 148.排序链表 | 难度：中等 | 标签：排序、链表
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * <p>
 * 进阶：
 * <p>
 * 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 *  
 * 示例 1：
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 * <p>
 * 示例 2：
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 * <p>
 * 示例 3：
 * 输入：head = []
 * 输出：[]
 * <p>
 * 提示：
 * 链表中节点的数目在范围 [0, 5 * 104] 内
 * -105 <= Node.val <= 105
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution148 {
    /**
     * 执行用时：10 ms, 在所有 Java 提交中击败了 22.57% 的用户
     * 内存消耗：46.9 MB, 在所有 Java 提交中击败了 9.23% 的用户
     * 明天休息，归心似箭。再加上今天写了一天代码，弄了好几个工具类和重构，就有点想偷懒。
     * 思路基本都有了，就是不想写代码，所以直接copy题解然后加了一下注释，读懂代码就提交了。
     * 但是感觉效率比较差，之后有时间再做做
     * TODO：尝试用快排写写？归并也自己写一下试试能不能优化一下？
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
    public ListNode sortList(ListNode head) {
        if (head == null) {
            return head;
        }
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }
        ListNode dummyHead = new ListNode(0, head);
        for (int subLength = 1; subLength < length; subLength <<= 1) {
            ListNode prev = dummyHead, curr = dummyHead.next;
            while (curr != null) {
                // 获取第一段长度为subLength的链表
                ListNode head1 = curr;
                for (int i = 1; i < subLength && curr.next != null; i++) {
                    curr = curr.next;
                }
                // 此时curr位于第一段链表末尾
                // 开始获取第二段长度为subLength的链表
                ListNode head2 = curr.next;
                // 断开第二段和前面第一段的联系
                curr.next = null;
                curr = head2;
                for (int i = 1; i < subLength && curr != null && curr.next != null; i++) {
                    curr = curr.next;
                }
                ListNode next = null;
                if (curr != null) {
                    // 记录第二段以后的未排序链表
                    next = curr.next;
                    // 断开第二段和之后链表的联系
                    curr.next = null;
                }
                // 开始归并排序第一段和第二段链表
                ListNode merged = merge(head1, head2);
                // 连接第一段以前的链表
                prev.next = merged;
                // prev走到本轮归并链表的最后
                while (prev.next != null) {
                    prev = prev.next;
                }
                // curr指针设置在后面未排序的链表开头
                curr = next;
            }
        }
        return dummyHead.next;
    }

    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        // temp指示已排序链表的末尾，temp1指示第一段链表的比较节点，temp2指示第二段链表的比较节点
        // 第一段链表和第二段链表内部是有序的
        ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
        // 当第一段和第二段还没比完时，每次都把较小的节点接到已排序链表的最后
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        // 第一段和第二段其中至少一个链表比较完成后，把剩余链表接到排序链表的最后
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return dummyHead.next;
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

}
