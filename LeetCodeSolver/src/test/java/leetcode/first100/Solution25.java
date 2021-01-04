package leetcode.first100;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/31 17:05
 * @Description: 25. K 个一组翻转链表 | 难度：困难 | 标签：链表
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。
 * <p>
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *  
 * 示例：
 * 给你这个链表：1->2->3->4->5
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 * <p>
 * 说明：
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution25 {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 38.87% 的用户
     * 内存消耗： 38.7 MB , 在所有 Java 提交中击败了 58.35% 的用户
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        if (k == 1) {
            return head;
        }
        ListNode dummy = new ListNode();
        ListNode pre = dummy;
        ListNode kthPtr = head;
        ListNode firstOfKth = kthPtr;
        ListNode index = head;
        ListNode post;
        ListNode postPost;

        while (kthPtr != null) {
            int count = 0;
            while (count < k) {
                if (kthPtr.next == null && count != k - 1) {
                    // 如果剩余节点不足k个
                    pre.next = firstOfKth;
                    return dummy.next;
                }
                kthPtr = kthPtr.next;
                count++;
            }
            post = index.next;
            postPost = post.next;
            index.next = null;
            while (postPost != kthPtr) {
                post.next = index;
                index = post;
                post = postPost;
                postPost = post.next;
            }
            post.next = index;
            pre.next = post;
            index = postPost;
            pre = firstOfKth;
            firstOfKth = kthPtr;
        }
        return dummy.next;
    }

    /**
     * 解题思路不对，最后多余不足k的节点不需要颠倒顺序。
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup_wrongAnswer(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        if (k == 1) {
            return head;
        }
        ListNode index = head;
        ListNode post = head.next;
        if (post == null) {
            return head;
        }
        index.next = null;
        ListNode postPost = post.next;
        post.next = null;
        ListNode dummy = new ListNode();
        int count = 1;
        ListNode pre = dummy;
        loop:
        while (postPost != null) {
            ListNode tail = index;
            while (count < k) {
                post.next = index;
                index = post;
                post = postPost;
                postPost = postPost.next;
                post.next = null;
                count++;
                if (postPost == null) {
                    break loop;
                }
            }
            pre.next = index;
            index = post;
            post = postPost;
            postPost = postPost.next;
            post.next = null;
            count = 1;
            pre = tail;
        }
        if (count == k) {
            pre.next = index;
            index.next = post;
        } else {
            pre.next = post;
            post.next = index;
        }
        return dummy.next;
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
