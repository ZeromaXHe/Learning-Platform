package leetcode.jianzhi_offer_II;

import java.util.Collections;
import java.util.List;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer II 035. 最小时间差 | 难度：中等 | 标签：数组、数学、字符串、排序
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
 * 注意：本题与主站 539 题相同： https://leetcode-cn.com/problems/minimum-time-difference/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/569nqc
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/21 16:59
 */
public class SolutionOfferII35 {
    /**
     * 执行用时：9 ms, 在所有 Java 提交中击败了 42.67% 的用户
     * 内存消耗：43 MB, 在所有 Java 提交中击败了 23.99% 的用户
     * 通过测试用例：113 / 113
     *
     * @param timePoints
     * @return
     */
    public int findMinDifference(List<String> timePoints) {
        Collections.sort(timePoints);
        int min = Integer.MAX_VALUE;
        int n = timePoints.size();
        for (int i = 0; i < n; i++) {
            min = Math.min(min, diff(timePoints.get(i), timePoints.get((i + 1) % n)));
            if (min == 0) {
                return min;
            }
        }
        return min;
    }

    private int diff(String s1, String s2) {
        if (s1.equals(s2)) {
            return 0;
        }
        int min1 = ((s1.charAt(0) - '0') * 10 + (s1.charAt(1) - '0')) * 60 + (s1.charAt(3) - '0') * 10 + (s1.charAt(4) - '0');
        int min2 = ((s2.charAt(0) - '0') * 10 + (s2.charAt(1) - '0')) * 60 + (s2.charAt(3) - '0') * 10 + (s2.charAt(4) - '0');
        return Math.min(Math.abs(min1 - min2), Math.min(min1, min2) + 24 * 60 - Math.max(min1, min2));
    }
}
