package leetcode.from851to900;

/**
 * @Author: zhuxi
 * @Time: 2022/4/26 9:55
 * @Description: 883. 三维形体投影面积 | 难度：简单 | 标签：
 * 在 n x n 的网格 grid 中，我们放置了一些与 x，y，z 三轴对齐的 1 x 1 x 1 立方体。
 * <p>
 * 每个值 v = grid[i][j] 表示 v 个正方体叠放在单元格 (i, j) 上。
 * <p>
 * 现在，我们查看这些立方体在 xy 、yz 和 zx 平面上的投影。
 * <p>
 * 投影 就像影子，将 三维 形体映射到一个 二维 平面上。从顶部、前面和侧面看立方体时，我们会看到“影子”。
 * <p>
 * 返回 所有三个投影的总面积 。
 * <p>
 * 示例 1：
 * 输入：[[1,2],[3,4]]
 * 输出：17
 * 解释：这里有该形体在三个轴对齐平面上的三个投影(“阴影部分”)。
 * <p>
 * 示例 2:
 * 输入：grid = [[2]]
 * 输出：5
 * <p>
 * 示例 3：
 * 输入：[[1,0],[0,2]]
 * 输出：8
 * <p>
 * 提示：
 * n == grid.length == grid[i].length
 * 1 <= n <= 50
 * 0 <= grid[i][j] <= 50
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/projection-area-of-3d-shapes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution883 {
    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 41.1 MB , 在所有 Java 提交中击败了 45.10% 的用户
     * 通过测试用例： 90 / 90
     * <p>
     * 官方题解空间复杂度更低，直接grid[i][j] 和 grid[j][i] 就可以在 j 循环里一次把行最大和列最大找出来，然后 i 循环做累加就好了。
     *
     * @param grid
     * @return
     */
    public int projectionArea(int[][] grid) {
        int[] maxX = new int[grid.length];
        int[] maxY = new int[grid[0].length];
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] > 0) {
                    count++;
                    if (grid[i][j] > maxX[i]) {
                        maxX[i] = grid[i][j];
                    }
                    if (grid[i][j] > maxY[j]) {
                        maxY[j] = grid[i][j];
                    }
                }
            }
        }
        int sum = count;
        for (int i : maxX) {
            sum += i;
        }
        for (int i : maxY) {
            sum += i;
        }
        return sum;
    }
}
