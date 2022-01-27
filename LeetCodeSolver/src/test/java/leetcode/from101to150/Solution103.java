package leetcode.from101to150;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/22 9:56
 * @Description: 103. 二叉树的锯齿形层序遍历 | 难度：中等 | 标签：栈、树、广度优先搜索
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回锯齿形层序遍历如下：
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution103 {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 98.42% 的用户
     * 内存消耗： 38.4 MB , 在所有 Java 提交中击败了 78.91% 的用户
     *
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        int preCount = 1;
        queue.add(root);
        LinkedList<Integer> line = new LinkedList<>();
        boolean leftToRight = true;
        int nextCount = 0;
        while (preCount > 0) {
            for (int i = 0; i < preCount; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    nextCount++;
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    nextCount++;
                    queue.offer(node.right);
                }
                if (leftToRight) {
                    line.addLast(node.val);
                } else {
                    line.addFirst(node.val);
                }
            }
            result.add(line);
            preCount = nextCount;
            nextCount = 0;
            line = new LinkedList<>();
            leftToRight = !leftToRight;
        }
        return result;
    }
}