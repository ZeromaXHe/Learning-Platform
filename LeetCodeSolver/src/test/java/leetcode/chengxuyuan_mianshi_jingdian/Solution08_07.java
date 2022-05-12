package leetcode.chengxuyuan_mianshi_jingdian;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/5/12 16:56
 * @Description: 面试题 08.07. 无重复字符串的排列组合 | 难度：中等 | 标签：字符串、回溯
 * 无重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合，字符串每个字符均不相同。
 * <p>
 * 示例1:
 * 输入：S = "qwe"
 * 输出：["qwe", "qew", "wqe", "weq", "ewq", "eqw"]
 * <p>
 * 示例2:
 * 输入：S = "ab"
 * 输出：["ab", "ba"]
 * <p>
 * 提示:
 * 字符都是英文字母。
 * 字符串长度在[1, 9]之间。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/permutation-i-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution08_07 {
    /**
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 91.74% 的用户
     * 内存消耗： 49.3 MB , 在所有 Java 提交中击败了 15.06% 的用户
     * 通过测试用例： 30 / 30
     *
     * @param S
     * @return
     */
    public String[] permutation(String S) {
        char[] chars = S.toCharArray();
        List<String> list = new LinkedList<>();
        backtrace(chars, 0, list);
        return list.toArray(new String[0]);
    }

    private void backtrace(char[] chars, int index, List<String> result) {
        if (index == chars.length) {
            result.add(new String(chars));
            return;
        }
        backtrace(chars, index + 1, result);
        for (int i = index + 1; i < chars.length; i++) {
            char temp = chars[index];
            chars[index] = chars[i];
            chars[i] = temp;
            backtrace(chars, index + 1, result);
            chars[i] = chars[index];
            chars[index] = temp;
        }
    }
}
