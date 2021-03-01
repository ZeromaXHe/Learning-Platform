from typing import List


# 1.两数之和 | 难度：简单 | 标签：数组、哈希表
# 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 的那两个整数，并返回它们的数组下标。
# 
# 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
# 
# 你可以按任意顺序返回答案。
#
# 示例 1：
# 输入：nums = [2,7,11,15], target = 9
# 输出：[0,1]
# 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
#
# 示例 2：
# 输入：nums = [3,2,4], target = 6
# 输出：[1,2]
#
# 示例 3：
# 输入：nums = [3,3], target = 6
# 输出：[0,1]
#
# 提示：
# 2 <= nums.length <= 10^3
# -10^9 <= nums[i] <= 10^9
# -10^9 <= target <= 10^9
# 只会存在一个有效答案
# 
# 来源：力扣（LeetCode）
# 链接：https://leetcode-cn.com/problems/two-sum
# 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution:
    # 执行用时： 36 ms , 在所有 Python3 提交中击败了 84.56% 的用户
    # 内存消耗： 14.8 MB , 在所有 Python3 提交中击败了 67.14% 的用户
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        # 哈希表
        hashmap = {}
        # enumerate() 函数用于将一个可遍历的数据对象(如列表、元组或字符串)组合为一个索引序列，同时列出数据和数据下标，一般用在 for 循环当中。
        for i, num in enumerate(nums):
            if hashmap.get(target - num) is not None:
                return [hashmap.get(target - num), i]
            hashmap[num] = i  # 这句不能放在if语句之前，解决list中有重复值或target-num=num的情况


if __name__ == '__main__':
    print(Solution().twoSum(nums=[2, 7, 11, 15], target=9))  # [0,1]
    print(Solution().twoSum(nums=[3, 2, 4], target=6))  # [1,2]
    print(Solution().twoSum(nums=[3, 3], target=6))  # [0,1]
