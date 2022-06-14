package leetcode.from451to500;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author zhuxi
 * @apiNote
 * @implNote 498. 对角线遍历 | 难度：中等 | 标签：数组、矩阵、模拟
 * 给你一个大小为 m x n 的矩阵 mat ，请以对角线遍历的顺序，用一个数组返回这个矩阵中的所有元素。
 * <p>
 * 示例 1：
 * 输入：mat = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,4,7,5,3,6,8,9]
 * <p>
 * 示例 2：
 * 输入：mat = [[1,2],[3,4]]
 * 输出：[1,2,3,4]
 * <p>
 * 提示：
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 104
 * 1 <= m * n <= 104
 * -105 <= mat[i][j] <= 105
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/diagonal-traverse
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @since 2022/6/14 9:48
 */
public class Solution498 {
    @Test
    public void findDiagonalOrderTest() {
        Assert.assertArrayEquals(new int[]{1, 2, 4, 7, 5, 3, 6, 8, 9},
                findDiagonalOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
        Assert.assertArrayEquals(new int[]{1, 2, 3, 4},
                findDiagonalOrder(new int[][]{{1, 2}, {3, 4}}));
    }

    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 78.95% 的用户
     * 内存消耗： 43.1 MB , 在所有 Java 提交中击败了 56.55% 的用户
     * 通过测试用例： 32 / 32
     *
     * @param mat
     * @return
     */
    public int[] findDiagonalOrder(int[][] mat) {
        int x = mat.length;
        int y = mat[0].length;
        int index = 0;
        int[] result = new int[x * y];
        for (int i = 0; i < x + y - 1; i++) {
            if (i % 2 == 0) {
                for (int j = Math.max(0, i - x + 1); i - j >= 0 && j < y; j++) {
                    result[index++] = mat[i - j][j];
                }
            } else {
                for (int j = Math.max(0, i - y + 1); i - j >= 0 && j < x; j++) {
                    result[index++] = mat[j][i - j];
                }
            }
        }
        return result;
    }
}
