package leetcode.chengxuyuan_mianshi_jingdian;

/**
 * @Author: zhuxi
 * @Time: 2022/5/6 9:48
 * @Description: 面试题 04.02. 最小高度树 | 难度：简单 | 标签：树、二叉搜索树、数组、分治、二叉树
 * 给定一个有序整数数组，元素各不相同且按升序排列，编写一个算法，创建一棵高度最小的二叉搜索树。
 * <p>
 * 示例:
 * 给定有序数组: [-10,-3,0,5,9],
 * <p>
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 * <p>
 * |          0
 * |         / \
 * |       -3   9
 * |       /   /
 * |     -10  5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-height-tree-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution04_02 {
    /**
     * Definition for a binary tree node.
     * | public class TreeNode {
     * |     int val;
     * |     TreeNode left;
     * |     TreeNode right;
     * |     TreeNode(int x) { val = x; }
     * | }
     * <p>
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 40.9 MB , 在所有 Java 提交中击败了 93.13% 的用户
     * 通过测试用例： 32 / 32
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return sortedArrayToBST(nums, 0, nums.length);
    }

    private TreeNode sortedArrayToBST(int[] nums, int from, int to) {
        int mid = (from + to) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        if (mid > from) {
            root.left = sortedArrayToBST(nums, from, mid);
        }
        if (to > mid + 1) {
            root.right = sortedArrayToBST(nums, mid + 1, to);
        }
        return root;
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
