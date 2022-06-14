package leetcode.from1001to1050;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zhuxi
 * @apiNote
 * @implNote 1037. 有效的回旋镖 | 难度：简单 | 标签：几何、数组、数学
 * 给定一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点，如果这些点构成一个 回旋镖 则返回 true 。
 * <p>
 * 回旋镖 定义为一组三个点，这些点 各不相同 且 不在一条直线上 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：points = [[1,1],[2,3],[3,2]]
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：points = [[1,1],[2,2],[3,3]]
 * 输出：false
 * <p>
 * 提示：
 * points.length == 3
 * points[i].length == 2
 * 0 <= xi, yi <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/valid-boomerang
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @since 2022/6/8 10:04
 */
public class Solution1037 {
    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 39.5 MB , 在所有 Java 提交中击败了 5.11% 的用户
     * 通过测试用例： 206 / 206
     * <p>
     * 换边乘法后就不需要浮点了，这个时候其实按题解直接用叉乘就更简洁
     *
     * @param points
     * @return
     */
    public boolean isBoomerang(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(arr -> arr[0]));
        if (points[0][1] == points[1][1] && points[1][1] == points[2][1]) {
            return false;
        } else if (points[0][1] == points[1][1]) {
            return points[0][0] != points[1][0];
        } else if (points[1][1] == points[2][1]) {
            return points[1][0] != points[2][0];
        }
        return (0.0 + points[0][0] - points[1][0]) / (points[0][1] - points[1][1])
                != (0.0 + points[1][0] - points[2][0]) / (points[1][1] - points[2][1]);
    }
}
