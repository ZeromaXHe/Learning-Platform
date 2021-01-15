package leetcode.first100;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2021/1/15 16:05
 * @Description: 99.恢复二叉搜索树 | 难度：困难 | 标签：树、深度优先搜索
 * 给你二叉搜索树的根节点 root ，该树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。
 * <p>
 * 进阶：使用 O(n) 空间复杂度的解法很容易实现。你能想出一个只使用常数空间的解决方案吗？
 * <p>
 * 示例 1：
 * 输入：root = [1,3,null,null,2]
 * 输出：[3,1,null,null,2]
 * 解释：3 不能是 1 左孩子，因为 3 > 1 。交换 1 和 3 使二叉搜索树有效。
 * <p>
 * 示例 2：
 * 输入：root = [3,1,4,null,null,2]
 * 输出：[2,1,4,null,null,3]
 * 解释：2 不能在 3 的右子树中，因为 2 < 3 。交换 2 和 3 使二叉搜索树有效。
 * <p>
 * 提示：
 * 树上节点的数目在范围 [2, 1000] 内
 * -2^31 <= Node.val <= 2^31 - 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/recover-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution99 {
    /**
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 30.90% 的用户
     * 内存消耗： 38.7 MB , 在所有 Java 提交中击败了 82.21% 的用户
     * <p>
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
     */
    public void recoverTree(TreeNode root) {
        LinkedList<TreeNode> list = new LinkedList<>();
        inOrder(root, list);
        TreeNode[] swapped = findTwoSwapped(list);
        int temp = swapped[0].val;
        swapped[0].val = swapped[1].val;
        swapped[1].val = temp;
    }

    public void inOrder(TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        }
        inOrder(root.left, list);
        list.add(root);
        inOrder(root.right, list);
    }

    public TreeNode[] findTwoSwapped(List<TreeNode> list) {
        int n = list.size();
        TreeNode x = null, y = null;
        for (int i = 0; i < n - 1; ++i) {
            if (list.get(i + 1).val < list.get(i).val) {
                y = list.get(i + 1);
                // 第一次出现递减的情况
                if (x == null) {
                    x = list.get(i);
                } else {
                    // 第二次出现递减的情况
                    break;
                }
                // 实际可能出现一次，也可能出现两次递减
            }
        }
        return new TreeNode[]{x, y};
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
