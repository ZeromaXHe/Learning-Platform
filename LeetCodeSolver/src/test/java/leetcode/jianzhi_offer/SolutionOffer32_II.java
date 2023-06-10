package leetcode.jianzhi_offer;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer 32 - II. 从上到下打印二叉树 II | 难度：简单 | 标签：树、广度优先搜索、二叉树
 * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
 * <p>
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 * <p>
 * |    3
 * |   / \
 * |  9  20
 * |    /  \
 * |   15   7
 * 返回其层次遍历结果：
 * <p>
 * | [
 * |   [3],
 * |   [9,20],
 * |   [15,7]
 * | ]
 * <p>
 * 提示：
 * 节点总数 <= 1000
 * 注意：本题与主站 102 题相同：https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/10 10:20
 */
public class SolutionOffer32_II {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了 77.34% 的用户
     * 内存消耗：41.1 MB, 在所有 Java 提交中击败了 83.18% 的用户
     * 通过测试用例：34 / 34
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> layer = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                layer.add(poll.val);
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
            result.add(layer);
        }
        return result;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
