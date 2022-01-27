package leetcode.from501to550;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @Author: zhuxi
 * @Time: 2021/12/2 10:24
 * @Description: 506. 相对名次 | 难度：简单 | 标签：数组、排序、堆（优先队列）
 * 给你一个长度为 n 的整数数组 score ，其中 score[i] 是第 i 位运动员在比赛中的得分。所有得分都 互不相同 。
 * <p>
 * 运动员将根据得分 决定名次 ，其中名次第 1 的运动员得分最高，名次第 2 的运动员得分第 2 高，依此类推。运动员的名次决定了他们的获奖情况：
 * <p>
 * 名次第 1 的运动员获金牌 "Gold Medal" 。
 * 名次第 2 的运动员获银牌 "Silver Medal" 。
 * 名次第 3 的运动员获铜牌 "Bronze Medal" 。
 * 从名次第 4 到第 n 的运动员，只能获得他们的名次编号（即，名次第 x 的运动员获得编号 "x"）。
 * 使用长度为 n 的数组 answer 返回获奖，其中 answer[i] 是第 i 位运动员的获奖情况。
 * <p>
 * 示例 1：
 * 输入：score = [5,4,3,2,1]
 * 输出：["Gold Medal","Silver Medal","Bronze Medal","4","5"]
 * 解释：名次为 [1st, 2nd, 3rd, 4th, 5th] 。
 * <p>
 * 示例 2：
 * 输入：score = [10,3,8,9,4]
 * 输出：["Gold Medal","5","Bronze Medal","Silver Medal","4"]
 * 解释：名次为 [1st, 5th, 3rd, 2nd, 4th] 。
 * <p>
 * 提示：
 * n == score.length
 * 1 <= n <= 104
 * 0 <= score[i] <= 106
 * score 中的所有值 互不相同
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/relative-ranks
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution506 {
    @Test
    public void findRelativeRanksTest() {
        Assert.assertArrayEquals(new String[]{"Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"},
                findRelativeRanks(new int[]{5, 4, 3, 2, 1}));
        Assert.assertArrayEquals(new String[]{"Gold Medal", "5", "Bronze Medal", "Silver Medal", "4"},
                findRelativeRanks(new int[]{10, 3, 8, 9, 4}));
    }

    /**
     * 执行用时： 6 ms , 在所有 Java 提交中击败了 76.62% 的用户
     * 内存消耗： 39.5 MB , 在所有 Java 提交中击败了 63.21% 的用户
     * 通过测试用例： 17 / 17
     *
     * @param score
     * @return
     */
    public String[] findRelativeRanks(int[] score) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < score.length; i++) {
            map.put(score[i], i);
        }
        Arrays.sort(score);
        String[] result = new String[score.length];
        for (int i = 0; i < score.length; i++) {
            int index = map.get(score[score.length - 1 - i]);
            if (i >= 3) {
                result[index] = String.valueOf(i + 1);
            } else if (i == 0) {
                result[index] = "Gold Medal";
            } else if (i == 1) {
                result[index] = "Silver Medal";
            } else if (i == 2) {
                result[index] = "Bronze Medal";
            }
        }
        return result;
    }
}
