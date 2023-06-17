package leetcode.jianzhi_offer_II;

/**
 * @author ZeromaXHe
 * @apiNote 剑指 Offer II 029. 排序的循环链表 | 难度：中等 | 标签：链表
 * 给定循环单调非递减列表中的一个点，写一个函数向这个列表中插入一个新元素 insertVal ，使这个列表仍然是循环升序的。
 * <p>
 * 给定的可以是这个列表中任意一个顶点的指针，并不一定是这个列表中最小元素的指针。
 * <p>
 * 如果有多个满足条件的插入位置，可以选择任意一个位置插入新的值，插入后整个列表仍然保持有序。
 * <p>
 * 如果列表为空（给定的节点是 null），需要创建一个循环有序列表并返回这个节点。否则。请返回原先给定的节点。
 * <p>
 * 示例 1：
 * 输入：head = [3,4,1], insertVal = 2
 * 输出：[3,4,1,2]
 * 解释：在上图中，有一个包含三个元素的循环有序列表，你获得值为 3 的节点的指针，我们需要向表中插入元素 2 。新插入的节点应该在 1 和 3 之间，插入之后，整个列表如上图所示，最后返回节点 3 。
 * <p>
 * 示例 2：
 * 输入：head = [], insertVal = 1
 * 输出：[1]
 * 解释：列表为空（给定的节点是 null），创建一个循环有序列表并返回这个节点。
 * <p>
 * 示例 3：
 * 输入：head = [1], insertVal = 0
 * 输出：[1,0]
 * <p>
 * 提示：
 * 0 <= Number of Nodes <= 5 * 10^4
 * -10^6 <= Node.val <= 10^6
 * -10^6 <= insertVal <= 10^6
 * <p>
 * 注意：本题与主站 708 题相同： https://leetcode-cn.com/problems/insert-into-a-sorted-circular-linked-list/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/4ueAj6
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2022/6/18 13:53
 */
public class SolutionOfferII29 {
    /**
     * // Definition for a Node.
     * | class Node {
     * |     public int val;
     * |     public Node next;
     * |
     * |     public Node() {}
     * |
     * |     public Node(int _val) {
     * |         val = _val;
     * |     }
     * |
     * |     public Node(int _val, Node _next) {
     * |         val = _val;
     * |         next = _next;
     * |     }
     * | };
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：40.8 MB, 在所有 Java 提交中击败了 63.22% 的用户
     * 通过测试用例： 106 / 106
     *
     * @param head
     * @param insertVal
     * @return
     */
    public Node insert(Node head, int insertVal) {
        if (head == null) {
            Node node = new Node(insertVal);
            node.next = node;
            return node;
        } else if (head.next == head) {
            head.next = new Node(insertVal, head);
            return head;
        }
        Node less = head;
        Node more = head.next;
        while (more != head && !pointToInsert(less, more, insertVal)) {
            less = less.next;
            more = more.next;
        }
        less.next = new Node(insertVal, more);
        return head;
    }

    private boolean pointToInsert(Node less, Node more, int insertVal) {
        return (less.val <= insertVal && more.val >= insertVal)
                || (less.val > more.val && (insertVal <= more.val || insertVal >= less.val));
    }

    // Definition for a Node.
    class Node {
        public int val;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }
}
