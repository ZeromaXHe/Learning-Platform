from typing import List


class Solution:
    """
    59.螺旋矩阵II | 难度：中等 | 标签：数组
    给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。

    示例 1：
    输入：n = 3
    输出：[[1,2,3],[8,9,4],[7,6,5]]

    示例 2：
    输入：n = 1
    输出：[[1]]

    提示：
    1 <= n <= 20

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/spiral-matrix-ii
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    """

    def generateMatrix(self, n: int) -> List[List[int]]:
        """
        执行用时： 44 ms , 在所有 Python3 提交中击败了 34.77% 的用户
        内存消耗： 15 MB , 在所有 Python3 提交中击败了 16.27% 的用户
        :param n:
        :return:
        """
        # python初始化二维数组
        result = [[0] * n for i in range(n)]

        minCol = 0
        maxCol = n
        minRow = 0
        maxRow = n
        num = 1
        while minCol < maxCol:
            for i in range(minCol, maxCol, 1):
                result[minRow][i] = num
                num += 1
            minRow += 1
            if minRow >= maxRow:
                break
            for i in range(minRow, maxRow, 1):
                result[i][maxCol - 1] = num
                num += 1
            maxCol -= 1
            if maxCol <= minCol:
                break
            for i in range(maxCol - 1, minCol - 1, -1):
                result[maxRow - 1][i] = num
                num += 1
            maxRow -= 1
            if maxRow <= minRow:
                break
            for i in range(maxRow - 1, minRow - 1, -1):
                result[i][minCol] = num
                num += 1
            minCol += 1
        return result
