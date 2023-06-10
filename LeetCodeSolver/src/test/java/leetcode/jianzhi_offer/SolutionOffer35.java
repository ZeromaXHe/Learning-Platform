package leetcode.jianzhi_offer;

import java.util.HashMap;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer 35. 复杂链表的复制 | 难度：中等 | 标签：哈希表、链表
 * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
 * <p>
 * 示例 1：
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 * <p>
 * 示例 2：
 * 输入：head = [[1,1],[2,1]]
 * 输出：[[1,1],[2,1]]
 * <p>
 * 示例 3：
 * 输入：head = [[3,null],[3,0],[3,null]]
 * 输出：[[3,null],[3,0],[3,null]]
 * <p>
 * 示例 4：
 * 输入：head = []
 * 输出：[]
 * 解释：给定的链表为空（空指针），因此返回 null。
 * <p>
 * 提示：
 * -10000 <= Node.val <= 10000
 * Node.random 为空（null）或指向链表中的节点。
 * 节点数目不超过 1000 。
 * <p>
 * 注意：本题与主站 138 题相同：https://leetcode-cn.com/problems/copy-list-with-random-pointer/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/fu-za-lian-biao-de-fu-zhi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/10 18:19
 */
public class SolutionOffer35 {
    /**
     * 官方题解方法二感觉很妙，值得一看，相当于是把链表的相对位置关系当成一个哈希表的作用了。
     * 自己就直接哈希表做的
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：42 MB, 在所有 Java 提交中击败了 19.17% 的用户
     * 通过测试用例：18 / 18
     *
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        HashMap<Node, Node> map = new HashMap<>();
        Node dummy = new Node(-1);
        Node ptr = head;
        Node ptrNew = dummy;
        while (ptr != null) {
            ptrNew.next = new Node(ptr.val);
            ptrNew.next.random = ptr.random;
            map.put(ptr, ptrNew.next);
            ptr = ptr.next;
            ptrNew = ptrNew.next;
        }
        ptr = dummy.next;
        while (ptr != null) {
            if (ptr.random != null) {
                ptr.random = map.get(ptr.random);
            }
            ptr = ptr.next;
        }
        return dummy.next;
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
