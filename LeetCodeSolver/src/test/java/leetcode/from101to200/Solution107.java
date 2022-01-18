package leetcode.from101to200;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author: zhuxi
 * @Time: 2022/1/18 16:13
 * @Description: 107. 二叉树的层序遍历 II | 难度：中等 | 标签：树、广度优先搜索、二叉树
 * 给你二叉树的根节点 root ，返回其节点值 自底向上的层序遍历 。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * <p>
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[15,7],[9,20],[3]]
 * <p>
 * 示例 2：
 * 输入：root = [1]
 * 输出：[[1]]
 * <p>
 * 示例 3：
 * 输入：root = []
 * 输出：[]
 * <p>
 * 提示：
 * 树中节点数目在范围 [0, 2000] 内
 * -1000 <= Node.val <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution107 {
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
     * 参考第102题二叉树的层序遍历, 唯一改动就是使用 result.addFirst
     * <p>
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 94.72% 的用户
     * 内存消耗： 38.3 MB , 在所有 Java 提交中击败了 91.81% 的用户
     * 通过测试用例： 34 / 34
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> layer = new LinkedList<>();
            int size = queue.size();
            while (size > 0) {
                TreeNode poll = queue.poll();
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
                layer.add(poll.val);
                size--;
            }
            result.addFirst(layer);
        }
        return result;
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
