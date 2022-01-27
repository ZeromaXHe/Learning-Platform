package leetcode.from2001to2050;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2022/1/27 17:31
 * @Description: 2047. 句子中的有效单词数 | 难度：简单 | 标签：字符串
 * 句子仅由小写字母（'a' 到 'z'）、数字（'0' 到 '9'）、连字符（'-'）、标点符号（'!'、'.' 和 ','）以及空格（' '）组成。每个句子可以根据空格分解成 一个或者多个 token ，这些 token 之间由一个或者多个空格 ' ' 分隔。
 * <p>
 * 如果一个 token 同时满足下述条件，则认为这个 token 是一个有效单词：
 * <p>
 * 仅由小写字母、连字符和/或标点（不含数字）。
 * 至多一个 连字符 '-' 。如果存在，连字符两侧应当都存在小写字母（"a-b" 是一个有效单词，但 "-ab" 和 "ab-" 不是有效单词）。
 * 至多一个 标点符号。如果存在，标点符号应当位于 token 的 末尾 。
 * 这里给出几个有效单词的例子："a-b."、"afad"、"ba-c"、"a!" 和 "!" 。
 * <p>
 * 给你一个字符串 sentence ，请你找出并返回 sentence 中 有效单词的数目 。
 * <p>
 * 示例 1：
 * 输入：sentence = "cat and  dog"
 * 输出：3
 * 解释：句子中的有效单词是 "cat"、"and" 和 "dog"
 * <p>
 * 示例 2：
 * 输入：sentence = "!this  1-s b8d!"
 * 输出：0
 * 解释：句子中没有有效单词
 * "!this" 不是有效单词，因为它以一个标点开头
 * "1-s" 和 "b8d" 也不是有效单词，因为它们都包含数字
 * <p>
 * 示例 3：
 * 输入：sentence = "alice and  bob are playing stone-game10"
 * 输出：5
 * 解释：句子中的有效单词是 "alice"、"and"、"bob"、"are" 和 "playing"
 * "stone-game10" 不是有效单词，因为它含有数字
 * <p>
 * 示例 4：
 * 输入：sentence = "he bought 2 pencils, 3 erasers, and 1  pencil-sharpener."
 * 输出：6
 * 解释：句子中的有效单词是 "he"、"bought"、"pencils,"、"erasers,"、"and" 和 "pencil-sharpener."
 * <p>
 * 提示：
 * 1 <= sentence.length <= 1000
 * sentence 由小写英文字母、数字（0-9）、以及字符（' '、'-'、'!'、'.' 和 ','）组成
 * 句子中至少有 1 个 token
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-valid-words-in-a-sentence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution2047 {
    @Test
    public void countValidWordsTest() {
        Assert.assertEquals(3, countValidWords("cat and  dog"));
        Assert.assertEquals(0, countValidWords("!this  1-s b8d!"));
        Assert.assertEquals(5, countValidWords("alice and  bob are playing stone-game10"));
        Assert.assertEquals(6, countValidWords("he bought 2 pencils, 3 erasers, and 1  pencil-sharpener."));
        // 通过测试用例： 111 / 244
        Assert.assertEquals(1, countValidWords("!"));
        // 通过测试用例： 199 / 244
        Assert.assertEquals(0, countValidWords("a-b-c"));
        // 通过测试用例： 206 / 244
        Assert.assertEquals(65, countValidWords(",ee3-jxp 6i! r  -  tk, h yh3h w-i10cws gl0   h ckd9drxyh !mr jii jaoj . na,b5a2v.!s2 pan e253yo87mvacrm ysw 7-e  7n.a!fr e6nxcxb axs  !.  ,  v2bz,p.t9iw8wu!  4q36au.zl8 39na dn rvdpfys   1qj48pi c    l6v -fqfd 3c  3 ytn,xm   !53y2a m 8fqq- 3 2ral f jhj v o  4!8  .3 p ,ijhq b2la89v5 xzax!e bjw  nq qwu! eod qqwnf 2sc mw0  ko r fi7p 0e9jc4!7bw,ki    ojf uo 7-jf1swt70! wr  3ahsb xs! v cb.h   ybb is4cx71  4r qmy  qi7rn r i0apj og z  tp545by!ct9h 8pugwy   ipyn.tfi6o 3 4 2f. l 1ex2 l9 a  5nn  s4m!xb2if 3  4dj4  7  7 4dxe g pu3 -nd1qb x x-ucx-7,455 ,cy  egdz  xvutn  p! n e,u  qgs  k48-gq7t52n wasqim8u -k yp 26z ux sgpwn5cm6 5m dfbkej pr g x t1ww10 s -d dh   10  -      -kt gb   1et !8 f!3w 8fe7czp hsxy7u6o -wu4hcbijze 27m5 6l 3vx 7oq! 1z8 7-,.t--   oat   -g2!.  o !ri72ox w7 p ko wi4kfx7vpd fq4 zffk eqvu6, xox57 f75vo1m  ln9  fw 07 d4 .  s3e ofwam1fd!e8n  p yenyky5p   09  q  pqs q1v2fdwi a4vm"));
    }

    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 98.68% 的用户
     * 内存消耗： 40.3 MB , 在所有 Java 提交中击败了 5.03% 的用户
     * 通过测试用例： 244 / 244
     *
     * @param sentence
     * @return
     */
    public int countValidWords(String sentence) {
        int count = 0;
        boolean newWord = false;
        boolean alphabeted = false;
        boolean lastBar = false;
        boolean bar = false;
        boolean punctuation = false;
        boolean wasted = false;
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            if (c == ' ') {
                if (!wasted && newWord && !lastBar) {
                    count++;
                }
                newWord = false;
                alphabeted = false;
                lastBar = false;
                bar = false;
                punctuation = false;
                wasted = false;
                continue;
            } else if (wasted) {
                continue;
            }

            if (c == '-') {
                if (bar) {
                    wasted = true;
                } else if (alphabeted) {
                    lastBar = true;
                    bar = true;
                } else {
                    wasted = true;
                }
            } else if (Character.isAlphabetic(c)) {
                alphabeted = true;
                if (punctuation) {
                    wasted = true;
                } else if (lastBar) {
                    lastBar = false;
                } else {
                    newWord = true;
                }
            } else if (Character.isDigit(c)) {
                wasted = true;
                newWord = false;
            } else {
                // 标点符号
                if (punctuation) {
                    wasted = true;
                }
                punctuation = true;
                newWord = true;
            }
        }
        if (!wasted && newWord && !lastBar) {
            count++;
        }
        return count;
    }
}
