package leetcode.from101to200;

import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2022/1/19 14:57
 * @Description: 117. 填充每个节点的下一个右侧节点指针 II | 难度：中等 | 标签：树、深度优先搜索、广度优先搜索、二叉树
 * 给定一个二叉树
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
 * 输入：root = [1,2,3,4,5,null,7]
 * 输出：[1,#,2,3,#,4,5,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化输出按层序遍历顺序（由 next 指针连接），'#' 表示每层的末尾。
 * <p>
 * 提示：
 * 树中的节点数小于 6000
 * -100 <= node.val <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution117 {
    @Test
    public void connectTest() {
        // 通过测试用例： 36 / 55
        // 输入： [3,9,20,null,null,15,7]
        // 预期结果： [3,#,9,20,#,15,7,#]
        Node node15 = new Node(15);
        Node node7 = new Node(7);
        Node node20 = new Node(20, node15, node7, null);
        Node node9 = new Node(9);
        Node node3 = new Node(3, node9, node20, null);
        connect(node3);
    }

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
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.2 MB , 在所有 Java 提交中击败了 14.09% 的用户
     * 通过测试用例： 55 / 55
     *
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }
        Node cur = root;
        while (cur.left != null || cur.right != null || cur.next != null) {
            Node first = null;
            Node before = null;
            Node tmp = cur;
            while (tmp != null) {
                if (tmp.left != null) {
                    if (first == null) {
                        first = tmp.left;
                    }
                    if (before != null) {
                        before.next = tmp.left;
                    }
                    before = tmp.left;
                }
                if (tmp.right != null) {
                    if (first == null) {
                        first = tmp.right;
                    }
                    if (before != null) {
                        before.next = tmp.right;
                    }
                    before = tmp.right;
                }
                tmp = tmp.next;
            }
            if (first == null) {
                break;
            }
            cur = first;
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

    ;
}
