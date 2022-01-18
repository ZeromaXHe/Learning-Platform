package leetcode.from101to200;

/**
 * @Author: zhuxi
 * @Time: 2022/1/18 16:36
 * @Description: 110. 平衡二叉树 | 难度：简单 | 标签：树、深度优先搜素、二叉树
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * <p>
 * 本题中，一棵高度平衡二叉树定义为：
 * <p>
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 * <p>
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：root = [1,2,2,3,3,null,null,4,4]
 * 输出：false
 * <p>
 * 示例 3：
 * 输入：root = []
 * 输出：true
 * <p>
 * 提示：
 * 树中的节点数在范围 [0, 5000] 内
 * -104 <= Node.val <= 104
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/balanced-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution110 {
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
     * <p>
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.7 MB , 在所有 Java 提交中击败了 8.53% 的用户
     * 通过测试用例： 228 / 228
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        return isBalancedReturnDepth(root) >= 0;
    }

    private int isBalancedReturnDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return isBalancedReturnDepth(root.left, root.right);
    }

    private int isBalancedReturnDepth(TreeNode left, TreeNode right) {
        int leftDepth = isBalancedReturnDepth(left);
        if (leftDepth == -1) {
            return -1;
        }
        int rightDepth = isBalancedReturnDepth(right);
        if (rightDepth == -1) {
            return -1;
        }
        return Math.abs(leftDepth - rightDepth) <= 1 ? Math.max(leftDepth, rightDepth) + 1 : -1;
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
