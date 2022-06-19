package leetcode.from501to550;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ZeromaXHe
 * @apiNote 508. 出现次数最多的子树元素和 | 难度：中等 | 标签：树、深度优先搜索、哈希表、二叉树
 * 给你一个二叉树的根结点 root ，请返回出现次数最多的子树元素和。如果有多个元素出现的次数相同，返回所有出现次数最多的子树元素和（不限顺序）。
 * <p>
 * 一个结点的 「子树元素和」 定义为以该结点为根的二叉树上所有结点的元素之和（包括结点本身）。
 * <p>
 * 示例 1：
 * 输入: root = [5,2,-3]
 * 输出: [2,-3,4]
 * <p>
 * 示例 2：
 * 输入: root = [5,2,-5]
 * 输出: [2]
 * <p>
 * 提示:
 * 节点数在 [1, 104] 范围内
 * -105 <= Node.val <= 105
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/most-frequent-subtree-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2022/6/19 13:40
 */
public class Solution508 {
    @Test
    public void findFrequentTreeSumTest() {
        TreeNode root = new TreeNode(5, new TreeNode(2), new TreeNode(-3));
        Assert.assertArrayEquals(new int[]{2, -3, 4}, findFrequentTreeSum(root));
    }

    HashMap<TreeNode, Integer> map;
    HashMap<Integer, Integer> countMap;
    List<Integer> list;
    int maxCount;

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
     * 执行用时：4 ms, 在所有 Java 提交中击败了 70.84% 的用户
     * 内存消耗：42.8 MB, 在所有 Java 提交中击败了 5.13% 的用户
     * 通过测试用例： 58 / 58
     *
     * @param root
     * @return
     */
    public int[] findFrequentTreeSum(TreeNode root) {
        map = new HashMap<>();
        countMap = new HashMap<>();
        list = new LinkedList<>();
        maxCount = 0;
        traversal(root);
        int[] result = new int[list.size()];
        int i = 0;
        for (int sum : list) {
            result[i++] = sum;
        }
        return result;
    }

    private int traversal(TreeNode root) {
        int sum = root.val;
        if (root.left != null) {
            if (map.containsKey(root.left)) {
                sum += map.get(root.left);
            } else {
                int val = traversal(root.left);
                sum += val;
            }
        }
        if (root.right != null) {
            if (map.containsKey(root.right)) {
                sum += map.get(root.right);
            } else {
                sum += traversal(root.right);
            }
        }
        int newCount = countMap.getOrDefault(sum, 0) + 1;
        if (newCount > maxCount) {
            list.clear();
            list.add(sum);
            maxCount = newCount;
        } else if (newCount == maxCount) {
            list.add(sum);
        }
        countMap.put(sum, newCount);
        map.put(root, sum);
        return sum;
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
