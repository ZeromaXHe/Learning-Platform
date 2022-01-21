package leetcode.from101to200;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: zhuxi
 * @Time: 2022/1/21 14:12
 * @Description: 139. 单词拆分 | 难度：中等 | 标签：字典树、记忆化搜索、哈希表、字符串、动态规划
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
 * <p>
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 * <p>
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
 * <p>
 * 示例 2：
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
 *      注意，你可以重复使用字典中的单词。
 * <p>
 * 示例 3：
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 * <p>
 * 提示：
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s 和 wordDict[i] 仅有小写英文字母组成
 * wordDict 中的所有字符串 互不相同
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-break
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution139 {
    @Test
    public void wordBreakTest() {
        Assert.assertTrue(wordBreak("leetcode", Arrays.asList("leet", "code")));
        Assert.assertTrue(wordBreak("applepenapple", Arrays.asList("apple", "pen")));
        Assert.assertFalse(wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
        // 35 / 45 个通过测试用例
        Assert.assertFalse(wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
                Arrays.asList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa")));
        // 通过测试用例： 37 / 45
        Assert.assertTrue(wordBreak("a", Arrays.asList("a")));
    }

    /**
     * 不引入lenSet、minLen、maxLen等逻辑进行剪枝时速度比较慢
     * <p>
     * 执行用时： 7 ms , 在所有 Java 提交中击败了 68.72% 的用户
     * 内存消耗： 39 MB , 在所有 Java 提交中击败了 6.75% 的用户
     * 通过测试用例： 45 / 45
     * <p>
     * 引入剪枝后效率提高
     * <p>
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 99.62% 的用户
     * 内存消耗： 36.3 MB , 在所有 Java 提交中击败了 97.10% 的用户
     * 通过测试用例： 45 / 45
     * <p>
     * 感觉substring的操作估计比较耗时，估计如果改善后效果会更好。看别人的0ms的好像就是没用substring
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        Set<String> wordSet = new HashSet<>(wordDict);
        Set<Integer> lenSet = new HashSet<>();
        int minLen = 20;
        int maxLen = 1;
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
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = Math.max(0, i - maxLen); j < i - minLen + 1; j++) {
                if (dp[j] && lenSet.contains(i - j) && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
