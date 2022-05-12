package leetcode.chengxuyuan_mianshi_jingdian;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/5/12 18:31
 * @Description: 面试题 08.12. 八皇后 | 难度：困难 | 标签：数组、回溯
 * 设计一种算法，打印 N 皇后在 N × N 棋盘上的各种摆法，其中每个皇后都不同行、不同列，也不在对角线上。这里的“对角线”指的是所有的对角线，不只是平分整个棋盘的那两条对角线。
 * <p>
 * 注意：本题相对原题做了扩展
 * <p>
 * 示例:
 * 输入：4
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * 解释: 4 皇后问题存在如下两个不同的解法。
 * [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 * <p>
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/eight-queens-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution08_12 {
    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 99.93% 的用户
     * 内存消耗： 41.2 MB , 在所有 Java 提交中击败了 93.56% 的用户
     * 通过测试用例： 9 / 9
     *
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        boolean[][] chessBoard = new boolean[n][n];
        boolean[] col = new boolean[n];
        boolean[] slash = new boolean[2 * n + 1];
        boolean[] backslash = new boolean[2 * n + 1];
        List<List<String>> result = new LinkedList<>();
        backtrace(result, chessBoard, 0, col, slash, backslash);
        return result;
    }

    private void backtrace(List<List<String>> result, boolean[][] chessBoard, int row, boolean[] col, boolean[] slash, boolean[] backslash) {
        int n = chessBoard.length;
        if (row == n) {
            List<String> ans = new ArrayList<>(n);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.setLength(0);
                for (int j = 0; j < n; j++) {
                    sb.append(chessBoard[i][j] ? 'Q' : '.');
                }
                ans.add(sb.toString());
            }
            result.add(ans);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!col[i] && !slash[n - i + row] && !backslash[row + i]) {
                chessBoard[row][i] = true;
                col[i] = true;
                slash[n - i + row] = true;
                backslash[row + i] = true;
                backtrace(result, chessBoard, row + 1, col, slash, backslash);
                backslash[row + i] = false;
                slash[n - i + row] = false;
                col[i] = false;
                chessBoard[row][i] = false;
            }
        }
    }
}
