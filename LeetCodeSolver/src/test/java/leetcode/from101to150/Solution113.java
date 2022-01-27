package leetcode.from101to150;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/1/19 11:57
 * @Description: 113. 路径总和 II | 难度：中等 | 标签：树、深度优先搜索、回溯、二叉树
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * <p>
 * 叶子节点 是指没有子节点的节点。
 * <p>
 * 示例 1：
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：[[5,4,11,2],[5,8,4,5]]
 * <p>
 * 示例 2：
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：root = [1,2], targetSum = 0
 * 输出：[]
 * <p>
 * 提示：
 * 树中节点总数在范围 [0, 5000] 内
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution113 {
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
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.9 MB , 在所有 Java 提交中击败了 28.95% 的用户
     * 通过测试用例： 115 / 115
     *
     * @param root
     * @param targetSum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        LinkedList<Integer> test = new LinkedList<>();
        pathSum(root, targetSum, test, result);
        return result;
    }

    private void pathSum(TreeNode root, int targetSum, LinkedList<Integer> test, List<List<Integer>> result) {
        test.addLast(root.val);
        if (root.left == null && root.right == null) {
            if (root.val == targetSum) {
                result.add(new ArrayList<>(test));
            }
        }
        if (root.left != null) {
            pathSum(root.left, targetSum - root.val, test, result);
        }
        if (root.right != null) {
            pathSum(root.right, targetSum - root.val, test, result);
        }
        test.removeLast();
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
