package leetcode.chengxuyuan_mianshi_jingdian;

/**
 * @author zhuxi
 * @apiNote 面试题 16.04. 井字游戏 | 难度：中等 | 标签：数组、计数、矩阵
 * 设计一个算法，判断玩家是否赢了井字游戏。输入是一个 N x N 的数组棋盘，由字符" "，"X"和"O"组成，其中字符" "代表一个空位。
 * <p>
 * 以下是井字游戏的规则：
 * <p>
 * 玩家轮流将字符放入空位（" "）中。
 * 第一个玩家总是放字符"O"，且第二个玩家总是放字符"X"。
 * "X"和"O"只允许放置在空位中，不允许对已放有字符的位置进行填充。
 * 当有N个相同（且非空）的字符填充任何行、列或对角线时，游戏结束，对应该字符的玩家获胜。
 * 当所有位置非空时，也算为游戏结束。
 * 如果游戏结束，玩家不允许再放置字符。
 * 如果游戏存在获胜者，就返回该游戏的获胜者使用的字符（"X"或"O"）；如果游戏以平局结束，则返回 "Draw"；如果仍会有行动（游戏未结束），则返回 "Pending"。
 * <p>
 * 示例 1：
 * 输入： board = ["O X"," XO","X O"]
 * 输出： "X"
 * <p>
 * 示例 2：
 * 输入： board = ["OOX","XXO","OXO"]
 * 输出： "Draw"
 * 解释： 没有玩家获胜且不存在空位
 * <p>
 * 示例 3：
 * 输入： board = ["OOX","XXO","OX "]
 * 输出： "Pending"
 * 解释： 没有玩家获胜且仍存在空位
 * <p>
 * 提示：
 * 1 <= board.length == board[i].length <= 100
 * 输入一定遵循井字棋规则
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/tic-tac-toe-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2022/6/30 10:51
 */
public class Solution16_04 {
    /**
     * 执行用时：3 ms, 在所有 Java 提交中击败了 73.84% 的用户
     * 内存消耗：39.5 MB, 在所有 Java 提交中击败了 23.96% 的用户
     * 通过测试用例：70 / 70
     *
     * @param board
     * @return
     */
    public String tictactoe(String[] board) {
        int n = board.length;
        int[] col = new int[n];
        int[] diag = new int[2];
        boolean space = false;
        for (int i = 0; i < n; i++) {
            int row = 0;
            for (int j = 0; j < n; j++) {
                char c = board[i].charAt(j);
                if (c == ' ') {
                    space = true;
                } else {
                    int add = c == 'O' ? 1 : -1;
                    row += add;
                    col[j] += add;
                    if (i == j) {
                        diag[0] += add;
                    }
                    if (i == n - 1 - j) {
                        diag[1] += add;
                    }
                }
            }
            if (row == n) {
                return "O";
            } else if (row == -n) {
                return "X";
            }
        }
        for (int i = 0; i < n; i++) {
            if (col[i] == n) {
                return "O";
            } else if (col[i] == -n) {
                return "X";
            }
        }
        if (diag[0] == n || diag[1] == n) {
            return "O";
        } else if (diag[0] == -n || diag[1] == -n) {
            return "X";
        }
        return space ? "Pending" : "Draw";
    }

    /**
     * 执行用时：3 ms, 在所有 Java 提交中击败了 73.84% 的用户
     * 内存消耗：39.6 MB, 在所有 Java 提交中击败了 21.27% 的用户
     * 通过测试用例：70 / 70
     * <p>
     * 强行模拟的，写得自己都想吐。主要一开始以为就是 3 * 3，没想到是任意大小的…… 改吧改吧就变成这样了
     *
     * @param board
     * @return
     */
    public String tictactoe_ugly(String[] board) {
        int n = board.length;
        boolean anySpace = false;
        loop:
        for (int i = 0; i < n; i++) {
            char c = '#';
            for (int j = 0; j < n; j++) {
                if (c == '#') {
                    c = board[i].charAt(j);
                }
                if (board[i].charAt(j) == ' ') {
                    anySpace = true;
                    continue loop;
                } else if (c != board[i].charAt(j)) {
                    continue loop;
                }
            }
            return String.valueOf(board[i].charAt(0));
        }
        loop:
        for (int i = 0; i < n; i++) {
            boolean res = true;
            char c = '#';
            for (int j = 0; j < n; j++) {
                if (c == '#') {
                    c = board[j].charAt(i);
                }
                if (board[j].charAt(i) == ' ') {
                    anySpace = true;
                    continue loop;
                } else if (c != board[j].charAt(i)) {
                    res = false;
                    if (anySpace) {
                        continue loop;
                    }
                }
            }
            if (res) {
                return String.valueOf(board[0].charAt(i));
            }
        }
        char c = '#';
        for (int i = 0; i < n; i++) {
            if (c == '#') {
                c = board[i].charAt(i);
            }
            if (board[i].charAt(i) == ' ' || c != board[i].charAt(i)) {
                c = '*';
                break;
            }
        }
        if (c != '*') {
            return String.valueOf(c);
        }
        c = '#';
        for (int i = 0; i < n; i++) {
            if (c == '#') {
                c = board[i].charAt(n - 1 - i);
            }
            if (board[i].charAt(n - 1 - i) == ' ' || c != board[i].charAt(n - 1 - i)) {
                c = '*';
                break;
            }
        }
        if (c != '*') {
            return String.valueOf(c);
        }
        if (anySpace) {
            return "Pending";
        } else {
            return "Draw";
        }
    }
}
