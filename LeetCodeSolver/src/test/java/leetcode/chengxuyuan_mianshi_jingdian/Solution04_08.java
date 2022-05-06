package leetcode.chengxuyuan_mianshi_jingdian;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @Author: zhuxi
 * @Time: 2022/5/6 13:46
 * @Description: 面试题 04.08. 首个共同祖先 | 难度：中等 | 标签：树、深度优先搜索、二叉树
 * 设计并实现一个算法，找出二叉树中某两个节点的第一个共同祖先。不得将其他的节点存储在另外的数据结构中。注意：这不一定是二叉搜索树。
 * <p>
 * 例如，给定如下二叉树: root = [3,5,1,6,2,0,8,null,null,7,4]
 * <p>
 * |     3
 * |    / \
 * |   5   1
 * |  / \ / \
 * | 6  2 0  8
 * |   / \
 * |  7   4
 * <p>
 * 示例 1:
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 * <p>
 * 示例 2:
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 * <p>
 * 说明:
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉树中。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/first-common-ancestor-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution04_08 {
    /**
     * Definition for a binary tree node.
     * | public class TreeNode {
     * |     int val;
     * |     TreeNode left;
     * |     TreeNode right;
     * |     TreeNode(int x) { val = x; }
     * | }
     * <p>
     * 执行用时： 7 ms , 在所有 Java 提交中击败了 28.88% 的用户
     * 内存消耗： 42.9 MB , 在所有 Java 提交中击败了 22.10% 的用户
     * 通过测试用例： 31 / 31
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }

    /**
     * 执行用时： 9 ms , 在所有 Java 提交中击败了 10.28% 的用户
     * 内存消耗： 43.1 MB , 在所有 Java 提交中击败了 6.07% 的用户
     * 通过测试用例： 31 / 31
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor_stack(TreeNode root, TreeNode p, TreeNode q) {
        LinkedList<TreeNode> stackP = new LinkedList<>();
        LinkedList<TreeNode> stackQ = new LinkedList<>();
        find(root, p, q, new boolean[]{false, false}, stackP, stackQ);
        Iterator<TreeNode> iteratorP = stackP.iterator();
        Iterator<TreeNode> iteratorQ = stackQ.iterator();
        TreeNode result = null;
        while (iteratorP.hasNext() && iteratorQ.hasNext()) {
            TreeNode nextP = iteratorP.next();
            TreeNode nextQ = iteratorQ.next();
            if (nextP != nextQ) {
                break;
            } else {
                result = nextP;
            }
        }
        return result;
    }

    private boolean[] find(TreeNode root, TreeNode p, TreeNode q, boolean[] result,
                           LinkedList<TreeNode> stackP, LinkedList<TreeNode> stackQ) {
        if (!result[0]) {
            stackP.addLast(root);
        }
        if (!result[1]) {
            stackQ.addLast(root);
        }
        if (root == p) {
            result[0] = true;
            if (result[1]) {
                return result;
            }
        }
        if (root == q) {
            result[1] = true;
            if (result[0]) {
                return result;
            }
        }
        if (root.left != null) {
            result = find(root.left, p, q, result, stackP, stackQ);
            if (result[0] && result[1]) {
                return result;
            }
        }
        if (root.right != null) {
            result = find(root.right, p, q, result, stackP, stackQ);
            if (result[0] && result[1]) {
                return result;
            }
        }
        if (!result[0]) {
            stackP.removeLast();
        }
        if (!result[1]) {
            stackQ.removeLast();
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
