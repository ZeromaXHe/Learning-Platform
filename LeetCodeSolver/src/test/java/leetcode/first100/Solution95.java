package leetcode.first100;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/1/14 11:58
 * @Description: 95. 不同的二叉搜索树 II | 难度：中等 | 标签：树、二叉搜索树、动态规划、回溯、二叉树
 * 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
 * <p>
 * 示例 1：
 * 输入：n = 3
 * 输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
 * <p>
 * 示例 2：
 * 输入：n = 1
 * 输出：[[1]]
 * <p>
 * 提示：
 * 1 <= n <= 8
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution95 {
    @Test
    public void generateTreesTest() {
        System.out.println(generateTrees(3));
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
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 98.54% 的用户
     * 内存消耗： 39.4 MB , 在所有 Java 提交中击败了 5.15% 的用户
     * 通过测试用例： 8 / 8
     *
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        return generateTrees(0, n);
    }

    private List<TreeNode> generateTrees(int from, int to) {
        List<TreeNode> result = new LinkedList<>();
        for (int i = from; i < to; i++) {
            List<TreeNode> lefts = generateTrees(from, i);
            List<TreeNode> rights = generateTrees(i + 1, to);

            if (lefts.size() != 0 && rights.size() != 0) {
                for (TreeNode left : lefts) {
                    for (TreeNode right : rights) {
                        TreeNode root = new TreeNode(i + 1);
                        root.left = left;
                        root.right = right;
                        result.add(root);
                    }
                }
            } else if (lefts.size() != 0) {
                for (TreeNode left : lefts) {
                    TreeNode root = new TreeNode(i + 1);
                    root.left = left;
                    result.add(root);
                }
            } else if (rights.size() != 0) {
                for (TreeNode right : rights) {
                    TreeNode root = new TreeNode(i + 1);
                    root.right = right;
                    result.add(root);
                }
            } else {
                result.add(new TreeNode(i + 1));
            }
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

        @Override
        public String toString() {
            return val + ((left == null && right == null) ? "" : ("; " + left + "; " + right));
        }
    }
}
