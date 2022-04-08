package leetcode.from401to450;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/4/8 11:14
 * @Description: 429. N 叉树的层序遍历 | 难度：中等 | 标签：树、广度优先搜索
 * 给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。
 * <p>
 * 树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
 * <p>
 * 示例 1：
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[[1],[3,2,4],[5,6]]
 * <p>
 * 示例 2：
 * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：[[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
 * <p>
 * 提示：
 * 树的高度不会超过 1000
 * 树的节点总数在 [0, 10^4] 之间
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution429 {
    /**
     * // Definition for a Node.
     * | class Node {
     * |     public int val;
     * |     public List<Node> children;
     * |
     * |     public Node() {}
     * |
     * |     public Node(int _val) {
     * |         val = _val;
     * |     }
     * |
     * |     public Node(int _val, List<Node> _children) {
     * |         val = _val;
     * |         children = _children;
     * |     }
     * | };
     * <p>
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 92.11% 的用户
     * 内存消耗： 42.2 MB , 在所有 Java 提交中击败了 53.70% 的用户
     * 通过测试用例： 38 / 38
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node poll = queue.poll();
                if (poll.children != null && poll.children.size() > 0) {
                    for (Node child : poll.children) {
                        queue.offer(child);
                    }
                }
                list.add(poll.val);
            }
            result.add(list);
        }
        return result;
    }

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    ;
}
