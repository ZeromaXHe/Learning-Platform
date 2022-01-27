package leetcode.from101to150;

/**
 * @Author: zhuxi
 * @Time: 2021/1/22 10:26
 * @Description: 124.二叉树的最大路径和 | 难度：困难 | 标签：树、深度优先搜索、递归
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。该路径 至少包含一个 节点，且不一定经过根节点。
 * <p>
 * 路径和 是路径中各节点值的总和。
 * <p>
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 * <p>
 * 示例 1：
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
 * <p>
 * 示例 2：
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 * <p>
 * 提示：
 * 树中节点数目范围是 [1, 3 * 104]
 * -1000 <= Node.val <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-maximum-path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution124 {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     * 执行用时： 231 ms , 在所有 Java 提交中击败了 5.54% 的用户
     * 内存消耗： 40.3 MB , 在所有 Java 提交中击败了 55.86% 的用户
     * 慢，待优化
     * 需要想办法合并两次迭代（maxPathSum 和 singleMaxPathSum）。
     */
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return root.val;
        } else if (root.left == null) {
            return Math.max(
                    maxPathSum(root.right),
                    Math.max(root.val, root.val > 0 ? (root.val + singleMaxPathSum(root.right)) : singleMaxPathSum(root.right))
            );
        } else if (root.right == null) {
            return Math.max(
                    maxPathSum(root.left),
                    Math.max(root.val, root.val > 0 ? (root.val + singleMaxPathSum(root.left)) : singleMaxPathSum(root.left))
            );
        } else {
            int max = Math.max(maxPathSum(root.left), maxPathSum(root.right));
            int left = singleMaxPathSum(root.left);
            int right = singleMaxPathSum(root.right);
            max = Math.max(max, root.val + (Math.max(left, 0)) + (Math.max(right, 0)));
            if (root.val < 0) {
                max = Math.max(max, left);
                max = Math.max(max, right);
            }
            return max;
        }
    }

    private int singleMaxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return root.val;
        } else if (root.left == null) {
            return Math.max(root.val, root.val + singleMaxPathSum(root.right));
        } else if (root.right == null) {
            return Math.max(root.val, root.val + singleMaxPathSum(root.left));
        } else {
            int left = singleMaxPathSum(root.left);
            int right = singleMaxPathSum(root.right);
            return root.val + Math.max(Math.max(left, right), 0);
        }
    }

    class TreeNode {
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
