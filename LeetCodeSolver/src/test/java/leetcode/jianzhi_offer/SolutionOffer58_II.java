package leetcode.jianzhi_offer;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer 58 - II. 左旋转字符串 | 难度：简单 | 标签：数学、双指针、字符串
 * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
 * <p>
 * 示例 1：
 * 输入: s = "abcdefg", k = 2
 * 输出: "cdefgab"
 * <p>
 * 示例 2：
 * 输入: s = "lrloseumgh", k = 6
 * 输出: "umghlrlose"
 * <p>
 * 限制：
 * 1 <= k < s.length <= 10000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/12 10:21
 */
public class SolutionOffer58_II {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了 60.02% 的用户
     * 内存消耗：42.4 MB, 在所有 Java 提交中击败了 11.63% 的用户
     * 通过测试用例：34 / 34
     *
     * @param s
     * @param n
     * @return
     */
    public String reverseLeftWords(String s, int n) {
        char[] chars = s.toCharArray();
        reverse(chars, 0, chars.length);
        reverse(chars, 0, chars.length - n);
        reverse(chars, chars.length - n, chars.length);
        return new String(chars);
    }

    private void reverse(char[] chars, int from, int to) {
        int l = from;
        int r = to - 1;
        while (l < r) {
            char temp = chars[l];
            chars[l] = chars[r];
            chars[r] = temp;
            l++;
            r--;
        }
    }
}
