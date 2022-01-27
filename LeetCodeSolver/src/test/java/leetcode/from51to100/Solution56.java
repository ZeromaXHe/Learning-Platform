package leetcode.from51to100;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/1/12 15:38
 * @Description: 56. 合并区间 | 难度：中等 | 标签：数组、排序
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 * <p>
 * 示例 1：
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * <p>
 * 示例 2：
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * <p>
 * 提示：
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution56 {
    /**
     * 执行用时： 9 ms , 在所有 Java 提交中击败了 14.94% 的用户
     * 内存消耗： 42.8 MB , 在所有 Java 提交中击败了 7.90% 的用户
     * 通过测试用例： 169 / 169
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));

        List<int[]> result = new LinkedList<>();
        int[] interval = new int[]{intervals[0][0], intervals[0][1]};
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= interval[1]) {
                interval[1] = Math.max(interval[1], intervals[i][1]);
            } else {
                result.add(interval);
                interval = new int[]{intervals[i][0], intervals[i][1]};
            }
        }
        result.add(interval);

        return result.toArray(new int[result.size()][]);
    }
}
