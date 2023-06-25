package leetcode.from1401to1450;

/**
 * @author zhuxi
 * @apiNote 1401. 圆和矩形是否有重叠 | 难度：中等 | 标签：几何、数学
 * 给你一个以 (radius, xCenter, yCenter) 表示的圆和一个与坐标轴平行的矩形 (x1, y1, x2, y2) ，其中 (x1, y1) 是矩形左下角的坐标，而 (x2, y2) 是右上角的坐标。
 * <p>
 * 如果圆和矩形有重叠的部分，请你返回 true ，否则返回 false 。
 * <p>
 * 换句话说，请你检测是否 存在 点 (xi, yi) ，它既在圆上也在矩形上（两者都包括点落在边界上的情况）。
 * <p>
 * 示例 1 ：
 * 输入：radius = 1, xCenter = 0, yCenter = 0, x1 = 1, y1 = -1, x2 = 3, y2 = 1
 * 输出：true
 * 解释：圆和矩形存在公共点 (1,0) 。
 * <p>
 * 示例 2 ：
 * 输入：radius = 1, xCenter = 1, yCenter = 1, x1 = 1, y1 = -3, x2 = 2, y2 = -1
 * 输出：false
 * <p>
 * 示例 3 ：
 * 输入：radius = 1, xCenter = 0, yCenter = 0, x1 = -1, y1 = 0, x2 = 0, y2 = 1
 * 输出：true
 * <p>
 * 提示：
 * 1 <= radius <= 2000
 * -104 <= xCenter, yCenter <= 104
 * -104 <= x1 < x2 <= 104
 * -104 <= y1 < y2 <= 104
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/circle-and-rectangle-overlapping
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/25 9:49
 */
public class Solution1401 {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了 68.48% 的用户
     * 通过测试用例：90 / 90
     *
     * @param radius
     * @param xCenter
     * @param yCenter
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        int diffX1 = x1 - xCenter;
        int diffX2 = x2 - xCenter;
        int diffY1 = y1 - yCenter;
        int diffY2 = y2 - yCenter;
        int radiusSquare = radius * radius;
        return diffX1 * diffX1 + diffY1 * diffY1 <= radiusSquare
                || diffX2 * diffX2 + diffY1 * diffY1 <= radiusSquare
                || diffX1 * diffX1 + diffY2 * diffY2 <= radiusSquare
                || diffX2 * diffX2 + diffY2 * diffY2 <= radiusSquare
                || (xCenter <= x2 + radius && xCenter >= x1 - radius && yCenter <= y2 && yCenter >= y1)
                || (xCenter <= x2 && xCenter >= x1 && yCenter <= y2 + radius && yCenter >= y1 - radius);
    }
}
