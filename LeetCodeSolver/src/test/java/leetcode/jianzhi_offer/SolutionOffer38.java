package leetcode.jianzhi_offer;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer 38. 字符串的排列 | 难度：中等 | 标签：字符串、回溯
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * <p>
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 * <p>
 * 示例:
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 * <p>
 * 限制：
 * 1 <= s 的长度 <= 8
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/zi-fu-chuan-de-pai-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/10 11:58
 */
public class SolutionOffer38 {
    @Test
    public void testPermutation() {
        System.out.println(Arrays.toString(permutation("abc")));
    }

    /**
     * 执行用时：7 ms, 在所有 Java 提交中击败了 91.19% 的用户
     * 内存消耗：48.3 MB, 在所有 Java 提交中击败了 9.04% 的用户
     * 通过测试用例：52 / 52
     *
     * @param s
     * @return
     */
    public String[] permutation(String s) {
        LinkedList<String> result = new LinkedList<>();
        char[] chars = s.toCharArray();
        dfs(chars, 0, result);
        return result.toArray(new String[0]);
    }

    private void dfs(char[] chars, int i, LinkedList<String> result) {
        if (i == chars.length - 1) {
            result.add(new String(chars));
            return;
        }
        HashSet<Character> set = new HashSet<>();
        for (int j = i; j < chars.length; j++) {
            if (set.contains(chars[j])) {
                continue;
            }
            set.add(chars[j]);
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            dfs(chars, i + 1, result);
            chars[j] = chars[i];
            chars[i] = temp;
        }
    }
}
