package leetcode.from551to600;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/2/10 18:15
 * @Description: 567.字符串的排列 | 难度：中等 | 标签：双指针、滑动窗口
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 * <p>
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 * <p>
 * 示例1:
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 * <p>
 * 示例2:
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 * <p>
 * 注意：
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutation-in-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: ZeromaXHe
 */
public class Solution567 {
    /**
     * 执行用时： 23 ms , 在所有 Java 提交中击败了 58.95% 的用户
     * 内存消耗： 38.3 MB , 在所有 Java 提交中击败了 90.68% 的用户
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        int[] count = new int[26];
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s1.toCharArray()) {
            map.merge(c, 1, Integer::sum);
        }
        int index = 0;
        while ((index = initialCount(count, s1, s2, index, s1.length(), map)) != -1) {
            while (s2.length() > index) {
                boolean find = true;
                for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                    if (entry.getValue() != count[entry.getKey() - 'a']) {
                        find = false;
                        break;
                    }
                }
                if (find) {
                    return true;
                }
                if (index == s2.length() - 1) {
                    return false;
                }
                if (!map.containsKey(s2.charAt(index + 1))) {
                    index += 2;
                    break;
                }
                index++;
                count[s2.charAt(index) - 'a']++;
                count[s2.charAt(index - s1.length()) - 'a']--;
            }
        }
        return false;
    }

    private int initialCount(int[] count, String s1, String s2, int start, int digit, HashMap<Character, Integer> map) {
        if (start + digit > s2.length()) {
            return -1;
        }
        int index = start - 1;
        Arrays.fill(count, 0);
        for (int i = 0; i < digit; i++) {
            index++;
            if (!map.containsKey(s2.charAt(index))) {
                if (s2.length() - index <= s1.length()) {
                    return -1;
                }
                i = -1;
                Arrays.fill(count, 0);
                continue;
            }
            count[s2.charAt(index) - 'a']++;
        }
        return index;
    }

    @Test
    public void checkInclusionTest() {
        Assert.assertTrue(checkInclusion("ab", "eidbaooo"));
        Assert.assertTrue(checkInclusion("a", "ab"));
        Assert.assertTrue(checkInclusion("adc", "dcda"));
        Assert.assertTrue(checkInclusion("nb", "tjgrhzhzwaovnvhvwtomgtqkqutufhkghqzudxrhsqoyklhixjhwaqxawcntspwbpykidlnvempggnmmltgzsnsfppguphrtvjorsbsrksmxuorijwlqilnzejbzgztjusnnpbsudcbnhydkumwbrlbwcwsujqgcobezsmgfatdasnwcainwjrpqxyqsitbzcfrwsndsvlcxvkujgjqhnoocxpwnbcpqbxyuuxqaqqoxjvyycufyjhbcgqegxljupwizchlwscczlszjepkopoalhpofmhagdanhclbtlcyxqhkoogymnoakznszzkxzohhmyixhlrdisohwzzhbclxedlgyfhrrbvmehaaksgovfecxxfnmhspneboziyokydatocaukehpiicthcjuapgeaohuxaftonsjxzdnjkjjhorbkplkhsmbjharpchcivxdcezxdqwzpedmmanqhrwsycxbhbeaqezixyxckeakmnoztrlpqdiairweimlfzwpefxejzlkxchutqzgtxjrovwlvrqzcksvrpmpsffdkuihacouiusoynzqwznisznxjsajuetramqfvrspazalpkqfromorffnupblqkfngbefndwbycmzbctswzjzvxqwcbcczrdnwctvhoccsnbjybmmtvhtsmjnryrqqefszeqbvhtlxuqumqdsnjlqbeqtmtbjtkilgyeuiizwnevxvuryanbancehacxilalknwqitkrkwazlpdkiszecqqewezvnhefkgjdlcpntqlitlpykefffoizpohzqmlgktmykhykwbzqkhwhobvlfbzhsngczdqexpnihlcujlkgnfuejhnrpxxhdbworsafpexnzfsdarldochahifyctawnatidaxrtfufwjxzxpsqwwguqktpwrrqzsqyoeeydjppknulaibguahgblamokqcivsbscjwvbxfatetequvfmxlrhdyqbzisvgxo"));
        Assert.assertFalse(checkInclusion("rokx", "otrxvfszxroxrzdsltg"));
    }
}
