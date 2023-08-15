package leetcode.from801to850;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author zhuxi
 * @apiNote 833. 字符串中的查找与替换 | 难度：中等 | 标签：数组、字符串、排序
 * 你会得到一个字符串 s (索引从 0 开始)，你必须对它执行 k 个替换操作。替换操作以三个长度均为 k 的并行数组给出：indices, sources,  targets。
 * <p>
 * 要完成第 i 个替换操作:
 * <p>
 * 检查 子字符串  sources[i] 是否出现在 原字符串 s 的索引 indices[i] 处。
 * 如果没有出现， 什么也不做 。
 * 如果出现，则用 targets[i] 替换 该子字符串。
 * 例如，如果 s = "abcd" ， indices[i] = 0 , sources[i] = "ab"， targets[i] = "eee" ，那么替换的结果将是 "eeecd" 。
 * <p>
 * 所有替换操作必须 同时 发生，这意味着替换操作不应该影响彼此的索引。测试用例保证元素间不会重叠 。
 * <p>
 * 例如，一个 s = "abc" ，  indices = [0,1] ， sources = ["ab"，"bc"] 的测试用例将不会生成，因为 "ab" 和 "bc" 替换重叠。
 * 在对 s 执行所有替换操作后返回 结果字符串 。
 * <p>
 * 子字符串 是字符串中连续的字符序列。
 * <p>
 * 示例 1：
 * 输入：s = "abcd", indexes = [0,2], sources = ["a","cd"], targets = ["eee","ffff"]
 * 输出："eeebffff"
 * 解释：
 * "a" 从 s 中的索引 0 开始，所以它被替换为 "eee"。
 * "cd" 从 s 中的索引 2 开始，所以它被替换为 "ffff"。
 * <p>
 * 示例 2：
 * 输入：s = "abcd", indexes = [0,2], sources = ["ab","ec"], targets = ["eee","ffff"]
 * 输出："eeecd"
 * 解释：
 * "ab" 从 s 中的索引 0 开始，所以它被替换为 "eee"。
 * "ec" 没有从原始的 S 中的索引 2 开始，所以它没有被替换。
 * <p>
 * 提示：
 * 1 <= s.length <= 1000
 * k == indices.length == sources.length == targets.length
 * 1 <= k <= 100
 * 0 <= indexes[i] < s.length
 * 1 <= sources[i].length, targets[i].length <= 50
 * s 仅由小写英文字母组成
 * sources[i] 和 targets[i] 仅由小写英文字母组成
 * @implNote
 * @since 2023/8/15 9:47
 */
public class Solution833 {
    @Test
    public void findReplaceStringTest() {
        Assert.assertEquals("eeebffff",
                findReplaceString("abcd", new int[]{0, 2}, new String[]{"a", "cd"}, new String[]{"eee", "ffff"}));
        Assert.assertEquals("vbfrssozp",
                findReplaceString("vmokgggqzp", new int[]{3, 5, 1}, new String[]{"kg", "ggq", "mo"}, new String[]{"s", "so", "bfr"}));
        Assert.assertEquals("abcde",
                findReplaceString("abcde", new int[]{2, 2}, new String[]{"cdef", "bc"}, new String[]{"f", "fe"}));
    }

    /**
     * 时间 5 ms
     * 击败 7.77% 使用 Java 的用户
     * 内存 39.47 mb
     * 击败 33.98% 使用 Java 的用户
     *
     * @param s
     * @param indices
     * @param sources
     * @param targets
     * @return
     */
    public String findReplaceString_list(String s, int[] indices, String[] sources, String[] targets) {
        List<Integer> list = new ArrayList<>();
        int n = indices.length;
        loop:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < sources[i].length(); j++) {
                if (indices[i] + j >= s.length() || s.charAt(indices[i] + j) != sources[i].charAt(j)) {
                    continue loop;
                }
            }
            list.add(i);
        }
        list.sort(Comparator.comparingInt(i -> indices[i]));
        int si = 0;
        int li = 0;
        StringBuilder sb = new StringBuilder();
        while (si < s.length()) {
            if (li >= list.size() || si < indices[list.get(li)]) {
                sb.append(s.charAt(si));
                si++;
            } else {
                sb.append(targets[list.get(li)]);
                si += sources[list.get(li)].length();
                li++;
            }
        }
        return sb.toString();
    }

    /**
     * 时间 1 ms
     * 击败 95.15% 使用 Java 的用户
     * 内存 39.26 mb
     * 击败 72.82% 使用 Java 的用户
     *
     * @param s
     * @param indices
     * @param sources
     * @param targets
     * @return
     */
    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        int sLen = s.length();
        int[] map = new int[sLen];
        Arrays.fill(map, -1);
        int n = indices.length;
        loop:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < sources[i].length(); j++) {
                if (indices[i] + j >= sLen || s.charAt(indices[i] + j) != sources[i].charAt(j)) {
                    continue loop;
                }
            }
            map[indices[i]] = i;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sLen; i++) {
            if (map[i] != -1) {
                sb.append(targets[map[i]]);
                i += sources[map[i]].length() - 1;
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
