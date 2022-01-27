package leetcode.from401to450;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhuxi
 * @Time: 2021/9/28 10:56
 * @Description: 437. 路径总和 III | 难度：中等 | 标签：树、深度优先遍历、二叉树
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * <p>
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * <p>
 * 示例 1：
 * 输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * 输出：3
 * 解释：和等于 8 的路径有 3 条，如图所示。
 * <p>
 * 示例 2：
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：3
 * <p>
 * 提示:
 * 二叉树的节点个数的范围是 [0,1000]
 * -10^9 <= Node.val <= 10^9 
 * -1000 <= targetSum <= 1000 
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution437 {
    /**
     * Definition for a binary tree node.
     * |public class TreeNode {
     * |    int val;
     * |    TreeNode left;
     * |    TreeNode right;
     * |    TreeNode() {}
     * |    TreeNode(int val) { this.val = val; }
     * |    TreeNode(int val, TreeNode left, TreeNode right) {
     * |        this.val = val;
     * |        this.left = left;
     * |        this.right = right;
     * |    }
     * |}
     * <p>
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.2 MB , 在所有 Java 提交中击败了 65.77% 的用户
     * 通过测试用例： 126 / 126
     *
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum(TreeNode root, int targetSum) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        return pathSum(root, targetSum, map, 0);
    }

    private int pathSum(TreeNode root, int targetSum, Map<Integer, Integer> map, int preSum) {
        if (root == null) {
            return 0;
        }
        preSum += root.val;
        int result = map.getOrDefault(preSum - targetSum, 0);
        map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        result += pathSum(root.left, targetSum, map, preSum);
        result += pathSum(root.right, targetSum, map, preSum);
        map.put(preSum, map.getOrDefault(preSum, 0) - 1);
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
