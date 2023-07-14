package leetcode.from951to1000;

/**
 * @author zhuxi
 * @apiNote 979. 在二叉树中分配硬币 | 难度：中等 | 标签：树、深度优先搜索、二叉树
 * 给定一个有 N 个结点的二叉树的根结点 root，树中的每个结点上都对应有 node.val 枚硬币，并且总共有 N 枚硬币。
 * <p>
 * 在一次移动中，我们可以选择两个相邻的结点，然后将一枚硬币从其中一个结点移动到另一个结点。(移动可以是从父结点到子结点，或者从子结点移动到父结点。)。
 * <p>
 * 返回使每个结点上只有一枚硬币所需的移动次数。
 * <p>
 * 示例 1：
 * 输入：[3,0,0]
 * 输出：2
 * 解释：从树的根结点开始，我们将一枚硬币移到它的左子结点上，一枚硬币移到它的右子结点上。
 * <p>
 * 示例 2：
 * 输入：[0,3,0]
 * 输出：3
 * 解释：从根结点的左子结点开始，我们将两枚硬币移到根结点上 [移动两次]。然后，我们把一枚硬币从根结点移到右子结点上。
 * <p>
 * 示例 3：
 * 输入：[1,0,2]
 * 输出：2
 * <p>
 * 示例 4：
 * 输入：[1,0,0,null,3]
 * 输出：4
 * <p>
 * 提示：
 * 1<= N <= 100
 * 0 <= node.val <= N
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/distribute-coins-in-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/7/14 9:43
 */
public class Solution979 {
    private int result = 0;

    /**
     * 参考题解思路做的，自己没想到
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了 59.77% 的用户
     * 通过测试用例：148 / 148
     *
     * @param root
     * @return
     */
    public int distributeCoins(TreeNode root) {
        result = 0;
        countCoinsAndNodesDiff(root);
        return result;
    }

    private int countCoinsAndNodesDiff(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int count = root.val - 1 + countCoinsAndNodesDiff(root.left) + countCoinsAndNodesDiff(root.right);
        result += Math.abs(count);
        return count;
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
