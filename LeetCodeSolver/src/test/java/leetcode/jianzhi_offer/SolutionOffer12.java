package leetcode.jianzhi_offer;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer 12. 矩阵中的路径 | 难度：中等 | 标签：数组、回溯、矩阵
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * 例如，在下面的 3×4 的矩阵中包含单词 "ABCCED"（单词中的字母已标出）。
 * <p>
 * 示例 1：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：board = [["a","b"],["c","d"]], word = "abcd"
 * 输出：false
 * <p>
 * 提示：
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board 和 word 仅由大小写英文字母组成
 * 注意：本题与主站 79 题相同：https://leetcode-cn.com/problems/word-search/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/ju-zhen-zhong-de-lu-jing-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/7 15:31
 */
public class SolutionOffer12 {
    @Test
    public void testExist() {
        Assert.assertTrue(exist(new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}}, "ABCCED"));
        Assert.assertTrue(exist(new char[][]{{'a', 'b'}, {'c', 'd'}}, "acdb"));
    }

    /**
     * 执行用时：81 ms, 在所有 Java 提交中击败了 58.11% 的用户
     * 内存消耗：39.3 MB, 在所有 Java 提交中击败了 87.50% 的用户
     * 通过测试用例：83 / 83
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visit = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, visit, word, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(char[][] board, boolean[][] visit, String word, int index, int x, int y) {
        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length || visit[x][y]) {
            return false;
        }
        if (board[x][y] == word.charAt(index)) {
            if (index == word.length() - 1) {
                return true;
            }
            visit[x][y] = true;
            if (dfs(board, visit, word, index + 1, x, y - 1)
                    || dfs(board, visit, word, index + 1, x, y + 1)
                    || dfs(board, visit, word, index + 1, x + 1, y)
                    || dfs(board, visit, word, index + 1, x - 1, y)) {
                return true;
            }
            visit[x][y] = false;
        }
        return false;
    }
}
