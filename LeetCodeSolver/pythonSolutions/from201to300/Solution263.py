class Solution:
    """
    263.丑数 | 难度：简单 | 标签：数学
    给你一个整数 n ，请你判断 n 是否为 丑数 。如果是，返回 true ；否则，返回 false 。
    <p>
    丑数 就是只包含质因数 2、3 和/或 5 的正整数。
    <p>
    示例 1：
    输入：n = 6
    输出：true
    解释：6 = 2 × 3
    <p>
    示例 2：
    输入：n = 8
    输出：true
    解释：8 = 2 × 2 × 2
    <p>
    示例 3：
    输入：n = 14
    输出：false
    解释：14 不是丑数，因为它包含了另外一个质因数 7 。
    <p>
    示例 4：
    输入：n = 1
    输出：true
    解释：1 通常被视为丑数。
    <p>
    提示：
    -2^31 <= n <= 2^31 - 1
    <p>
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/ugly-number
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    """

    def isUgly(self, n: int) -> bool:
        """
        执行用时： 36 ms , 在所有 Python3 提交中击败了 89.60% 的用户
        内存消耗： 14.8 MB , 在所有 Python3 提交中击败了 49.28% 的用户
        :param n:
        :return:
        """
        if n < 1:
            return False
        while n % 2 == 0:
            n /= 2
        while n % 3 == 0:
            n /= 3
        while n % 5 == 0:
            n /= 5
        return n == 1
