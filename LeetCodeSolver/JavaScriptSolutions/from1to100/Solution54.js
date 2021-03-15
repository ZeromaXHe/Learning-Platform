/**
 * 54.螺旋矩阵 | 难度：中等 | 标签：数组
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 *
 * 示例 2：
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/spiral-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 执行用时： 88 ms , 在所有 JavaScript 提交中击败了 29.61% 的用户
 * 内存消耗： 37.8 MB , 在所有 JavaScript 提交中击败了 17.77% 的用户
 *
 * @param {number[][]} matrix
 * @return {number[]}
 */
var spiralOrder = function (matrix) {
    if (matrix == null || matrix.length === 0 || matrix[0].length === 0) {
        return [];
    }
    var list = [];
    var minCol = 0;
    var minRow = 0;
    var maxCol = matrix[0].length;
    var maxRow = matrix.length;
    while (minCol < maxCol) {
        for (let i = minCol; i < maxCol; i++) {
            list.push(matrix[minRow][i]);
        }
        if (++minRow >= maxRow) {
            break;
        }
        for (let i = minRow; i < maxRow; i++) {
            list.push(matrix[i][maxCol - 1]);
        }
        if (--maxCol <= minCol) {
            break;
        }
        for (let i = maxCol - 1; i >= minCol; i--) {
            list.push(matrix[maxRow - 1][i]);
        }
        if (--maxRow <= minRow) {
            break;
        }
        for (let i = maxRow - 1; i >= minRow; i--) {
            list.push(matrix[i][minCol]);
        }
        minCol++;
    }
    return list;
};