package leetcode.chengxuyuan_mianshi_jingdian;

/**
 * @Author: zhuxi
 * @Time: 2022/4/29 10:16
 * @Description: 面试题 01.03. URL化 | 难度：简单 | 标签：字符串
 * URL化。编写一种方法，将字符串中的空格全部替换为%20。假定该字符串尾部有足够的空间存放新增字符，并且知道字符串的“真实”长度。
 * （注：用Java实现的话，请使用字符数组实现，以便直接在数组上操作。）
 * <p>
 * 示例 1：
 * 输入："Mr John Smith    ", 13
 * 输出："Mr%20John%20Smith"
 * <p>
 * 示例 2：
 * 输入："               ", 5
 * 输出："%20%20%20%20%20"
 * <p>
 * 提示：
 * 字符串长度在 [0, 500000] 范围内。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/string-to-url-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution01_03 {
    /**
     * 执行用时： 9 ms , 在所有 Java 提交中击败了 83.75% 的用户
     * 内存消耗： 48.5 MB , 在所有 Java 提交中击败了 89.47% 的用户
     * 通过测试用例： 32 / 32
     * <p>
     * 使用 char[] 模拟简化的 StringBuilder 逻辑
     * 看结果最快的 7ms 的提交用例是直接用 S.toCharArray() 的数组直接 i-- 反向插入操作，这个估计是原题的意思。
     *
     * @param S
     * @param length
     * @return
     */
    public String replaceSpaces(String S, int length) {
        char[] sb = new char[S.length()];
        int newLen = 0;
        for (int i = 0; i < length; i++) {
            char c = S.charAt(i);
            if (c == ' ') {
                sb[newLen++] = '%';
                sb[newLen++] = '2';
                sb[newLen++] = '0';
            } else {
                sb[newLen++] = c;
            }
        }
        return String.valueOf(sb, 0, newLen);
    }

    /**
     * 执行用时： 14 ms , 在所有 Java 提交中击败了 54.21% 的用户
     * 内存消耗： 49.3 MB , 在所有 Java 提交中击败了 13.09% 的用户
     * 通过测试用例： 32 / 32
     * <p>
     * 如果 new StringBuilder 不指定 capacity 为 S.length() 的话：
     * 执行用时： 18 ms , 在所有 Java 提交中击败了 28.60% 的用户
     * 内存消耗： 49.5 MB , 在所有 Java 提交中击败了 6.30% 的用户
     * 通过测试用例： 32 / 32
     *
     * @param S
     * @param length
     * @return
     */
    public String replaceSpaces_StringBuilder(String S, int length) {
        StringBuilder sb = new StringBuilder(S.length());
        for (int i = 0; i < length; i++) {
            char c = S.charAt(i);
            if (c == ' ') {
                sb.append("%20");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
