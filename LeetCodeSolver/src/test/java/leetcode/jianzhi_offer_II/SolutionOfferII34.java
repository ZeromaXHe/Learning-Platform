package leetcode.jianzhi_offer_II;

import java.util.Comparator;
import java.util.HashMap;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer II 034. 外星语言是否排序 | 难度：简单 | 标签：数组、哈希表、字符串
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
 * 注意：本题与主站 953 题相同： https://leetcode-cn.com/problems/verifying-an-alien-dictionary/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/lwyVBB
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/21 16:42
 */
public class SolutionOfferII34 {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了 39.36% 的用户
     * 内存消耗：40.2 MB, 在所有 Java 提交中击败了 43.65% 的用户
     * 通过测试用例：119 / 119
     *
     * @param words
     * @param order
     * @return
     */
    public boolean isAlienSorted(String[] words, String order) {
        HashMap<Character, Integer> orderMap = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            orderMap.put(order.charAt(i), i);
        }
        for (int i = 0; i < words.length - 1; i++) {
            int idx = 0;
            int minLen = Math.min(words[i].length(), words[i + 1].length());
            while (idx < minLen) {
                int preOrd = orderMap.get(words[i].charAt(idx));
                int postOrd = orderMap.get(words[i + 1].charAt(idx));
                if (preOrd < postOrd) {
                    break;
                }
                if (preOrd > postOrd) {
                    return false;
                }
                idx++;
            }
            if (idx == minLen && words[i].length() > words[i + 1].length()) {
                return false;
            }
        }
        return true;
    }
}
