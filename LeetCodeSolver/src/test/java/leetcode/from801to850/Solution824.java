package leetcode.from801to850;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

/**
 * @Author: zhuxi
 * @Time: 2022/4/21 10:34
 * @Description: 824. 山羊拉丁文 | 难度：简单 | 标签：字符串
 * 给你一个由若干单词组成的句子 sentence ，单词间由空格分隔。每个单词仅由大写和小写英文字母组成。
 * <p>
 * 请你将句子转换为 “山羊拉丁文（Goat Latin）”（一种类似于 猪拉丁文 - Pig Latin 的虚构语言）。山羊拉丁文的规则如下：
 * <p>
 * 如果单词以元音开头（'a', 'e', 'i', 'o', 'u'），在单词后添加"ma"。
 * 例如，单词 "apple" 变为 "applema" 。
 * 如果单词以辅音字母开头（即，非元音字母），移除第一个字符并将它放到末尾，之后再添加"ma"。
 * 例如，单词 "goat" 变为 "oatgma" 。
 * 根据单词在句子中的索引，在单词最后添加与索引相同数量的字母'a'，索引从 1 开始。
 * 例如，在第一个单词后添加 "a" ，在第二个单词后添加 "aa" ，以此类推。
 * 返回将 sentence 转换为山羊拉丁文后的句子。
 * <p>
 * 示例 1：
 * 输入：sentence = "I speak Goat Latin"
 * 输出："Imaa peaksmaaa oatGmaaaa atinLmaaaaa"
 * <p>
 * 示例 2：
 * 输入：sentence = "The quick brown fox jumped over the lazy dog"
 * 输出："heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"
 * <p>
 * 提示：
 * 1 <= sentence.length <= 150
 * sentence 由英文字母和空格组成
 * sentence 不含前导或尾随空格
 * sentence 中的所有单词由单个空格分隔
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/goat-latin
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution824 {
    @Test
    public void toGoatLatinTest() {
        Assert.assertEquals("Imaa peaksmaaa oatGmaaaa atinLmaaaaa", toGoatLatin("I speak Goat Latin"));
        Assert.assertEquals("heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa",
                toGoatLatin("The quick brown fox jumped over the lazy dog"));
    }

    HashSet<Character> vowels = new HashSet<>();

    {
        vowels.add('a');
        vowels.add('A');
        vowels.add('e');
        vowels.add('E');
        vowels.add('i');
        vowels.add('I');
        vowels.add('o');
        vowels.add('O');
        vowels.add('u');
        vowels.add('U');
    }

    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 80.74% 的用户
     * 内存消耗： 39.6 MB , 在所有 Java 提交中击败了 66.52% 的用户
     * 通过测试用例： 99 / 99
     *
     * @param sentence
     * @return
     */
    public String toGoatLatin(String sentence) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sbA = new StringBuilder();
        String[] split = sentence.split(" ");
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            if (sb.length() > 0) {
                sb.append(' ');
            }
            if (vowels.contains(s.charAt(0))) {
                sb.append(s);
            } else {
                sb.append(s.substring(1)).append(s.charAt(0));
            }
            sbA.append('a');
            sb.append("ma").append(sbA);
        }
        return sb.toString();
    }
}
