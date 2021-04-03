class Solution:
    """
    1143.最长公共子序列 | 难度：中等 | 标签：动态规划
    给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
    <p>
    一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
    <p>
    例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
    两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
    <p>
    示例 1：
    输入：text1 = "abcde", text2 = "ace"
    输出：3
    解释：最长公共子序列是 "ace" ，它的长度为 3 。
    <p>
    示例 2：
    输入：text1 = "abc", text2 = "abc"
    输出：3
    解释：最长公共子序列是 "abc" ，它的长度为 3 。
    <p>
    示例 3：
    输入：text1 = "abc", text2 = "def"
    输出：0
    解释：两个字符串没有公共子序列，返回 0 。
    <p>
    提示：
    1 <= text1.length, text2.length <= 1000
    text1 和 text2 仅由小写英文字符组成。
    <p>
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/longest-common-subsequence
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    """

    def longestCommonSubsequence(self, text1: str, text2: str) -> int:
        """
        执行用时： 332 ms , 在所有 Python3 提交中击败了 97.34% 的用户
        内存消耗： 14.9 MB , 在所有 Python3 提交中击败了 96.65% 的用户
        :param text1:
        :param text2:
        :return:
        """
        len1 = len(text1)
        len2 = len(text2)
        dp = [0] * (len2 + 1)
        for i in range(0, len1):
            dp_backup = 0
            for j in range(0, len2):
                if text1[i] == text2[j]:
                    dp_backup, dp[j + 1] = dp[j + 1], dp_backup + 1
                else:
                    dp_backup = dp[j + 1]
                    dp[j + 1] = max(dp[j + 1], dp[j])
        return dp[len2]
