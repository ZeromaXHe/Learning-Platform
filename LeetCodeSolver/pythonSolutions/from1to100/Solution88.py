from typing import List


class Solution:
    """
    88.合并两个有序数组 | 难度：简单 | 标签：数组、双指针
    给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
    <p>
    初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。
    <p>
    示例 1：
    输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
    输出：[1,2,2,3,5,6]
    <p>
    示例 2：
    输入：nums1 = [1], m = 1, nums2 = [], n = 0
    输出：[1]
    <p>
    提示：
    nums1.length == m + n
    nums2.length == n
    0 <= m, n <= 200
    1 <= m + n <= 200
    -10^9 <= nums1[i], nums2[i] <= 10^9
    <p>
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/merge-sorted-array
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    """

    def merge(self, nums1: List[int], m: int, nums2: List[int], n: int) -> None:
        """
        执行用时： 20 ms , 在所有 Python3 提交中击败了 99.99% 的用户
        内存消耗： 14.8 MB , 在所有 Python3 提交中击败了 74.93% 的用户
        :param nums1:
        :param m:
        :param nums2:
        :param n:
        :return:
        """
        """
        Do not return anything, modify nums1 in-place instead.
        """
        for i in range(m):
            nums1[n + m - 1 - i] = nums1[m - 1 - i]
        index1 = n
        index2 = 0
        index = 0
        while index1 < m + n and index2 < n:
            if nums2[index2] <= nums1[index1]:
                nums1[index] = nums2[index2]
                index += 1
                index2 += 1
            else:
                nums1[index] = nums1[index1]
                index += 1
                index1 += 1
        if index1 == m + n:
            while index2 < n:
                nums1[index] = nums2[index2]
                index += 1
                index2 += 1
