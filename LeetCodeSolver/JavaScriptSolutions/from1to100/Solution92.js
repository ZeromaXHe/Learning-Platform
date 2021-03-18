/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
function ListNode(val, next) {
    this.val = (val === undefined ? 0 : val)
    this.next = (next === undefined ? null : next)
}

/**
 * 92.反转链表II | 难度：中等 | 标签：链表
 * 给你单链表的头节点 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 * <p>
 * 示例 1：
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 * <p>
 * 示例 2：
 * 输入：head = [5], left = 1, right = 1
 * 输出：[5]
 * <p>
 * 提示：
 * 链表中节点数目为 n
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 执行用时： 84 ms , 在所有 JavaScript 提交中击败了 59.22% 的用户
 * 内存消耗： 37.9 MB , 在所有 JavaScript 提交中击败了 54.30% 的用户
 * @param {ListNode} head
 * @param {number} left
 * @param {number} right
 * @return {ListNode}
 */
var reverseBetween = function (head, left, right) {
    if (left === right) {
        return head;
    }
    var dummy = new ListNode(0, head);
    var beforePtr = dummy;
    var afterPtr = dummy;
    var count = 0;
    while (count < left - 1) {
        beforePtr = beforePtr.next;
        afterPtr = afterPtr.next;
        count++;
    }
    while (count <= right) {
        afterPtr = afterPtr.next;
        count++;
    }
    var end = beforePtr.next;
    var ptr = beforePtr.next;
    beforePtr.next = null;
    var temp;
    var after = ptr.next;
    while (ptr !== afterPtr) {
        temp = beforePtr.next;
        after = ptr.next;
        beforePtr.next = ptr;
        ptr.next = temp;
        ptr = after;
    }
    end.next = afterPtr;
    return dummy.next;
};