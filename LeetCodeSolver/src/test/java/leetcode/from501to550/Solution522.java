package leetcode.from501to550;

/**
 * @author zhuxi
 * @apiNote 522. 最长特殊序列 II | 难度：中等 | 标签：数组、哈希表、双指针、字符串、排序
 * 给定字符串列表 strs ，返回其中 最长的特殊序列 。如果最长特殊序列不存在，返回 -1 。
 * <p>
 * 特殊序列 定义如下：该序列为某字符串 独有的子序列（即不能是其他字符串的子序列）。
 * <p>
 *  s 的 子序列可以通过删去字符串 s 中的某些字符实现。
 * <p>
 * 例如，"abc" 是 "aebdc" 的子序列，因为您可以删除"aebdc"中的下划线字符来得到 "abc" 。"aebdc"的子序列还包括"aebdc"、 "aeb" 和 "" (空字符串)。
 * <p>
 * 示例 1：
 * 输入: strs = ["aba","cdc","eae"]
 * 输出: 3
 * <p>
 * 示例 2:
 * 输入: strs = ["aaa","aaa","aa"]
 * 输出: -1
 * <p>
 * 提示:
 * 2 <= strs.length <= 50
 * 1 <= strs[i].length <= 10
 * strs[i] 只包含小写英文字母
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-uncommon-subsequence-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2022/6/27 10:48
 */
public class Solution522 {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了 99.11% 的用户
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了 36.22% 的用户
     * 通过测试用例：98 / 98
     *
     * @param strs
     * @return
     */
    public int findLUSlength(String[] strs) {
        int max = -1;
        loop:
        for (int i = 0; i < strs.length; i++) {
            for (int j = 0; j < strs.length; j++) {
                if (i == j) {
                    continue;
                }
                if (subSeq(strs[i], strs[j])) {
                    continue loop;
                }
            }
            max = Math.max(max, strs[i].length());
        }
        return max;
    }

    private boolean subSeq(String str1, String str2) {
        if (str1.length() > str2.length()) {
            return false;
        }
        int p1 = 0, p2 = 0;
        while (p1 < str1.length() && p2 < str2.length()) {
            if (str1.charAt(p1) == str2.charAt(p2)) {
                p1++;
            }
            p2++;
        }
        return p1 == str1.length();
    }
}
