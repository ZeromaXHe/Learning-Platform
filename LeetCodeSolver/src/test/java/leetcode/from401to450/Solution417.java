package leetcode.from401to450;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/4/27 9:58
 * @Description: 417. 太平洋大西洋水流问题 | 难度：中等 | 标签：深度优先搜索、广度优先搜索、数组、矩阵
 * 有一个 m × n 的矩形岛屿，与 太平洋 和 大西洋 相邻。 “太平洋” 处于大陆的左边界和上边界，而 “大西洋” 处于大陆的右边界和下边界。
 * <p>
 * 这个岛被分割成一个由若干方形单元格组成的网格。给定一个 m x n 的整数矩阵 heights ， heights[r][c] 表示坐标 (r, c) 上单元格 高于海平面的高度 。
 * <p>
 * 岛上雨水较多，如果相邻单元格的高度 小于或等于 当前单元格的高度，雨水可以直接向北、南、东、西流向相邻单元格。水可以从海洋附近的任何单元格流入海洋。
 * <p>
 * 返回 网格坐标 result 的 2D列表 ，其中 result[i] = [ri, ci] 表示雨水可以从单元格 (ri, ci) 流向 太平洋和大西洋 。
 * <p>
 * 示例 1：
 * 输入: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
 * 输出: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 * <p>
 * 示例 2：
 * 输入: heights = [[2,1],[1,2]]
 * 输出: [[0,0],[0,1],[1,0],[1,1]]
 * <p>
 * 提示：
 * m == heights.length
 * n == heights[r].length
 * 1 <= m, n <= 200
 * 0 <= heights[r][c] <= 10^5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/pacific-atlantic-water-flow
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution417 {
    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 43.1 MB , 在所有 Java 提交中击败了 6.92% 的用户
     * 通过测试用例： 113 / 113
     *
     * @param heights
     * @return
     */
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        byte[][] visit = new byte[m][n];
        for (int i = 0; i < m; i++) {
            dfsPacific(heights, visit, i, 0);
        }
        for (int i = 1; i < n; i++) {
            dfsPacific(heights, visit, 0, i);
        }
        List<List<Integer>> result = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            dfsAtlantic(heights, visit, i, n - 1, result);
        }
        for (int i = 0; i < n - 1; i++) {
            dfsAtlantic(heights, visit, m - 1, i, result);
        }
        return result;
    }

    private void dfsPacific(int[][] heights, byte[][] visit, int x, int y) {
        if ((visit[x][y] & 1) > 0) {
            return;
        }
        visit[x][y] += 1;
        if (x < heights.length - 1 && heights[x + 1][y] >= heights[x][y]) {
            dfsPacific(heights, visit, x + 1, y);
        }
        if (y < heights[0].length - 1 && heights[x][y + 1] >= heights[x][y]) {
            dfsPacific(heights, visit, x, y + 1);
        }
        if (x > 0 && heights[x - 1][y] >= heights[x][y]) {
            dfsPacific(heights, visit, x - 1, y);
        }
        if (y > 0 && heights[x][y - 1] >= heights[x][y]) {
            dfsPacific(heights, visit, x, y - 1);
        }
    }

    private void dfsAtlantic(int[][] heights, byte[][] visit, int x, int y, List<List<Integer>> result) {
        if ((visit[x][y] & 2) > 0) {
            return;
        }
        visit[x][y] += 2;
        if (visit[x][y] == 3) {
            ArrayList<Integer> pair = new ArrayList<>();
            pair.add(x);
            pair.add(y);
            result.add(pair);
        }
        if (x < heights.length - 1 && heights[x + 1][y] >= heights[x][y]) {
            dfsAtlantic(heights, visit, x + 1, y, result);
        }
        if (y < heights[0].length - 1 && heights[x][y + 1] >= heights[x][y]) {
            dfsAtlantic(heights, visit, x, y + 1, result);
        }
        if (x > 0 && heights[x - 1][y] >= heights[x][y]) {
            dfsAtlantic(heights, visit, x - 1, y, result);
        }
        if (y > 0 && heights[x][y - 1] >= heights[x][y]) {
            dfsAtlantic(heights, visit, x, y - 1, result);
        }
    }
}
