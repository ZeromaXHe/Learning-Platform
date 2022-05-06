package leetcode.chengxuyuan_mianshi_jingdian;

import java.util.LinkedList;

/**
 * @Author: zhuxi
 * @Time: 2022/5/6 9:55
 * @Description: 面试题 04.03. 特定深度节点链表 | 难度：中等 | 标签：树、广度优先搜索、链表、二叉树
 * 给定一棵二叉树，设计一个算法，创建含有某一深度上所有节点的链表（比如，若一棵树的深度为 D，则会创建出 D 个链表）。返回一个包含所有深度的链表的数组。
 * <p>
 * 示例：
 * 输入：[1,2,3,4,5,null,7,8]
 * <p>
 * |        1
 * |       /  \
 * |      2    3
 * |     / \    \
 * |    4   5    7
 * |   /
 * |  8
 * <p>
 * 输出：[[1],[2,3],[4,5,7],[8]]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/list-of-depth-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution04_03 {
    /**
     * Definition for a binary tree node.
     * | public class TreeNode {
     * |     int val;
     * |     TreeNode left;
     * |     TreeNode right;
     * |     TreeNode(int x) { val = x; }
     * | }
     * <p>
     * Definition for singly-linked list.
     * | public class ListNode {
     * |     int val;
     * |     ListNode next;
     * |     ListNode(int x) { val = x; }
     * | }
     * <p>
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 50.24% 的用户
     * 内存消耗： 39.8 MB , 在所有 Java 提交中击败了 28.92% 的用户
     * 通过测试用例： 49 / 49
     */
    public ListNode[] listOfDepth(TreeNode tree) {
        if (tree == null) {
            return null;
        }
        LinkedList<ListNode> list = new LinkedList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(tree);
        int size = 1;
        ListNode dummy = new ListNode(-1);
        while (!queue.isEmpty()) {
            ListNode ptr = dummy;
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                ptr.next = new ListNode(poll.val);
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
                ptr = ptr.next;
            }
            list.add(dummy.next);
            dummy.next = null;
            size = queue.size();
        }
        ListNode[] result = new ListNode[list.size()];
        return list.toArray(result);
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
