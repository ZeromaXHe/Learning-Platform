package leetcode.from501to550;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/1/18 10:07
 * @Description: 539. 最小时间差 | 难度：中等 | 标签：数组、数学、字符串、排序
 * 给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
 * <p>
 * 示例 1：
 * 输入：timePoints = ["23:59","00:00"]
 * 输出：1
 * <p>
 * 示例 2：
 * 输入：timePoints = ["00:00","23:59","00:00"]
 * 输出：0
 * <p>
 * 提示：
 * 2 <= timePoints <= 2 * 104
 * timePoints[i] 格式为 "HH:MM"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-time-difference
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution539 {
    @Test
    public void findMinDifferenceTest() {
        List<String> timePoints = new LinkedList<>();
        timePoints.add("23:59");
        timePoints.add("00:00");
        Assert.assertEquals(1, findMinDifference(timePoints));
    }

    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 87.83% 的用户
     * 内存消耗： 37.6 MB , 在所有 Java 提交中击败了 98.76% 的用户
     * 通过测试用例： 113 / 113
     *
     * @param timePoints
     * @return
     */
    public int findMinDifference(List<String> timePoints) {
        int[] timeArr = new int[timePoints.size()];
        int index = 0;
        for (String timePoint : timePoints) {
            timeArr[index++] = ((timePoint.charAt(0) - '0') * 10 + timePoint.charAt(1) - '0') * 60
                    + (timePoint.charAt(3) - '0') * 10 + timePoint.charAt(4) - '0';
        }
        Arrays.sort(timeArr);
        int min = timeArr[0] + 24 * 60 - timeArr[timeArr.length - 1];
        for (int i = 1; i < timeArr.length; i++) {
            if (timeArr[i] - timeArr[i - 1] < min) {
                min = timeArr[i] - timeArr[i - 1];
            }
        }
        return min;
    }
}
