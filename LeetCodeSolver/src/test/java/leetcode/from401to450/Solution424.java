package leetcode.from401to450;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/2/2 11:21
 * @Description: 424.替换后的最长重复字符 | 难度：中等 | 标签：双指针、滑动窗口
 * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。在执行上述操作后，找到包含重复字母的最长子串的长度。
 * <p>
 * 注意：字符串长度 和 k 不会超过 104。
 * <p>
 * 示例 1：
 * 输入：s = "ABAB", k = 2
 * 输出：4
 * 解释：用两个'A'替换为两个'B',反之亦然。
 * <p>
 * 示例 2：
 * 输入：s = "AABABBA", k = 1
 * 输出：4
 * 解释：
 * 将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
 * 子串 "BBBB" 有最长重复字母, 答案为 4。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-repeating-character-replacement
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution424 {
    /**
     * 执行用时： 8 ms , 在所有 Java 提交中击败了 38.63% 的用户
     * 内存消耗： 38.5 MB , 在所有 Java 提交中击败了 45.87% 的用户
     * 先看了官方题解的思路，但是自己实现的比官方题解复杂
     * 官方题解的左指针++的条件是count[s.charAt(右指针) - 'A'] > i - index - k + 1，所以就可以简化max的取值在每次无须判断直接计算。
     *
     * @param s
     * @param k
     * @return
     */
    public int characterReplacement(String s, int k) {
        if (k >= s.length() - 1) {
            return s.length();
        }
        int[] count = new int[26];
        int index = 0;
        for (int i = 0; i < k + 1; i++) {
            count[s.charAt(i) - 'A']++;
        }
        int max = k + 1;
        for (int i = k + 1; i < s.length(); i++) {
            count[s.charAt(i) - 'A']++;
            if (count[s.charAt(i) - 'A'] >= i - index - k + 1 || count[s.charAt(i - 1) - 'A'] >= i - index - k + 1) {
                max = Math.max(max, i - index + 1);
            } else {
                count[s.charAt(index) - 'A']--;
                index++;
            }
        }
        return max;
    }

    /**
     * 官方题解
     *
     * @param s
     * @param k
     * @return
     */
    public int characterReplacement_authoritySolution(String s, int k) {
        int[] num = new int[26];
        int n = s.length();
        int maxn = 0;
        int left = 0, right = 0;
        while (right < n) {
            num[s.charAt(right) - 'A']++;
            maxn = Math.max(maxn, num[s.charAt(right) - 'A']);
            if (right - left + 1 - maxn > k) {
                num[s.charAt(left) - 'A']--;
                left++;
            }
            right++;
        }
        return right - left;
    }

    @Test
    public void characterReplacementTest() {
        Assert.assertEquals(4, characterReplacement("ABAB", 2));
        Assert.assertEquals(4, characterReplacement("AABABBA", 1));
        Assert.assertEquals(5, characterReplacement("BAAAB", 2));
    }
}
