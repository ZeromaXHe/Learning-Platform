class Solution:
    """
    115.不同的子序列 | 难度：困难 | 标签：字符串、动态规划
    给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。

    字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）

    题目数据保证答案符合 32 位带符号整数范围。

    示例 1：
    输入：s = "rabbbit", t = "rabbit"
    输出：3
    解释：
    如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
    (上箭头符号 ^ 表示选取的字母)
    rabbbit
    ^^^^ ^^
    rabbbit
    ^^ ^^^^
    rabbbit
    ^^^ ^^^

    示例 2：
    输入：s = "babgbag", t = "bag"
    输出：5
    解释：
    如下图所示, 有 5 种可以从 s 中得到 "bag" 的方案。
    (上箭头符号 ^ 表示选取的字母)
    babgbag
    ^^ ^
    babgbag
    ^^    ^
    babgbag
    ^    ^^
    babgbag
      ^  ^^
    babgbag
        ^^^

    提示：
    0 <= s.length, t.length <= 1000
    s 和 t 由英文字母组成

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/distinct-subsequences
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    """

    def numDistinct(self, s: str, t: str) -> int:
        """
        执行用时： 48 ms , 在所有 Python3 提交中击败了 75.14% 的用户
        内存消耗： 15.7 MB , 在所有 Python3 提交中击败了 10.14% 的用户
        :param s:
        :param t:
        :return:
        """
        len_s = len(s)
        len_t = len(t)
        if len_s < len_t:
            return 0
        # 初始化二维数组 可以简化成翻转数组做dp
        dp = [[0] * len_t for i in range(len_s)]
        if s[0] == t[0]:
            dp[0][0] = 1
        for i in range(1, len_s):
            # python 三元表达式（三目运算符）
            dp[i][0] = (1 if s[i] == t[0] else 0) + dp[i - 1][0]
            for j in range(1, min(len_t, i + 1)):
                dp[i][j] = (dp[i - 1][j - 1] if s[i] == t[j] else 0) + dp[i - 1][j]
        return dp[len_s - 1][len_t - 1]


if __name__ == "__main__":
    print(Solution().numDistinct("rabbbit", "rabbit"))  # 3
    print(Solution().numDistinct("", "a"))  # 3
