/**
 * 633.平方数之和 | 难度：中等 | 标签：数学
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a^2 + b^2 = c 。
 * <p>
 * 示例 1：
 * 输入：c = 5
 * 输出：true
 * 解释：1 * 1 + 2 * 2 = 5
 * <p>
 * 示例 2：
 * 输入：c = 3
 * 输出：false
 * <p>
 * 示例 3：
 * 输入：c = 4
 * 输出：true
 * <p>
 * 示例 4：
 * 输入：c = 2
 * 输出：true
 * <p>
 * 示例 5：
 * 输入：c = 1
 * 输出：true
 * <p>
 * 提示：
 * 0 <= c <= 2^31 - 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-of-square-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 执行用时： 100 ms , 在所有 JavaScript 提交中击败了 31.80% 的用户
 * 内存消耗： 37.9 MB , 在所有 JavaScript 提交中击败了 64.44% 的用户
 *
 * @param {number} c
 * @return {boolean}
 */
var judgeSquareSum = function (c) {
    const max = Math.floor(Math.sqrt(c));
    for (let a = 0; a <= max; a++) {
        const b = Math.floor(Math.sqrt(c - a * a));
        if (b * b + a * a === c) {
            return true;
        }
    }
    return false;
};