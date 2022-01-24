package leetcode.from101to200;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhuxi
 * @Time: 2022/1/24 11:14
 * @Description: 149. 直线上最多的点数 | 难度：困难 | 标签：几何、哈希表、数学
 * 给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
 * <p>
 * 示例 1：
 * 输入：points = [[1,1],[2,2],[3,3]]
 * 输出：3
 * <p>
 * 示例 2：
 * 输入：points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * 输出：4
 * <p>
 * 提示：
 * 1 <= points.length <= 300
 * points[i].length == 2
 * -104 <= xi, yi <= 104
 * points 中的所有点 互不相同
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/max-points-on-a-line
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution149 {
    @Test
    public void maxPointsTest() {
        // 通过测试用例： 24 / 34
        Assert.assertEquals(5, maxPoints(new int[][]{{0, 0}, {4, 5}, {7, 8}, {8, 9}, {5, 6}, {3, 4}, {1, 1}}));
        // 通过测试用例： 32 / 34
        Assert.assertEquals(6, maxPoints(new int[][]{{7, 3}, {19, 19}, {-16, 3}, {13, 17}, {-18, 1}, {-18, -17},
                {13, -3}, {3, 7}, {-11, 12}, {7, 19}, {19, -12}, {20, -18}, {-16, -15}, {-10, -15}, {-16, -18}, {-14, -1},
                {18, 10}, {-13, 8}, {7, -5}, {-4, -9}, {-11, 2}, {-9, -9}, {-5, -16}, {10, 14}, {-3, 4}, {1, -20}, {2, 16},
                {0, 14}, {-14, 5}, {15, -11}, {3, 11}, {11, -10}, {-1, -7}, {16, 7}, {1, -11}, {-8, -3}, {1, -6}, {19, 7},
                {3, 6}, {-1, -2}, {7, -3}, {-6, -8}, {7, 1}, {-15, 12}, {-17, 9}, {19, -9}, {1, 0}, {9, -10}, {6, 20},
                {-12, -4}, {-16, -17}, {14, 3}, {0, -1}, {-18, 9}, {-15, 15}, {-3, -15}, {-5, 20}, {15, -14}, {9, -17},
                {10, -14}, {-7, -11}, {14, 9}, {1, -1}, {15, 12}, {-5, -1}, {-17, -5}, {15, -2}, {-12, 11}, {19, -18},
                {8, 7}, {-5, -3}, {-17, -1}, {-18, 13}, {15, -3}, {4, 18}, {-14, -15}, {15, 8}, {-18, -12}, {-15, 19},
                {-9, 16}, {-9, 14}, {-12, -14}, {-2, -20}, {-3, -13}, {10, -7}, {-2, -10}, {9, 10}, {-1, 7}, {-17, -6},
                {-15, 20}, {5, -17}, {6, -6}, {-11, -8}}));
        Assert.assertEquals(3, maxPoints(new int[][]{{4, 5}, {4, -1}, {4, 0}}));
    }

    /**
     * 执行用时： 5 ms , 在所有 Java 提交中击败了 83.78% 的用户
     * 内存消耗： 37.6 MB , 在所有 Java 提交中击败了 67.84% 的用户
     * 通过测试用例： 34 / 34
     *
     * @param points
     * @return
     */
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) {
            return n;
        }
        Map<Double, Integer> map = new HashMap<>();
        int max = 2;
        for (int i = n - 1; i >= max; i--) {
            int[] pointI = points[i];
            map.clear();
            for (int j = 0; j < i; j++) {
                int[] pointJ = points[j];
                double k = ((double) pointI[1] - pointJ[1]) / (pointI[0] - pointJ[0]);
                if (k == Double.NEGATIVE_INFINITY) {
                    // 在垂直x轴的直线上的特殊情况
                    k = Double.POSITIVE_INFINITY;
                }
                int count = map.getOrDefault(k, 1) + 1;
                map.put(k, count);
                if (count > max) {
                    max = count;
                }
            }
            if (max > n / 2) {
                break;
            }
        }
        return max;
    }
}
