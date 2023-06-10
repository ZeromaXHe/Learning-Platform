package leetcode.jianzhi_offer;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer 32 - III. 从上到下打印二叉树 III | 难度：中等 | 标签：树、广度优先搜索、二叉树
 * 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
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
 * |   [20,9],
 * |   [15,7]
 * | ]
 * <p>
 * 提示：
 * 节点总数 <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/10 11:00
 */
public class SolutionOffer32_III {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了 96.31% 的用户
     * 内存消耗：41 MB, 在所有 Java 提交中击败了 89.31% 的用户
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
        boolean leftToRight = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> layer = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (leftToRight) {
                    layer.addLast(poll.val);
                } else {
                    layer.addFirst(poll.val);
                }
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
            result.add(layer);
            leftToRight = !leftToRight;
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
