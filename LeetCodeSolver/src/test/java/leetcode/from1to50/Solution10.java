package leetcode.from1to50;

import java.util.LinkedList;

/**
 * @Author: zhuxi
 * @Time: 2020/11/10 17:23
 * @Description: 10. 正则表达式匹配 | 难度：困难 | 标签：字符串、动态规划、回溯算法
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * <p>
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *  
 * 示例 1：
 * 输入：s = "aa" p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串。
 * <p>
 * 示例 2:
 * 输入：s = "aa" p = "a*"
 * 输出：true
 * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * <p>
 * 示例 3：
 * 输入：s = "ab" p = ".*"
 * 输出：true
 * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * <p>
 * 示例 4：
 * 输入：s = "aab" p = "c*a*b"
 * 输出：true
 * 解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * <p>
 * 示例 5：
 * 输入：s = "mississippi" p = "mis*is*p*."
 * 输出：false
 *  
 * 提示：
 * <p>
 * 0 <= s.length <= 20
 * 0 <= p.length <= 30
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 保证每次出现字符 * 时，前面都匹配到有效的字符
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/regular-expression-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution10 {
    /**
     * 字符串p解析出来的匹配元素队列
     * 匹配元素： 我定义的，":"对应于".*",字母大写对应相应小写字母加"*"
     */
    LinkedList<Character> pList = new LinkedList<>();

    /**
     * 用来回溯的记录匹配元素的栈
     */
    LinkedList<Character> matchStack = new LinkedList<>();

    /**
     * 用来回溯的记录任意匹配究竟匹配了多少个的栈
     */
    LinkedList<Integer> numStack = new LinkedList<>();

    private static final int BACK_TRACK_FAIL = Integer.MIN_VALUE;
    private static final int BACK_TRACK_SUCCESS = Integer.MAX_VALUE;

    /**
     * 执行用时： 148 ms , 在所有 Java 提交中击败了 5.02% 的用户
     * 内存消耗： 38.9 MB , 在所有 Java 提交中击败了 16.82% 的用户
     * TODO: 待优化，太慢了~ 考虑使用动态规划解决
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        parseP(p);
        boolean result = backTrackMatch(s);
        pList.clear();
        matchStack.clear();
        numStack.clear();
        return result;
    }

    /**
     * 解析字符串p
     * 生成的队列中a-z以及.代表单个匹配，A-Z以及:代表任意个匹配
     *
     * @param p
     */
    private void parseP(String p) {
        char[] chars = p.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == '*') {
                i--;
                if (chars[i] == '.') {
                    // :代表匹配任意个任意字符
                    pList.addFirst(':');
                } else {
                    pList.addFirst(Character.toUpperCase(chars[i]));
                }
            } else {
                pList.addFirst(chars[i]);
            }
        }
    }

    /**
     * 回溯算法尝试匹配字符串
     *
     * @param s
     * @return 是否匹配
     */
    private boolean backTrackMatch(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            // 测试语句，之后删除
//            System.out.println("pList: " + pList + "; matchStack: " + matchStack + "; numStack: " + numStack + ";");
//            System.out.println("i: " + i + "; chars: " + Arrays.toString(chars) + ";");

            if (pList.isEmpty()) {
                int backTrackIndex = backTrack(i, chars);
                if (backTrackIndex == BACK_TRACK_FAIL || backTrackIndex == BACK_TRACK_SUCCESS) {
                    // 回溯匹配失败或成功均无法继续
                    return backTrackIndex == BACK_TRACK_SUCCESS;
                } else {
                    i = backTrackIndex;
                    continue;
                }
            }
            char c = pList.peekFirst();
            if (isSingleMatch(c)) {
                if (c == '.') {
                    // .直接匹配掉当前字符，将匹配元素弹出后压入回溯栈。
                    // 以防在这个匹配元素前面有可任意次匹配的匹配元素最后匹配失败，需要回溯信息
                    // 单一匹配的匹配元素不记录匹配次数（numStack）
                    matchStack.push(pList.removeFirst());
                    continue;
                } else {
                    if (chars[i] == c) {
                        matchStack.push(pList.removeFirst());
                    } else {
                        int backTrackIndex = backTrack(i, chars);
                        if (backTrackIndex == BACK_TRACK_FAIL || backTrackIndex == BACK_TRACK_SUCCESS) {
                            // 回溯匹配失败或成功均无法继续
                            return backTrackIndex == BACK_TRACK_SUCCESS;
                        } else {
                            i = backTrackIndex;
                            continue;
                        }
                    }
                }
            } else {
                // 只要是任意次匹配的匹配元素，通通从0次开始
                matchStack.push(pList.removeFirst());
                numStack.push(0);
                i--;
                continue;
            }
        }
        return (pList.isEmpty() || allNotSingle(pList));
    }

    private int backTrack(int index, char[] chars) {
        while (!matchStack.isEmpty()) {
            char c = matchStack.peek();
            boolean isSingle = isSingleMatch(c);

            //测试语句，之后删除
//            System.out.println("pList: " + pList + "; matchStack: " + matchStack + "; numStack: " + numStack + ";");
//            System.out.println("index: " + index + "; chars: " + Arrays.toString(chars) + "; c: " + c);

            if (!isSingle && (c == ':' || (chars[index] == Character.toLowerCase(c)))) {
                //测试语句，之后删除
//                System.out.println("backTrack继续匹配分支");

                // 如果是任意次匹配的匹配元素，且还可以继续匹配一个字符
                // 那就继续匹配一个字符，此次回溯到此
                numStack.push(numStack.pop() + 1);
                index++;
                if (index == chars.length) {
                    if (pList.isEmpty() || allNotSingle(pList)) {
                        return BACK_TRACK_SUCCESS;
                    } else {
//                        System.out.println("index: " + index + "; pList: " + pList + "; matchStack: " + matchStack + "; numStack: " + numStack + ";");

                        // 无法继续匹配，则回溯上一次操作
                        pList.addFirst(matchStack.pop());
                        index -= numStack.pop();

//                        System.out.println("index: " + index + "; pList: " + pList + "; matchStack: " + matchStack + "; numStack: " + numStack + ";");
                    }
                } else {
                    return index - 1;
                }
            } else {
                //测试语句，之后删除
//                System.out.println("backTrack无法匹配分支");

                // 无法继续匹配，则回溯上一次操作
                pList.addFirst(matchStack.pop());
                if (isSingle) {
                    index--;
                } else {
                    index -= numStack.pop();
                }
            }
        }
        return BACK_TRACK_FAIL;
    }

    /**
     * 判断pList是不是剩下的都是任意匹配的元素
     *
     * @param pList
     * @return
     */
    private boolean allNotSingle(LinkedList<Character> pList) {
        for (char c : pList) {
            if (isSingleMatch(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否为单一字符匹配的匹配元素
     *
     * @param c 匹配元素
     * @return true 单一字符匹配的匹配元素; false 在本题中就是匹配任意个的匹配元素
     */
    private boolean isSingleMatch(char c) {
        return Character.isLowerCase(c) || c == '.';
    }

    public static void main(String[] args) {
        // next 4 lines are all false
        System.out.println(Character.isUpperCase(':'));
        System.out.println(Character.isUpperCase('.'));
        System.out.println(Character.isLowerCase(':'));
        System.out.println(Character.isLowerCase('.'));

        System.out.println("====================================");
        Solution10 solution10 = new Solution10();
        // false
        boolean isMatch1 = solution10.isMatch("aa", "a");
        System.out.println("case1：" + isMatch1 + " - should be false");
        // true
        boolean isMatch2 = solution10.isMatch("aa", "a*");
        System.out.println("case2：" + isMatch2 + " - should be true");
        // true
        boolean isMatch3 = solution10.isMatch("ab", ".*");
        System.out.println("case3：" + isMatch3 + " - should be true");
        // true
        boolean isMatch4 = solution10.isMatch("aab", "c*a*b");
        System.out.println("case4：" + isMatch4 + " - should be true");
        // false
        boolean isMatch5 = solution10.isMatch("mississippi", "mis*is*p*");
        System.out.println("case5：" + isMatch5 + " - should be false");
        // true
        boolean isMatch6 = solution10.isMatch("mississippi", "mis*is*ip*i");
        System.out.println("case6：" + isMatch6 + " - should be true");
        // false
        boolean isMatch7 = solution10.isMatch("ab", ".*c");
        System.out.println("case7：" + isMatch7 + " - should be false");
        // true
        boolean isMatch8 = solution10.isMatch("a", "ab*");
        System.out.println("case8：" + isMatch8 + " - should be true");
        // false
        boolean isMatch9 = solution10.isMatch("acaabbaccbbacaabbbb", "a*.*b*.*a*aa*a*");
        System.out.println("case9：" + isMatch9 + " - should be false");

        // 断言默认不开启的
        assert isMatch1 : "isMatch(\"aa\",\"a\") is wrong";
        assert !isMatch2 : "isMatch(\"aa\",\"a*\") is wrong";
        assert !isMatch3 : "isMatch(\"ab\",\".*\") is wrong";
        assert !isMatch4 : "isMatch(\"aab\",\"c*a*b\") is wrong";
        assert isMatch5 : "isMatch(\"mississippi\",\"mis*is*p*\") is wrong";
    }
}
