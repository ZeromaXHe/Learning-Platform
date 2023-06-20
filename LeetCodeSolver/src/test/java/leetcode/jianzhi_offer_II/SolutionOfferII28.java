package leetcode.jianzhi_offer_II;

import javafx.util.Pair;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer II 028. 展平多级双向链表 | 难度：中等 | 标签：深度优先搜索、链表、双向链表
 * 多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，可能指向单独的双向链表。
 * 这些子列表也可能会有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。
 * <p>
 * 给定位于列表第一级的头节点，请扁平化列表，即将这样的多级双向链表展平成普通的双向链表，使所有结点出现在单级双链表中。
 * <p>
 * 示例 1：
 * 输入：head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
 * 输出：[1,2,3,7,8,11,12,9,10,4,5,6]
 * 解释：
 * <p>
 * 输入的多级列表如下图所示：
 * <p>
 * 扁平化后的链表如下图：
 * <p>
 * 示例 2：
 * 输入：head = [1,2,null,3]
 * 输出：[1,3,2]
 * 解释：
 * <p>
 * 输入的多级列表如下图所示：
 * <p>
 * |  1---2---NULL
 * |  |
 * |  3---NULL
 * <p>
 * 示例 3：
 * 输入：head = []
 * 输出：[]
 * <p>
 * 如何表示测试用例中的多级链表？
 * <p>
 * 以 示例 1 为例：
 * <p>
 * | 1---2---3---4---5---6--NULL
 * |         |
 * |         7---8---9---10--NULL
 * |             |
 * |             11--12--NULL
 * 序列化其中的每一级之后：
 * <p>
 * [1,2,3,4,5,6,null]
 * [7,8,9,10,null]
 * [11,12,null]
 * 为了将每一级都序列化到一起，我们需要每一级中添加值为 null 的元素，以表示没有节点连接到上一级的上级节点。
 * <p>
 * [1,2,3,4,5,6,null]
 * [null,null,7,8,9,10,null]
 * [null,11,12,null]
 * 合并所有序列化结果，并去除末尾的 null 。
 * <p>
 * [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
 * <p>
 * 提示：
 * 节点数目不超过 1000
 * 1 <= Node.val <= 10^5
 * <p>
 * 注意：本题与主站 430 题相同： https://leetcode-cn.com/problems/flatten-a-multilevel-doubly-linked-list/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/Qv1Da2
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/20 14:19
 */
public class SolutionOfferII28 {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：39.4 MB, 在所有 Java 提交中击败了 79.94% 的用户
     * 通过测试用例：26 / 26
     *
     * @param head
     * @return
     */
    public Node flatten(Node head) {
        return flatten2(head).getKey();
    }

    private Pair<Node, Node> flatten2(Node head) {
        Node end = null;
        Node ptr = head;
        while (ptr != null) {
            end = ptr;
            if (ptr.child != null) {
                Pair<Node, Node> pair = flatten2(ptr.child);
                if (ptr.next != null) {
                    ptr.next.prev = pair.getValue();
                    pair.getValue().next = ptr.next;
                }
                ptr.next = pair.getKey();
                pair.getKey().prev = ptr;
                ptr.child = null;
                ptr = pair.getValue().next;
            } else {
                ptr = ptr.next;
            }
        }
        return new Pair<>(head, end);
    }

    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }
}
