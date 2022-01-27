package leetcode.from101to150;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhuxi
 * @Time: 2022/1/18 11:53
 * @Description: 105. 从前序与中序遍历序列构造二叉树 | 难度：简单 | 标签：树、数组、哈希表、分治、二叉树
 * 给定一棵树的前序遍历 preorder 与中序遍历  inorder。请构造二叉树并返回其根节点。
 * <p>
 * 示例 1:
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 * <p>
 * 示例 2:
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 * <p>
 * 提示:
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder 和 inorder 均无重复元素
 * inorder 均出现在 preorder
 * preorder 保证为二叉树的前序遍历序列
 * inorder 保证为二叉树的中序遍历序列
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution105 {
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
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 99.10% 的用户
     * 内存消耗： 38.3 MB , 在所有 Java 提交中击败了 74.47% 的用户
     * 通过测试用例： 203 / 203
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 构造哈希映射，帮助我们快速定位根节点
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return buildTree(indexMap, preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    private TreeNode buildTree(Map<Integer, Integer> indexMap,
                               int[] preorder, int preFrom, int preTo,
                               int[] inorder, int inFrom, int inTo) {
        TreeNode root = new TreeNode(preorder[preFrom]);
        int inRoot = indexMap.get(root.val);
        if (inRoot > inFrom) {
            root.left = buildTree(indexMap, preorder, preFrom + 1, preFrom + 1 + inRoot - inFrom,
                    inorder, inFrom, inRoot);
        }
        if (inRoot < inTo - 1) {
            root.right = buildTree(indexMap, preorder, preFrom + 1 + inRoot - inFrom, preTo,
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
