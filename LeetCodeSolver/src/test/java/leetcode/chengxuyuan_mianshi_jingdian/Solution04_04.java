package leetcode.chengxuyuan_mianshi_jingdian;

/**
 * @Author: zhuxi
 * @Time: 2022/5/6 10:08
 * @Description: 面试题 04.04. 检查平衡性 | 难度：简单 | 标签：树、深度优先搜索、二叉树
 * 实现一个函数，检查二叉树是否平衡。在这个问题中，平衡树的定义如下：任意一个节点，其两棵子树的高度差不超过 1。
 * <p>
 * 示例 1:
 * 给定二叉树 [3,9,20,null,null,15,7]
 * |    3
 * |   / \
 * |  9  20
 * |    /  \
 * |   15   7
 * 返回 true 。
 * <p>
 * 示例 2:
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 * |       1
 * |      / \
 * |     2   2
 * |    / \
 * |   3   3
 * |  / \
 * | 4   4
 * 返回 false 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/check-balance-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution04_04 {
    /**
     * Definition for a binary tree node.
     * | public class TreeNode {
     * |     int val;
     * |     TreeNode left;
     * |     TreeNode right;
     * |     TreeNode(int x) { val = x; }
     * | }
     * <p>
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 41.1 MB , 在所有 Java 提交中击败了 31.77% 的用户
     * 通过测试用例： 227 / 227
     */
    public boolean isBalanced(TreeNode root) {
        return depthWhenBalanced(root) >= 0;
    }

    private int depthWhenBalanced(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int depthLeft = depthWhenBalanced(root.left);
        int depthRight = depthWhenBalanced(root.right);
        if (depthLeft < 0 || depthRight < 0 || Math.abs(depthLeft - depthRight) > 1) {
            return -1;
        }
        return Math.max(depthLeft, depthRight) + 1;
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
