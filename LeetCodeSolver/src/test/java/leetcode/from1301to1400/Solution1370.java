package leetcode.from1301to1400;

import java.util.Arrays;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/11/25 19:51
 * @Description: 1370.上升下降字符串 | 难度：简单 | 标签：排序、字符串
 * 给你一个字符串 s ，请你根据下面的算法重新构造字符串：
 * <p>
 * 从 s 中选出 最小 的字符，将它 接在 结果字符串的后面。
 * 从 s 剩余字符中选出 最小 的字符，且该字符比上一个添加的字符大，将它 接在 结果字符串后面。
 * 重复步骤 2 ，直到你没法从 s 中选择字符。
 * 从 s 中选出 最大 的字符，将它 接在 结果字符串的后面。
 * 从 s 剩余字符中选出 最大 的字符，且该字符比上一个添加的字符小，将它 接在 结果字符串后面。
 * 重复步骤 5 ，直到你没法从 s 中选择字符。
 * 重复步骤 1 到 6 ，直到 s 中所有字符都已经被选过。
 * 在任何一步中，如果最小或者最大字符不止一个 ，你可以选择其中任意一个，并将其添加到结果字符串。
 * <p>
 * 请你返回将 s 中字符重新排序后的 结果字符串 。
 * <p>
 * 示例 1：
 * 输入：s = "aaaabbbbcccc"
 * 输出："abccbaabccba"
 * 解释：第一轮的步骤 1，2，3 后，结果字符串为 result = "abc"
 * 第一轮的步骤 4，5，6 后，结果字符串为 result = "abccba"
 * 第一轮结束，现在 s = "aabbcc" ，我们再次回到步骤 1
 * 第二轮的步骤 1，2，3 后，结果字符串为 result = "abccbaabc"
 * 第二轮的步骤 4，5，6 后，结果字符串为 result = "abccbaabccba"
 * <p>
 * 示例 2：
 * 输入：s = "rat"
 * 输出："art"
 * 解释：单词 "rat" 在上述算法重排序以后变成 "art"
 * <p>
 * 示例 3：
 * 输入：s = "leetcode"
 * 输出："cdelotee"
 * <p>
 * 示例 4：
 * 输入：s = "ggggggg"
 * 输出："ggggggg"
 * <p>
 * 示例 5：
 * 输入：s = "spo"
 * 输出："ops"
 * <p>
 * 提示：
 * 1 <= s.length <= 500
 * s 只包含小写英文字母。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/increasing-decreasing-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution1370 {
    /**
     * 执行用时：5 ms, 在所有 Java 提交中击败了 25.92% 的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了 91.30% 的用户
     * 尝试优化，反而变慢了……
     *
     * @param s
     * @return
     */
    public String sortString(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        int[] sortCount = Arrays.copyOf(count, 26);
        Arrays.sort(sortCount);
        int index = 0;
        // 将index置到第一个非0的位置
        while (index < 26 && sortCount[index] == 0) {
            index++;
        }
        int pre = 0;
        int num = sortCount[index];
        StringBuilder sb = new StringBuilder();
        StringBuilder sbTemp = new StringBuilder();
        boolean ascend = true;
        while (sb.length() < s.length()) {
            int repeat = num - pre;
            for (int i = 0; i < 26; i++) {
                if (count[i] >= repeat) {
                    sbTemp.append((char) ('a' + i));
                    count[i] -= repeat;
                }
            }
            String asc = sbTemp.toString();
            String desc = sbTemp.reverse().toString();
            if (repeat % 2 == 0) {
                for (int i = 0; i < repeat / 2; i++) {
                    sb.append(ascend ? asc + desc : desc + asc);
                }
            } else {
                sb.append(ascend ? asc : desc);
                for (int i = 0; i < repeat / 2; i++) {
                    sb.append(ascend ? desc + asc : asc + desc);
                }
                ascend = !ascend;
            }
            sbTemp.setLength(0);
            while (index < 25 && sortCount[index + 1] == num) {
                index++;
            }
            if (index == 25) {
                break;
            }
            pre = num;
            num = sortCount[++index];
        }
        return sb.toString();
    }

    /**
     * 执行用时：3 ms, 在所有 Java 提交中击败了 98.12% 的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了 85.94% 的用户
     *
     * @param s
     * @return
     */
    public String sortString_version1(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        int len = s.length();
        loop:
        while (sb.length() < len) {
            for (int i = 0; i < 26; i++) {
                if (count[i] > 0) {
                    sb.append((char) ('a' + i));
                    if (sb.length() >= len) {
                        break loop;
                    }
                    count[i]--;
                }
            }
            for (int i = 25; i >= 0; i--) {
                if (count[i] > 0) {
                    sb.append((char) ('a' + i));
                    if (sb.length() >= len) {
                        break loop;
                    }
                    count[i]--;
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution1370 solution1370 = new Solution1370();
        // cdelotee
        System.out.println(solution1370.sortString("leetcode"));
        // "abccbaabccba"
        System.out.println(solution1370.sortString("aaaabbbbcccc"));
        // "defjkqvwxyzxkj"
        System.out.println(solution1370.sortString("jkzkydvxewqjfx"));
    }
}
