package leetcode.jianzhi_offer;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer 37. 序列化二叉树 | 难度：困难 | 标签：树、深度优先搜索、广度优先搜索、设计、字符串、二叉树
 * 请实现两个函数，分别用来序列化和反序列化二叉树。
 * <p>
 * 你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * <p>
 * 提示：输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * <p>
 * 示例：
 * 输入：root = [1,2,3,null,null,4,5]
 * 输出：[1,2,3,null,null,4,5]
 * <p>
 * 注意：本题与主站 297 题相同：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/xu-lie-hua-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/10 11:46
 */
public class SolutionOffer37 {

    /**
     * 执行用时：6 ms, 在所有 Java 提交中击败了 95.55% 的用户
     * 内存消耗：43.5 MB, 在所有 Java 提交中击败了 58.05% 的用户
     * 通过测试用例：48 / 48
     */
    public class Codec {
        private int idx = 0;

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            preOrder(root, sb);
            return sb.toString();
        }

        private void preOrder(TreeNode root, StringBuilder sb) {
            if (sb.length() > 0) {
                sb.append(',');
            }
            if (root == null) {
                sb.append("null");
            } else {
                sb.append(root.val);
                preOrder(root.left, sb);
                preOrder(root.right, sb);
            }
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] strs = data.split(",");
            idx = 0;
            return buildTree(strs);
        }

        private TreeNode buildTree(String[] strs) {
            if ("null".equals(strs[idx])) {
                idx++;
                return null;
            }
            TreeNode root = new TreeNode(Integer.parseInt(strs[idx++]));
            root.left = buildTree(strs);
            root.right = buildTree(strs);
            return root;
        }
    }

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
