/**
 * 59.螺旋矩阵II | 难度：中等 | 标签：数组
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 *
 * 示例 1：
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：[[1]]
 *
 * 提示：
 * 1 <= n <= 20
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/spiral-matrix-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 执行用时： 84 ms , 在所有 JavaScript 提交中击败了 61.15% 的用户
 * 内存消耗： 37.8 MB , 在所有 JavaScript 提交中击败了 70.86% 的用户
 *
 * @param {number} n
 * @return {number[][]}
 */
var generateMatrix = function (n) {
    var result = [];
    for (let i = 0; i < n; i++) {
        result.push([]);
    }
    var minCol = 0;
    var minRow = 0;
    var maxCol = n;
    var maxRow = n;
    var num = 1;
    while (minCol < maxCol) {
        for (let i = minCol; i < maxCol; i++) {
            result[minRow][i] = num++;
        }
        if (++minRow >= maxRow) {
            break;
        }
        for (let i = minRow; i < maxRow; i++) {
            result[i][maxCol - 1] = num++;
        }
        if (--maxCol <= minCol) {
            break;
        }
        for (let i = maxCol - 1; i >= minCol; i--) {
            result[maxRow - 1][i] = num++;
        }
        if (--maxRow <= minRow) {
            break;
        }
        for (let i = maxRow - 1; i >= minRow; i--) {
            result[i][minCol] = num++;
        }
        minCol++;
    }
    return result;
};