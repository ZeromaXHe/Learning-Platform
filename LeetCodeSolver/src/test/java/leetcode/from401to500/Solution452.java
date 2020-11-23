package leetcode.from401to500;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/11/23 18:48
 * @Description: 452. 用最少数量的箭引爆气球 | 难度：中等 | 标签：贪心算法、排序
 * 在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。由于它是水平的，所以纵坐标并不重要，因此只要知道开始和结束的横坐标就足够了。开始坐标总是小于结束坐标。
 * <p>
 * 一支弓箭可以沿着 x 轴从不同点完全垂直地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
 * <p>
 * 给你一个数组 points ，其中 points [i] = [xstart,xend] ，返回引爆所有气球所必须射出的最小弓箭数。
 * <p>
 * 示例 1：
 * 输入：points = [[10,16],[2,8],[1,6],[7,12]]
 * 输出：2
 * 解释：对于该样例，x = 6 可以射爆 [2,8],[1,6] 两个气球，以及 x = 11 射爆另外两个气球
 * <p>
 * 示例 2：
 * 输入：points = [[1,2],[3,4],[5,6],[7,8]]
 * 输出：4
 * <p>
 * 示例 3：
 * 输入：points = [[1,2],[2,3],[3,4],[4,5]]
 * 输出：2
 * <p>
 * 示例 4：
 * 输入：points = [[1,2]]
 * 输出：1
 * <p>
 * 示例 5：
 * 输入：points = [[2,3],[2,3]]
 * 输出：1
 * <p>
 * 提示：
 * 0 <= points.length <= 104
 * points[i].length == 2
 * -231 <= xstart < xend <= 231 - 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-number-of-arrows-to-burst-balloons
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution452 {
    /**
     * 执行用时：21 ms, 在所有 Java 提交中击败了 76.19% 的用户
     * 内存消耗：46 MB, 在所有 Java 提交中击败了 81.38% 的用户
     * <p>
     * 参考题解做的，是时候复习复习贪心算法了。
     * 感觉题解思路和那种看最多电影的贪心算法经典例题有点像，找个时间回去看看
     *
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        Arrays.sort(points, Comparator.comparingInt(p -> p[1]));
        int pos = points[0][1];
        int ans = 1;
        for (int[] balloon : points) {
            if (balloon[0] > pos) {
                pos = balloon[1];
                ++ans;
            }
        }
        return ans;
    }

    /**
     * 错误思路
     *
     * @param points
     * @return
     */
    public int findMinArrowShots_wrongAnswer(int[][] points) {
        LinkedList<int[]> minShoot = new LinkedList<>();
        for (int[] point : points) {
            checkMinShoot(point, minShoot);
        }
        return minShoot.size();
    }

    private boolean checkMinShoot(int[] point, LinkedList<int[]> minShoot) {
        if (minShoot.size() == 0) {
            minShoot.add(point);
            return false;
        }
        for (int[] shoot : minShoot) {
            if (point[0] <= shoot[1] && point[1] >= shoot[0]) {
                shoot[0] = Math.max(point[0], shoot[0]);
                shoot[1] = Math.min(point[1], shoot[1]);
                return true;
            }
        }
        minShoot.add(point);
        return false;
    }

    public static void main(String[] args) {
        Solution452 solution452 = new Solution452();
        // 2
        System.out.println(solution452.findMinArrowShots(new int[][]{{10, 16}, {2, 8}, {1, 6}, {7, 12}}));
        // 4
        System.out.println(solution452.findMinArrowShots(new int[][]{{1, 2}, {3, 4}, {5, 6}, {7, 8}}));
        // 2
        System.out.println(solution452.findMinArrowShots(new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}}));
        // 1
        System.out.println(solution452.findMinArrowShots(new int[][]{{1, 2}}));
        // 1
        System.out.println(solution452.findMinArrowShots(new int[][]{{2, 3}, {2, 3}}));
        // 2   错误答案这个过不去
        System.out.println(solution452.findMinArrowShots(new int[][]{{3, 9}, {7, 12}, {3, 8}, {6, 8}, {9, 10}, {2, 9}, {0, 9}, {3, 9}, {0, 6}, {2, 8}}));
    }
}
