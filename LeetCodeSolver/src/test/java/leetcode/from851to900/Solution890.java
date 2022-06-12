package leetcode.from851to900;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/6/12 19:11
 * @Description: 890. 查找和替换模式 | 难度：中等 | 标签：数组、哈希表、字符串
 * 你有一个单词列表 words 和一个模式  pattern，你想知道 words 中的哪些单词与模式匹配。
 * <p>
 * 如果存在字母的排列 p ，使得将模式中的每个字母 x 替换为 p(x) 之后，我们就得到了所需的单词，那么单词与模式是匹配的。
 * <p>
 * （回想一下，字母的排列是从字母到字母的双射：每个字母映射到另一个字母，没有两个字母映射到同一个字母。）
 * <p>
 * 返回 words 中与给定模式匹配的单词列表。
 * <p>
 * 你可以按任何顺序返回答案。
 * <p>
 * 示例：
 * 输入：words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
 * 输出：["mee","aqq"]
 * 解释：
 * "mee" 与模式匹配，因为存在排列 {a -> m, b -> e, ...}。
 * "ccc" 与模式不匹配，因为 {a -> c, b -> c, ...} 不是排列。
 * 因为 a 和 b 映射到同一个字母。
 * <p>
 * 提示：
 * 1 <= words.length <= 50
 * 1 <= pattern.length = words[i].length <= 20
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-and-replace-pattern
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: ZeromaXHe
 */
public class Solution890 {
    @Test
    public void findAndReplacePatternTest() {
        System.out.println(findAndReplacePattern(new String[]{"abc", "deq", "mee", "aqq", "dkd", "ccc"}, "abb"));
    }

    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 33.33% 的用户
     * 内存消耗： 41.2 MB, 在所有 Java 提交中击败了 68.59% 的用户
     * 通过测试用例： 47 / 47
     *
     * @param words
     * @param pattern
     * @return
     */
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        HashMap<Character, Character> map = new HashMap<>();
        HashSet<Character> set = new HashSet<>();
        List<String> result = new LinkedList<>();
        loop:
        for (String word : words) {
            map.clear();
            set.clear();
            for (int i = 0; i < word.length(); i++) {
                char key = word.charAt(i);
                if (map.containsKey(key)) {
                    if (map.get(key) != pattern.charAt(i)) {
                        continue loop;
                    }
                } else {
                    if (set.contains(pattern.charAt(i))) {
                        continue loop;
                    }
                    map.put(key, pattern.charAt(i));
                    set.add(pattern.charAt(i));
                }
            }
            result.add(word);
        }
        return result;
    }
}
