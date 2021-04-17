package com.zerox.gobang.utils;

import com.zerox.gobang.constant.GoBangEnum;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/4/18 3:39
 * @Description:
 * @Modified By: ZeromaXHe
 */
public class AiFromWebUtils {
    private final static int N = 15;

    public static int[] AI(int[][] p, GoBangEnum side) {
        if (p[8][8] == 0) {
            return new int[]{8, 8};
        }
        int i, j;
        int keyp = -100000, keyi = -1, keyj = -1, tempp;
        for (i = 1; i <= N; i++) {
            for (j = 1; j <= N; j++) {
                if (!BoardFromWebUtils.ok(i, j, p)) {
                    continue;
                }
                p[i][j] = side.getVal();
                tempp = ValueFromWebUtils.point(i, j, p);
                if (tempp == 0) {
                    p[i][j] = 0;
                    continue;
                }//高效剪枝，避开了禁手点和无效点
                if (tempp == 10000) {
                    return new int[]{i, j};
                }
                tempp = AI2(p, side);
                p[i][j] = 0;
                if (tempp > keyp) {
                    keyp = tempp;
                    keyi = i;
                    keyj = j;//第一层取极大
                }
            }
        }
        return new int[]{keyi, keyj};
    }

    private static int AI2(int[][] p, GoBangEnum side) {
        int keyp = 100000, tempp;
        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= N; j++) {
                if (!BoardFromWebUtils.ok(i, j, p)) {
                    continue;
                }
                p[i][j] = 3 - side.getVal();
                tempp = ValueFromWebUtils.point(i, j, p);
                if (tempp == 0) {
                    p[i][j] = GoBangEnum.EMPTY.getVal();
                    continue;
                }
                if (tempp == 10000) {
                    p[i][j] = 0;
                    return -10000;
                }
                tempp = AI3(tempp, p, side);
                p[i][j] = 0;
                if (tempp < keyp) {
                    keyp = tempp;//第二层取极小
                }
            }
        return keyp;
    }

    private static int AI3(int p2, int[][] p, GoBangEnum side) {
        int keyp = -100000, tempp;
        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= N; j++) {
                if (!BoardFromWebUtils.ok(i, j, p)) {
                    continue;
                }
                p[i][j] = side.getVal();
                tempp = ValueFromWebUtils.point(i, j, p);
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
}
