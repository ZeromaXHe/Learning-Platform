package leetcode.from101to150;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: zhuxi
 * @Time: 2022/1/21 16:23
 * @Description: 140. 单词拆分 II | 难度：困难 | 标签：字典树、记忆化搜索、哈希表、字符串、动态规划、回溯
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。
 * <p>
 * 说明：
 * 分隔时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * <p>
 * 示例 1：
 * 输入:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * 输出:
 * [
 *   "cats and dog",
 *   "cat sand dog"
 * ]
 * <p>
 * 示例 2：
 * 输入:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * 输出:
 * [
 *   "pine apple pen apple",
 *   "pineapple pen apple",
 *   "pine applepen apple"
 * ]
 * 解释: 注意你可以重复使用字典中的单词。
 * <p>
 * 示例 3：
 * 输入:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出:
 * []
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-break-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution140 {
    @Test
    public void wordBreakTest() {
        System.out.println(wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog")));
        System.out.println(wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
    }

    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 42.10% 的用户
     * 内存消耗： 36.6 MB , 在所有 Java 提交中击败了 70.76% 的用户
     * 通过测试用例： 26 / 26
     *
     * @param s
     * @param wordDict
     * @return
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        Set<String> wordSet = new HashSet<>(wordDict);
        Set<Integer> lenSet = new HashSet<>();
        int minLen = Integer.MAX_VALUE;
        int maxLen = Integer.MIN_VALUE;
        for (String word : wordDict) {
            int len = word.length();
            lenSet.add(len);
            if (len > maxLen) {
                maxLen = len;
            }
            if (len < minLen) {
                minLen = len;
            }
        }
        Map<Integer, List<Integer>> dp = new HashMap<>();
        dp.put(0, new LinkedList<>());

        for (int i = 1; i <= n; i++) {
            for (int j = Math.max(0, i - maxLen); j < i - minLen + 1; j++) {
                if (dp.get(j) != null && lenSet.contains(i - j) && wordSet.contains(s.substring(j, i))) {
                    dp.putIfAbsent(i, new LinkedList<>());
                    dp.get(i).add(j);
                }
            }
        }

        List<String> result = new LinkedList<>();
        if (dp.get(n) == null) {
            return result;
        }
        LinkedList<Integer> stack = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        dfs(result, stack, s, dp, n, sb);
        return result;
    }

    private void dfs(List<String> result, LinkedList<Integer> stack, String s, Map<Integer, List<Integer>> dp,
                     int index, StringBuilder sb) {
        if (index == 0) {
            sb.delete(0, sb.length());
            int from = 0;
            for (int to : stack) {
                sb.append(s, from, to).append(' ');
                from = to;
            }
            sb.deleteCharAt(sb.length() - 1);
            result.add(sb.toString());
            return;
        }
        List<Integer> list = dp.get(index);
        stack.addFirst(index);
        for (int i : list) {
            dfs(result, stack, s, dp, i, sb);
        }
        stack.removeFirst();
    }
}
