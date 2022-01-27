package leetcode.from1to50;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/3 17:14
 * @Description: 6. Z 字形变换 | 难度：中等 | 标签：字符串
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * <p>
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 * <p>
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * <p>
 * 请你实现这个将字符串进行指定行数变换的函数：
 * <p>
 * string convert(string s, int numRows);
 * <p>
 * 示例 1:
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * <p>
 * 示例 2:
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * <p>
 * 解释:
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zigzag-conversion
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution6 {
    /**
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 98.64% 的用户
     * 内存消耗： 38.4 MB , 在所有 Java 提交中击败了 98.38% 的用户
     * <p>
     * 观察每行的规律周期（V字型重复），就比较容易想到实现了。
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if (numRows < 2) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        // 周期
        int T = (numRows - 1) * 2;
        for (int i = 0; i < s.length(); i += T) {
            sb.append(s.charAt(i));
        }
        for (int i = 1; i < numRows - 1; i++) {
            for (int j = i; j < s.length(); j += T) {
                sb.append(s.charAt(j));
                if (j - 2 * i + T < s.length()) {
                    sb.append(s.charAt(j - 2 * i + T));
                }
            }
        }
        for (int i = numRows - 1; i < s.length(); i += T) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}
