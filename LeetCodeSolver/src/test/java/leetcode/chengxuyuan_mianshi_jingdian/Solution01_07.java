package leetcode.chengxuyuan_mianshi_jingdian;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2022/4/29 11:22
 * @Description: 面试题 01.07. 旋转矩阵 | 难度：中等 | 标签：数组、数学、矩阵
 * 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
 * <p>
 * 不占用额外内存空间能否做到？
 * <p>
 * 示例 1:
 * 给定 matrix =
 * [
 * [1,2,3],
 * [4,5,6],
 * [7,8,9]
 * ],
 * <p>
 * 原地旋转输入矩阵，使其变为:
 * [
 * [7,4,1],
 * [8,5,2],
 * [9,6,3]
 * ]
 * <p>
 * 示例 2:
 * 给定 matrix =
 * [
 * [ 5, 1, 9,11],
 * [ 2, 4, 8,10],
 * [13, 3, 6, 7],
 * [15,14,12,16]
 * ],
 * <p>
 * 原地旋转输入矩阵，使其变为:
 * [
 * [15,13, 2, 5],
 * [14, 3, 4, 1],
 * [12, 6, 8, 9],
 * [16, 7,10,11]
 * ]
 * 注意：本题与主站 48 题相同：https://leetcode-cn.com/problems/rotate-image/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-matrix-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution01_07 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 40.3 MB , 在所有 Java 提交中击败了 18.11% 的用户
     * 通过测试用例： 21 / 21
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < (n + 1) / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = temp;
            }
        }
    }

    /**
     * 异或交换 4 个数
     * 不过这里应该用不上，异或交换要求数字都不等
     */
    @Test
    public void xorSwap4Test() {
        int a = 1;
        int b = 2;
        int c = 4;
        int d = 8;
        a ^= b ^= a;
        b ^= a;
        a ^= c ^= a;
        c ^= a;
        a ^= d ^= a;
        d ^= a;
        Assert.assertEquals(8, a);
        Assert.assertEquals(1, b);
        Assert.assertEquals(2, c);
        Assert.assertEquals(4, d);
    }
}
