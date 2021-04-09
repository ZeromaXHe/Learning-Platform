from typing import List


class Solution:
    """
    145.寻找旋转排序数组中的最小值 II | 难度：困难 | 标签：数组、二分查找
    已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,4,4,5,6,7] 在变化后可能得到：
    若旋转 4 次，则可以得到 [4,5,6,7,0,1,4]
    若旋转 7 次，则可以得到 [0,1,4,4,5,6,7]
    注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
    <p>
    给你一个可能存在 重复 元素值的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
    <p>
    示例 1：
    输入：nums = [1,3,5]
    输出：1
    <p>
    示例 2：
    输入：nums = [2,2,2,0,1]
    输出：0
    <p>
    提示：
    n == nums.length
    1 <= n <= 5000
    -5000 <= nums[i] <= 5000
    nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
    <p>
    进阶：
    这道题是 寻找旋转排序数组中的最小值 的延伸题目。
    允许重复会影响算法的时间复杂度吗？会如何影响，为什么？
    <p>
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    """

    def findMin(self, nums: List[int]) -> int:
        """
        执行用时： 40 ms , 在所有 Python3 提交中击败了 69.14% 的用户
        内存消耗： 15.3 MB , 在所有 Python3 提交中击败了 16.52% 的用户
        :param nums:
        :return:
        """
        low = 0
        high = len(nums) - 1
        while low < high:
            pivot = low + (high - low) // 2
            if nums[pivot] < nums[high]:
                high = pivot
            elif nums[pivot] > nums[high]:
                low = pivot + 1
            else:
                high -= 1
        return nums[low]
