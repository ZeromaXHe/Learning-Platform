# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:
    """
    83.删除排序链表中的重复元素 | 难度：简单 | 标签：链表
    存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
    <p>
    返回同样按升序排列的结果链表。
    <p>
    示例 1：
    输入：head = [1,1,2]
    输出：[1,2]
    <p>
    示例 2：
    输入：head = [1,1,2,3,3]
    输出：[1,2,3]
    <p>
    提示：
    链表中节点数目在范围 [0, 300] 内
    -100 <= Node.val <= 100
    题目数据保证链表已经按升序排列
    <p>
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    """

    def deleteDuplicates(self, head: ListNode) -> ListNode:
        """
        执行用时： 52 ms , 在所有 Python3 提交中击败了 38.10% 的用户
        内存消耗： 14.9 MB , 在所有 Python3 提交中击败了 58.08% 的用户
        :param head:
        :return:
        """
        ptr = head
        while ptr and ptr.next:
            pre = ptr
            ptr = ptr.next
            while ptr and ptr.val == pre.val:
                ptr = ptr.next
            pre.next = ptr
        return head
