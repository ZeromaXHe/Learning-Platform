package leetcode.from101to150;

import java.util.Arrays;

/**
 * @Author: zhuxi
 * @Time: 2021/3/8 9:49
 * @Description: 132.分割回文串II | 难度：困难 | 标签：动态规划
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。
 * <p>
 * 返回符合要求的 最少分割次数 。
 * <p>
 * 示例 1：
 * 输入：s = "aab"
 * 输出：1
 * 解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
 * <p>
 * 示例 2：
 * 输入：s = "a"
 * 输出：0
 * <p>
 * 示例 3：
 * 输入：s = "ab"
 * 输出：1
 * <p>
 * 提示：
 * 1 <= s.length <= 2000
 * s 仅由小写英文字母组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-partitioning-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution132 {
    /**
     * 执行用时： 17 ms , 在所有 Java 提交中击败了 42.69% 的用户
     * 内存消耗： 38.4 MB , 在所有 Java 提交中击败了 45.39% 的用户
     *
     * @param s
     * @return
     */
    public int minCut(String s) {
        int n = s.length();
        // 判断从i到j是否是回文串的回文串二维数组
        boolean[][] g = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(g[i], true);
        }

        // 初始化回文串二维数组
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                g[i][j] = s.charAt(i) == s.charAt(j) && g[i + 1][j - 1];
            }
        }

        int[] f = new int[n];
        Arrays.fill(f, Integer.MAX_VALUE);
        for (int i = 0; i < n; ++i) {
            if (g[0][i]) {
                // 如果前i个字符直接组成回文串
                f[i] = 0;
            } else {
                for (int j = 0; j < i; ++j) {
                    // 对前i个字符进行不同分割点的拆分，取次数最小的
                    if (g[j + 1][i]) {
                        f[i] = Math.min(f[i], f[j] + 1);
                    }
                }
            }
        }

        return f[n - 1];
    }
}
