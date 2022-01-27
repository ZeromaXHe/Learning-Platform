package leetcode.from51to100;

import com.zerox.util.Array2DUtils;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/3/16 9:53
 * @Description: 59.螺旋矩阵II | 难度：中等 | 标签：数组
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 * <p>
 * 示例 1：
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 * <p>
 * 示例 2：
 * 输入：n = 1
 * 输出：[[1]]
 * <p>
 * 提示：
 * 1 <= n <= 20
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/spiral-matrix-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution59 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.7 MB , 在所有 Java 提交中击败了 17.95% 的用户
     *
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int minCol = 0;
        int minRow = 0;
        int maxCol = n;
        int maxRow = n;
        int num = 1;
        while (minCol < maxCol) {
            for (int i = minCol; i < maxCol; i++) {
                result[minRow][i] = num++;
            }
            if (++minRow >= maxRow) {
                break;
            }
            for (int i = minRow; i < maxRow; i++) {
                result[i][maxCol - 1] = num++;
            }
            if (--maxCol <= minCol) {
                break;
            }
            for (int i = maxCol - 1; i >= minCol; i--) {
                result[maxRow - 1][i] = num++;
            }
            if (--maxRow <= minRow) {
                break;
            }
            for (int i = maxRow - 1; i >= minRow; i--) {
                result[i][minCol] = num++;
            }
            minCol++;
        }
        return result;
    }

    @Test
    public void generateMatrixTest() {
        System.out.println(Array2DUtils.print2DArrWithChangeLine(generateMatrix(3)));
        System.out.println(Array2DUtils.print2DArrWithChangeLine(generateMatrix(1)));
    }
}
