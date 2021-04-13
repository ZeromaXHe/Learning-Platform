# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    """
    783.二叉搜索树节点最小距离 | 难度：简单 | 标签：树、深度优先搜索、递归
    给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
    <p>
    注意：本题与 530：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/ 相同
    <p>
    示例 1：
    输入：root = [4,2,6,1,3]
    输出：1
    <p>
    示例 2：
    输入：root = [1,0,48,null,null,12,49]
    输出：1
    <p>
    提示：
    树中节点数目在范围 [2, 100] 内
    0 <= Node.val <= 10^5
    <p>
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    """

    def minDiffInBST(self, root: TreeNode) -> int:
        """
        执行用时： 44 ms , 在所有 Python3 提交中击败了 44.29% 的用户
        内存消耗： 15 MB , 在所有 Python3 提交中击败了 36.27% 的用户
        :param root:
        :return:
        """
        stack = []
        index = root
        while index is not None:
            stack.append(index)
            index = index.left
        pre = -100000
        result = 100000
        while stack:
            popped = stack.pop()
            if popped.val - pre < result:
                result = popped.val - pre
            pre = popped.val
            index = popped.right
            while index is not None:
                stack.append(index)
                index = index.left
        return result
