package leetcode.from101to200;

/**
 * @Author: zhuxi
 * @Time: 2022/1/17 16:51
 * @Description: 101. 对称二叉树 | 难度：简单 | 标签：树、深度优先搜索、广度优先搜索、二叉树
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 * <p>
 * 示例 1：
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 * <p>
 * 提示：
 * 树中节点数目在范围 [1, 1000] 内
 * -100 <= Node.val <= 100
 * <p>
 * 进阶：你可以运用递归和迭代两种方法解决这个问题吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/symmetric-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution101 {
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
     * 内存消耗： 36.2 MB , 在所有 Java 提交中击败了 94.35% 的用户
     * 通过测试用例： 197 / 197
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root.left == null && root.right == null) {
            return true;
        }
        if (root.left == null || root.right == null) {
            return false;
        }
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left.val != right.val) {
            return false;
        }
        if (left.left != null) {
            if (right.right == null || !isSymmetric(left.left, right.right)) {
                return false;
            }
        } else if (right.right != null) {
            return false;
        }

        if (left.right != null) {
            if (right.left == null || !isSymmetric(left.right, right.left)) {
                return false;
            }
        } else if (right.left != null) {
            return false;
        }

        return true;
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
