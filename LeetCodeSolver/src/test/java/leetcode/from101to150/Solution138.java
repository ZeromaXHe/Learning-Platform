package leetcode.from101to150;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhuxi
 * @Time: 2022/1/21 13:45
 * @Description: 138. 复制带随机指针的链表 | 难度：中等 | 标签：哈希表、链表
 * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
 * <p>
 * 构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。
 * 新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。
 * 复制链表中的指针都不应指向原链表中的节点 。
 * <p>
 * 例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。
 * <p>
 * 返回复制链表的头节点。
 * <p>
 * 用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
 * <p>
 * val：一个表示 Node.val 的整数。
 * random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
 * 你的代码 只 接受原链表的头节点 head 作为传入参数。
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
 * 0 <= n <= 1000
 * -10000 <= Node.val <= 10000
 * Node.random 为空（null）或指向链表中的节点。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/copy-list-with-random-pointer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution138 {
    @Test
    public void copyRandomListTest() {
        Node node7 = new Node(7);
        Node node13 = new Node(13);
        Node node11 = new Node(11);
        Node node10 = new Node(10);
        Node node1 = new Node(1);
        node7.next = node13;
        node13.next = node11;
        node11.next = node10;
        node10.next = node1;
        node13.random = node7;
        node11.random = node1;
        node10.random = node11;
        node1.random = node7;
        System.out.println(copyRandomList(node7));
    }

    /**
     * | // Definition for a Node.
     * | class Node {
     * |     int val;
     * |     Node next;
     * |     Node random;
     * |
     * |     public Node(int val) {
     * |         this.val = val;
     * |         this.next = null;
     * |         this.random = null;
     * |     }
     * | }
     * <p>
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.1 MB , 在所有 Java 提交中击败了 47.60% 的用户
     * 通过测试用例： 19 / 19
     *
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node ptr = head;
        Map<Node, Node> map = new HashMap<>();
        Node ptr2pre = null;
        while (ptr != null) {
            Node ptr2 = new Node(ptr.val);
            if (ptr2pre != null) {
                ptr2pre.next = ptr2;
            }
            map.put(ptr, ptr2);
            ptr2pre = ptr2;
            ptr = ptr.next;
        }
        ptr = head;
        while (ptr != null) {
            if (ptr.random != null) {
                Node node = map.get(ptr.random);
                map.get(ptr).random = node;
            }
            ptr = ptr.next;
        }
        return map.get(head);
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }

        @Override
        public String toString() {
            return "[" + val + ", " + (random == null ? "null" : random.val) + "], " + next;
        }
    }
}
