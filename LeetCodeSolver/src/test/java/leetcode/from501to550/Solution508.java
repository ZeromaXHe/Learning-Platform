package leetcode.from501to550;

import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
     * 执行用时：2 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：42 MB, 在所有 Java 提交中击败了 10.82% 的用户
     * 通过测试用例： 58 / 58
     *
     * @param root
     * @return
     */
    public int[] findFrequentTreeSum(TreeNode root) {
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
            sum += traversal(root.left);
        }
        if (root.right != null) {
            sum += traversal(root.right);
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
        return sum;
    }

    /**
     * 执行用时：8 ms, 在所有 Java 提交中击败了 10.38% 的用户
     * 内存消耗：41.8 MB, 在所有 Java 提交中击败了 26.89% 的用户
     * 通过测试用例：58 / 58
     *
     * @param root
     * @return
     */
    public int[] findFrequentTreeSum2(TreeNode root) {
        HashMap<Integer, Integer> sumToCount = new HashMap<>();
        traversal(root, sumToCount);
        int max = sumToCount.values().stream()
                .max(Comparator.comparingInt(Integer::intValue)).orElse(0);
        List<Integer> resultList = sumToCount.entrySet().stream()
                .filter(e -> e.getValue() == max)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        int[] result = new int[resultList.size()];
        int i = 0;
        for (int sum : resultList) {
            result[i++] = sum;
        }
        return result;
    }

    private int traversal(TreeNode root, HashMap<Integer, Integer> sumToCount) {
        int sum = root.val;
        if (root.left != null) {
            sum += traversal(root.left, sumToCount);
        }
        if (root.right != null) {
            sum += traversal(root.right, sumToCount);
        }
        sumToCount.put(sum, sumToCount.getOrDefault(sum, 0) + 1);
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
