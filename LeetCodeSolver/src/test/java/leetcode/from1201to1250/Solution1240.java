package leetcode.from1201to1250;

/**
 * @author zhuxi
 * @apiNote 1240. 铺瓷砖 | 难度：困难 | 标签：动态规划、回溯
 * 你是一位施工队的工长，根据设计师的要求准备为一套设计风格独特的房子进行室内装修。
 * <p>
 * 房子的客厅大小为 n x m，为保持极简的风格，需要使用尽可能少的 正方形 瓷砖来铺盖地面。
 * <p>
 * 假设正方形瓷砖的规格不限，边长都是整数。
 * <p>
 * 请你帮设计师计算一下，最少需要用到多少块方形瓷砖？
 * <p>
 * 示例 1：
 * 输入：n = 2, m = 3
 * 输出：3
 * 解释：3 块地砖就可以铺满卧室。
 * 2 块 1x1 地砖
 * 1 块 2x2 地砖
 * <p>
 * 示例 2：
 * 输入：n = 5, m = 8
 * 输出：5
 * <p>
 * 示例 3：
 * 输入：n = 11, m = 13
 * 输出：6
 * <p>
 * 提示：
 * 1 <= n <= 13
 * 1 <= m <= 13
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/tiling-a-rectangle-with-the-fewest-squares
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/8 9:50
 */
public class Solution1240 {
    private static int[][] dp = new int[14][14];

    static {
        for (int i = 1; i < 14; i++) {
            for (int j = 1; j < 14; j++) {
                if (i == j) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = i * j;
                    for (int k = 2; k <= Math.min(i, j); k++) {
                        dp[i][j] = Math.min(dp[i][j], 1 + dp[i - k][j] + dp[k][j - k]);
                        dp[i][j] = Math.min(dp[i][j], 1 + dp[i - k][k] + dp[i][j - k]);
                    }
                }
            }
        }
    }

    public int tilingRectangle_wrongAnswer(int n, int m) {
        return dp[n][m];
    }

    private int ans;

    /**
     * 官方题解
     *
     * @param n
     * @param m
     * @return
     */
    public int tilingRectangle(int n, int m) {
        // 这个是参考资料里面的结论之一
        ans = Math.max(n, m);
        boolean[][] rect = new boolean[n][m];
        dfs(0, 0, rect, 0);
        return ans;
    }

    public void dfs(int x, int y, boolean[][] rect, int cnt) {
        int n = rect.length, m = rect[0].length;
        if (cnt >= ans) {
            return;
        }
        // 检查完所有行数，记录结果
        if (x >= n) {
            ans = cnt;
            return;
        }
        if (y >= m) {
            // 检测下一行
            dfs(x + 1, 0, rect, cnt);
            return;
        }
        // 如当前已经被覆盖，则直接尝试下一个位置
        if (rect[x][y]) {
            dfs(x, y + 1, rect, cnt);
            return;
        }

        for (int k = Math.min(n - x, m - y); k >= 1 && isAvailable(rect, x, y, k); k--) {
            // 将长度为 k 的正方形区域标记覆盖
            fillUp(rect, x, y, k, true);
            // 跳过 k 个位置开始检测
            dfs(x, y + k, rect, cnt + 1);
            // 回溯
            fillUp(rect, x, y, k, false);
        }
    }

    public boolean isAvailable(boolean[][] rect, int x, int y, int k) {
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                if (rect[x + i][y + j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void fillUp(boolean[][] rect, int x, int y, int k, boolean val) {
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                rect[x + i][y + j] = val;
            }
        }
    }
}
