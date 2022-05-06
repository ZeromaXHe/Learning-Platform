package leetcode.chengxuyuan_mianshi_jingdian;

import java.util.HashMap;

/**
 * @Author: zhuxi
 * @Time: 2022/5/6 15:38
 * @Description: 面试题 04.12. 求和路径 | 难度：中等 | 标签：
 * 给定一棵二叉树，其中每个节点都含有一个整数数值(该值或正或负)。设计一个算法，打印节点数值总和等于某个给定值的所有路径的数量。
 * 注意，路径不一定非得从二叉树的根节点或叶节点开始或结束，但是其方向必须向下(只能从父节点指向子节点方向)。
 * <p>
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 * |              5
 * |             / \
 * |            4   8
 * |           /   / \
 * |          11  13  4
 * |         /  \    / \
 * |        7    2  5   1
 * 返回:
 * 3
 * 解释：和为 22 的路径有：[5,4,11,2], [5,8,4,5], [4,11,7]
 * <p>
 * 提示：
 * 节点总数 <= 10000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/paths-with-sum-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution04_12 {
    private int count;
    private HashMap<Long, Integer> preSum;

    /**
     * Definition for a binary tree node.
     * | public class TreeNode {
     * |     int val;
     * |     TreeNode left;
     * |     TreeNode right;
     * |     TreeNode(int x) { val = x; }
     * | }
     * <p>
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 41.1 MB , 在所有 Java 提交中击败了 45.78% 的用户
     * 通过测试用例： 114 / 114
     * <p>
     * 看题解里面评论区说和 437 题一模一样，不过也没有印象了……
     * 自己按想法写的，不过一开始 HashMap 用的 LinkedList，想依靠栈来解决。结果发现有正有负，就改成题解的 Map 了。
     */
    public int pathSum(TreeNode root, int sum) {
        count = 0;
        preSum = new HashMap<>();
        preOrder(root, sum, 0);
        return count;
    }

    private void preOrder(TreeNode root, int sum, long pre) {
        if (root == null) {
            return;
        }
        pre += root.val;
        count += preSum.getOrDefault(pre - sum, 0);
        // if 语句可以替换为在 preSum 初始化后执行 preSum.put(0L, 1);
        if (pre == sum) {
            count++;
        }
        preSum.put(pre, preSum.getOrDefault(pre, 0) + 1);
        preOrder(root.left, sum, pre);
        preOrder(root.right, sum, pre);
        preSum.put(pre, preSum.get(pre) - 1);
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
