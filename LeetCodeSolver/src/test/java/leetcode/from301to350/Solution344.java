package leetcode.from301to350;

/**
 * @author zhuxi
 * @apiNote 344. 反转字符串 | 难度：简单 | 标签：双指针、字符串
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
 * <p>
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * <p>
 * 示例 1：
 * 输入：s = ["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 * <p>
 * 示例 2：
 * 输入：s = ["H","a","n","n","a","h"]
 * 输出：["h","a","n","n","a","H"]
 * <p>
 * 提示：
 * 1 <= s.length <= 105
 * s[i] 都是 ASCII 码表中的可打印字符
 * @implNote
 * @since 2023/8/7 9:56
 */
public class Solution344 {
    /**
     * 时间 - ms
     * 击败 100.00% 使用 Java 的用户
     * <p>
     * 内存 48.25 mb
     * 击败 42.31% 使用 Java 的用户
     *
     * @param s
     */
    public void reverseString(char[] s) {
        for (int i = 0; i < s.length / 2; i++) {
            char c = s[i];
            s[i] = s[s.length - 1 - i];
            s[s.length - 1 - i] = c;
        }
    }
}
