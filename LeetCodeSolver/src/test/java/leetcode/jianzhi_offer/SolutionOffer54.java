package leetcode.jianzhi_offer;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer 54. 二叉搜索树的第k大节点 | 难度：简单 | 标签：树、深度优先搜索、二叉搜索树、二叉树
 * 给定一棵二叉搜索树，请找出其中第 k 大的节点的值。
 * <p>
 * 示例 1:
 * 输入: root = [3,1,4,null,2], k = 1
 * |   3
 * |  / \
 * | 1   4
 * |  \
 * |   2
 * 输出: 4
 * 示例 2:
 * <p>
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 * |       5
 * |      / \
 * |     3   6
 * |    / \
 * |   2   4
 * |  /
 * | 1
 * 输出: 4
 * <p>
 * 限制：
 * 1 ≤ k ≤ 二叉搜索树元素个数
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/10 16:08
 */
public class SolutionOffer54 {
    private int count = 0;

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：43 MB, 在所有 Java 提交中击败了 9.89% 的用户
     * 通过测试用例：91 / 91
     *
     * @param root
     * @param k
     * @return
     */
    public int kthLargest(TreeNode root, int k) {
        count = k;
        return inOrder(root).val;
    }

    private TreeNode inOrder(TreeNode root) {
        if (root.right != null) {
            TreeNode node = inOrder(root.right);
            if (node != null) {
                return node;
            }
        }
        count--;
        if (count == 0) {
            return root;
        }
        if (root.left != null) {
            TreeNode node = inOrder(root.left);
            if (node != null) {
                return node;
            }
        }
        return null;
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
