/**
 * 1143.最长公共子序列 | 难度：中等 | 标签：动态规划
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
 *
 * 执行用时： 100 ms , 在所有 JavaScript 提交中击败了 97.64% 的用户
 * 内存消耗： 37.8 MB , 在所有 JavaScript 提交中击败了 100.00% 的用户
 *
 * @param {string} text1
 * @param {string} text2
 * @return {number}
 */
var longestCommonSubsequence = function (text1, text2) {
    const len1 = text1.length;
    const len2 = text2.length;
    // js 数组需要fill赋初始值，否则为NaN
    let dp = new Array(len2 + 1).fill(0);
    for (let i = 0; i < len1; i++) {
        let dpBackup = 0;
        for (let j = 0; j < len2; j++) {
            if (text1.charAt(i) === text2.charAt(j)) {
                let temp = dpBackup;
                dpBackup = dp[j + 1];
                dp[j + 1] = temp + 1;
            } else {
                dpBackup = dp[j + 1];
                dp[j + 1] = Math.max(dp[j], dp[j + 1]);
            }
        }
    }
    return dp[len2];
};