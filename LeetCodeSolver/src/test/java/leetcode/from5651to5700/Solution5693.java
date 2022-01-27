package leetcode.from5651to5700;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/3/20 22:30
 * @Description: 5693.字符串中第二大的数字 | 难度：简单 | 标签：字符串
 * 给你一个混合字符串 s ，请你返回 s 中 第二大 的数字，如果不存在第二大的数字，请你返回 -1 。
 * <p>
 * 混合字符串 由小写英文字母和数字组成。
 * <p>
 * 示例 1：
 * 输入：s = "dfa12321afd"
 * 输出：2
 * 解释：出现在 s 中的数字包括 [1, 2, 3] 。第二大的数字是 2 。
 * <p>
 * 示例 2：
 * 输入：s = "abc1111"
 * 输出：-1
 * 解释：出现在 s 中的数字只包含 [1] 。没有第二大的数字。
 * <p>
 * 提示：
 * 1 <= s.length <= 500
 * s 只包含小写英文字母和（或）数字。
 * @Modified By: ZeromaXHe
 */
public class Solution5693 {
    /**
     * 300 / 300 个通过测试用例
     * 状态：通过
     * 执行用时: 2 ms
     * 内存消耗: 38.2 MB
     *
     * @param s
     * @return
     */
    public int secondHighest(String s) {
        int max = -1;
        int max2 = -1;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                int i = c - '0';
                if (i > max) {
                    max2 = max;
                    max = i;
                } else if (i < max && i > max2) {
                    max2 = i;
                }
            }
        }
        return max2;
    }
}
