package leetcode.from1201to1250;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/2/5 9:57
 * @Description: 1208.尽可能使字符串相等 | 难度：中等 | 标签：数组、滑动窗口
 * 给你两个长度相同的字符串，s 和 t。
 * <p>
 * 将 s 中的第 i 个字符变到 t 中的第 i 个字符需要 |s[i] - t[i]| 的开销（开销可能为 0），也就是两个字符的 ASCII 码值的差的绝对值。
 * <p>
 * 用于变更字符串的最大预算是 maxCost。在转化字符串时，总开销应当小于等于该预算，这也意味着字符串的转化可能是不完全的。
 * <p>
 * 如果你可以将 s 的子字符串转化为它在 t 中对应的子字符串，则返回可以转化的最大长度。
 * <p>
 * 如果 s 中没有子字符串可以转化成 t 中对应的子字符串，则返回 0。
 * <p>
 * 示例 1：
 * 输入：s = "abcd", t = "bcdf", cost = 3
 * 输出：3
 * 解释：s 中的 "abc" 可以变为 "bcd"。开销为 3，所以最大长度为 3。
 * <p>
 * 示例 2：
 * 输入：s = "abcd", t = "cdef", cost = 3
 * 输出：1
 * 解释：s 中的任一字符要想变成 t 中对应的字符，其开销都是 2。因此，最大长度为 1。
 * <p>
 * 示例 3：
 * 输入：s = "abcd", t = "acde", cost = 0
 * 输出：1
 * 解释：你无法作出任何改动，所以最大长度为 1。
 * <p>
 * 提示：
 * 1 <= s.length, t.length <= 10^5
 * 0 <= maxCost <= 10^6
 * s 和 t 都只含小写英文字母。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/get-equal-substrings-within-budget
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution1208 {
    /**
     * 执行用时： 8 ms , 在所有 Java 提交中击败了 61.35% 的用户
     * 内存消耗： 38.6 MB , 在所有 Java 提交中击败了 53.62% 的用户
     *
     * @param s
     * @param t
     * @param maxCost
     * @return
     */
    public int equalSubstring(String s, String t, int maxCost) {
        int l = 0;
        int r = 0;
        int sum = 0;
        int maxLen = 0;
        while (r < s.length()) {
            int diff = Math.abs(s.charAt(r) - t.charAt(r));
            while (sum + diff > maxCost) {
                if (r - l > maxLen) {
                    maxLen = r - l;
                }
                sum -= Math.abs(s.charAt(l) - t.charAt(l));
                l++;
            }
            sum += diff;
            r++;
        }
        return Math.max(maxLen, r - l);
    }

    @Test
    public void equalSubstringTest() {
        Assert.assertEquals(2, equalSubstring("krrgw", "zjxss", 19));
        Assert.assertEquals(3, equalSubstring("abcd", "bcdf", 3));
        Assert.assertEquals(1, equalSubstring("abcd", "cdef", 3));
        Assert.assertEquals(1, equalSubstring("abcd", "acde", 0));
    }
}
