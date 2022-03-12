package leetcode.from551to600;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/3/12 14:57
 * @Description: 590. N 叉树的后序遍历 | 难度：简单 | 标签：栈、树、深度优先遍历
 * 给定一个 n 叉树的根节点 root ，返回 其节点值的 后序遍历 。
 * <p>
 * n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
 * <p>
 * 示例 1：
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[5,6,3,2,4,1]
 * <p>
 * 示例 2：
 * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：[2,6,14,11,7,3,12,8,4,13,9,10,5,1]
 * <p>
 * 提示：
 * 节点总数在范围 [0, 104] 内
 * 0 <= Node.val <= 104
 * n 叉树的高度小于或等于 1000
 * <p>
 * 进阶：递归法很简单，你可以使用迭代法完成此题吗?
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution590 {
    /**
     * | // Definition for a Node.
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
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 42 MB , 在所有 Java 提交中击败了 27.34% 的用户
     * 通过测试用例： 38 / 38
     *
     * @param root
     * @return
     */
    public List<Integer> postorder(Node root) {
        List<Integer> result = new LinkedList<>();
        postorder(root, result);
        return result;
    }

    private void postorder(Node root, List<Integer> result) {
        if (root == null) {
            return;
        }
        if (root.children != null && !root.children.isEmpty()) {
            for (Node child : root.children) {
                postorder(child, result);
            }
        }
        result.add(root.val);
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
