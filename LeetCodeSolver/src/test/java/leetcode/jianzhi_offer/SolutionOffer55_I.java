package leetcode.jianzhi_offer;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer 55 - I. 二叉树的深度 | 难度：简单 | 标签：树、深度优先搜索、广度优先搜索、二叉树
 * 输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。
 * <p>
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 * <p>
 * |    3
 * |   / \
 * |  9  20
 * |    /  \
 * |   15   7
 * 返回它的最大深度 3 。
 * <p>
 * 提示：
 * 节点总数 <= 10000
 * 注意：本题与主站 104 题相同：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/er-cha-shu-de-shen-du-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/10 16:19
 */
public class SolutionOffer55_I {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了 18.78% 的用户
     * 内存消耗：40.7 MB, 在所有 Java 提交中击败了 74.00% 的用户
     * 通过测试用例：39 / 39
     *
     * @param root
     * @return
     */
    public int maxDepth_levelOrder(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
            result++;
        }
        return result;
    }

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：40.1 MB, 在所有 Java 提交中击败了 98.18% 的用户
     * 通过测试用例：39 / 39
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
