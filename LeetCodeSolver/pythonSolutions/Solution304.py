from typing import List

"""
304.二维区域和检索 - 矩阵不可变 | 难度：中等 | 标签：动态规划
给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1,col1) ，右下角为 (row2,col2) 。

上图子矩阵左上角(row1, col1) = (2, 1)，右下角(row2, col2) = (4, 3)，该子矩形内元素的总和为 8。

示例：
给定 matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12

提示：
你可以假设矩阵不可变。
会多次调用sumRegion方法。
你可以假设row1 ≤ row2 且col1 ≤ col2 。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/range-sum-query-2d-immutable
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

Your NumMatrix object will be instantiated and called as such:
obj = NumMatrix(matrix)
param_1 = obj.sumRegion(row1,col1,row2,col2)
"""


class NumMatrix:
    """
    执行用时： 80 ms , 在所有 Python3 提交中击败了# 50.99% 的用户
    内存消耗： 17.7 MB , 在所有 Python3 提交中击败了 31.85% 的用户
    """

    def __init__(self, matrix: List[List[int]]):
        if matrix is None or len(matrix) == 0 or len(matrix[0]) == 0:
            self.sum = None
            return
        self.sum = [[0] * (1 + len(matrix[0])) for i in range(1 + len(matrix))]
        for i in range(len(matrix)):
            for j in range(len(matrix[0])):
                if (i == 0) & (j == 0):
                    self.sum[i + 1][j + 1] = matrix[i][j]
                elif i == 0:
                    self.sum[i + 1][j + 1] = self.sum[i + 1][j] + matrix[i][j]
                elif j == 0:
                    self.sum[i + 1][j + 1] = self.sum[i][j + 1] + matrix[i][j]
                else:
                    self.sum[i + 1][j + 1] = self.sum[i + 1][j] + self.sum[i][j + 1] - self.sum[i][j] + matrix[i][j]

    def sumRegion(self, row1: int, col1: int, row2: int, col2: int) -> int:
        if self.sum is None:
            return 0
        return self.sum[row2 + 1][col2 + 1] + self.sum[row1][col1] - self.sum[row2 + 1][col1] - self.sum[row1][col2 + 1]


if __name__ == '__main__':
    matrix = NumMatrix([
        [3, 0, 1, 4, 2],
        [5, 6, 3, 2, 1],
        [1, 2, 0, 1, 5],
        [4, 1, 0, 1, 7],
        [1, 0, 3, 0, 5]
    ])
    print(matrix.sumRegion(2, 1, 4, 3))
    print(matrix.sumRegion(1, 1, 2, 2))
    print(matrix.sumRegion(1, 2, 2, 4))

    matrix = NumMatrix([[]])
