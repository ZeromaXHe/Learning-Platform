from typing import List


class Solution:
    """
    363.矩形区域不超过 K 的最大数值和 | 难度：困难 | 标签：队列、二分查找、动态规划
    给你一个 m x n 的矩阵 matrix 和一个整数 k ，找出并返回矩阵内部矩形区域的不超过 k 的最大数值和。
    <p>
    题目数据保证总会存在一个数值和不超过 k 的矩形区域。
    <p>
    示例 1：
    输入：matrix = [[1,0,1],[0,-2,3]], k = 2
    输出：2
    解释：蓝色边框圈出来的矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
    <p>
    示例 2：
    输入：matrix = [[2,2,-1]], k = 3
    输出：3
    <p>
    提示：
    m == matrix.length
    n == matrix[i].length
    1 <= m, n <= 100
    -100 <= matrix[i][j] <= 100
    -10^5 <= k <= 10^5
    <p>
    进阶：如果行数远大于列数，该如何设计解决方案？
    <p>
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/max-sum-of-rectangle-no-larger-than-k
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    """

    def maxSumSubmatrix(self, matrix: List[List[int]], k: int) -> int:
        """
        执行用时： 2284 ms , 在所有 Python3 提交中击败了 20.18% 的用户
        内存消耗： 15.4 MB , 在所有 Python3 提交中击败了 83.23% 的用户
        :param matrix:
        :param k:
        :return:
        """
        m = len(matrix)
        n = len(matrix[0])
        res = -1E10
        n_bigger_than_m = n > m
        max_len = max(m, n)
        min_len = min(m, n)
        for i in range(0, min_len):
            sums = [0] * max_len
            for j in range(i, min_len):
                for y in range(0, max_len):
                    sums[y] += matrix[j][y] if n_bigger_than_m else matrix[y][j]
                curr = 0
                max_sum = sums[0]
                for sum1 in sums:
                    curr = max(sum1, curr + sum1)
                    max_sum = max(curr, max_sum)
                    if max_sum == k:
                        return max_sum
                if max_sum < k:
                    res = max(max_sum, res)
                else:
                    for a in range(0, max_len):
                        curr_sum = 0
                        for b in range(a, max_len):
                            curr_sum += sums[b]
                            if curr_sum <= k:
                                res = max(curr_sum, res)
                    if res == k:
                        return res
        return res

# 23 / 27 个通过测试用例
# 状态：解答错误
# 提交时间：3 分钟前
# 输入：
# [[5,-4,-3,4],[-3,-4,4,5],[5,1,5,-4]]
# -2
# 输出：-1
# 预期：-2
