from typing import List


class Solution:
    """
    面试题17.21 直方图的水量 | 难度：困难 | 标签：栈、数组、双指针
    给定一个直方图(也称柱状图)，假设有人从上面源源不断地倒水，最后直方图能存多少水量?直方图的宽度为 1。
    <p>
    上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的直方图，在这种情况下，可以接 6 个单位的水（蓝色部分表示水）。 感谢 Marcos 贡献此图。
    <p>
    示例:
    输入: [0,1,0,2,1,0,1,3,2,1,2,1]
    输出: 6
    <p>
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/volume-of-histogram-lcci
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    """

    def trap(self, height: List[int]) -> int:
        """
        执行用时： 48 ms , 在所有 Python3 提交中击败了 61.21% 的用户
        内存消耗： 15 MB , 在所有 Python3 提交中击败了 64.38% 的用户
        :param height:
        :return:
        """
        left = 0
        right = len(height) - 1
        result = 0
        max_l = 0
        max_r = 0
        while left <= right:
            if max_l < max_r:
                result += max(0, max_l - height[left])
                max_l = max(max_l, height[left])
                left += 1
            else:
                result += max(0, max_r - height[right])
                max_r = max(max_r, height[right])
                right -= 1
        return result
