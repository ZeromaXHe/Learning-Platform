package leetcode.from51to100;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2022/1/17 11:19
 * @Description: 98. 验证二叉搜索树 | 难度：中等 | 标签：树、深度优先搜索、二叉搜索树、二叉树
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 有效 二叉搜索树定义如下：
 * <p>
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * <p>
 * 示例 1：
 * 输入：root = [2,1,3]
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：root = [5,1,4,null,null,3,6]
 * 输出：false
 * 解释：根节点的值是 5 ，但是右子节点的值是 4 。
 * <p>
 * 提示：
 * 树中节点数目范围在[1, 104] 内
 * -231 <= Node.val <= 231 - 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution98 {
    @Test
    public void isValidBSTTest() {
        // 通过测试用例： 62 / 80
        Assert.assertFalse(isValidBST(new TreeNode(2, new TreeNode(2), new TreeNode(2))));
        // [5,4,6,null,null,3,7]
        // 通过测试用例： 72 / 80
        Assert.assertFalse(isValidBST(new TreeNode(5, new TreeNode(4), new TreeNode(6, new TreeNode(3), new TreeNode(7)))));
    }

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
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.1 MB , 在所有 Java 提交中击败了 50.01% 的用户
     * 通过测试用例： 80 / 80
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBSTAndIfYesWithMinMax(root)[0] == 1;
    }

    public int[] isValidBSTAndIfYesWithMinMax(TreeNode root) {
        int min = root.val;
        int max = root.val;
        if (root.left != null) {
            int[] leftResult = isValidBSTAndIfYesWithMinMax(root.left);
            if (leftResult[0] == 0 || leftResult[2] >= root.val) {
                return new int[]{0};
            }
            min = leftResult[1];
        }
        if (root.right != null) {
            int[] rightResult = isValidBSTAndIfYesWithMinMax(root.right);
            if (rightResult[0] == 0 || rightResult[1] <= root.val) {
                return new int[]{0};
            }
            max = rightResult[2];
        }
        return new int[]{1, min, max};
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
