package leetcode.from801to900;

/**
 * @Author: zhuxi
 * @Time: 2021/4/25 9:57
 * @Description: 897.递增顺序搜索树 | 难度：简单 | 标签：树、深度优先遍历、递归
 * 给你一棵二叉搜索树，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
 * <p>
 * 示例 1：
 * 输入：root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
 * <p>
 * 示例 2：
 * 输入：root = [5,1,7]
 * 输出：[1,null,5,null,7]
 * <p>
 * 提示：
 * 树中节点数的取值范围是 [1, 100]
 * 0 <= Node.val <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/increasing-order-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution897 {
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
     * 内存消耗： 36.1 MB , 在所有 Java 提交中击败了 33.84% 的用户
     *
     * @param root
     * @return
     */
    public TreeNode increasingBST(TreeNode root) {
        return inOrderTraverseAndReformTree(root)[0];
    }

    private TreeNode[] inOrderTraverseAndReformTree(TreeNode root) {
        TreeNode[] result = {root, root};
        if (root.left != null) {
            TreeNode[] leftStartEnd = inOrderTraverseAndReformTree(root.left);
            leftStartEnd[1].right = root;
            result[0] = leftStartEnd[0];
            root.left = null;
        }
        if (root.right != null) {
            TreeNode[] rightStartEnd = inOrderTraverseAndReformTree(root.right);
            root.right = rightStartEnd[0];
            result[1] = rightStartEnd[1];
        }
        return result;
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
