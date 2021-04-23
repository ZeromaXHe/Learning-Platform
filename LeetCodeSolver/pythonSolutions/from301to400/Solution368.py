from typing import List


class Solution:
    """
    368.最大整除子集 | 难度：中等 | 标签：数学、动态规划
    给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，子集中每一元素对 (answer[i], answer[j]) 都应当满足：
    answer[i] % answer[j] == 0 ，或
    answer[j] % answer[i] == 0
    如果存在多个有效解子集，返回其中任何一个均可。
    <p>
    示例 1：
    输入：nums = [1,2,3]
    输出：[1,2]
    解释：[1,3] 也会被视为正确答案。
    <p>
    示例 2：
    输入：nums = [1,2,4,8]
    输出：[1,2,4,8]
    <p>
    提示：
    1 <= nums.length <= 1000
    1 <= nums[i] <= 2 * 10^9
    nums 中的所有整数 互不相同
    <p>
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/largest-divisible-subset
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    """

    def largestDivisibleSubset(self, nums: List[int]) -> List[int]:
        """
        执行用时： 384 ms , 在所有 Python3 提交中击败了 38.58% 的用户
        内存消耗： 15.2 MB , 在所有 Python3 提交中击败了 24.93% 的用户
        :param nums:
        :return:
        """
        nums.sort()
        n = len(nums)
        # python 二维数组
        dp = [[0 for i in range(2)] for j in range(n)]
        dp[0][0] = 1
        max_max_count = 1
        max_max_index = 0
        for i in range(1, n):
            max_count = 1
            max_index = i
            for j in range(0, i):
                if nums[i] % nums[j] == 0 and dp[j][0] + 1 > max_count:
                    max_count = dp[j][0] + 1
                    max_index = j
            dp[i][0] = max_count
            dp[i][1] = max_index
            if max_count > max_max_count:
                max_max_count = max_count
                max_max_index = i
        result = [0] * max_max_count
        index = max_max_index
        for i in range(max_max_count - 1, -1, -1):
            result[i] = nums[index]
            index = dp[index][1]
        return result
