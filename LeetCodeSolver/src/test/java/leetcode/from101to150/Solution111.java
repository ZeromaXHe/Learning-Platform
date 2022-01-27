package leetcode.from101to150;

/**
 * @Author: zhuxi
 * @Time: 2022/1/18 16:50
 * @Description: 111. 二叉树的最小深度 | 难度：简单 | 标签：树、深度优先搜素、广度优先搜索、二叉树
 * 给定一个二叉树，找出其最小深度。
 * <p>
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * <p>
 * 说明：叶子节点是指没有子节点的节点。
 * <p>
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：2
 * <p>
 * 示例 2：
 * 输入：root = [2,null,3,null,4,null,5,null,6]
 * 输出：5
 * <p>
 * 提示：
 * 树中节点数的范围在 [0, 105] 内
 * -1000 <= Node.val <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution111 {
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
     * 执行用时： 5 ms , 在所有 Java 提交中击败了 68.13% 的用户
     * 内存消耗： 58.5 MB , 在所有 Java 提交中击败了 62.00% 的用户
     * 通过测试用例： 52 / 52
     * <p>
     * 看其他人的答案，使用层次遍历会更快一点
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int min = Integer.MAX_VALUE;
        if (root.left != null) {
            min = Math.min(min, minDepth(root.left) + 1);
        }
        if (root.right != null) {
            min = Math.min(min, minDepth(root.right) + 1);
        }
        return min;
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
