package leetcode.from701to750;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zhuxi
 * @apiNote 722. 删除注释 | 难度：中等 | 标签：
 * 给一个 C++ 程序，删除程序中的注释。这个程序source是一个数组，其中source[i]表示第 i 行源码。 这表示每行源码由 '\n' 分隔。
 * <p>
 * 在 C++ 中有两种注释风格，行内注释和块注释。
 * <p>
 * 字符串 // 表示行注释，表示 // 和其右侧的其余字符应该被忽略。
 * 字符串 /* 表示一个块注释，它表示直到下一个（非重叠）出现的 * / 之间的所有字符都应该被忽略。（阅读顺序为从左到右）非重叠是指，字符串 /* / 并没有结束块注释，因为注释的结尾与开头相重叠。
 * 第一个有效注释优先于其他注释。
 * <p>
 * 如果字符串//出现在块注释中会被忽略。
 * 同样，如果字符串/*出现在行或块注释中也会被忽略。
 * 如果一行在删除注释之后变为空字符串，那么不要输出该行。即，答案列表中的每个字符串都是非空的。
 * <p>
 * 样例中没有控制字符，单引号或双引号字符。
 * <p>
 * 比如，source = "string s = "/* Not a comment. * /";"不会出现在测试样例里。
 * 此外，没有其他内容（如定义或宏）会干扰注释。
 * <p>
 * 我们保证每一个块注释最终都会被闭合， 所以在行或块注释之外的/*总是开始新的注释。
 * <p>
 * 最后，隐式换行符可以通过块注释删除。 有关详细信息，请参阅下面的示例。
 * <p>
 * 从源代码中删除注释后，需要以相同的格式返回源代码。
 * <p>
 * 示例 1:
 * 输入: source = ["/*Test program * /", "int main()", "{", "  // variable declaration ", "int a, b, c;", "/* This is a test", "   multiline  ", "   comment for ", "   testing * /", "a = b + c;", "}"]
 * 输出:["int main()","{ ","  ","int a, b, c;","a = b + c;","}"]
 * 解释:示例代码可以编排成这样:
 * /*Test program * /
 * int main()
 * {
 * // variable declaration
 * int a,b,c;
 * /* This is a test
 * multiline
 * comment for
 * testing * /
 * a=b+c;
 * }
 * 第 1行和第 6-9行的字符串 /* 表示块注释。第 4 行的字符串 // 表示行注释。
 * 编排后:
 * int main()
 * {
 * <p>
 * int a, b, c;
 * a = b + c;
 * }
 * 示例 2:
 * 输入: source = ["a/*comment", "line", "more_comment* /b"]
 * 输出:["ab"]
 * 解释:原始的 source 字符串是"a/*comment\nline\nmore_comment* /b",其中我们用粗体显示了换行符。删除注释后，隐含的换行符被删除，留下字符串"ab"用换行符分隔成数组时就是["ab"].
 * <p>
 * 提示:
 * <p>
 * 1<=source.length<=100
 * 0<=source[i].length<=80
 * source[i] 由可打印的 ASCII 字符组成。
 * 每个块注释都会被闭合。
 * 给定的源码中不会有单引号、双引号或其他控制字符。
 *  ​​​​​​
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/remove-comments
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/8/3 9:52
 */
public class Solution722 {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了 63.55% 的用户
     * 内存消耗：40.3 MB, 在所有 Java 提交中击败了 15.89% 的用户
     * 通过测试用例：54 / 54
     *
     * @param source
     * @return
     */
    public List<String> removeComments(String[] source) {
        List<String> result = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        boolean inBlock = false;
        for (String s : source) {
            if ("".equals(s)) {
                continue;
            }
            if (!inBlock) {
                sb.delete(0, sb.length());
            }
            Character pre = null;
            for (int i = 0; i < s.length(); i++) {
                if (inBlock) {
                    if (pre == null) {
                        pre = s.charAt(i);
                    } else if (pre == '*' && s.charAt(i) == '/') {
                        inBlock = false;
                        if (i + 1 < s.length()) {
                            i++;
                            pre = s.charAt(i);
                        } else {
                            pre = null;
                            break;
                        }
                    } else {
                        pre = s.charAt(i);
                    }
                } else {
                    if (pre == null) {
                        pre = s.charAt(i);
                    } else if (pre == '/') {
                        if (s.charAt(i) == '/') {
                            pre = null;
                            break;
                        }
                        if (s.charAt(i) == '*') {
                            inBlock = true;
                            if (i + 1 < s.length()) {
                                i++;
                                pre = s.charAt(i);
                            } else {
                                pre = null;
                                break;
                            }
                        } else {
                            sb.append(pre);
                            pre = s.charAt(i);
                        }
                    } else {
                        sb.append(pre);
                        pre = s.charAt(i);
                    }
                }
            }
            if (!inBlock) {
                if (pre != null) {
                    sb.append(pre);
                }
                if (!"".equals(sb.toString())) {
                    result.add(sb.toString());
                }
            }
        }
        return result;
    }
}
