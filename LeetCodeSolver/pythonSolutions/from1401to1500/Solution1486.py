class Solution:
    """
    1486.数组异或操作 | 难度：简单 | 标签：位运算、数组
    给你两个整数，n 和 start 。
    <p>
    数组 nums 定义为：nums[i] = start + 2*i（下标从 0 开始）且 n == nums.length 。
    <p>
    请返回 nums 中所有元素按位异或（XOR）后得到的结果。
    <p>
    示例 1：
    输入：n = 5, start = 0
    输出：8
    解释：数组 nums 为 [0, 2, 4, 6, 8]，其中 (0 ^ 2 ^ 4 ^ 6 ^ 8) = 8 。
    "^" 为按位异或 XOR 运算符。
    <p>
    示例 2：
    输入：n = 4, start = 3
    输出：8
    解释：数组 nums 为 [3, 5, 7, 9]，其中 (3 ^ 5 ^ 7 ^ 9) = 8.
    <p>
    示例 3：
    输入：n = 1, start = 7
    输出：7
    <p>
    示例 4：
    输入：n = 10, start = 5
    输出：2
    <p>
    提示：
    1 <= n <= 1000
    0 <= start <= 1000
    n == nums.length
    <p>
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/xor-operation-in-an-array
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    """

    def xorOperation(self, n: int, start: int) -> int:
        """
        执行用时： 40 ms , 在所有 Python3 提交中击败了 64.78% 的用户
        内存消耗： 14.9 MB , 在所有 Python3 提交中击败了 29.46% 的用户
        :param n:
        :param start:
        :return:
        """
        result = 0
        for i in range(n):
            result ^= (start + i * 2)
        return result
