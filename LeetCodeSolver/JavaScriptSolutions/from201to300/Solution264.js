/**
 * 264.丑数 II | 难度：中等 | 标签：堆、数学、动态规划
 * 给你一个整数 n ，请你找出并返回第 n 个 丑数 。
 * <p>
 * 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
 * <p>
 * 示例 1：
 * 输入：n = 10
 * 输出：12
 * 解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
 * <p>
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 * 解释：1 通常被视为丑数。
 * <p>
 * 提示：
 * 1 <= n <= 1690
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ugly-number-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 执行用时： 96 ms , 在所有 JavaScript 提交中击败了 72.69% 的用户
 * 内存消耗： 39.2 MB , 在所有 JavaScript 提交中击败了 97.22% 的用户
 *
 * @param {number} n
 * @return {number}
 */
var nthUglyNumber = function (n) {
    let dp = new Array(n + 1);
    dp[1] = 1;
    let p2 = 1, p3 = 1, p5 = 1;
    for (let i = 2; i <= n; i++) {
        let num2 = dp[p2] * 2, num3 = dp[p3] * 3, num5 = dp[p5] * 5;
        dp[i] = Math.min(num2, num3, num5);
        if (dp[i] === num2) {
            p2++;
        }
        if (dp[i] === num3) {
            p3++;
        }
        if (dp[i] === num5) {
            p5++;
        }
    }
    return dp[n];
};