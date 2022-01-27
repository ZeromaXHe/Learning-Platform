package leetcode.from101to150;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/1/15 16:24
 * @Description: 115.不同的子序列 | 难度：困难 | 标签：字符串、动态规划
 * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
 *
 * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
 *
 * 题目数据保证答案符合 32 位带符号整数范围。
 *
 * 示例 1：
 * 输入：s = "rabbbit", t = "rabbit"
 * 输出：3
 * 解释：
 * 如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
 * (上箭头符号 ^ 表示选取的字母)
 * rabbbit
 * ^^^^ ^^
 * rabbbit
 * ^^ ^^^^
 * rabbbit
 * ^^^ ^^^
 *
 * 示例 2：
 * 输入：s = "babgbag", t = "bag"
 * 输出：5
 * 解释：
 * 如下图所示, 有 5 种可以从 s 中得到 "bag" 的方案。
 * (上箭头符号 ^ 表示选取的字母)
 * babgbag
 * ^^ ^
 * babgbag
 * ^^    ^
 * babgbag
 * ^    ^^
 * babgbag
 *   ^  ^^
 * babgbag
 *     ^^^
 *
 * 提示：
 * 0 <= s.length, t.length <= 1000
 * s 和 t 由英文字母组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/distinct-subsequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution115 {
    /**
     * 执行用时： 5 ms , 在所有 Java 提交中击败了 58.55% 的用户
     * 内存消耗： 36.6 MB , 在所有 Java 提交中击败了 76.35% 的用户
     *
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        if (t.length() == 0) {
            return 1;
        }
        if (s.length() < t.length()) {
            return 0;
        }
        int[] dp = new int[s.length()];
        dp[0] = s.charAt(0) == t.charAt(0) ? 1 : 0;
        for (int j = 1; j < s.length(); j++) {
            dp[j] = s.charAt(j) == t.charAt(0) ? dp[j - 1] + 1 : dp[j - 1];
        }
        for (int i = 1; i < t.length(); i++) {
            int pre = dp[i];
            if (s.charAt(i) == t.charAt(i)) {
                dp[i] = dp[i - 1];
            } else {
                dp[i] = 0;
            }
            int temp;
            for (int j = i + 1; j < s.length(); j++) {
                temp = dp[j];
                dp[j] = s.charAt(j) == t.charAt(i) ? dp[j - 1] + pre : dp[j - 1];
                pre = temp;
            }
        }
        return dp[s.length() - 1];
    }

    @Test
    public void numDistinctTest() {
        Assert.assertEquals(5, numDistinct("babgbag", "bag"));
    }
}
