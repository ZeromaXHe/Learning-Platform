package leetcode.from951to1000;

import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;
import java.util.Objects;

/**
 * @Author: zhuxi
 * @Time: 2022/5/17 11:08
 * @Description: 953. 验证外星语词典 | 难度：简单 | 标签：数组、哈希表、字符串
 * 某种外星语也使用英文小写字母，但可能顺序 order 不同。字母表的顺序（order）是一些小写字母的排列。
 * <p>
 * 给定一组用外星语书写的单词 words，以及其字母表的顺序 order，只有当给定的单词在这种外星语中按字典序排列时，返回 true；否则，返回 false。
 * <p>
 * 示例 1：
 * 输入：words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 * 输出：true
 * 解释：在该语言的字母表中，'h' 位于 'l' 之前，所以单词序列是按字典序排列的。
 * <p>
 * 示例 2：
 * 输入：words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
 * 输出：false
 * 解释：在该语言的字母表中，'d' 位于 'l' 之后，那么 words[0] > words[1]，因此单词序列不是按字典序排列的。
 * <p>
 * 示例 3：
 * 输入：words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
 * 输出：false
 * 解释：当前三个字符 "app" 匹配时，第二个字符串相对短一些，然后根据词典编纂规则 "apple" > "app"，因为 'l' > '∅'，其中 '∅' 是空白字符，定义为比任何其他字符都小（更多信息）。
 * <p>
 * 提示：
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 20
 * order.length == 26
 * 在 words[i] 和 order 中的所有字符都是英文小写字母。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/verifying-an-alien-dictionary
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution953 {
    @Test
    public void isAlienSortedTest() {
        Assert.assertTrue(isAlienSorted(new String[]{"hello", "leetcode"}, "hlabcdefgijkmnopqrstuvwxyz"));
        Assert.assertTrue(isAlienSorted(new String[]{"hello", "hellocode"}, "hlabcdefgijkmnopqrstuvwxyz"));
        Assert.assertFalse(isAlienSorted(new String[]{"word", "world", "row"}, "worldabcefghijkmnpqstuvxyz"));
        Assert.assertFalse(isAlienSorted(new String[]{"apple", "app"}, "abcdefghijklmnopqrstuvwxyz"));
    }

    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 33.57% 的用户
     * 内存消耗： 40.1 MB , 在所有 Java 提交中击败了 46.29% 的用户
     * 通过测试用例： 120 / 120
     *
     * @param words
     * @param order
     * @return
     */
    public boolean isAlienSorted(String[] words, String order) {
        int[] compare = new int[26];
        for (int i = 0; i < 26; i++) {
            compare[order.charAt(i) - 'a'] = i;
        }
        Comparator<String> comparator = (s1, s2) -> {
            for (int j = 0; j < Math.min(s1.length(), s2.length()); j++) {
                if (s1.charAt(j) != s2.charAt(j)) {
                    return compare[s1.charAt(j) - 'a'] - compare[s2.charAt(j) - 'a'];
                }
            }
            if (s1.length() == s2.length()) {
                return 0;
            } else {
                return s1.length() - s2.length();
            }
        };
        for (int i = 1; i < words.length; i++) {
            if (Objects.compare(words[i - 1], words[i], comparator) > 0) {
                return false;
            }
        }
        return true;
    }
}
