package leetcode.first100;

import java.util.HashSet;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/11/20 18:07
 * @Description: 3.无重复字符的最长子串 | 难度：中等 | 标签：哈希表、双指针、字符串、滑动窗口
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * <p>
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution3 {
    /**
     * 执行用时：6 ms, 在所有 Java 提交中击败了 86.48% 的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了 79.06% 的用户
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) {
            return s.length();
        }
        char[] chars = s.toCharArray();
        int left = 0;
        int right = 1;
        int max = 1;
        HashSet<Character> set = new HashSet<>();
        set.add(chars[left]);
        while (right < chars.length) {
            if (set.contains(chars[right])) {
                while (chars[left] != chars[right]) {
                    set.remove(chars[left]);
                    left++;
                }
                left++;
            } else {
                set.add(chars[right]);
                if (right - left + 1 > max) {
                    max = right - left + 1;
                }
            }
            right++;
        }
        return max;
    }

    public static void main(String[] args) {
        Solution3 solution3 = new Solution3();
        // 妈的，还有这种输入。
        // 一开始理解成一定是小写字符了，用的数组判断存在。
        // 提交后报错才发现还有这样的输入，改成哈希表就过了。
        // 题目里也不说明清楚，我还专门看了一下，会不会有异常输入，屁都没说。真的恶心。
        System.out.println(solution3.lengthOfLongestSubstring("   "));
    }
}
