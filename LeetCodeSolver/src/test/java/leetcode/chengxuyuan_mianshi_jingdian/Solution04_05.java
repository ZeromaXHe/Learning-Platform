package leetcode.chengxuyuan_mianshi_jingdian;

/**
 * @Author: zhuxi
 * @Time: 2022/5/6 11:03
 * @Description: 面试题 04.05. 合法二叉搜索树 | 难度：中等 | 标签：树、深度优先搜索、二叉搜索树、二叉树
 * 实现一个函数，检查一棵二叉树是否为二叉搜索树。
 * <p>
 * 示例 1:
 * 输入:
 * |    2
 * |   / \
 * |  1   3
 * 输出: true
 * <p>
 * 示例 2:
 * 输入:
 * |    5
 * |   / \
 * |  1   4
 * |     / \
 * |    3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/legal-binary-search-tree-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution04_05 {
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
     * 内存消耗： 41 MB , 在所有 Java 提交中击败了 31.58% 的用户
     * 通过测试用例： 75 / 75
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode root, long min, long max) {
        return root == null || (
                root.val < max && root.val > min
                        && (root.left == null || isValidBST(root.left, min, root.val))
                        && (root.right == null || isValidBST(root.right, root.val, max)));
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
