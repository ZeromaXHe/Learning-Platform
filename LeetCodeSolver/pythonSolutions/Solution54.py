from typing import List


class Solution:
    """
    54.螺旋矩阵 | 难度：中等 | 标签：数组
    给你一个 m 行 n 列的矩阵  matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。

    示例 1：
    输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
    输出：[1,2,3,6,9,8,7,4,5]

    示例 2：
    输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
    输出：[1,2,3,4,8,12,11,10,9,5,6,7]

    提示：
    m == matrix.length
    n == matrix[i].length
    1 <= m, n <= 10
    -100 <= matrix[i][j] <= 100

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/spiral-matrix
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    """

    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        """
        执行用时： 44 ms , 在所有 Python3 提交中击败了 28.71% 的用户
        内存消耗： 15 MB , 在所有 Python3 提交中击败了 13.58% 的用户
        很慢，待优化
        :param matrix:
        :return:
        """
        list = []
        minCol = 0
        minRow = 0
        maxCol = len(matrix[0])
        maxRow = len(matrix)
        while minCol < maxCol:
            for i in range(minCol, maxCol, 1):
                list.append(matrix[minRow][i])
            minRow += 1
            if minRow >= maxRow:
                break
            for i in range(minRow, maxRow, 1):
                list.append(matrix[i][maxCol - 1])
            maxCol -= 1
            if maxCol <= minCol:
                break
            for i in range(maxCol - 1, minCol - 1, -1):
                list.append(matrix[maxRow - 1][i])
            maxRow -= 1
            if maxRow <= minRow:
                break
            for i in range(maxRow - 1, minRow - 1, -1):
                list.append(matrix[i][minCol])
            minCol += 1
        return list
