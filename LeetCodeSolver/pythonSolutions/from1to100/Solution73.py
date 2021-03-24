from typing import List


class Solution:
    """
    73.矩阵置零 | 难度：中等 | 标签：数组
    给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。

    示例 1:
    输入:
    [
      [1,1,1],
      [1,0,1],
      [1,1,1]
    ]
    输出:
    [
      [1,0,1],
      [0,0,0],
      [1,0,1]
    ]

    示例 2:
    输入:
    [
      [0,1,2,0],
      [3,4,5,2],
      [1,3,1,5]
    ]
    输出:
    [
      [0,0,0,0],
      [0,4,5,0],
      [0,3,1,0]
    ]

    进阶:
    一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
    一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
    你能想出一个常数空间的解决方案吗？

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/set-matrix-zeroes
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    """

    def setZeroes(self, matrix: List[List[int]]) -> None:
        """
        执行用时： 48 ms , 在所有 Python3 提交中击败了 73.79% 的用户
        内存消耗： 15.1 MB , 在所有 Python3 提交中击败了 51.65% 的用户

        :param matrix:
        :return:
        """
        """
        Do not return anything, modify matrix in-place instead.
        """
        m = len(matrix)
        n = len(matrix[0])
        flag_col0 = False
        flag_row0 = False
        # 第一列是否有0
        for i in range(m):
            if matrix[i][0] == 0:
                flag_col0 = True
        # 第一行是否有0
        for j in range(n):
            if matrix[0][j] == 0:
                flag_row0 = True
        # 将后面的行数列是否有0的信息存储在第一行和第一列中
        for i in range(1, m):
            for j in range(1, n):
                if matrix[i][j] == 0:
                    matrix[i][0] = matrix[0][j] = 0
        # 根据第一行和第一列去更新其他数据
        for i in range(1, m):
            for j in range(1, n):
                # or 的优先级比 == 低，但是 | 的优先级比 == 高。
                # 所以 or 可以不加两边括号，| 必须加。
                if (matrix[i][0] == 0) or (matrix[0][j] == 0):
                    matrix[i][j] = 0
        # 更新第一列
        if flag_col0:
            for i in range(m):
                matrix[i][0] = 0
        # 更新第一行
        if flag_row0:
            for j in range(n):
                matrix[0][j] = 0

# Python 运算符优先级和结合性一览表
# 运算符说明	Python运算符	            优先级	结合性	优先级顺序
# 小括号	    ( )	                    19	    无	    高
# 索引运算符	x[i] 或 x[i1: i2 [:i3]]	18	    左
# 属性访问	x.attribute	            17	    左
# 乘方	    **	                    16	    右
# 按位取反	~	                    15	    右
# 符号运算符	+（正号）、-（负号）	    14	    右
# 乘除	    *、/、//、%	            13	    左
# 加减	    +、-	                12	    左
# 位移	    >>、<<	                11	    左
# 按位与	    &	                    10	    右
# 按位异或	^	                    9	    左
# 按位或	    |	                    8	    左
# 比较运算符	==、!=、>、>=、<、<= 	7	    左
# is 运算符	is、is not	            6	    左
# in 运算符	in、not in	            5	    左
# 逻辑非	    not	                    4	    右
# 逻辑与	    and	                    3	    左
# 逻辑或	    or	                    2	    左
# 逗号运算符	exp1, exp2	            1	    左       低
