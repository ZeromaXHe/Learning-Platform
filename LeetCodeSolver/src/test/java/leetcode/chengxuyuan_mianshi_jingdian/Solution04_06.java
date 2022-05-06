package leetcode.chengxuyuan_mianshi_jingdian;

/**
 * @Author: zhuxi
 * @Time: 2022/5/6 11:18
 * @Description: 面试题 04.06. 后继者 | 难度：中等 | 标签：树、深度优先搜索、二叉搜索树、二叉树
 * 设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
 * <p>
 * 如果指定节点没有对应的“下一个”节点，则返回null。
 * <p>
 * 示例 1:
 * 输入: root = [2,1,3], p = 1
 * |   2
 * |  / \
 * | 1   3
 * 输出: 2
 * <p>
 * 示例 2:
 * 输入: root = [5,3,6,2,4,null,null,1], p = 6
 * |       5
 * |      / \
 * |     3   6
 * |    / \
 * |   2   4
 * |  /
 * | 1
 * 输出: null
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/successor-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution04_06 {
    private TreeNode pre;

    /**
     * Definition for a binary tree node.
     * | public class TreeNode {
     * |     int val;
     * |     TreeNode left;
     * |     TreeNode right;
     * |     TreeNode(int x) { val = x; }
     * | }
     * <p>
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 54.33% 的用户
     * 内存消耗： 42 MB , 在所有 Java 提交中击败了 81.31% 的用户
     * 通过测试用例： 24 / 24
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        pre = null;
        return inorder(root, p);
    }

    private TreeNode inorder(TreeNode root, TreeNode p) {
        if (root.left != null) {
            TreeNode left = inorder(root.left, p);
            if (left != null) {
                return left;
            }
        }
        if (p == pre) {
            return root;
        }
        pre = root;
        if (root.right != null) {
            TreeNode right = inorder(root.right, p);
            return right;
        }
        return null;
    }

    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 42.5 MB , 在所有 Java 提交中击败了 19.72% 的用户
     * 通过测试用例： 24 / 24
     *
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderSuccessor_complex(TreeNode root, TreeNode p) {
        TreeNode result = inorder(root, p, false);
        return result == p ? null : result;
    }

    private TreeNode inorder(TreeNode root, TreeNode p, boolean findFirst) {
        TreeNode result = null;
        if (findFirst) {
            result = root;
            while (result.left != null) {
                result = result.left;
            }
            return result;
        }
        if (root.left != null) {
            TreeNode left = inorder(root.left, p, findFirst);
            if (left != null) {
                return left == p ? root : left;
            }
        }
        if (root == p) {
            findFirst = true;
            result = p;
        }
        if (root.right != null) {
            TreeNode right = inorder(root.right, p, findFirst);
            if (right != null) {
                return right;
            }
        }
        return result;
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
