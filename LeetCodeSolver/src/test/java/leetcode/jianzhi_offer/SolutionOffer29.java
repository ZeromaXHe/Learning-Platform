package leetcode.jianzhi_offer;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer 29. 顺时针打印矩阵 | 难度：简单 | 标签：数组、矩阵、模拟
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 * <p>
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * <p>
 * 示例 2：
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 * <p>
 * 限制：
 * 0 <= matrix.length <= 100
 * 0 <= matrix[i].length <= 100
 * 注意：本题与主站 54 题相同：https://leetcode-cn.com/problems/spiral-matrix/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/shun-shi-zhen-da-yin-ju-zhen-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/8 14:08
 */
public class SolutionOffer29 {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了 80.58% 的用户
     * 内存消耗：43.2 MB, 在所有 Java 提交中击败了 66.64% 的用户
     * 通过测试用例：27 / 27
     *
     * @param matrix
     * @return
     */
    public int[] spiralOrder(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return new int[0];
        }
        int n = matrix[0].length;
        if (n == 0) {
            return new int[0];
        }
        int minX = 0;
        int minY = 0;
        int maxX = m - 1;
        int maxY = n - 1;
        int x = 0;
        int y = 0;
        int dir = 0;
        int index = 0;
        int[] result = new int[m * n];
        while (index < result.length) {
            result[index] = matrix[x][y];
            switch (dir) {
                case 0:
                    if (y == maxY) {
                        minX++;
                        x++;
                        dir = 1;
                    } else {
                        y++;
                    }
                    break;
                case 1:
                    if (x == maxX) {
                        maxY--;
                        y--;
                        dir = 2;
                    } else {
                        x++;
                    }
                    break;
                case 2:
                    if (y == minY) {
                        maxX--;
                        x--;
                        dir = 3;
                    } else {
                        y--;
                    }
                    break;
                case 3:
                    if (x == minX) {
                        minY++;
                        y++;
                        dir = 0;
                    } else {
                        x--;
                    }
                    break;
            }
            index++;
        }
        return result;
    }
}
