package leetcode.first100;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/31 16:51
 * @Description: 23.合并K个升序链表 | 难度：困难 | 标签：堆、链表、分治算法
 * @Modified By: zhuxiaohe
 */
public class Solution23 {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     * 执行用时： 8 ms , 在所有 Java 提交中击败了 39.25% 的用户
     * 内存消耗： 40.4 MB , 在所有 Java 提交中击败了 37.59% 的用户
     * 优化的余地就在于每个ListNode[]中对应的链表都是有序的，所以直接用lists.length大小的堆就可以了，每次poll就把后面一个节点offer进去。
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode node : lists) {
            ListNode node1 = node;
            while (node1 != null) {
                heap.offer(node1);
                node1 = node1.next;
            }
        }
        ListNode dummy = new ListNode();
        ListNode index = dummy;
        while (!heap.isEmpty()) {
            index.next = heap.poll();
            index = index.next;
            index.next = null;
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
