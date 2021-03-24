# python的类好像必须声明在引用之前？
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    """
    92.反转链表II | 难度：中等 | 标签：链表
    给你单链表的头节点 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
    <p>
    示例 1：
    输入：head = [1,2,3,4,5], left = 2, right = 4
    输出：[1,4,3,2,5]
    <p>
    示例 2：
    输入：head = [5], left = 1, right = 1
    输出：[5]
    <p>
    提示：
    链表中节点数目为 n
    1 <= n <= 500
    -500 <= Node.val <= 500
    1 <= left <= right <= n
    <p>
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    """

    def reverseBetween(self, head: ListNode, left: int, right: int) -> ListNode:
        """
        执行用时： 36 ms , 在所有 Python3 提交中击败了 83.68% 的用户
        内存消耗： 15 MB , 在所有 Python3 提交中击败了 36.57% 的用户
        :param head:
        :param left:
        :param right:
        :return:
        """
        if left == right:
            return head
        dummy = ListNode(0, head)
        before_ptr = dummy
        after_ptr = dummy
        count = 0
        while count < left - 1:
            before_ptr = before_ptr.next
            after_ptr = after_ptr.next
            count += 1
        while count <= right:
            after_ptr = after_ptr.next
            count += 1
        end = before_ptr.next
        ptr = before_ptr.next
        before_ptr.next = None
        while ptr != after_ptr:
            temp = before_ptr.next
            after = ptr.next
            before_ptr.next = ptr
            ptr.next = temp
            ptr = after
        end.next = after_ptr
        return dummy.next
