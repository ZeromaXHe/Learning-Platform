package leetcode.from1101to1200;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/4/3 16:20
 * @Description: 1143.最长公共子序列 | 难度：中等 | 标签：动态规划
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * <p>
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * <p>
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 * <p>
 * 示例 1：
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
 * <p>
 * 示例 2：
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
 * <p>
 * 示例 3：
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0 。
 * <p>
 * 提示：
 * 1 <= text1.length, text2.length <= 1000
 * text1 和 text2 仅由小写英文字符组成。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: ZeromaXHe
 */
public class Solution1143 {
    /**
     * 一维数组dp
     * <p>
     * 执行用时： 12 ms , 在所有 Java 提交中击败了 79.02% 的用户
     * 内存消耗： 36.4 MB , 在所有 Java 提交中击败了 97.20% 的用户
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        int[] dp = new int[len2 + 1];
        for (int i = 0; i < len1; i++) {
            int dpBackup = 0;
            for (int j = 0; j < len2; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    int temp = dpBackup;
                    dpBackup = dp[j + 1];
                    dp[j + 1] = temp + 1;
                } else {
                    dpBackup = dp[j + 1];
                    dp[j + 1] = Math.max(dp[j], dp[j + 1]);
                }
            }
        }
        return dp[len2];
    }

    /**
     * 题解的dp二维数组多建立一行一列为0的，使得过程大大简化（都套用最大的循环中那同一个逻辑）。
     * 感觉空间如果要优化，还可以优化为一维数组。
     * <p>
     * 执行用时： 12 ms , 在所有 Java 提交中击败了 79.02% 的用户
     * 内存消耗： 42.2 MB , 在所有 Java 提交中击败了 55.71% 的用户
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence_2dArray(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        int[][] dp = new int[len1][len2];
        dp[0][0] = text1.charAt(0) == text2.charAt(0) ? 1 : 0;
        for (int i = 1; i < len1; i++) {
            dp[i][0] = (text1.charAt(i) == text2.charAt(0) || dp[i - 1][0] == 1) ? 1 : 0;
        }
        for (int i = 1; i < len2; i++) {
            dp[0][i] = (text1.charAt(0) == text2.charAt(i) || dp[0][i - 1] == 1) ? 1 : 0;
        }
        for (int i = 1; i < len1; i++) {
            for (int j = 1; j < len2; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[len1 - 1][len2 - 1];
    }
}
