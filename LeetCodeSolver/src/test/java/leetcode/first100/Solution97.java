package leetcode.first100;

/**
 * @Author: zhuxi
 * @Time: 2021/1/15 15:08
 * @Description: 97.交错字符串 | 难度：困难 | 标签：字符串、动态规划
 * 给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
 * <p>
 * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
 * <p>
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 * 提示：a + b 意味着字符串 a 和 b 连接。
 * <p>
 * 示例 1：
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出：false
 * <p>
 * 示例 3：
 * 输入：s1 = "", s2 = "", s3 = ""
 * 输出：true
 * <p>
 * 提示：
 * 0 <= s1.length, s2.length <= 100
 * 0 <= s3.length <= 200
 * s1、s2、和 s3 都由小写英文字母组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/interleaving-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution97 {
    /**
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 65.42% 的用户
     * 内存消耗： 36.9 MB , 在所有 Java 提交中击败了 17.58% 的用户
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length(), t = s3.length();
        if (n + m != t) {
            return false;
        }
        // 滚动数组dp
        boolean[] f = new boolean[m + 1];
        f[0] = true;
        for (int i = 0; i <= n; ++i) {
            // 第i次循环时，f[j]对应的意思就是使用s1的前i个字母和s2的前j个字母能否交错成s3的前i+j位
            for (int j = 0; j <= m; ++j) {
                int p = i + j - 1;
                if (i > 0) {
                    // 之前的f[j]代表i-1次循环的时候，s1的前i-1个字母和s2的前j个字母能交错成s3的前i+j-1位
                    // 如果 s1.charAt(i - 1) == s3.charAt(p) 的话，那么说明s1的第i个字母直接加在最后一位就可以交错成s3的前i+j位
                    f[j] = f[j] && s1.charAt(i - 1) == s3.charAt(p);
                }
                if (j > 0) {
                    // i = 0的时候，这个会把所有f[j]置为f[j - 1] && s2.charAt(j - 1) == s3.charAt(p);(j > 0)
                    // 即 s2和s3的前缀最大相同的部分全部置为了true，后面都是false
                    //
                    // 其他的 i 的话，||的前面f[j]这里是对s1的第i个字母直接加在最后的情况的结果
                    // ||的后面是s2的第 j 位字母加在最后能否匹配s3前 i+j 位的结果
                    f[j] = f[j] || (f[j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                }
            }
        }
        return f[m];
    }
}
