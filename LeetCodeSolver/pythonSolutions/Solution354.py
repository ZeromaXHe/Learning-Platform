from typing import List


# 354.俄罗斯套娃信封问题 | 难度：困难 | 标签：二分查找、动态规划
# 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
# 
# 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
# 
# 说明:
# 不允许旋转信封。
# 
# 示例:
# 输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
# 输出: 3 
# 解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
# 
# 来源：力扣（LeetCode）
# 链接：https://leetcode-cn.com/problems/russian-doll-envelopes
# 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution:
    # 执行用时： 184 ms , 在所有 Python3 提交中击败了 73.50% 的用户
    # 内存消耗： 16.9 MB , 在所有 Python3 提交中击败了 67.82% 的用户
    def maxEnvelopes(self, envelopes: List[List[int]]) -> int:
        n = len(envelopes)
        if n == 0:
            return 0
        # 先按e[0]正序排序，如果相同就按e[1]逆序排序
        envelopes.sort(key=lambda e: (e[0], -e[1]))
        height = []
        for envelope in envelopes:
            height.append(envelope[1])
        return self.lengthOfLIS(height)

    # 最长递增子序列 模板
    def lengthOfLIS(self, nums: List[int]) -> int:
        piles = 0
        n = len(nums)
        top = [0] * n
        for i in range(n):
            # 要处理的扑克牌
            poker = nums[i]
            left = 0
            right = piles
            # 二分查找插入位置
            while left < right:
                mid = (left + right) // 2
                if top[mid] >= poker:
                    right = mid
                else:
                    left = mid + 1
            if left == piles:
                piles += 1
            # 把这张牌放到牌堆顶
            top[left] = poker
        # 牌堆数就是 LIS 长度
        return piles
