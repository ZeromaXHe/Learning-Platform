package leetcode.from101to150;

/**
 * @Author: zhuxi
 * @Time: 2022/1/18 16:27
 * @Description: 109. 有序链表转换二叉搜索树 | 难度：中等 | 标签：树、二叉搜索树、数组、分治、二叉树
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 * <p>
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * <p>
 * 示例:
 * <p>
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 * <p>
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 * <p>
 * |      0
 * |     / \
 * |   -3   9
 * |   /   /
 * | -10  5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution109 {
    /**
     * Definition for singly-linked list.
     * | public class ListNode {
     * |     int val;
     * |     ListNode next;
     * |     ListNode() {}
     * |     ListNode(int val) { this.val = val; }
     * |     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * | }
     * <p>
     * Definition for a binary tree node.
     * | public class TreeNode {
     * |     int val;
     * |     TreeNode left;
     * |     TreeNode right;
     * |     TreeNode() {}
     * |     TreeNode(int val) { this.val = val; }
     * |     TreeNode(int val, TreeNode left, TreeNode right) {
     * |         this.val = val;
     * |         this.left = left;
     * |         this.right = right;
     * |     }
     * | }
     * <p>
     * 参考第108题，先把链表转成数组，然后复用108题的代码
     * <p>
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 39.7 MB , 在所有 Java 提交中击败了 33.53% 的用户
     * 通过测试用例： 32 / 32
     *
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        int i = 0;
        ListNode ptr = head;
        while (ptr != null) {
            i++;
            ptr = ptr.next;
        }
        if (i == 0) {
            return null;
        }
        int[] nums = new int[i];
        ptr = head;
        i = 0;
        while (ptr != null) {
            nums[i] = ptr.val;
            ptr = ptr.next;
            i++;
        }
        return sortedArrayToBST(nums, 0, nums.length);
    }

    private TreeNode sortedArrayToBST(int[] nums, int from, int to) {
        int mid = (from + to - 1) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        if (mid > from) {
            root.left = sortedArrayToBST(nums, from, mid);
        }
        if (mid < to - 1) {
            root.right = sortedArrayToBST(nums, mid + 1, to);
        }
        return root;
    }

    public class ListNode {
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

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
