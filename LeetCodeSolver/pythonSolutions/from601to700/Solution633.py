class Solution:
    """
    633.平方数之和 | 难度：中等 | 标签：数学
    给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a^2 + b^2 = c 。
    <p>
    示例 1：
    输入：c = 5
    输出：true
    解释：1 * 1 + 2 * 2 = 5
    <p>
    示例 2：
    输入：c = 3
    输出：false
    <p>
    示例 3：
    输入：c = 4
    输出：true
    <p>
    示例 4：
    输入：c = 2
    输出：true
    <p>
    示例 5：
    输入：c = 1
    输出：true
    <p>
    提示：
    0 <= c <= 2^31 - 1
    <p>
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/sum-of-square-numbers
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    """

    def judgeSquareSum(self, c: int) -> bool:
        """
        执行用时： 216 ms , 在所有 Python3 提交中击败了 65.40% 的用户
        内存消耗： 14.8 MB , 在所有 Python3 提交中击败了 55.65% 的用户
        :param c:
        :return:
        """
        upLimit = int(c ** 0.5)
        for a in range(0, upLimit + 1):
            b = int((c - a * a) ** 0.5)
            if b * b + a * a == c:
                return True
        return False
