package leetcode.from1501to1600;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2021/1/19 10:40
 * @Description: 1584.连接所有点的最小费用 | 难度：中等 | 标签：并查集
 * 给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。
 * <p>
 * 连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 val 的绝对值。
 * <p>
 * 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。
 * <p>
 * 示例 1：
 * 输入：points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
 * 输出：20
 * 解释：
 * 我们可以按照上图所示连接所有点得到最小总费用，总费用为 20 。
 * 注意到任意两个点之间只有唯一一条路径互相到达。
 * <p>
 * 示例 2：
 * 输入：points = [[3,12],[-2,5],[-4,1]]
 * 输出：18
 * <p>
 * 示例 3：
 * 输入：points = [[0,0],[1,1],[1,0],[-1,1]]
 * 输出：4
 * <p>
 * 示例 4：
 * 输入：points = [[-1000000,-1000000],[1000000,1000000]]
 * 输出：4000000
 * <p>
 * 示例 5：
 * 输入：points = [[0,0]]
 * 输出：0
 * <p>
 * 提示：
 * 1 <= points.length <= 1000
 * -10^6 <= xi, yi <= 10^6
 * 所有点 (xi, yi) 两两不同。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/min-cost-to-connect-all-points
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution1584 {
    /**
     * 执行用时： 642 ms , 在所有 Java 提交中击败了 34.21% 的用户
     * 内存消耗： 79.7 MB , 在所有 Java 提交中击败了 7.11% 的用户
     * 慢，待优化
     *
     * @param points
     * @return
     */
    public int minCostConnectPoints(int[][] points) {
        List<int[]> list = new LinkedList<>();
        int result = 0;
        int count = 0;
        // 这里选边可以优化
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                list.add(new int[]{Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]), i, j});
            }
        }
        list.sort(Comparator.comparingInt(o -> o[0]));

        int[][] union = new int[points.length][2];
        for (int i = 0; i < union.length; i++) {
            union[i] = new int[]{i, 1};
        }
        for (int[] edge : list) {
            if (findRoot(union, edge[1]) != findRoot(union, edge[2])) {
                combine(union, edge[1], edge[2]);
                result += edge[0];
                count++;
                if (count == points.length - 1) {
                    break;
                }
            }
        }
        return result;
    }

    private void combine(int[][] union, int x, int y) {
        int xRoot = findRoot(union, x);
        int yRoot = findRoot(union, y);
        if (union[xRoot][1] < union[yRoot][1]) {
            union[xRoot][0] = yRoot;
        } else {
            union[yRoot][0] = xRoot;
            if (union[xRoot][1] == union[yRoot][1]) {
                union[xRoot][1]++;
            }
        }
    }

    private int findRoot(int[][] union, int i) {
        int root = i;
        while (union[root][0] != root) {
            root = union[root][0];
        }
        return root;
    }

    @Test
    public void minCostConnectPointsTest() {
        Assert.assertEquals(20, minCostConnectPoints(new int[][]{{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}}));
    }
}
