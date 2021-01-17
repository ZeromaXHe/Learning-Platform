package leetcode.from1201to1300;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/1/17 15:25
 * @Description: 1232.缀点成线 | 难度：简单 | 标签：几何、数组、数学
 * 在一个 XY 坐标系中有一些点，我们用数组 coordinates 来分别记录它们的坐标，其中 coordinates[i] = [x, y] 表示横坐标为 x、纵坐标为 y 的点。
 * <p>
 * 请你来判断，这些点是否在该坐标系中属于同一条直线上，是则返回 true，否则请返回 false。
 * <p>
 * 示例 1：
 * 输入：coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
 * 输出：false
 * <p>
 * 提示：
 * 2 <= coordinates.length <= 1000
 * coordinates[i].length == 2
 * -10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
 * coordinates 中不含重复的点
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/check-if-it-is-a-straight-line
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: ZeromaXHe
 */
public class Solution1232 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 37.8 MB , 在所有 Java 提交中击败了 95.56% 的用户
     *
     * @param coordinates
     * @return
     */
    public boolean checkStraightLine(int[][] coordinates) {
        if (coordinates.length <= 2) {
            return true;
        }
        if (coordinates[0][0] == coordinates[1][0]) {
            for (int i = 2; i < coordinates.length; i++) {
                if (coordinates[i][0] != coordinates[0][0]) {
                    return false;
                }
            }
            return true;
        } else if (coordinates[0][1] == coordinates[1][1]) {
            for (int i = 2; i < coordinates.length; i++) {
                if (coordinates[i][1] != coordinates[0][1]) {
                    return false;
                }
            }
            return true;
        } else {
            double a = (double) (coordinates[0][1] - coordinates[1][1]) / (double) (coordinates[0][0] - coordinates[1][0]);
            double b = coordinates[0][1] - a * coordinates[0][0];
            for (int i = 2; i < coordinates.length; i++) {
                if (Math.abs(coordinates[i][1] - b - a * coordinates[i][0]) > 0.001) {
                    return false;
                }
            }
            return true;
        }
    }

    @Test
    public void checkStraightLineTest() {
        Assert.assertFalse(checkStraightLine(new int[][]{{0, 0}, {0, 5}, {5, 5}, {5, 0}}));
    }
}
