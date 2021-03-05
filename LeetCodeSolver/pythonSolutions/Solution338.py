from typing import List

"""
338.比特位计数 | 难度：中等 | 标签：位运算、动态规划
给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。

示例 1:
输入: 2
输出: [0,1,1]

示例 2:
输入: 5
输出: [0,1,1,2,1,2]

进阶:
给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
要求算法的空间复杂度为O(n)。
你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/counting-bits
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""


class Solution:
    """
    执行用时： 68 ms , 在所有 Python3 提交中击败了 99.75% 的用户
    内存消耗： 21.1 MB , 在所有 Python3 提交中击败了 80.25% 的用户
    """

    def countBits(self, num: int) -> List[int]:
        result = [0] * (num + 1)
        if num > 0:
            result[1] = 1
            for i in range(2, num + 1):
                result[i] = result[i // 2] + (1 if i % 2 == 1 else 0)
        return result


if __name__ == '__main__':
    print(Solution().countBits(5))
