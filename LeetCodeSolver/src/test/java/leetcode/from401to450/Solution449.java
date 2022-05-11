package leetcode.from401to450;

import java.util.LinkedList;

/**
 * @Author: zhuxi
 * @Time: 2022/5/11 18:22
 * @Description: 449. 序列化和反序列化二叉搜索树 | 难度：中等 | 标签：树、深度优先搜索、广度优先搜索、设计、二叉搜索树、字符串、二叉树
 * 序列化是将数据结构或对象转换为一系列位的过程，以便它可以存储在文件或内存缓冲区中，或通过网络连接链路传输，以便稍后在同一个或另一个计算机环境中重建。
 * <p>
 * 设计一个算法来序列化和反序列化 二叉搜索树 。 对序列化/反序列化算法的工作方式没有限制。 您只需确保二叉搜索树可以序列化为字符串，并且可以将该字符串反序列化为最初的二叉搜索树。
 * <p>
 * 编码的字符串应尽可能紧凑。
 * <p>
 * 示例 1：
 * 输入：root = [2,1,3]
 * 输出：[2,1,3]
 * <p>
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 * <p>
 * 提示：
 * 树中节点数范围是 [0, 104]
 * 0 <= Node.val <= 104
 * 题目数据 保证 输入的树是一棵二叉搜索树。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/serialize-and-deserialize-bst
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution449 {
    /**
     * Definition for a binary tree node.
     * | public class TreeNode {
     * |     int val;
     * |     TreeNode left;
     * |     TreeNode right;
     * |     TreeNode(int x) { val = x; }
     * | }
     * <p>
     * 执行用时： 9 ms , 在所有 Java 提交中击败了 61.15% 的用户
     * 内存消耗： 41.9 MB , 在所有 Java 提交中击败了 86.40% 的用户
     * 通过测试用例： 62 / 62
     */
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    if (sb.length() > 0) {
                        sb.append(',');
                    }
                    TreeNode poll = queue.poll();
                    sb.append(poll == null ? "null" : poll.val);
                    if (poll != null) {
                        queue.offer(poll.left);
                        queue.offer(poll.right);
                    }
                }
            }
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] split = data.split(",");
            LinkedList<TreeNode> queue = new LinkedList<>();
            TreeNode root = stringToTreeNode(split[0]);
            if (root != null) {
                queue.offer(root);
            }
            int index = 1;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode poll = queue.poll();
                    poll.left = stringToTreeNode(split[index++]);
                    if (poll.left != null) {
                        queue.offer(poll.left);
                    }
                    poll.right = stringToTreeNode(split[index++]);
                    if (poll.right != null) {
                        queue.offer(poll.right);
                    }
                }
            }
            return root;
        }

        private TreeNode stringToTreeNode(String s) {
            if ("null".equals(s)) {
                return null;
            } else {
                return new TreeNode(Integer.parseInt(s));
            }
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// String tree = ser.serialize(root);
// TreeNode ans = deser.deserialize(tree);
// return ans;
}
