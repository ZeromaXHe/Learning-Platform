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
    897.递增顺序搜索树 | 难度：简单 | 标签：树、深度优先遍历、递归
    给你一棵二叉搜索树，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
    <p>
    示例 1：
    输入：root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
    输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
    <p>
    示例 2：
    输入：root = [5,1,7]
    输出：[1,null,5,null,7]
    <p>
    提示：
    树中节点数的取值范围是 [1, 100]
    0 <= Node.val <= 1000
    <p>
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/increasing-order-search-tree
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    """

    def increasingBST(self, root: TreeNode) -> TreeNode:
        """
        执行用时： 44 ms , 在所有 Python3 提交中击败了 41.27% 的用户
        内存消耗： 14.9 MB , 在所有 Python3 提交中击败了 43.44% 的用户
        :param root:
        :return:
        """
        dummy = TreeNode()
        # python 全局变量
        self.last_node = dummy
        # python 调用同类中的方法
        self.inOrderTraverse(root)
        return dummy.right

    def inOrderTraverse(self, root: TreeNode):
        if not root:
            return
        self.inOrderTraverse(root.left)
        # 在中序遍历的过程中修改节点指向
        self.last_node.right = root
        root.left = None
        self.last_node = root

        self.inOrderTraverse(root.right)
