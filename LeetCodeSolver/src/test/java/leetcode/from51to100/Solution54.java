package leetcode.from51to100;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2021/3/15 9:52
 * @Description: 54.螺旋矩阵 | 难度：中等 | 标签：数组
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 * <p>
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * <p>
 * 示例 2：
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 * <p>
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/spiral-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution54 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.5 MB, 在所有 Java 提交中击败了 78.61% 的用户
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return null;
        }
        ArrayList<Integer> list = new ArrayList<>(matrix.length * matrix[0].length);
        int minCol = 0;
        int minRow = 0;
        int maxCol = matrix[0].length;
        int maxRow = matrix.length;
        while (minCol < maxCol) {
            for (int i = minCol; i < maxCol; i++) {
                list.add(matrix[minRow][i]);
            }
            if (++minRow >= maxRow) {
                break;
            }
            for (int i = minRow; i < maxRow; i++) {
                list.add(matrix[i][maxCol - 1]);
            }
            if (--maxCol <= minCol) {
                break;
            }
            for (int i = maxCol - 1; i >= minCol; i--) {
                list.add(matrix[maxRow - 1][i]);
            }
            if (--maxRow <= minRow) {
                break;
            }
            for (int i = maxRow - 1; i >= minRow; i--) {
                list.add(matrix[i][minCol]);
            }
            minCol++;
        }
        return list;
    }

    @Test
    public void spiralOrderTest() {
        System.out.println(spiralOrder(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}}));
    }
}
