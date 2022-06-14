package leetcode.from1051to1100;

import java.util.Arrays;

/**
 * @author zhuxi
 * @apiNote
 * @implNote 1051. 高度检查器 | 难度：简单 | 标签：数组、计数排序、排序
 * 学校打算为全体学生拍一张年度纪念照。根据要求，学生需要按照 非递减 的高度顺序排成一行。
 * <p>
 * 排序后的高度情况用整数数组 expected 表示，其中 expected[i] 是预计排在这一行中第 i 位的学生的高度（下标从 0 开始）。
 * <p>
 * 给你一个整数数组 heights ，表示 当前学生站位 的高度情况。heights[i] 是这一行中第 i 位学生的高度（下标从 0 开始）。
 * <p>
 * 返回满足 heights[i] != expected[i] 的 下标数量 。
 * <p>
 * 示例：
 * 输入：heights = [1,1,4,2,1,3]
 * 输出：3
 * 解释：
 * 高度：[1,1,4,2,1,3]
 * 预期：[1,1,1,2,3,4]
 * 下标 2 、4 、5 处的学生高度不匹配。
 * <p>
 * 示例 2：
 * 输入：heights = [5,1,2,3,4]
 * 输出：5
 * 解释：
 * 高度：[5,1,2,3,4]
 * 预期：[1,2,3,4,5]
 * 所有下标的对应学生高度都不匹配。
 * <p>
 * 示例 3：
 * 输入：heights = [1,2,3,4,5]
 * 输出：0
 * 解释：
 * 高度：[1,2,3,4,5]
 * 预期：[1,2,3,4,5]
 * 所有下标的对应学生高度都匹配。
 * <p>
 * 提示：
 * 1 <= heights.length <= 100
 * 1 <= heights[i] <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/height-checker
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @since 2022/6/13 9:44
 */
public class Solution1051 {
    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 68.84% 的用户
     * 内存消耗： 39.3 MB , 在所有 Java 提交中击败了 38.25% 的用户
     * 通过测试用例： 81 / 81
     *
     * @param heights
     * @return
     */
    public int heightChecker(int[] heights) {
        int[] heightsCopy = Arrays.copyOf(heights, heights.length);
        Arrays.sort(heightsCopy);
        int result = 0;
        for (int i = 0; i < heights.length; i++) {
            if (heights[i] != heightsCopy[i]) {
                result++;
            }
        }
        return result;
    }
}
