package leetcode.from851to900;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/11/23 9:49
 * @Description: 859. 亲密字符串 | 难度：简单 | 标签：哈希表、字符串
 * 给你两个字符串 s 和 goal ，只要我们可以通过交换 s 中的两个字母得到与 goal 相等的结果，就返回 true ；否则返回 false 。
 * <p>
 * 交换字母的定义是：取两个下标 i 和 j （下标从 0 开始）且满足 i != j ，接着交换 s[i] 和 s[j] 处的字符。
 * <p>
 * 例如，在 "abcd" 中交换下标 0 和下标 2 的元素可以生成 "cbad" 。
 * <p>
 * 示例 1：
 * 输入：s = "ab", goal = "ba"
 * 输出：true
 * 解释：你可以交换 s[0] = 'a' 和 s[1] = 'b' 生成 "ba"，此时 s 和 goal 相等。
 * <p>
 * 示例 2：
 * 输入：s = "ab", goal = "ab"
 * 输出：false
 * 解释：你只能交换 s[0] = 'a' 和 s[1] = 'b' 生成 "ba"，此时 s 和 goal 不相等。
 * <p>
 * 示例 3：
 * 输入：s = "aa", goal = "aa"
 * 输出：true
 * 解释：你可以交换 s[0] = 'a' 和 s[1] = 'a' 生成 "aa"，此时 s 和 goal 相等。
 * <p>
 * 示例 4：
 * 输入：s = "aaaaaaabc", goal = "aaaaaaacb"
 * 输出：true
 * <p>
 * 提示：
 * 1 <= s.length, goal.length <= 2 * 104
 * s 和 goal 由小写英文字母组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/buddy-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution859 {
    @Test
    public void buddyStringsTest() {
        Assert.assertTrue(buddyStrings("ab", "ba"));
        Assert.assertFalse(buddyStrings("ab", "ab"));
        Assert.assertTrue(buddyStrings("aa", "aa"));
        Assert.assertTrue(buddyStrings("aaaaaaabc", "aaaaaaacb"));
        Assert.assertFalse(buddyStrings("abcaa", "abcbb"));
        Assert.assertFalse(buddyStrings("ab", "ca"));
    }

    /**
     * 执行用时： 5 ms , 在所有 Java 提交中击败了 16.57% 的用户
     * 内存消耗： 38.2 MB , 在所有 Java 提交中击败了 74.73% 的用户
     * 通过测试用例： 34 / 34
     * <p>
     * 简单题也做错3次，也是醉了。算法效率也不高
     * 除了一开始忘记考虑相等且有重复字符时候的判断，其他都是马虎，细节写错了。
     *
     * @param s
     * @param goal
     * @return
     */
    public boolean buddyStrings(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        int[] count = new int[26];
        boolean sameChar = false;
        char sChar = '#';
        char goalChar = '#';
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != goal.charAt(i)) {
                if (sChar == '*') {
                    return false;
                } else if (sChar == '#') {
                    sChar = s.charAt(i);
                    goalChar = goal.charAt(i);
                } else if (goal.charAt(i) != sChar || s.charAt(i) != goalChar) {
                    return false;
                } else {
                    sChar = '*';
                    goalChar = '*';
                }
            } else {
                count[s.charAt(i) - 'a']++;
                if (count[s.charAt(i) - 'a'] >= 2) {
                    sameChar = true;
                }
            }
        }
        return sChar == '*' || (sChar == '#' && s.equals(goal) && sameChar);
    }
}
