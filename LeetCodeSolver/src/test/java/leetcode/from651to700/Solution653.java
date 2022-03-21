package leetcode.from651to700;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

/**
 * @Author: zhuxi
 * @Time: 2022/3/21 9:53
 * @Description: 653. 两数之和 IV - 输入 BST | 难度：简单 | 标签：
 * 给定一个二叉搜索树 root 和一个目标结果 k，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
 * <p>
 * 示例 1：
 * 输入: root = [5,3,6,2,4,null,7], k = 9
 * 输出: true
 * <p>
 * 示例 2：
 * 输入: root = [5,3,6,2,4,null,7], k = 28
 * 输出: false
 * <p>
 * 提示:
 * 二叉树的节点个数的范围是  [1, 104].
 * -104 <= Node.val <= 104
 * root 为二叉搜索树
 * -105 <= k <= 105
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution653 {
    @Test
    public void findTargetTest() {
        // 通过测试用例： 257 / 422
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node2.left = node1;
        node2.right = node3;
        Assert.assertTrue(findTarget(node2, 4));
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
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 42 MB , 在所有 Java 提交中击败了 21.13% 的用户
     * 通过测试用例： 422 / 422
     *
     * @param root
     * @param k
     * @return
     */
    public boolean findTarget(TreeNode root, int k) {
        LinkedList<TreeNode> left = new LinkedList<>();
        LinkedList<TreeNode> right = new LinkedList<>();
        left.push(root);
        right.push(root);
        TreeNode node = root;
        while (node.left != null) {
            node = node.left;
            left.push(node);
        }
        // 少了这一行也能通过 257 个案例，就 tm 离谱
        node = root;
        while (node.right != null) {
            node = node.right;
            right.push(node);
        }
        while (!left.isEmpty() && !right.isEmpty()) {
            TreeNode l = left.peek();
            TreeNode r = right.peek();
            if (l == r) {
                break;
            }
            int sum = l.val + r.val;
            if (sum == k) {
                return true;
            } else if (sum < k) {
                if (l.right != null) {
                    node = left.pop().right;
                    left.push(node);
                    while (node.left != null) {
                        node = node.left;
                        left.push(node);
                    }
                } else {
                    left.pop();
                }
            } else {
                if (r.left != null) {
                    node = right.pop().left;
                    right.push(node);
                    while (node.right != null) {
                        node = node.right;
                        right.push(node);
                    }
                } else {
                    right.pop();
                }
            }
        }
        return false;
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
