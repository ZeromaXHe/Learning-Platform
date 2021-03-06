from typing import List

"""
503.下一个更大元素II | 难度：中等 | 标签：栈
给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。

示例 1:
输入: [1,2,1]
输出: [2,-1,2]
解释: 第一个 1 的下一个更大的数是 2；
数字 2 找不到下一个更大的数；
第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
注意: 输入数组的长度不会超过 10000。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/next-greater-element-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""


class Solution:
    """
    执行用时： 308 ms , 在所有 Python3 提交中击败了 17.86% 的用户
    内存消耗： 16.2 MB , 在所有 Python3 提交中击败了 67.51% 的用户
    """

    def nextGreaterElements(self, nums: List[int]) -> List[int]:
        n = len(nums)
        result = [-1] * n
        stack = [0]
        for i in range(1, 2 * n - 1):
            if nums[i % n] > nums[(i - 1) % n]:
                while stack and nums[stack[-1]] < nums[i % n]:
                    result[stack.pop()] = nums[i % n]
            stack.append(i % n)
        return result


if __name__ == "__main__":
    print(Solution().nextGreaterElements([1, 2, 1]))
