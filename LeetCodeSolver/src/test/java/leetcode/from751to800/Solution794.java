package leetcode.from751to800;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/12/9 11:15
 * @Description: 794. 有效的井字游戏 | 难度：中等 | 标签：数组、字符串
 * 给你一个字符串数组 board 表示井字游戏的棋盘。当且仅当在井字游戏过程中，棋盘有可能达到 board 所显示的状态时，才返回 true 。
 * <p>
 * 井字游戏的棋盘是一个 3 x 3 数组，由字符 ' '，'X' 和 'O' 组成。字符 ' ' 代表一个空位。
 * <p>
 * 以下是井字游戏的规则：
 * <p>
 * 玩家轮流将字符放入空位（' '）中。
 * 玩家 1 总是放字符 'X' ，而玩家 2 总是放字符 'O' 。
 * 'X' 和 'O' 只允许放置在空位中，不允许对已放有字符的位置进行填充。
 * 当有 3 个相同（且非空）的字符填充任何行、列或对角线时，游戏结束。
 * 当所有位置非空时，也算为游戏结束。
 * 如果游戏结束，玩家不允许再放置字符。
 * <p>
 * 示例 1：
 * 输入：board = ["O  ","   ","   "]
 * 输出：false
 * 解释：玩家 1 总是放字符 "X" 。
 * <p>
 * 示例 2：
 * 输入：board = ["XOX"," X ","   "]
 * 输出：false
 * 解释：玩家应该轮流放字符。
 * <p>
 * 示例 3：
 * 输入：board = ["XXX","   ","OOO"]
 * 输出：false
 * <p>
 * Example 4:
 * 输入：board = ["XOX","O O","XOX"]
 * 输出：true
 * <p>
 * 提示：
 * board.length == 3
 * board[i].length == 3
 * board[i][j] 为 'X'、'O' 或 ' '
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-tic-tac-toe-state
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution794 {
    @Test
    public void validTicTacToeTest() {
        Assert.assertFalse(validTicTacToe(new String[]{"O  ", "   ", "   "}));
        Assert.assertFalse(validTicTacToe(new String[]{"XOX", " X ", "   "}));
        Assert.assertFalse(validTicTacToe(new String[]{"XXX", "   ", "OOO"}));
        Assert.assertTrue(validTicTacToe(new String[]{"XOX", "O O", "XOX"}));
        Assert.assertFalse(validTicTacToe(new String[]{"XO ", "XO ", "XO "}));
        Assert.assertFalse(validTicTacToe(new String[]{"XXX", "XOO", "OO "}));
    }

    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 35.7 MB , 在所有 Java 提交中击败了 59.17% 的用户
     * 通过测试用例： 109 / 109
     *
     * @param board
     * @return
     */
    public boolean validTicTacToe(String[] board) {
        int countO = 0;
        int countX = 0;
        boolean winO = false;
        boolean winX = false;
        // 检查对角线
        if (board[0].charAt(0) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(2)) {
            if (board[0].charAt(0) == 'O') {
                winO = true;
            } else if (board[0].charAt(0) == 'X') {
                winX = true;
            }
        }
        if (board[2].charAt(0) == board[1].charAt(1) && board[1].charAt(1) == board[0].charAt(2)) {
            if (board[2].charAt(0) == 'O') {
                winO = true;
            } else if (board[2].charAt(0) == 'X') {
                winX = true;
            }
        }
        for (int i = 0; i < 3; i++) {
            // 检查行
            if ("OOO".equals(board[i])) {
                winO = true;
            } else if ("XXX".equals(board[i])) {
                winX = true;
            }
            // 检查列
            boolean allVerticalO = true;
            boolean allVerticalX = true;
            for (int j = 0; j < 3; j++) {
                // 计数
                char c = board[i].charAt(j);
                if (c == 'O') {
                    countO++;
                } else if (c == 'X') {
                    countX++;
                }

                // 列的具体字检查
                char vert = board[j].charAt(i);
                if (vert != 'O') {
                    allVerticalO = false;
                }
                if (vert != 'X') {
                    allVerticalX = false;
                }
            }
            if (allVerticalO) {
                winO = true;
            }
            if (allVerticalX) {
                winX = true;
            }
        }
        return (!winO && countX - countO == 1)
                || (!winX && countX == countO);
    }
}
