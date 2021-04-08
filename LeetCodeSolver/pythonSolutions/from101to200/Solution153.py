from typing import List


class Solution:
    """
    153.寻找旋转排序数组中的最小值 | 难度：中等 | 标签：数组、二分查找
    已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
    若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
    若旋转 4 次，则可以得到 [0,1,2,4,5,6,7]
    注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
    <p>
    给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
    <p>
    示例 1：
    输入：nums = [3,4,5,1,2]
    输出：1
    解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
    <p>
    示例 2：
    输入：nums = [4,5,6,7,0,1,2]
    输出：0
    解释：原数组为 [0,1,2,4,5,6,7] ，旋转 4 次得到输入数组。
    <p>
    示例 3：
    输入：nums = [11,13,15,17]
    输出：11
    解释：原数组为 [11,13,15,17] ，旋转 4 次得到输入数组。
    <p>
    提示：
    n == nums.length
    1 <= n <= 5000
    -5000 <= nums[i] <= 5000
    nums 中的所有整数 互不相同
    nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
    <p>
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    """

    def findMin(self, nums: List[int]) -> int:
        """
        执行用时： 36 ms , 在所有 Python3 提交中击败了 82.56% 的用户
        内存消耗： 15.1 MB , 在所有 Python3 提交中击败了 43.35% 的用户
        :param nums:
        :return:
        """
        low = 0
        high = len(nums) - 1
        while low < high:
            pivot = low + (high - low) // 2
            if nums[pivot] < nums[high]:
                high = pivot
            else:
                low = pivot + 1
        return nums[low]
