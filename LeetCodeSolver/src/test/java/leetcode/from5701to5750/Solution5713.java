package leetcode.from5701to5750;

import java.util.HashSet;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/3/28 10:30
 * @Description: 5713. 字符串中不同整数的数目 | 难度：简单 | 标签：字符串
 * 给你一个字符串 word ，该字符串由数字和小写英文字母组成。
 * <p>
 * 请你用空格替换每个不是数字的字符。例如，"a123bc34d8ef34" 将会变成 " 123  34 8  34" 。注意，剩下的这些整数间至少要用一个空格隔开："123"、"34"、"8" 和 "34" 。
 * <p>
 * 返回对 word 完成替换后形成的 不同 整数的数目。
 * <p>
 * 如果两个整数的 不含前导零 的十进制表示不同，则认为这两个整数也不同。
 * <p>
 * 示例 1：
 * 输入：word = "a123bc34d8ef34"
 * 输出：3
 * 解释：不同的整数有 "123"、"34" 和 "8" 。注意，"34" 只计数一次。
 * <p>
 * 示例 2：
 * 输入：word = "leet1234code234"
 * 输出：2
 * <p>
 * 示例 3：
 * 输入：word = "a1b01c001"
 * 输出：1
 * 解释："1"、"01" 和 "001" 视为同一个整数的十进制表示，因为在比较十进制值时会忽略前导零的存在。
 * <p>
 * 提示：
 * 1 <= word.length <= 1000
 * word 由数字和小写英文字母组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-different-integers-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: ZeromaXHe
 */
public class Solution5713 {
    /**
     * 68 / 68 个通过测试用例
     * 状态：通过
     * 执行用时: 2 ms
     * 内存消耗: 36.9 MB
     *
     * @param word
     * @return
     */
    public int numDifferentIntegers(String word) {
        HashSet<Integer> set = new HashSet<>();
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isDigit(chars[i])) {
                int num = chars[i] - '0';
                while (i + 1 < chars.length && Character.isDigit(chars[i + 1])) {
                    i++;
                    num *= 10;
                    num += chars[i] - '0';
                }
                set.add(num);
            } else {
                while (i + 1 < chars.length && !Character.isDigit(chars[i + 1])) {
                    i++;
                }
            }
        }
        return set.size();
    }
}
