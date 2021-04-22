/**
 * 363.矩形区域不超过 K 的最大数值和 | 难度：困难 | 标签：队列、二分查找、动态规划
 * 给你一个 m x n 的矩阵 matrix 和一个整数 k ，找出并返回矩阵内部矩形区域的不超过 k 的最大数值和。
 * <p>
 * 题目数据保证总会存在一个数值和不超过 k 的矩形区域。
 * <p>
 * 示例 1：
 * 输入：matrix = [[1,0,1],[0,-2,3]], k = 2
 * 输出：2
 * 解释：蓝色边框圈出来的矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
 * <p>
 * 示例 2：
 * 输入：matrix = [[2,2,-1]], k = 3
 * 输出：3
 * <p>
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -100 <= matrix[i][j] <= 100
 * -10^5 <= k <= 10^5
 * <p>
 * 进阶：如果行数远大于列数，该如何设计解决方案？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/max-sum-of-rectangle-no-larger-than-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 执行用时：160 ms , 在所有 JavaScript 提交中击败了 81.08% 的用户
 * 内存消耗： 41 MB , 在所有 JavaScript 提交中击败了 35.14% 的用户
 *
 * @param {number[][]} matrix
 * @param {number} k
 * @return {number}
 */
var maxSumSubmatrix = function (matrix, k) {
    const m = matrix.length;
    const n = matrix[0].length;
    let res = -1E10;
    const nBiggerThanM = n > m;
    const maxLen = Math.max(m, n);
    const minLen = Math.min(m, n);
    for (let i = 0; i < minLen; i++) {
        let sums = new Array(maxLen).fill(0);
        for (let j = i; j < minLen; j++) {
            for (let y = 0; y < maxLen; y++) {
                sums[y] += nBiggerThanM ? matrix[j][y] : matrix[y][j];
            }
            let curr = 0;
            let max = sums[0];
            // js forEach
            sums.forEach((sum) => {
                curr = Math.max(sum, curr + sum);
                max = Math.max(curr, max);
                if (max === k) {
                    return max;
                }
            });
            if (max < k) {
                res = Math.max(max, res);
            } else {
                for (let a = 0; a < maxLen; a++) {
                    let currSum = 0;
                    for (let b = a; b < maxLen; b++) {
                        currSum += sums[b];
                        if (currSum <= k) {
                            res = Math.max(currSum, res);
                        }
                    }
                }
                if (res === k) {
                    return res;
                }
            }
        }
    }
    return res;
};