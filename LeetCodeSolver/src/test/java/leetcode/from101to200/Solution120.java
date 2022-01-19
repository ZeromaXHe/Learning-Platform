package leetcode.from101to200;

import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/1/19 18:05
 * @Description: 120. 三角形的最短路径和 | 难度：中等 | 标签：数组、动态规划
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 * <p>
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 * <p>
 * 示例 1：
 * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * 输出：11
 * 解释：如下面简图所示：
 * |    2
 * |   3 4
 * |  6 5 7
 * | 4 1 8 3
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * <p>
 * 示例 2：
 * 输入：triangle = [[-10]]
 * 输出：-10
 * <p>
 * 提示：
 * 1 <= triangle.length <= 200
 * triangle[0].length == 1
 * triangle[i].length == triangle[i - 1].length + 1
 * -104 <= triangle[i][j] <= 104
 * <p>
 * 进阶：
 * 你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/triangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution120 {
    /**
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 89.56% 的用户
     * 内存消耗： 38.1 MB , 在所有 Java 提交中击败了 48.60% 的用户
     * 通过测试用例： 44 / 44
     * <p>
     * 貌似从最后一层倒推会快一点？省一次循环。不过没试过，有时间可以试试。
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] paths = new int[triangle.size()];
        for (List<Integer> layer : triangle) {
            int i = 0;
            int prePath = 1_000_000;
            for (Integer path : layer) {
                int temp = paths[i];
                if (i == 0) {
                    paths[i] += path;
                } else if (i < layer.size() - 1) {
                    paths[i] = Math.min(paths[i], prePath) + path;
                } else {
                    paths[i] = prePath + path;
                }
                prePath = temp;
                i++;
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < paths.length; i++) {
            if (paths[i] < min) {
                min = paths[i];
            }
        }
        return min;
    }
}
