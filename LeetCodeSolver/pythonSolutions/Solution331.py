class Solution:
    """
    331.验证二叉树的前序序列化 | 难度：中等 | 标签：栈
    序列化二叉树的一种方法是使用前序遍历。当我们遇到一个非空节点时，我们可以记录下这个节点的值。如果它是一个空节点，我们可以使用一个标记值记录，例如 #。
    <p>
    |      _9_
    |     /   \
    |    3     2
    |   / \   / \
    |  4   1  #  6
    | / \ / \   / \
    | # # # #   # #
    例如，上面的二叉树可以被序列化为字符串 "9,3,4,#,#,1,#,#,2,#,6,#,#"，其中 # 代表一个空节点。
    <p>
    给定一串以逗号分隔的序列，验证它是否是正确的二叉树的前序序列化。编写一个在不重构树的条件下的可行算法。
    <p>
    每个以逗号分隔的字符或为一个整数或为一个表示 null 指针的 '#' 。
    <p>
    你可以认为输入格式总是有效的，例如它永远不会包含两个连续的逗号，比如 "1,,3" 。
    <p>
    示例 1:
    输入: "9,3,4,#,#,1,#,#,2,#,6,#,#"
    输出: true
    <p>
    示例 2:
    输入: "1,#"
    输出: false
    <p>
    示例 3:
    输入: "9,#,#,1"
    输出: false
    <p>
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/verify-preorder-serialization-of-a-binary-tree
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    """

    def isValidSerialization(self, preorder: str) -> bool:
        """
        python自己写的循环一般都巨慢，不知道是不是字符串取第n位这种操作写成preorder[i]在python效率低？
        执行用时： 52 ms , 在所有 Python3 提交中击败了 18.13% 的用户
        内存消耗： 14.9 MB , 在所有 Python3 提交中击败了 16.51% 的用户
        :param preorder:
        :return:
        """
        count = 1
        i = 0
        len_str = len(preorder)
        while i < len_str:
            c = preorder[i]
            if c == ',':
                i += 1
                continue
            if c == '#':
                count -= 1
                if count == 0:
                    return i == len_str - 1
            else:
                while i + 1 < len_str and preorder[i + 1] != '#' and preorder[i + 1] != ',':
                    i += 1
                count += 1
            i += 1
        return count == 0

    def isValidSerialization_comment_ans(self, preorder: str) -> bool:
        """
        题解区的别人的题解
        执行用时： 40 ms , 在所有 Python3 提交中击败了 81.56% 的用户
        内存消耗： 14.7 MB , 在所有 Python3 提交中击败了 79.05% 的用户
        :param preorder:
        :return:
        """
        nodes = preorder.split(',')
        diff = 1
        for node in nodes:
            diff -= 1
            if diff < 0:
                return False
            if node != '#':
                diff += 2
        return diff == 0


if __name__ == "__main__":
    print(Solution().isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"))  # True
    print(Solution().isValidSerialization("1,#"))  # False
    print(Solution().isValidSerialization("9,#,#,1"))  # False
    print(Solution().isValidSerialization("9,#,92,#,#"))  # True
