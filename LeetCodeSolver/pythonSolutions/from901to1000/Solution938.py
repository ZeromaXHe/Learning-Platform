class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    """
    938.二叉搜索树的范围和 | 难度：简单 | 标签：树、深度优先搜索、递归
    给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
    <p>
    示例 1：
    输入：root = [10,5,15,3,7,null,18], low = 7, high = 15
    输出：32
    <p>
    示例 2：
    输入：root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
    输出：23
    <p>
    提示：
    树中节点数目在范围 [1, 2 * 10^4] 内
    1 <= Node.val <= 10^5
    1 <= low <= high <= 10^5
    所有 Node.val 互不相同
    <p>
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/range-sum-of-bst
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    """

    def rangeSumBST(self, root: TreeNode, low: int, high: int) -> int:
        """
        执行用时： 284 ms , 在所有 Python3 提交中击败了 20.00% 的用户
        内存消耗： 23.1 MB , 在所有 Python3 提交中击败了 24.76% 的用户
        :param root:
        :param low:
        :param high:
        :return:
        """
        if root is None:
            return 0
        result = 0
        if low <= root.val <= high:
            result += root.val
            result += self.rangeSumBST(root.left, low, high)
            result += self.rangeSumBST(root.right, low, high)
        elif root.val < low:
            result += self.rangeSumBST(root.right, low, high)
        else:
            result += self.rangeSumBST(root.left, low, high)
        return result
