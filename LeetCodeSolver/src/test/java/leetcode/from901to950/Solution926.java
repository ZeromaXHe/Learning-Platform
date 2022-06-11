package leetcode.from901to950;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2022/6/11 11:02
 * @Description: 926. 将字符串翻转到单调递增 | 难度：中等 | 标签：字符串、动态规划
 * 如果一个二进制字符串，是以一些 0（可能没有 0）后面跟着一些 1（也可能没有 1）的形式组成的，那么该字符串是 单调递增 的。
 * <p>
 * 给你一个二进制字符串 s，你可以将任何 0 翻转为 1 或者将 1 翻转为 0 。
 * <p>
 * 返回使 s 单调递增的最小翻转次数。
 * <p>
 * 示例 1：
 * 输入：s = "00110"
 * 输出：1
 * 解释：翻转最后一位得到 00111.
 * <p>
 * 示例 2：
 * 输入：s = "010110"
 * 输出：2
 * 解释：翻转得到 011111，或者是 000111。
 * <p>
 * 示例 3：
 * 输入：s = "00011000"
 * 输出：2
 * 解释：翻转得到 00000000。
 * <p>
 * 提示：
 * 1 <= s.length <= 105
 * s[i] 为 '0' 或 '1'
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/flip-string-to-monotone-increasing
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution926 {
    @Test
    public void minFlipsMonoIncrTest() {
        Assert.assertEquals(1, minFlipsMonoIncr("00110"));
        Assert.assertEquals(2, minFlipsMonoIncr("010110"));
        Assert.assertEquals(2, minFlipsMonoIncr("00011000"));
        Assert.assertEquals(1, minFlipsMonoIncr("11011"));
    }

    /**
     * 执行用时： 15 ms , 在所有 Java 提交中击败了 42.48% 的用户
     * 内存消耗： 42.1 MB , 在所有 Java 提交中击败了 44.54% 的用户
     * 通过测试用例： 93 / 93
     *
     * @param s
     * @return
     */
    public int minFlipsMonoIncr(String s) {
        int flip = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '0') {
                flip++;
            }
        }
        int min = flip;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '0') {
                flip--;
            } else {
                flip++;
            }
            min = Math.min(min, flip);
        }
        return min;
    }

    /**
     * 执行用时： 19 ms , 在所有 Java 提交中击败了 34.96% 的用户
     * 内存消耗： 43.3 MB , 在所有 Java 提交中击败了 29.20% 的用户
     * 通过测试用例： 93 / 93
     *
     * @param s
     * @return
     */
    public int minFlipsMonoIncr_doubleForDoubleArr(String s) {
        int len = s.length();
        int before1 = 0;
        int after0 = 0;
        int[] countBefore01 = new int[len];
        int[] countBefore1 = new int[len];
        int[] countAfter01 = new int[len];
        int[] countAfter0 = new int[len];
        for (int i = 1; i < len; i++) {
            if (s.charAt(i - 1) == '1') {
                before1++;
                countBefore01[i] = countBefore01[i - 1];
                countBefore1[i] = countBefore1[i - 1] + 1;
            } else {
                countBefore01[i] = countBefore01[i - 1] + before1;
                countBefore1[i] = countBefore1[i - 1];
                before1 = 0;
            }
        }
        for (int i = len - 2; i >= 0; i--) {
            if (s.charAt(i + 1) == '0') {
                after0++;
                countAfter01[i] = countAfter01[i + 1];
                countAfter0[i] = countAfter0[i + 1] + 1;
            } else {
                countAfter01[i] = countAfter01[i + 1] + after0;
                countAfter0[i] = countAfter0[i + 1];
                after0 = 0;
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < len; i++) {
            min = Math.min(min,
                    Math.min(countBefore1[i] + countAfter01[i - 1], countBefore01[i] + countAfter0[i - 1]));
        }
        return min;
    }
}
