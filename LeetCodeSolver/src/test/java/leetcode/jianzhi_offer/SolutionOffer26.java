package leetcode.jianzhi_offer;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer 26. 树的子结构 | 难度：中等 | 标签：树、深度优先搜索、二叉树
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 * <p>
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 * <p>
 * 例如:
 * 给定的树 A:
 * <p>
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * 给定的树 B：
 * <p>
 *    4 
 *   /
 *  1
 * 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
 * <p>
 * 示例 1：
 * 输入：A = [1,2,3], B = [3,1]
 * 输出：false
 * <p>
 * 示例 2：
 * 输入：A = [3,4,5,1,2], B = [4,1]
 * 输出：true
 * <p>
 * 限制：
 * 0 <= 节点个数 <= 10000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/shu-de-zi-jie-gou-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/8 11:49
 */
public class SolutionOffer26 {
    @Test
    public void isSubStructureTest() {
        TreeNode A = new TreeNode(1);
        A.left = new TreeNode(0);
        A.right = new TreeNode(1);
        A.left.left = new TreeNode(-4);
        A.left.right = new TreeNode(-3);
        TreeNode B = new TreeNode(1);
        B.left = new TreeNode(-4);
        Assert.assertFalse(isSubStructure(A, B));
    }

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：44.1 MB, 在所有 Java 提交中击败了 5.14% 的用户
     * 通过测试用例：48 / 48
     *
     * @param A
     * @param B
     * @return
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B == null) {
            return false;
        } else if (A == null) {
            return false;
        } else if (isSameB(A, B)) {
            return true;
        } else if (A.left != null && isSubStructure(A.left, B)) {
            return true;
        } else if (A.right != null && isSubStructure(A.right, B)) {
            return true;
        }
        return false;
    }

    private boolean isSameB(TreeNode A, TreeNode B) {
        if (A.val != B.val) {
            return false;
        } else if (B.left != null && (A.left == null || !isSameB(A.left, B.left))) {
            return false;
        } else if (B.right != null && (A.right == null || !isSameB(A.right, B.right))) {
            return false;
        }
        return true;
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
