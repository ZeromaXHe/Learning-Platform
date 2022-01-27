package leetcode.from101to150;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: zhuxi
 * @Time: 2022/1/19 14:35
 * @Description: 116. 填充每个节点的下一个右侧节点指针 | 难度：中等 | 标签：树、深度优先搜索、广度优先搜素、二叉树
 * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 * <p>
 * | struct Node {
 * |   int val;
 * |   Node *left;
 * |   Node *right;
 * |   Node *next;
 * | }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * <p>
 * 初始状态下，所有 next 指针都被设置为 NULL。
 * <p>
 * 进阶：
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 * <p>
 * 示例：
 * 输入：root = [1,2,3,4,5,6,7]
 * 输出：[1,#,2,3,#,4,5,6,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化的输出按层序遍历排列，同一层节点由 next 指针连接，'#' 标志着每一层的结束。
 * <p>
 * 提示：
 * 树中节点的数量少于 4096
 * -1000 <= node.val <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution116 {
    /**
     * | // Definition for a Node.
     * | class Node {
     * |     public int val;
     * |     public Node left;
     * |     public Node right;
     * |     public Node next;
     * |
     * |     public Node() {}
     * |
     * |     public Node(int _val) {
     * |         val = _val;
     * |     }
     * |
     * |     public Node(int _val, Node _left, Node _right, Node _next) {
     * |         val = _val;
     * |         left = _left;
     * |         right = _right;
     * |         next = _next;
     * |     }
     * | };
     * <p>
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 65.62% 的用户
     * 内存消耗： 38.8 MB , 在所有 Java 提交中击败了 16.43% 的用户
     * 通过测试用例： 59 / 59
     * <p>
     * 可以利用上一层建好的next索引，这样会快一点，看其他人的答案，0 ms。
     *
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node before = null;
            while (size > 0) {
                Node poll = queue.poll();
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
                if (before != null) {
                    before.next = poll;
                }
                before = poll;
                size--;
            }
        }
        return root;
    }

    // Definition for a Node.
    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
