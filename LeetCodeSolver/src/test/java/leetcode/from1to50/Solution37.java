package leetcode.from1to50;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Author: zhuxiaohe
 * @Time: 2021/1/5 9:59
 * @Description: 37.解数独 | 难度：困难 | 标签：哈希表、回溯算法
 * 编写一个程序，通过填充空格来解决数独问题。
 * <p>
 * 一个数独的解法需遵循如下规则：
 * <p>
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。
 * <p>
 * 一个数独。
 * <p>
 * 答案被标成红色。
 * <p>
 * 提示：
 * 给定的数独序列只包含数字 1-9 和字符 '.' 。
 * 你可以假设给定的数独只有唯一解。
 * 给定数独永远是 9x9 形式的。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sudoku-solver
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution37 {
    private boolean[][][] sharpExistence = new boolean[3][3][9];
    private boolean[][] rowExistence = new boolean[9][9];
    private boolean[][] columnExistence = new boolean[9][9];
    // 用于记录所有的空白位
    // 回溯算法直接暴力尝试所有空白位的可能
    private List<int[]> spaces = new ArrayList<>();

    /**
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 85.55% 的用户
     * 内存消耗： 35.8 MB , 在所有 Java 提交中击败了 80.72% 的用户
     *
     * @param board
     */
    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1';
                    sharpExistence[i / 3][j / 3][num] = true;
                    rowExistence[i][num] = true;
                    columnExistence[j][num] = true;
                } else {
                    spaces.add(new int[]{i, j});
                }
            }
        }
        dfs(board, 0);
    }

    public boolean dfs(char[][] board, int pos) {
        if (pos == spaces.size()) {
            // 所有空白被填满，已找到解
            return true;
        }
        int[] space = spaces.get(pos);
        int i = space[0], j = space[1];
        for (int digit = 0; digit < 9; ++digit) {
            if (!rowExistence[i][digit] && !columnExistence[j][digit] && !sharpExistence[i / 3][j / 3][digit]) {
                // 如果digit未出现过，就进行尝试
                rowExistence[i][digit] = columnExistence[j][digit] = sharpExistence[i / 3][j / 3][digit] = true;
                board[i][j] = (char) (digit + '0' + 1);
                if (dfs(board, pos + 1)) {
                    return true;
                } else {
                    // 回退
                    rowExistence[i][digit] = columnExistence[j][digit] = sharpExistence[i / 3][j / 3][digit] = false;
                }
            }
        }
        return false;
    }
}

/**
 * 官方题解
 * 方法一：递归
 */
class Solution37_officialSolution1 {
    private boolean[][] line = new boolean[9][9];
    private boolean[][] column = new boolean[9][9];
    private boolean[][][] block = new boolean[3][3][9];
    // 控制对board的修改，true时不能再对board修改
    private boolean valid = false;
    // 用于记录所有的空白位
    // 回溯算法直接暴力尝试所有空白位的可能
    private List<int[]> spaces = new ArrayList<>();

    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] == '.') {
                    spaces.add(new int[]{i, j});
                } else {
                    int digit = board[i][j] - '0' - 1;
                    line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = true;
                }
            }
        }

        dfs(board, 0);
    }

    public void dfs(char[][] board, int pos) {
        if (pos == spaces.size()) {
            // 已找到解，不再对board进行修改
            valid = true;
            return;
        }

        int[] space = spaces.get(pos);
        int i = space[0], j = space[1];
        for (int digit = 0; digit < 9 && !valid; ++digit) {
            if (!line[i][digit] && !column[j][digit] && !block[i / 3][j / 3][digit]) {
                // 如果digit未出现过，就进行尝试
                line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = true;
                board[i][j] = (char) (digit + '0' + 1);
                dfs(board, pos + 1);
                // 回退
                // 如果找到解后，valid已经置为true，对于line\column\block的修改已经无关紧要。
                // 后续会直接停止递归，把当前board作为结果。
                line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = false;
            }
        }
    }
}

/**
 * 官方题解
 * 方法三：枚举优化
 * 位运算看得头疼……
 * <p>
 * 如果一个空白格只有唯一的数可以填入，也就是其对应的 b 值和 b-1 进行按位与运算后得到 0（即 b 中只有一个二进制位为 1）。
 * 此时，我们就可以确定这个空白格填入的数，而不用等到递归时再去处理它。
 * <p>
 * 这样一来，我们可以不断地对整个数独进行遍历，将可以唯一确定的空白格全部填入对应的数。
 * 随后我们再使用与方法二相同的方法对剩余无法唯一确定的空白格进行递归 + 回溯
 * <p>
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/sudoku-solver/solution/jie-shu-du-by-leetcode-solution/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class Solution37_officialSolution3 {
    private int[] line = new int[9];
    private int[] column = new int[9];
    private int[][] block = new int[3][3];
    private boolean valid = false;
    private List<int[]> spaces = new ArrayList<int[]>();

    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] != '.') {
                    int digit = board[i][j] - '0' - 1;
                    flip(i, j, digit);
                }
            }
        }

        while (true) {
            boolean modified = false;
            for (int i = 0; i < 9; ++i) {
                for (int j = 0; j < 9; ++j) {
                    if (board[i][j] == '.') {
                        // 对于第 i 行第 j 列的位置，line[i] ∣ column[j] ∣ block[⌊i/3⌋][⌊j/3⌋] 中第 k 位为 1，表示该位置不能填入数字 k+1（因为已经出现过），其中 | 表示按位或运算。
                        // 如果我们对这个值进行 ~ 按位取反运算，那么第 k 位为 1 就表示该位置可以填入数字 k+1，我们就可以通过寻找 1 来进行枚举。
                        // 由于在进行按位取反运算后，这个数的高位也全部变成了 1，而这是我们不应当枚举到的，因此我们需要将这个数和 (111111111)2=(1FF)16 进行按位与运算 &，将所有无关的位置为 0；
                        int mask = ~(line[i] | column[j] | block[i / 3][j / 3]) & 0x1ff;
                        // 我们可以用 b 和最低位的 1 进行按位异或运算，就可以将其从 b 中去除，这样就可以枚举下一个 1。
                        // 同样地，我们也可以用 b 和 b-1 进行按位与运算达到相同的效果，读者可以自行尝试推导。
                        if ((mask & (mask - 1)) == 0) {
                            // (mask & (mask - 1)) == 0，那就说明只有一个可能数字
                            int digit = Integer.bitCount(mask - 1);
                            flip(i, j, digit);
                            board[i][j] = (char) (digit + '0' + 1);
                            modified = true;
                        }
                    }
                }
            }
            if (!modified) {
                break;
            }
        }

        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] == '.') {
                    spaces.add(new int[]{i, j});
                }
            }
        }

        dfs(board, 0);
    }

    public void dfs(char[][] board, int pos) {
        if (pos == spaces.size()) {
            valid = true;
            return;
        }

        int[] space = spaces.get(pos);
        int i = space[0], j = space[1];
        int mask = ~(line[i] | column[j] | block[i / 3][j / 3]) & 0x1ff;
        for (; mask != 0 && !valid; mask &= (mask - 1)) {
            int digitMask = mask & (-mask);
            int digit = Integer.bitCount(digitMask - 1);
            // 将line、column、block中对应代表的位数翻为1
            flip(i, j, digit);
            board[i][j] = (char) (digit + '0' + 1);
            dfs(board, pos + 1);
            // 将line、column、block中对应代表的位数翻回0
            flip(i, j, digit);
        }
    }

    /**
     * 修改压缩为int的代表存在数字的值
     *
     * @param i     横坐标
     * @param j     纵坐标
     * @param digit 数字
     */
    public void flip(int i, int j, int digit) {
        line[i] ^= (1 << digit);
        column[j] ^= (1 << digit);
        block[i / 3][j / 3] ^= (1 << digit);
    }
}

/**
 * 想通过堆来挑选最少选择的格子进行回溯，但写来写去估计PriorityQueue的remove太耗时了，实现刷新heap的操作还复杂化了问题，实现起来很麻烦。
 * 还不如直接回溯算了。所以放弃了这个思路
 */
class Solution37_complexThinking {
    public void solveSudoku(char[][] board) {
        SudokuPossible[][] possibles = new SudokuPossible[9][9];
        PriorityQueue<SudokuPossible> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o.possibleSet.size()));
        SudokuExistence existence = new SudokuExistence();
        initPossibles(possibles, board, heap, existence);
        backTraceSudoku(possibles, board, heap, existence);
    }

    private boolean backTraceSudoku(SudokuPossible[][] possibles, char[][] board, PriorityQueue<SudokuPossible> heap, SudokuExistence existence) {
        while (!heap.isEmpty() && heap.peek().possibleSet.size() == 1) {
            SudokuPossible possible = heap.poll();
            dealOnlyOnePossibility(board, existence, possible);
            refreshHeap(possibles, heap, possible);
        }
        return false;
    }

    private void refreshHeap(SudokuPossible[][] possibles, PriorityQueue<SudokuPossible> heap, SudokuPossible possible) {
        int num = possible.possibleSet.iterator().next();
        for (int i = 0; i < 9; i++) {
            if (i != possible.x) {
                SudokuPossible possible1 = possibles[i][possible.y];
                if (possible1.possibleSet.remove(num)) {
                    // 刷新heap, 能够删除num的说明一定在heap中（删除前还有多个可能性）
                    heap.remove(possible1);
                    heap.offer(possible1);
                }
            }
            if (i != possible.y) {
                SudokuPossible possible2 = possibles[possible.x][i];
                if (possible2.possibleSet.remove(num)) {
                    // 刷新heap，能够删除num的说明一定在heap中（删除前还有多个可能性）
                    heap.remove(possible2);
                    heap.offer(possible2);
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // 刷新同一大格的heap
            }
        }
    }

    /**
     * 处理只有一种可能的格子
     *
     * @param board
     * @param existence
     * @param possible
     */
    private void dealOnlyOnePossibility(char[][] board, SudokuExistence existence, SudokuPossible possible) {
        int num = possible.possibleSet.iterator().next();
        int i = possible.x;
        int j = possible.y;
        existence.sharpExistence[i / 3][j / 3][num] = true;
        existence.rowExistence[i][num] = true;
        existence.columnExistence[j][num] = true;
        board[i][j] = (char) ('0' + num);
    }

    /**
     * 初始化SudokuPossible类矩阵
     *
     * @param possibles
     * @param board
     * @param heap
     * @param existence
     */
    private void initPossibles(SudokuPossible[][] possibles, char[][] board, PriorityQueue<SudokuPossible> heap, SudokuExistence existence) {
        // 初步建立矩阵
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                SudokuPossible possible = new SudokuPossible(i, j);
                if (board[i][j] == '.') {
                    for (int k = 1; k <= 9; k++) {
                        possible.possibleSet.add(k);
                    }
                } else {
                    int num = board[i][j] - '0';
                    possible.possibleSet.add(num);
                    existence.sharpExistence[i / 3][j / 3][num] = true;
                    existence.rowExistence[i][num] = true;
                    existence.columnExistence[j][num] = true;
                }
                possibles[i][j] = possible;
            }
        }
        // 对矩阵中未知的区块排除不可能的数字
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                SudokuPossible possible = possibles[i][j];
                if (possible.possibleSet.size() == 9) {
                    for (int k = 1; k <= 9; k++) {
                        if (existence.sharpExistence[i / 3][j / 3][k] || existence.rowExistence[i][k] || existence.columnExistence[j][k]) {
                            possible.possibleSet.remove(k);
                        }
                    }
                    if (possible.possibleSet.size() == 1) {
                        dealOnlyOnePossibility(board, existence, possible);
                    } else {
                        heap.offer(possible);
                    }
                }
            }
        }
    }

    private class SudokuPossible {
        HashSet<Integer> possibleSet = new HashSet<>(9);
        int x;
        int y;

        public SudokuPossible(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private class SudokuExistence {
        boolean[][][] sharpExistence = new boolean[3][3][9];
        boolean[][] rowExistence = new boolean[9][9];
        boolean[][] columnExistence = new boolean[9][9];
    }
}