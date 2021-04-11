class Solution:
    """
    264.丑数 II | 难度：中等 | 标签：堆、数学、动态规划
    给你一个整数 n ，请你找出并返回第 n 个 丑数 。
    <p>
    丑数 就是只包含质因数 2、3 和/或 5 的正整数。
    <p>
    示例 1：
    输入：n = 10
    输出：12
    解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
    <p>
    示例 2：
    输入：n = 1
    输出：1
    解释：1 通常被视为丑数。
    <p>
    提示：
    1 <= n <= 1690
    <p>
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/ugly-number-ii
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    """

    def nthUglyNumber(self, n: int) -> int:
        """
        执行用时： 144 ms , 在所有 Python3 提交中击败了 74.56% 的用户
        内存消耗： 14.7 MB , 在所有 Python3 提交中击败了 96.83% 的用户
        :param n:
        :return:
        """
        dp = [0] * (n + 1)
        dp[1] = 1
        p2 = p3 = p5 = 1
        for i in range(2, n + 1):
            num2, num3, num5 = dp[p2] * 2, dp[p3] * 3, dp[p5] * 5
            dp[i] = min(num2, num3, num5)
            if dp[i] == num2:
                p2 += 1
            if dp[i] == num3:
                p3 += 1
            if dp[i] == num5:
                p5 += 1
        return dp[n]
