/**
 * 115.不同的子序列 | 难度：困难 | 标签：字符串、动态规划
 * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
 *
 * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
 *
 * 题目数据保证答案符合 32 位带符号整数范围。
 *
 * 示例 1：
 * 输入：s = "rabbbit", t = "rabbit"
 * 输出：3
 * 解释：
 * 如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
 * (上箭头符号 ^ 表示选取的字母)
 * rabbbit
 * ^^^^ ^^
 * rabbbit
 * ^^ ^^^^
 * rabbbit
 * ^^^ ^^^
 *
 * 示例 2：
 * 输入：s = "babgbag", t = "bag"
 * 输出：5
 * 解释：
 * 如下图所示, 有 5 种可以从 s 中得到 "bag" 的方案。
 * (上箭头符号 ^ 表示选取的字母)
 * babgbag
 * ^^ ^
 * babgbag
 * ^^    ^
 * babgbag
 * ^    ^^
 * babgbag
 * ^  ^^
 * babgbag
 * ^^^
 *
 * 提示：
 * 0 <= s.length, t.length <= 1000
 * s 和 t 由英文字母组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/distinct-subsequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 执行用时： 96 ms , 在所有 JavaScript 提交中击败了 74.12% 的用户
 * 内存消耗： 41 MB , 在所有 JavaScript 提交中击败了 22.35% 的用户
 *
 * @param {string} s
 * @param {string} t
 * @return {number}
 */
var numDistinct = function (s, t) {
    var lenS = s.length;
    var lenT = t.length;
    if (lenS < lenT) {
        return 0;
    }
    // js初始化二维数组
    // 题解中的创建方法：const dp = new Array(m + 1).fill(0).map(() => new Array(n + 1).fill(0));
    var dp = [];
    for (let i = 0; i < lenS; i++) {
        dp.push(new Array(lenT).fill(0));
    }
    if (s[0] === t[0]) {
        dp[0][0] = 1;
    }
    for (let i = 1; i < lenS; i++) {
        dp[i][0] = (s[i] === t[0] ? 1 : 0) + dp[i - 1][0];
        for (let j = 1; j < Math.min(lenT, i + 1); j++) {
            dp[i][j] = (s[i] === t[j] ? dp[i - 1][j - 1] : 0) + dp[i - 1][j];
        }
    }
    return dp[lenS - 1][lenT - 1];
};