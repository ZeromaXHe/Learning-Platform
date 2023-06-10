package leetcode.jianzhi_offer;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer 55 - II. 平衡二叉树 | 难度：简单 | 标签：树、深度优先搜索、二叉树
 * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
 * <p>
 * 示例 1:
 * 给定二叉树 [3,9,20,null,null,15,7]
 * <p>
 * |    3
 * |   / \
 * |  9  20
 * |    /  \
 * |   15   7
 * 返回 true 。
 * <p>
 * 示例 2:
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 * <p>
 * |       1
 * |      / \
 * |     2   2
 * |    / \
 * |   3   3
 * |  / \
 * | 4   4
 * 返回 false 。
 * <p>
 * 限制：
 * 0 <= 树的结点个数 <= 10000
 * 注意：本题与主站 110 题相同：https://leetcode-cn.com/problems/balanced-binary-tree/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/ping-heng-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/10 16:29
 */
public class SolutionOffer55_II {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：42 MB, 在所有 Java 提交中击败了 10.77% 的用户
     * 通过测试用例：227 / 227
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        return notBalancedOrDepth(root) >= 0;
    }

    public int notBalancedOrDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftRes = notBalancedOrDepth(root.left);
        if (leftRes == -1) {
            return -1;
        }
        int rightRes = notBalancedOrDepth(root.right);
        if (rightRes == -1 || Math.abs(leftRes - rightRes) > 1) {
            return -1;
        }
        return 1 + Math.max(leftRes, rightRes);
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
