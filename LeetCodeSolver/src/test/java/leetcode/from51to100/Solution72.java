package leetcode.from51to100;

/**
 * @Author: zhuxi
 * @Time: 2021/1/15 10:54
 * @Description: 72.编辑距离 | 难度：困难 | 标签：字符串、动态规划
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * <p>
 * 示例 1：
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * <p>
 * 示例 2：
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 *  
 * 提示：
 * 0 <= word1.length, word2.length <= 500
 * word1 和 word2 由小写英文字母组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/edit-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution72 {
    /**
     * 执行用时： 7 ms , 在所有 Java 提交中击败了 36.69% 的用户
     * 内存消耗： 38.5 MB , 在所有 Java 提交中击败了 49.56% 的用户
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int min = Integer.MAX_VALUE;
                    if (dp[i - 1][j - 1] < min) {
                        min = dp[i - 1][j - 1];
                    }
                    if (dp[i][j - 1] < min) {
                        min = dp[i][j - 1];
                    }
                    if (dp[i - 1][j] < min) {
                        min = dp[i - 1][j];
                    }
                    dp[i][j] = min + 1;
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
}
