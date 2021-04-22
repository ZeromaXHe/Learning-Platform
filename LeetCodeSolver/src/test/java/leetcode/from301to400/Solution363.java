package leetcode.from301to400;

import java.util.TreeSet;

/**
 * @Author: zhuxi
 * @Time: 2021/4/22 9:48
 * @Description: 363.矩形区域不超过 K 的最大数值和 | 难度：困难 | 标签：队列、二分查找、动态规划
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
 * @Modified By: zhuxi
 */
public class Solution363 {
    /**
     * 执行用时：32 ms , 在所有 Java 提交中击败了 74.62% 的用户
     * 内存消耗：38.5 MB , 在所有 Java 提交中击败了 79.30% 的用户
     * <p>
     * TreeSet直接换成O(maxLen^2)的循环，反而快了很多
     * 参考了最快的提交（4ms，就离谱。同样的代码重新提交就达不到那个时间，而是30ms了，估计是测试用例变过）
     * 把maxLen和minLen改为固定的m和n不会快，反而是35ms。
     *
     * @param matrix
     * @param k
     * @return
     */
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int res = Integer.MIN_VALUE;
        boolean nBiggerThanM = n > m;
        int maxLen = Math.max(m, n);
        int minLen = Math.min(m, n);
        for (int i = 0; i < minLen; i++) {
            int[] sums = new int[maxLen];
            for (int j = i; j < minLen; j++) {
                for (int y = 0; y < maxLen; y++) {
                    sums[y] += nBiggerThanM ? matrix[j][y] : matrix[y][j];
                }
                int curr = 0;
                int max = sums[0];
                for (int sum : sums) {
                    curr = Math.max(sum, curr + sum);
                    max = Math.max(curr, max);
                    if (max == k) {
                        return max;
                    }
                }
                if (max < k) {
                    res = Math.max(max, res);
                } else {
                    for (int a = 0; a < maxLen; a++) {
                        int currSum = 0;
                        for (int b = a; b < maxLen; b++) {
                            currSum += sums[b];
                            if (currSum <= k) {
                                res = Math.max(currSum, res);
                            }
                        }
                    }
                    if (res == k) {
                        return res;
                    }
                }
            }
        }
        return res;
    }

    /**
     * 执行用时： 233 ms , 在所有 Java 提交中击败了 35.06% 的用户
     * 内存消耗： 38.7 MB , 在所有 Java 提交中击败了 55.09% 的用户
     * <p>
     * 简化成 O(minLen^2 * maxLen), 但因为用了官方题解里面的TreeSet，速度好像也没快多少
     *
     * @param matrix
     * @param k
     * @return
     */
    public int maxSumSubmatrix_treeSet(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int max = Integer.MIN_VALUE;
        boolean nBiggerThanM = n > m;
        int maxLen = Math.max(m, n);
        int minLen = Math.min(m, n);
        for (int i = 0; i < minLen; i++) {
            int[] sums = new int[maxLen];
            for (int j = i; j < minLen; j++) {
                for (int y = 0; y < maxLen; y++) {
                    sums[y] += nBiggerThanM ? matrix[j][y] : matrix[y][j];
                }
                TreeSet<Integer> sumSet = new TreeSet<>();
                sumSet.add(0);
                int s = 0;
                for (int v : sums) {
                    s += v;
                    // floor(E e) 方法返回在这个集合中小于或者等于给定元素的最大元素，如果不存在这样的元素,返回null.
                    // ceiling(E e) 方法返回在这个集合中大于或者等于给定元素的最小元素，如果不存在这样的元素,返回null.
                    Integer ceil = sumSet.ceiling(s - k);
                    if (ceil != null) {
                        if (s - ceil == k) {
                            return k;
                        }
                        if (s - ceil > max) {
                            max = s - ceil;
                        }
                    }
                    sumSet.add(s);
                }
            }
        }
        /// 原来的未简化代码，且直接找了最大值，没有限制小于等于k。
//        if (n > m) {
//            for (int i = 0; i < m; i++) {
//                int[] sums = new int[n];
//                for (int j = i; j < m; j++) {
//                    for (int y = 0; y < n; y++) {
//                        sums[y] += matrix[j][y];
//                    }
//                    int sum = 0;
//                    for (int y = 0; y < n; y++) {
//                        sum += sums[y];
//                        if (sum > max) {
//                            max = sum;
//                            if (max == k) {
//                                return k;
//                            }
//                        }
//                        if (sum < 0) {
//                            sum = 0;
//                        }
//                    }
//                }
//            }
//        } else {
//            for (int i = 0; i < n; i++) {
//                int[] sums = new int[m];
//                for (int j = i; j < n; j++) {
//                    for (int x = 0; x < m; x++) {
//                        sums[x] += matrix[x][j];
//                    }
//                    int sum = 0;
//                    for (int x = 0; x < n; x++) {
//                        sum += sums[x];
//                        if (sum < 0) {
//                            sum = 0;
//                        } else if (sum > max) {
//                            max = sum;
//                            if (max == k) {
//                                return k;
//                            }
//                        }
//                    }
//                }
//            }
//        }
        return max;
    }

    /**
     * 执行用时： 285 ms , 在所有 Java 提交中击败了 30.89% 的用户
     * 内存消耗： 38.5 MB , 在所有 Java 提交中击败了 72.12% 的用户
     * <p>
     * 这里的循环可以优化，具体的思路就是固定左右边界（或者上下），下面按固定左右介绍
     * 对于同一个左边界就可以不断向右移动右边界，同时维护一个行的和的数组。
     * 然后对这个行之和的数组进行最大连续子序列和的计算
     *
     * @param matrix
     * @param k
     * @return
     */
    public int maxSumSubmatrix_Om2n2(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (m == n && m == 1) {
            return matrix[0][0];
        }
        int[][] sum = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[i + 1][j + 1] = sum[i][j + 1] + sum[i + 1][j] - sum[i][j] + matrix[i][j];
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int x = i; x < m; x++) {
                    for (int y = j; y < n; y++) {
                        int sumRectangle = sum[x + 1][y + 1] - sum[i][y + 1] - sum[x + 1][j] + sum[i][j];
                        if (sumRectangle == k) {
                            return k;
                        }
                        if (sumRectangle > max && sumRectangle < k) {
                            max = sumRectangle;
                        }
                    }
                }
            }
        }
        return max;
    }
}
