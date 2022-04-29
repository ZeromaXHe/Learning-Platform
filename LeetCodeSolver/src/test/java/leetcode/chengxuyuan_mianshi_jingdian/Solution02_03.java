package leetcode.chengxuyuan_mianshi_jingdian;

/**
 * @Author: zhuxi
 * @Time: 2022/4/29 14:19
 * @Description: 面试题 02.03. 删除中间节点 | 难度：简单 | 标签：链表
 * 若链表中的某个节点，既不是链表头节点，也不是链表尾节点，则称其为该链表的「中间节点」。
 * <p>
 * 假定已知链表的某一个中间节点，请实现一种算法，将该节点从链表中删除。
 * <p>
 * 例如，传入节点 c（位于单向链表 a->b->c->d->e->f 中），将其删除后，剩余链表为 a->b->d->e->f
 * <p>
 * 示例：
 * 输入：节点 5 （位于单向链表 4->5->1->9 中）
 * 输出：不返回任何数据，从链表中删除传入的节点 5，使链表变为 4->1->9
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/delete-middle-node-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution02_03 {
    /**
     * Definition for singly-linked list.
     * | public class ListNode {
     * |     int val;
     * |     ListNode next;
     * |     ListNode(int x) { val = x; }
     * | }
     * <p>
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 40.9 MB , 在所有 Java 提交中击败了 46.06% 的用户
     * 通过测试用例： 41 / 41
     * <p>
     * 这题有点无聊…… 描述也是不清不楚的……
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
