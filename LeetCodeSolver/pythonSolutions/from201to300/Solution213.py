from typing import List


class Solution:
    """
    213.打家劫舍II | 难度：中等 | 标签：动态规划
    你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
    <p>
    给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，能够偷窃到的最高金额。
    <p>
    示例 1：
    输入：nums = [2,3,2]
    输出：3
    解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
    <p>
    示例 2：
    输入：nums = [1,2,3,1]
    输出：4
    解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
         偷窃到的最高金额 = 1 + 3 = 4 。
    <p>
    示例 3：
    输入：nums = [0]
    输出：0
    <p>
    提示：
    1 <= nums.length <= 100
    0 <= nums[i] <= 1000
    <p>
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/house-robber-ii
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    """

    def rob(self, nums: List[int]) -> int:
        """
        执行用时： 44 ms , 在所有 Python3 提交中击败了 36.21% 的用户
        内存消耗： 15 MB , 在所有 Python3 提交中击败了 17.47% 的用户
        :param nums:
        :return:
        """
        n = len(nums)
        if n == 1:
            return nums[0]
        if n == 2:
            return max(nums[1], nums[0])
        dp_no_rob_first = [0] * n
        dp_rob_first = [0] * n
        dp_no_rob_first[0] = 0
        dp_no_rob_first[1] = nums[1]
        dp_rob_first[0] = nums[0]
        dp_rob_first[1] = max(nums[1], nums[0])
        for i in range(2, n):
            dp_rob_first[i] = max(dp_rob_first[i - 2] + nums[i], dp_rob_first[i - 1])
            dp_no_rob_first[i] = max(dp_no_rob_first[i - 2] + nums[i], dp_no_rob_first[i - 1])
        return max(dp_no_rob_first[n - 1], dp_rob_first[n - 2])
