package leetcode.from1401to1450;

/**
 * @author zhuxi
 * @apiNote 1410. HTML 实体解析器 | 难度：中等 | 标签：哈希表、字符串
 * 「HTML 实体解析器」 是一种特殊的解析器，它将 HTML 代码作为输入，并用字符本身替换掉所有这些特殊的字符实体。
 * <p>
 * HTML 里这些特殊字符和它们对应的字符实体包括：
 * <p>
 * 双引号：字符实体为 &quot; ，对应的字符是 " 。
 * 单引号：字符实体为 &apos; ，对应的字符是 ' 。
 * 与符号：字符实体为 &amp; ，对应对的字符是 & 。
 * 大于号：字符实体为 &gt; ，对应的字符是 > 。
 * 小于号：字符实体为 &lt; ，对应的字符是 < 。
 * 斜线号：字符实体为 &frasl; ，对应的字符是 / 。
 * 给你输入字符串 text ，请你实现一个 HTML 实体解析器，返回解析器解析后的结果。
 * <p>
 * 示例 1：
 * 输入：text = "&amp; is an HTML entity but &ambassador; is not."
 * 输出："& is an HTML entity but &ambassador; is not."
 * 解释：解析器把字符实体 &amp; 用 & 替换
 * <p>
 * 示例 2：
 * 输入：text = "and I quote: &quot;...&quot;"
 * 输出："and I quote: \"...\""
 * <p>
 * 示例 3：
 * 输入：text = "Stay home! Practice on Leetcode :)"
 * 输出："Stay home! Practice on Leetcode :)"
 * <p>
 * 示例 4：
 * 输入：text = "x &gt; y &amp;&amp; x &lt; y is always false"
 * 输出："x > y && x < y is always false"
 * <p>
 * 示例 5：
 * 输入：text = "leetcode.com&frasl;problemset&frasl;all"
 * 输出："leetcode.com/problemset/all"
 * <p>
 * 提示：
 * 1 <= text.length <= 10^5
 * 字符串可能包含 256 个ASCII 字符中的任意字符。
 * @implNote
 * @since 2023/11/23 9:52
 */
public class Solution1410 {
    /**
     * 执行用时分布 12 ms
     * 击败 99.15% 使用 Java 的用户
     * 消耗内存分布 43.31 MB
     * 击败 8.47% 使用 Java 的用户
     *
     * @param text
     * @return
     */
    public String entityParser(String text) {
        StringBuilder sb = new StringBuilder();
        int n = text.length();
        for (int i = 0; i < n; i++) {
            char c = text.charAt(i);
            if (c == '&' && i <= n - 4) {
                if (text.charAt(i + 2) == 't' && text.charAt(i + 3) == ';') {
                    if (text.charAt(i + 1) == 'g') {
                        sb.append('>');
                        i += 3;
                        continue;
                    }
                    if (text.charAt(i + 1) == 'l') {
                        sb.append('<');
                        i += 3;
                        continue;
                    }
                } else if (i <= n - 5) {
                    if (text.charAt(i + 1) == 'a' && text.charAt(i + 2) == 'm'
                            && text.charAt(i + 3) == 'p' && text.charAt(i + 4) == ';') {
                        sb.append('&');
                        i += 4;
                        continue;
                    }
                    if (i <= n - 6) {
                        if (text.charAt(i + 5) == ';') {
                            if (text.charAt(i + 1) == 'q' && text.charAt(i + 2) == 'u'
                                    && text.charAt(i + 3) == 'o' && text.charAt(i + 4) == 't') {
                                sb.append('\"');
                                i += 5;
                                continue;
                            }
                            if (text.charAt(i + 1) == 'a' && text.charAt(i + 2) == 'p'
                                    && text.charAt(i + 3) == 'o' && text.charAt(i + 4) == 's') {
                                sb.append('\'');
                                i += 5;
                                continue;
                            }
                        }
                        if (i <= n - 7 && text.charAt(i + 1) == 'f' && text.charAt(i + 2) == 'r'
                                && text.charAt(i + 3) == 'a' && text.charAt(i + 4) == 's'
                                && text.charAt(i + 5) == 'l' && text.charAt(i + 6) == ';') {
                            sb.append('/');
                            i += 6;
                            continue;
                        }
                    }
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
