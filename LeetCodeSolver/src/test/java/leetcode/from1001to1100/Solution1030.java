package leetcode.from1001to1100;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @Author: ZeromaXHe
 * @Time: 2020/11/17 23:30
 * @Description: 1030. 距离顺序排列矩阵单元格 | 难度：简单 | 标签：排序
 * 给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。
 * <p>
 * 另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。
 * <p>
 * 返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈顿距离，|r1 - r2| + |c1 - c2|。（你可以按任何满足此条件的顺序返回答案。）
 * <p>
 * 示例 1：
 * 输入：R = 1, C = 2, r0 = 0, c0 = 0
 * 输出：[[0,0],[0,1]]
 * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1]
 * <p>
 * 示例 2：
 * 输入：R = 2, C = 2, r0 = 0, c0 = 1
 * 输出：[[0,1],[0,0],[1,1],[1,0]]
 * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2]
 * [[0,1],[1,1],[0,0],[1,0]] 也会被视作正确答案。
 * <p>
 * 示例 3：
 * 输入：R = 2, C = 3, r0 = 1, c0 = 2
 * 输出：[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
 * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2,2,3]
 * 其他满足题目要求的答案也会被视为正确，例如 [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]]。
 * <p>
 * 提示：
 * 1 <= R <= 100
 * 1 <= C <= 100
 * 0 <= r0 < R
 * 0 <= c0 < C
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/matrix-cells-in-distance-order
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: ZeromaXHe
 */
public class Solution1030 {
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] visited = new int[R][C];
        visited[r0][c0] = 1;
        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[]{r0, c0});
        int[][] result = new int[R * C][2];
        int index = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                result[index] = queue.removeFirst();
                int x = result[index][0];
                int y = result[index][1];
                if (x > 0 && visited[x - 1][y] == 0) {
                    queue.addLast(new int[]{x - 1, y});
                    visited[x - 1][y] = 1;
                }
                if (y < C - 1 && visited[x][y + 1] == 0) {
                    queue.addLast(new int[]{x, y + 1});
                    visited[x][y + 1] = 1;
                }
                if (x < R - 1 && visited[x + 1][y] == 0) {
                    queue.addLast(new int[]{x + 1, y});
                    visited[x + 1][y] = 1;
                }
                if (y < C - 1 && visited[x][y + 1] == 0) {
                    queue.addLast(new int[]{x, y + 1});
                    visited[x][y + 1] = 1;
                }
                index++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution1030 solution1030 = new Solution1030();
        System.out.println(Arrays.toString(solution1030.allCellsDistOrder(1,2,0,0)));
        System.out.println(Arrays.toString(solution1030.allCellsDistOrder(2,2,0,1)));
    }
}
