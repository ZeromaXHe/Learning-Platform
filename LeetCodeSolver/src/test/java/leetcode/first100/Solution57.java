package leetcode.first100;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2021/1/13 19:17
 * @Description: 57.插入区间 | 难度：困难 | 标签：排序、数组
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * <p>
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * <p>
 * 示例 1：
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 * <p>
 * 示例 2：
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insert-interval
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution57 {
    /**
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 20.61% 的用户
     * 内存消耗： 41.1 MB , 在所有 Java 提交中击败了 5.02% 的用户
     *
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) {
            return new int[][]{newInterval};
        }
        List<int[]> list = new LinkedList<>();
        int[] insertInterval = new int[]{newInterval[0], newInterval[1]};
        boolean insert = false;
        for (int[] interval : intervals) {
            // interval和newInterval交叉(因为interval本身之间不交叉，所以这里的判断条件其实可以优化的)
            if (Math.max(interval[0], newInterval[0]) <= Math.min(interval[1], newInterval[1])) {
                if (interval[0] < insertInterval[0]) {
                    insertInterval[0] = interval[0];
                }
                if (interval[1] > insertInterval[1]) {
                    insertInterval[1] = interval[1];
                }
            } else {
                if (!insert && interval[0] > insertInterval[1]) {
                    list.add(insertInterval);
                    insert = true;
                }
                list.add(interval);
            }
        }
        if (!insert) {
            list.add(insertInterval);
        }
        int[][] result = new int[list.size()][2];
        int index = 0;
        for (int[] arr : list) {
            result[index++] = arr;
        }
        return result;
    }

    @Test
    public void insertTest() {
        Assert.assertArrayEquals(insert(new int[][]{{1, 3}, {6, 9}}, new int[]{2, 5}), new int[][]{{1, 5}, {6, 9}});
    }
}
