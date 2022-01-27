package leetcode.from401to450;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhuxi
 * @Time: 2021/9/13 9:42
 * @Description: 447. 回旋镖的数量 | 难度：中等 | 标签：数组、哈希表、数学
 * 给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi] 。
 * 回旋镖 是由点 (i, j, k) 表示的元组 ，其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。
 * <p>
 * 返回平面上所有回旋镖的数量。
 * <p>
 * 示例 1：
 * 输入：points = [[0,0],[1,0],[2,0]]
 * 输出：2
 * 解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
 * <p>
 * 示例 2：
 * 输入：points = [[1,1],[2,2],[3,3]]
 * 输出：2
 * <p>
 * 示例 3：
 * 输入：points = [[1,1]]
 * 输出：0
 * <p>
 * 提示：
 * n == points.length
 * 1 <= n <= 500
 * points[i].length == 2
 * -104 <= xi, yi <= 104
 * 所有点都 互不相同
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-boomerangs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution447 {
    /**
     * 执行用时： 96 ms , 在所有 Java 提交中击败了 89.11% 的用户
     * 内存消耗： 38.4 MB , 在所有 Java 提交中击败了 48.86% 的用户
     * 通过测试用例： 32 / 32
     *
     * @param points
     * @return
     */
    public int numberOfBoomerangs(int[][] points) {
        Map<Integer, Integer> map = new HashMap<>(points.length);
        int result = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                // 题解直接没管自己，因为只有1个，后面 value * (value - 1) = 0 算不进去的
                if (j != i) {
                    int distSquare = (points[i][0] - points[j][0]) * (points[i][0] - points[j][0])
                            + (points[i][1] - points[j][1]) * (points[i][1] - points[j][1]);
                    /*
                     * 用注释的代码的话，这里耗时会更久
                     *
                     * 执行用时： 139 ms , 在所有 Java 提交中击败了 78.99% 的用户
                     * 内存消耗： 37.8 MB , 在所有 Java 提交中击败了 99.24% 的用户
                     * 通过测试用例： 32 / 32
                     */
//                    map.putIfAbsent(distSquare, 0);
//                    map.put(distSquare, map.get(distSquare) + 1);
                    map.put(distSquare, map.getOrDefault(distSquare, 0) + 1);
                }
            }
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                Integer value = entry.getValue();
                // if 可以去掉，因为 value = 1 时 result += 0
                if (value > 1) {
                    result += value * (value - 1);
                }
            }
            map.clear();
        }
        return result;
    }
}
