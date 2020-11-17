package com.zerox.util;

/**
 * @Author: ZeromaXHe
 * @Time: 2020/11/18 0:02
 * @Description: 二维数组工具类
 * @Modified By: ZeromaXHe
 */
public class Array2DUtils {
    public static String print2DArr(int[][] arr2D) {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < arr2D.length; i++) {
            if (i != 0) sb.append(',');
            sb.append('[');
            for (int j = 0; j < arr2D[0].length; j++) {
                if (j != 0) sb.append(',');
                sb.append(arr2D[i][j]);
            }
            sb.append(']');
        }
        sb.append(']');
        return sb.toString();
    }
}
