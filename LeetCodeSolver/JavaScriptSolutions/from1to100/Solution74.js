/**
 * 74.搜索二维矩阵 | 难度：中等 | 标签：数组、二分查找
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * <p>
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * <p>
 * 示例 1：
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * 输出：false
 * <p>
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -10^4 <= matrix[i][j], target <= 10^4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 执行用时： 84 ms , 在所有 JavaScript 提交中击败了 69.25% 的用户
 * 内存消耗： 38.6 MB , 在所有 JavaScript 提交中击败了 91.83% 的用户
 *
 * @param {number[][]} matrix
 * @param {number} target
 * @return {boolean}
 */
var searchMatrix = function (matrix, target) {
    let x = 0;
    let y = matrix[0].length - 1;
    // 提取变量 let n = matrix.length 后效率变高：
    // 执行用时： 76 ms , 在所有 JavaScript 提交中击败了 93.35% 的用户
    // 内存消耗： 39 MB , 在所有 JavaScript 提交中击败了 32.27% 的用户
    while (x < matrix.length && y >= 0) {
        if (matrix[x][y] === target) {
            return true;
        } else if (matrix[x][y] > target) {
            y--;
        } else {
            x++;
        }
    }
    return false;
};