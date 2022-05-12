package leetcode.chengxuyuan_mianshi_jingdian;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/5/12 17:10
 * @Description: 面试题 08.08. 有重复字符串的排列组合 | 难度：中等 | 标签：字符串、回溯
 * 有重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合。
 * <p>
 * 示例1:
 * 输入：S = "qqe"
 * 输出：["eqq","qeq","qqe"]
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
 * 链接：https://leetcode.cn/problems/permutation-ii-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution08_08 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 41.5 MB , 在所有 Java 提交中击败了 51.27% 的用户
     * 通过测试用例： 29 / 29
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
        if (index == chars.length - 1) {
            result.add(new String(chars));
            return;
        }
        boolean[] visited = new boolean[56];
        backtrace(chars, index + 1, result);
        for (int i = index + 1; i < chars.length; i++) {
            if (chars[index] != chars[i] && !visited[chars[i] - 'A']) {
                // 只和第一个碰到的相同字母交换
                // 这样交换到第一个位置上后续递归会继续和后面第二个交换
                // 如果没有这个判断，本次递归中直接与第二个交换会和上面先交换到第一个位置再到第二个位置的重复
                visited[chars[i] - 'A'] = true;
                char temp = chars[index];
                chars[index] = chars[i];
                chars[i] = temp;
                backtrace(chars, index + 1, result);
                chars[i] = chars[index];
                chars[index] = temp;
            }
        }
    }

    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 98.52% 的用户
     * 内存消耗： 41.3 MB , 在所有 Java 提交中击败了 66.31% 的用户
     * 通过测试用例： 29 / 29
     * <p>
     * 直接用 set 的话，就少剪了枝，例如"abbbb"这种字符串，对于最后形成"bbbba"的计算就要重复很多次。
     *
     * @param S
     * @return
     */
    public String[] permutation_noPrune(String S) {
        char[] chars = S.toCharArray();
        HashSet<String> set = new HashSet<>();
        backtrace(chars, 0, set);
        return set.toArray(new String[0]);
    }

    private void backtrace(char[] chars, int index, HashSet<String> result) {
        if (index == chars.length) {
            result.add(new String(chars));
            return;
        }
        backtrace(chars, index + 1, result);
        for (int i = index + 1; i < chars.length; i++) {
            if (chars[index] == chars[i]) {
                continue;
            }
            char temp = chars[index];
            chars[index] = chars[i];
            chars[i] = temp;
            backtrace(chars, index + 1, result);
            chars[i] = chars[index];
            chars[index] = temp;
        }
    }
}
