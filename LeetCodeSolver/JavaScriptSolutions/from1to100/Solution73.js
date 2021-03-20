/**
 * 73.矩阵置零 | 难度：中等 | 标签：数组
 * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
 * <p>
 * 示例 1:
 * 输入:
 * [
 *   [1,1,1],
 *   [1,0,1],
 *   [1,1,1]
 * ]
 * 输出:
 * [
 *   [1,0,1],
 *   [0,0,0],
 *   [1,0,1]
 * ]
 * <p>
 * 示例 2:
 * 输入:
 * [
 *   [0,1,2,0],
 *   [3,4,5,2],
 *   [1,3,1,5]
 * ]
 * 输出:
 * [
 *   [0,0,0,0],
 *   [0,4,5,0],
 *   [0,3,1,0]
 * ]
 * <p>
 * 进阶:
 * 一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
 * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
 * 你能想出一个常数空间的解决方案吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/set-matrix-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 执行用时： 96 ms , 在所有 JavaScript 提交中击败了 98.35% 的用户
 * 内存消耗： 39.9 MB , 在所有 JavaScript 提交中击败了 72.37% 的用户
 *
 * @param {number[][]} matrix
 * @return {void} Do not return anything, modify matrix in-place instead.
 */
var setZeroes = function (matrix) {
    var m = matrix.length, n = matrix[0].length;
    var flagCol0 = false, flagRow0 = false;
    // 第一列是否有0
    for (let i = 0; i < m; i++) {
        if (matrix[i][0] === 0) {
            flagCol0 = true;
        }
    }
    // 第一行是否有0
    for (let j = 0; j < n; j++) {
        if (matrix[0][j] === 0) {
            flagRow0 = true;
        }
    }
    // 将后面的行数列是否有0的信息存储在第一行和第一列中
    for (let i = 1; i < m; i++) {
        for (let j = 1; j < n; j++) {
            if (matrix[i][j] === 0) {
                matrix[i][0] = matrix[0][j] = 0;
            }
        }
    }
    // 根据第一行和第一列去更新其他数据
    for (let i = 1; i < m; i++) {
        for (let j = 1; j < n; j++) {
            if (matrix[i][0] === 0 || matrix[0][j] === 0) {
                matrix[i][j] = 0;
            }
        }
    }
    // 更新第一列
    if (flagCol0) {
        for (let i = 0; i < m; i++) {
            matrix[i][0] = 0;
        }
    }
    // 更新第一行
    if (flagRow0) {
        for (let j = 0; j < n; j++) {
            matrix[0][j] = 0;
        }
    }
};