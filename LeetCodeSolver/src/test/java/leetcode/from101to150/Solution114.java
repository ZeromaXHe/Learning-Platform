package leetcode.from101to150;

/**
 * @Author: zhuxi
 * @Time: 2022/1/19 13:52
 * @Description: 114. 二叉树展开为链表 | 难度：中等 | 标签：栈、树、深度优先搜索、链表、二叉树
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * <p>
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 * <p>
 * 示例 1：
 * 输入：root = [1,2,5,3,4,null,6]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]
 * <p>
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：root = [0]
 * 输出：[0]
 * <p>
 * 提示：
 * 树中结点数在范围 [0, 2000] 内
 * -100 <= Node.val <= 100
 * <p>
 * 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution114 {
    /**
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
     */
    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 37.9 MB
     * , 在所有 Java 提交中击败了 24.44% 的用户
     * 通过测试用例： 225 / 225
     * @param root
     */
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flattenReturnRightest(root);
    }

    public TreeNode flattenReturnRightest(TreeNode root) {
        if (root.left == null) {
            return root.right == null ? root : flattenReturnRightest(root.right);
        }
        if (root.right == null) {
            root.right = root.left;
            root.left = null;
            return flattenReturnRightest(root.right);
        } else {
            TreeNode leftRightest = flattenReturnRightest(root.left);
            TreeNode rightRightest = flattenReturnRightest(root.right);
            leftRightest.right = root.right;
            root.right = root.left;
            root.left = null;
            return rightRightest;
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
