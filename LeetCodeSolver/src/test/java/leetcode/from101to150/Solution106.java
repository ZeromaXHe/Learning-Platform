package leetcode.from101to150;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhuxi
 * @Time: 2022/1/18 13:52
 * @Description: 106. 从中序与后序遍历序列构造二叉树 | 难度：中等 | 标签：树、数组、哈希表、分治、二叉树
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * <p>
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 * <p>
 * |    3
 * |   / \
 * |  9  20
 * |    /  \
 * |   15   7
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution106 {
    @Test
    public void buildTreeTest() {
        buildTree(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3});
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
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 99.62% 的用户
     * 内存消耗： 38.1 MB , 在所有 Java 提交中击败了 92.30% 的用户
     * 通过测试用例： 202 / 202
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = postorder.length;
        // 构造哈希映射，帮助我们快速定位根节点
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return buildTree(indexMap, postorder, 0, postorder.length, inorder, 0, inorder.length);
    }

    private TreeNode buildTree(Map<Integer, Integer> indexMap,
                               int[] postorder, int postFrom, int postTo,
                               int[] inorder, int inFrom, int inTo) {
        TreeNode root = new TreeNode(postorder[postTo - 1]);
        int inRoot = indexMap.get(root.val);
        if (inRoot > inFrom) {
            root.left = buildTree(indexMap, postorder, postFrom, postFrom + inRoot - inFrom,
                    inorder, inFrom, inRoot);
        }
        if (inRoot < inTo - 1) {
            root.right = buildTree(indexMap, postorder, postTo - inTo + inRoot, postTo - 1,
                    inorder, inRoot + 1, inTo);
        }
        return root;
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
