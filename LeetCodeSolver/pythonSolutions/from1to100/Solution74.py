from typing import List


class Solution:
    """
    74.搜索二维矩阵 | 难度：中等 | 标签：数组、二分查找
    编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
    <p>
    每行中的整数从左到右按升序排列。
    每行的第一个整数大于前一行的最后一个整数。
    <p>
    示例 1：
    输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
    输出：true
    <p>
    示例 2：
    输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
    输出：false
    <p>
    提示：
    m == matrix.length
    n == matrix[i].length
    1 <= m, n <= 100
    -10^4 <= matrix[i][j], target <= 10^4
    <p>
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/search-a-2d-matrix
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    """

    def searchMatrix(self, matrix: List[List[int]], target: int) -> bool:
        """
        执行用时： 44 ms , 在所有 Python3 提交中击败了 36.61% 的用户
        内存消耗： 15.1 MB , 在所有 Python3 提交中击败了 49.64% 的用户
        :param matrix:
        :param target:
        :return:
        """
        x = 0
        y = len(matrix[0]) - 1
        # 将len(matrix)提取为变量n的话，效率变高：
        # 执行用时： 36 ms , 在所有 Python3 提交中击败了 83.96% 的用户
        # 内存消耗： 14.8 MB , 在所有 Python3 提交中击败了 99.17% 的用户
        while x < len(matrix) and y >= 0:
            if matrix[x][y] == target:
                return True
            elif matrix[x][y] > target:
                y -= 1
            else:
                x += 1
        return False
