package gobang;

import java.util.Scanner;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/4/17 22:39
 * @Description: 代码改自CSDN线上的C语言代码
 * 出处链接:https://blog.csdn.net/nameofcsdn/article/details/78640511?utm_medium=distribute.pc_relevant.none-task-blog-2~default~BlogCommendFromMachineLearnPai2~default-1.control
 * @Modified By: ZeromaXHe
 */
public class GoBangAiFromWebTest {
    //#define same_u_i same(row + dx[u] * i, col + dy[u] * i, p[row][col])//u方向i距离的点是否同色
    //#define OutOrNotEmpty  (!inboard(row + dx[u] * i, col + dy[u] * i) || p[row + dx[u] * i][col + dy[u] * i] != 0)//出了棋盘或者非空格点
    private final static int N = 15;

    private int[][] p = new int[N + 2][N + 2]; //0空1黑2白  1●2○ -1▲-2△
    private int s = 0, ais = 1, s0;//s是轮到谁下,s=1,2，s=1是ai下，s=2是玩家，s=s0是黑方下，否则是白方下
    private boolean is_end = false;
    private int[] dx = {1, 1, 0, -1, -1, -1, 0, 1}; //flat技术
    private int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};//（dx,dy）是8个方向向量
    private int[][] manu = new int[2][300];
    private int manukey = 0;//棋谱

    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new GoBangAiFromWebTest().main();
    }

    /**
     * 打印棋盘
     */
    int out(int i, int j) {
        if (p[i][j] == 1) {
            return printf("● ");
        } else if (p[i][j] == 2) {
            return printf("○ ");
        } else if (p[i][j] == -1) {
            return printf("▲ ");
        } else if (p[i][j] == -2) {
            return printf("△ ");
        }

        if (i == N) {
            if (j == 1) {
                return printf("┏ ");
            } else if (j == N) {
                return printf("┓ ");
            }
            return printf("┯ ");
        } else if (i == 1) {
            if (j == 1) {
                return printf("┗ ");
            } else if (j == N) {
                return printf("┛ ");
            }
            return printf("┷ ");
        } else {
            if (j == 1) {
                return printf("┠ ");
            } else if (j == N) {
                return printf("┨ ");
            }
            return printf("┼ ");
        }
    }

    private int printf(String s) {
        System.out.print(s);
        return s.length();
    }

    private void printf(String s, Object... args) {
        System.out.printf(s, args);
    }

    /**
     * 打印整个游戏界面
     */
    void DrawBoard() {
        /// 清屏
//        system("cls");
        int row = 0, col = 0, keyr = 0, keyc = 0;
        char alpha = 'A';
        printf("\n\n\n     ");
        for (col = 1; col <= N; col++) {
            printf("%c ", alpha++);
        }
        for (row = N; row >= 1; row--) {
            printf("\n   %2d", row);
            for (col = 1; col <= N; col++) {
                out(row, col);
                if (p[row][col] < 0) {
                    keyr = row;
                    keyc = col;
                }
            }
            printf("%d", row);
        }
        alpha = 'A';
        printf("\n     ");
        for (col = 1; col <= N; col++) {
            printf("%c ", alpha++);
        }
        printf("\n\n");
        if (s0 == ais) {
            printf("  AI执黑，玩家执白\n");
        } else {
            printf("  AI执白，玩家执黑\n");
        }
        alpha = 'A';
        if (keyr != 0) {
            printf("  最后落子位置：%c%d\n", alpha + keyc - 1, keyr);
        }
    }

    /**
     * 游戏开局初始化
     */
    void init() {
        /// 背景白色(f),前景黑色(0)
//        system("color f0");
        s = 0;
        while (s != 1 && s != 2) {
            printf("输入1或者2进行选择\n1，AI执黑先行\n2，玩家执黑先行\n");
            s = Integer.parseInt(scanner.nextLine());
            // scanf("%d", & s);
        }
        s0 = s;
        int i, j;
        for (i = 0; i <= N + 1; i++) {
            for (j = 0; j <= N + 1; j++) {
                p[i][j] = 0;//以空格包围棋盘
            }
        }
        DrawBoard();
        for (j = 0; j < 300; j++) {
            manu[0][j] = manu[1][j] = 0;
        }
    }

    /**
     * 判断(row,col)是否在棋盘内
     *
     * @param row
     * @param col
     * @return
     */
    boolean inboard(int row, int col) {
        if (row < 1 || row > N) {
            return false;
        }
        return col >= 1 && col <= N;
    }

    /**
     * 判断2个棋子是否同色
     *
     * @param row
     * @param col
     * @param key
     * @return
     */
    boolean same(int row, int col, int key) {
        if (!inboard(row, col)) {
            return false;
        }
        return (p[row][col] == key || p[row][col] + key == 0);
    }

    /**
     * 坐标（row,col），方向向量u，返回该方向有多少连续同色棋子
     *
     * @param row
     * @param col
     * @param u
     * @return
     */
    int num(int row, int col, int u) {
        int i = row + dx[u], j = col + dy[u], sum = 0, ref = p[row][col];
        if (ref == 0) {
            return 0;
        }
        while (same(i, j, ref)) {
            sum++;
            i += dx[u];
            j += dy[u];
        }
        return sum;
    }

    /**
     * 落子成活4的数量
     *
     * @param row
     * @param col
     * @return
     */
    int live4(int row, int col) {
        int sum = 0, i, u;
        //4个方向，判断每个方向是否落子就成活4
        for (u = 0; u < 4; u++) {
            int sumk = 1;
            for (i = 1; same(row + dx[u] * i, col + dy[u] * i, p[row][col]); i++) {
                sumk++;
            }
            if ((!inboard(row + dx[u] * i, col + dy[u] * i) || p[row + dx[u] * i][col + dy[u] * i] != 0)) {
                continue;
            }
            for (i = -1; same(row + dx[u] * i, col + dy[u] * i, p[row][col]); i--) {
                sumk++;
            }
            if ((!inboard(row + dx[u] * i, col + dy[u] * i) || p[row + dx[u] * i][col + dy[u] * i] != 0)) {
                continue;
            }
            if (sumk == 4) {
                sum++;
            }
        }
        return sum;
    }

    /**
     * 成5点的数量
     *
     * @param row
     * @param col
     * @return
     */
    int cheng5(int row, int col) {
        int sum = 0, i, u;
        //8个成五点的方向
        for (u = 0; u < 8; u++) {
            int sumk = 0;
            boolean flag = true;
            for (i = 1; same(row + dx[u] * i, col + dy[u] * i, p[row][col]) || flag; i++) {
                //该方向的第一个不同色的点，超出边界或者对方棋子或空格
                if (!same(row + dx[u] * i, col + dy[u] * i, p[row][col])) {
                    if (p[row + dx[u] * i][col + dy[u] * i] != 0) {
                        sumk -= 10;//该方向的第一个不同色的点是对方棋子,没有成五点
                    }
                    flag = false;
                }
                sumk++;
            }
            if (!inboard(row + dx[u] * --i, col + dy[u] * i)) {
                continue;//该方向的第一个不同色的点是超出边界,没有成五点
            }
            for (i = -1; same(row + dx[u] * i, col + dy[u] * i, p[row][col]); i--) {
                sumk++;
            }
            if (sumk == 4) {
                sum++;
            }
        }
        return sum;
    }

    /**
     * 冲4的数量
     *
     * @param row
     * @param col
     * @return
     */
    int chong4(int row, int col) {
        return cheng5(row, col) - live4(row, col) * 2;
    }

    /**
     * 落子成活3的数量
     *
     * @param row
     * @param col
     * @return
     */
    int live3(int row, int col) {
        int key = p[row][col], sum = 0, i, u, flag = 2;
        //三连的活三
        for (u = 0; u < 4; u++) {
            int sumk = 1;
            for (i = 1; same(row + dx[u] * i, col + dy[u] * i, p[row][col]); i++) {
                sumk++;
            }
            if ((!inboard(row + dx[u] * i, col + dy[u] * i) || p[row + dx[u] * i][col + dy[u] * i] != 0)) {
                continue;
            }
            i++;
            if ((!inboard(row + dx[u] * i, col + dy[u] * i) || p[row + dx[u] * i][col + dy[u] * i] != 0)) {
                flag--;
            }
            for (i = -1; same(row + dx[u] * i, col + dy[u] * i, p[row][col]); i--) {
                sumk++;
            }
            if ((!inboard(row + dx[u] * i, col + dy[u] * i) || p[row + dx[u] * i][col + dy[u] * i] != 0)) {
                continue;
            }
            i--;
            if ((!inboard(row + dx[u] * i, col + dy[u] * i) || p[row + dx[u] * i][col + dy[u] * i] != 0)) {
                flag--;
            }
            if (sumk == 3 && flag > 0) {
                sum++;
            }
        }
        //8个方向，每个方向最多1个非三连的活三
        for (u = 0; u < 8; u++) {
            int sumk = 0;
            boolean flagBool = true;
            //成活四点的方向
            for (i = 1; same(row + dx[u] * i, col + dy[u] * i, p[row][col]) || flagBool; i++) {
                if (!same(row + dx[u] * i, col + dy[u] * i, p[row][col])) {
                    if (flagBool && p[row + dx[u] * i][col + dy[u] * i] != 0) {
                        sumk -= 10;
                    }
                    flagBool = false;
                }
                sumk++;
            }
            if ((!inboard(row + dx[u] * i, col + dy[u] * i) || p[row + dx[u] * i][col + dy[u] * i] != 0)) {
                continue;
            }
            if (p[row + dx[u] * --i][col + dy[u] * i] == 0) {
                continue;
            }
            for (i = 1; same(row + dx[u] * i, col + dy[u] * i, p[row][col]); i++) {
                sumk++;
            }
            if ((!inboard(row + dx[u] * i, col + dy[u] * i) || p[row + dx[u] * i][col + dy[u] * i] != 0)) {
                continue;
            }
            if (sumk == 3) {
                sum++;
            }
        }
        return sum;
    }

    /**
     * 长连禁手
     *
     * @param row
     * @param col
     * @return
     */
    boolean overline(int row, int col) {
        for (int u = 0; u < 4; u++) {
            if (num(row, col, u) + num(row, col, u + 4) > 4) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断落子后是否成禁手
     *
     * @param row
     * @param col
     * @return
     */
    boolean ban(int row, int col) {
        if (same(row, col, 2)) {
            return false;//白方无禁手
        }
        return live3(row, col) > 1 || overline(row, col) || live4(row, col) + chong4(row, col) > 1;
    }

    /**
     * (row,col)处落子之后是否游戏结束
     *
     * @param row
     * @param col
     * @return
     */
    boolean end_(int row, int col) {
        for (int u = 0; u < 4; u++) {
            if (num(row, col, u) + num(row, col, u + 4) >= 4) {
                is_end = true;
            }
        }
        if (is_end) {
            return true;
        }
        is_end = ban(row, col);
        return is_end;
    }

    /**
     * 落下一子
     *
     * @param row
     * @param col
     */
    void go(int row, int col) {
        if (s == s0) {
            p[row][col] = -1; //标出最新下的棋
        } else {
            p[row][col] = -2;
        }
        for (int i = 0; i <= N; i++)
            //取消上一个最新棋的标识
            for (int j = 0; j <= N; j++) {
                if (i == row && j == col) {
                    continue;
                }
                if (p[i][j] < 0) {
                    p[i][j] *= -1;
                }
            }
        DrawBoard();
        if (ban(row, col)) {
            printf("禁手\n");
            if (s0 == 1) {
                printf("玩家胜");
            } else {
                printf("AI胜");
            }
            //Sleep(10000);
        }
        if (end_(row, col)) {
            if (s == ais) {
                printf("AI胜");
            } else {
                printf("玩家胜");
            }
            //Sleep(10000);
        }
        manu[0][manukey] = row;
        manu[1][manukey++] = col;
    }

    /**
     * 能否落子
     *
     * @param row
     * @param col
     * @return
     */
    boolean ok(int row, int col) {
        return inboard(row, col) && (p[row][col] == 0);
    }

    /**
     * 非负分值
     *
     * @param row
     * @param col
     * @return
     */
    int point(int row, int col) {
        if (ban(row, col)) {
            return 0;//禁手0分
        }
        if (end_(row, col)) {
            is_end = false;
            return 10000;
        }
        int ret = live4(row, col) * 1000 + (chong4(row, col) + live3(row, col)) * 100, u;
        for (u = 0; u < 8; u++) {
            if (p[row + dx[u]][col + dy[u]] != 0) {
                ret++;//无效点0分
            }
        }
        return ret;
    }

    int AI3(int p2) {
        int keyp = -100000, tempp;
        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= N; j++) {
                if (!ok(i, j)) {
                    continue;
                }
                p[i][j] = s0;
                tempp = point(i, j);
                if (tempp == 0) {
                    p[i][j] = 0;
                    continue;
                }
                if (tempp == 10000) {
                    p[i][j] = 0;
                    return 10000;
                }
                p[i][j] = 0;
                if (tempp - p2 * 2 > keyp) {
                    keyp = tempp - p2 * 2;//第三层取极大
                }
            }
        return keyp;
    }

    int AI2() {
        int keyp = 100000, tempp;
        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= N; j++) {
                if (!ok(i, j)) {
                    continue;
                }
                p[i][j] = 3 - s0;
                tempp = point(i, j);
                if (tempp == 0) {
                    p[i][j] = 0;
                    continue;
                }
                if (tempp == 10000) {
                    p[i][j] = 0;
                    return -10000;
                }
                tempp = AI3(tempp);
                p[i][j] = 0;
                if (tempp < keyp) {
                    keyp = tempp;//第二层取极小
                }
            }
        return keyp;
    }

    void AI() {
        DrawBoard();
        printf("  轮到AI下，请稍候： ");
        if (p[8][8] == 0) {
            go(8, 8);
            return;
        }
        int i, j;
        int keyp = -100000, keyi = -1, keyj = -1, tempp;
        for (i = 1; i <= N; i++) {
            for (j = 1; j <= N; j++) {
                if (!ok(i, j)) {
                    continue;
                }
                p[i][j] = s0;
                tempp = point(i, j);
                if (tempp == 0) {
                    p[i][j] = 0;
                    continue;
                }//高效剪枝，避开了禁手点和无效点
                if (tempp == 10000) {
                    go(i, j);
                    return;
                }
                tempp = AI2();
                p[i][j] = 0;
                if (tempp > keyp) {
                    keyp = tempp;
                    keyi = i;
                    keyj = j;//第一层取极大
                }
            }
        }
        go(keyi, keyj);
    }

    void out_manual() {
        char alpha = 'A';
        int i;
        printf("\n  黑方落子位置: ");
        for (i = 0; i < manukey; i += 2) {
            printf("  %c%d", alpha + manu[1][i] - 1, manu[0][i]);
        }
        printf("\n  白方落子位置: ");
        for (i = 1; i < manukey; i += 2) {
            printf("  %c%d", alpha + manu[1][i] - 1, manu[0][i]);
        }
        //Sleep(5000);
    }

    void player() {
        DrawBoard();
        printf("  轮到玩家下，请输入坐标(输入=0查看棋谱)： \n");
        char c = '\n';
        int row = 0, col = 0;
        while (c < '0') {
            String next = scanner.nextLine();
            c = next.charAt(0);
            row = Integer.parseInt(next.substring(1));
            //scanf("%c%d", & c, &row);
        }
        if (c == '=') {
            out_manual();
            player();
            return;
        }
        if (c < 'a') {
            col = c - 'A' + 1;
        } else {
            col = c - 'a' + 1;
        }
        if (!ok(row, col)) {
            printf("此处不能下");
            //Sleep(1000);
            player();
            return;
        }
        go(row, col);
    }

    void main() {
        init();
        while (!is_end) {
            if (s == ais) {
                AI();
            } else {
                player();
            }
            s = 3 - s;//换下棋方
        }
    }
}