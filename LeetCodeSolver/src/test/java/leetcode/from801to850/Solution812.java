package leetcode.from801to850;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/15 15:01
 * @Description: 812. 最大三角形面积 | 难度：简单 | 标签：几何、数组、数学
 * 给定包含多个点的集合，从其中取三个点组成三角形，返回能组成的最大三角形的面积。
 * <p>
 * 示例:
 * 输入: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
 * 输出: 2
 * 解释:
 * 这五个点如下图所示。组成的橙色三角形是最大的，面积为2。
 * <p>
 * 注意:
 * 3 <= points.length <= 50.
 * 不存在重复的点。
 *  -50 <= points[i][j] <= 50.
 * 结果误差值在 10^-6 以内都认为是正确答案。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/largest-triangle-area
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: ZeromaXHe
 */
public class Solution812 {
    /**
     * 执行用时： 6 ms , 在所有 Java 提交中击败了 33.02% 的用户
     * 内存消耗： 39 MB , 在所有 Java 提交中击败了 60.10% 的用户
     * 通过测试用例： 57 / 57
     *
     * @param points
     * @return
     */
    public double largestTriangleArea(int[][] points) {
        double result = 0;
        for (int i = 0; i < points.length - 2; i++) {
            for (int j = i; j < points.length - 1; j++) {
                for (int k = j; k < points.length; k++) {
                    // 点积求面积
                    result = Math.max(result,
                            Math.abs((points[j][0] - points[i][0]) * (points[k][1] - points[i][1])
                                    - (points[j][1] - points[i][1]) * (points[k][0] - points[i][0])) / 2.0);
                }
            }
        }
        return result;
    }
}
