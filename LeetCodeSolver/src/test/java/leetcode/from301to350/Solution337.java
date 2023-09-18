package leetcode.from301to350;

/**
 * @author zhuxi
 * @apiNote 337. 打家劫舍 III | 难度：中等 | 标签：树、深度优先搜索、动态规划、二叉树
 * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
 * <p>
 * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
 * <p>
 * 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
 * <p>
 * 示例 1:
 * 输入: root = [3,2,3,null,3,null,1]
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7
 * <p>
 * 示例 2:
 * 输入: root = [3,4,5,1,3,null,1]
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 4 + 5 = 9
 * <p>
 * 提示：
 * 树的节点数在 [1, 104] 范围内
 * 0 <= Node.val <= 104
 * @implNote
 * @since 2023/9/18 10:01
 */
public class Solution337 {
    /**
     * 时间 0 ms
     * 击败 100.00% 使用 Java 的用户
     * 内存 41.63 MB
     * 击败 63.89% 使用 Java 的用户
     *
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        int[] max = robOrNotMax(root);
        return Math.max(max[0], max[1]);
    }

    private int[] robOrNotMax(TreeNode root) {
        int robMax = root.val;
        int notMax = 0;
        if (root.left != null) {
            int[] leftMax = robOrNotMax(root.left);
            robMax += leftMax[1];
            notMax += Math.max(leftMax[0], leftMax[1]);
        }
        if (root.right != null) {
            int[] rightMax = robOrNotMax(root.right);
            robMax += rightMax[1];
            notMax += Math.max(rightMax[0], rightMax[1]);
        }
        return new int[]{robMax, notMax};
    }

    public class TreeNode {
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
