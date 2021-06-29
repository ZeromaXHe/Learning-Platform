package com.zerox.gameFramework.utils;

/**
 * @Author: zhuxi
 * @Time: 2021/6/29 10:20
 * @Description: 参考
 * https://gist.github.com/indy/296676
 * https://zhuanlan.zhihu.com/p/206271895
 * @Modified By: zhuxi
 */
public final class PerlinNoiseUtils {
    static final int p[] = new int[512], permutation[] = {151, 160, 137, 91, 90, 15,
            131, 13, 201, 95, 96, 53, 194, 233, 7, 225, 140, 36, 103, 30, 69, 142, 8, 99, 37, 240, 21, 10, 23,
            190, 6, 148, 247, 120, 234, 75, 0, 26, 197, 62, 94, 252, 219, 203, 117, 35, 11, 32, 57, 177, 33,
            88, 237, 149, 56, 87, 174, 20, 125, 136, 171, 168, 68, 175, 74, 165, 71, 134, 139, 48, 27, 166,
            77, 146, 158, 231, 83, 111, 229, 122, 60, 211, 133, 230, 220, 105, 92, 41, 55, 46, 245, 40, 244,
            102, 143, 54, 65, 25, 63, 161, 1, 216, 80, 73, 209, 76, 132, 187, 208, 89, 18, 169, 200, 196,
            135, 130, 116, 188, 159, 86, 164, 100, 109, 198, 173, 186, 3, 64, 52, 217, 226, 250, 124, 123,
            5, 202, 38, 147, 118, 126, 255, 82, 85, 212, 207, 206, 59, 227, 47, 16, 58, 17, 182, 189, 28, 42,
            223, 183, 170, 213, 119, 248, 152, 2, 44, 154, 163, 70, 221, 153, 101, 155, 167, 43, 172, 9,
            129, 22, 39, 253, 19, 98, 108, 110, 79, 113, 224, 232, 178, 185, 112, 104, 218, 246, 97, 228,
            251, 34, 242, 193, 238, 210, 144, 12, 191, 179, 162, 241, 81, 51, 145, 235, 249, 14, 239, 107,
            49, 192, 214, 31, 181, 199, 106, 157, 184, 84, 204, 176, 115, 121, 50, 45, 127, 4, 150, 254,
            138, 236, 205, 93, 222, 114, 67, 29, 24, 72, 243, 141, 128, 195, 78, 66, 215, 61, 156, 180
    };

    static {
        for (int i = 0; i < 256; i++) p[256 + i] = p[i] = permutation[i];
    }

    public static double perlin3D(double x, double y, double z) {
        int X = (int) Math.floor(x) & 255,                  // FIND UNIT CUBE THAT
                Y = (int) Math.floor(y) & 255,                  // CONTAINS POINT.
                Z = (int) Math.floor(z) & 255;
        x -= Math.floor(x);                                // FIND RELATIVE X,Y,Z
        y -= Math.floor(y);                                // OF POINT IN CUBE.
        z -= Math.floor(z);
        double u = fade(x),                                // COMPUTE FADE CURVES
                v = fade(y),                                // FOR EACH OF X,Y,Z.
                w = fade(z);
        int A = p[X] + Y, AA = p[A] + Z, AB = p[A + 1] + Z,      // HASH COORDINATES OF
                B = p[X + 1] + Y, BA = p[B] + Z, BB = p[B + 1] + Z;      // THE 8 CUBE CORNERS,

        return lerp(w, lerp(v, lerp(u, grad(p[AA], x, y, z),  // AND ADD
                grad(p[BA], x - 1, y, z)), // BLENDED
                lerp(u, grad(p[AB], x, y - 1, z),  // RESULTS
                        grad(p[BB], x - 1, y - 1, z))),// FROM  8
                lerp(v, lerp(u, grad(p[AA + 1], x, y, z - 1),  // CORNERS
                        grad(p[BA + 1], x - 1, y, z - 1)), // OF CUBE
                        lerp(u, grad(p[AB + 1], x, y - 1, z - 1),
                                grad(p[BB + 1], x - 1, y - 1, z - 1))));
    }

    private static double fade(double t) {
        return t * t * t * (t * (t * 6 - 15) + 10);
    }

    private static double lerp(double t, double a, double b) {
        return a + t * (b - a);
    }

    private static double grad(int hash, double x, double y, double z) {
        int h = hash & 15;                      // CONVERT LO 4 BITS OF HASH CODE
        double u = h < 8 ? x : y,                 // INTO 12 GRADIENT DIRECTIONS.
                v = h < 4 ? y : h == 12 || h == 14 ? x : z;
        return ((h & 1) == 0 ? u : -u) + ((h & 2) == 0 ? v : -v);
    }

    public static double perlin1D(double x) {
        // 整数x1和x2的坐标
        int x1 = (int) Math.floor(x);
        int x2 = x1 + 1;

        // x1和x2的梯度值
        double grad1 = permutation[x1 % 255] * 2.0 - 255.0;
        double grad2 = permutation[x2 % 255] * 2.0 - 255.0;
        // x1和x2指向x的方向向量
        double vec1 = x - x1;
        double vec2 = x - x2;

        // x到x1的距离即vec1，利用公式3计算平滑参数
        double t = 3 * Math.pow(vec1, 2) - 2 * Math.pow(vec1, 3);

        // 梯度值与方向向量的乘积
        double product1 = grad1 * vec1;
        double product2 = grad2 * vec2;

        // 插值
        return product1 + t * (product2 - product1);
    }

    public static double perlin2D(double x, double y) {
        int p0x = (int) Math.floor(x); // P0坐标
        int p0y = (int) Math.floor(y);
        int p1x = p0x;      // P1坐标
        int p1y = p0y + 1;
        int p2x = p0x + 1;  // P2坐标
        int p2y = p0y + 1;
        int p3x = p0x + 1;  // P3坐标
        int p3y = p0y;

        double g0x = grad(p0x, p0y)[0];  // P0点的梯度
        double g0y = grad(p0x, p0y)[1];
        double g1x = grad(p1x, p1y)[0];  // P1点的梯度
        double g1y = grad(p1x, p1y)[1];
        double g2x = grad(p2x, p2y)[0];  // P2点的梯度
        double g2y = grad(p2x, p2y)[1];
        double g3x = grad(p3x, p3y)[0];  // P3点的梯度
        double g3y = grad(p3x, p3y)[1];

        double v0x = x - p0x;  // P0点的方向向量
        double v0y = y - p0y;
        double v1x = x - p1x;  // P1点的方向向量
        double v1y = y - p1y;
        double v2x = x - p2x;  // P2点的方向向量
        double v2y = y - p2y;
        double v3x = x - p3x;  // P3点的方向向量
        double v3y = y - p3y;

        double product0 = g0x * v0x + g0y * v0y;  // P0点梯度向量和方向向量的点乘
        double product1 = g1x * v1x + g1y * v1y;  // P1点梯度向量和方向向量的点乘
        double product2 = g2x * v2x + g2y * v2y;  // P2点梯度向量和方向向量的点乘
        double product3 = g3x * v3x + g3y * v3y;  // P3点梯度向量和方向向量的点乘

        // P1和P2的插值
        double d0 = x - p0x;
        double t0 = 6.0 * Math.pow(d0, 5.0) - 15.0 * Math.pow(d0, 4.0) + 10.0 * Math.pow(d0, 3.0);
        double n0 = product1 * (1.0 - t0) + product2 * t0;

        // P0和P3的插值
        double n1 = product0 * (1.0 - t0) + product3 * t0;

        // P点的插值
        double d1 = y - p0y;
        double t1 = 6.0 * Math.pow(d1, 5.0) - 15.0 * Math.pow(d1, 4.0) + 10.0 * Math.pow(d1, 3.0);
        return n1 * (1.0 - t1) + n0 * t1;
    }

    private static double[] grad(double x, double y) {
        double[] vec = new double[2];
        vec[0] = x * 127.1 + y * 311.7;
        vec[1] = x * 269.5 + y * 183.3;

        double sin0 = Math.sin(vec[0]) * 43758.5453123;
        double sin1 = Math.sin(vec[1]) * 43758.5453123;
        vec[0] = (sin0 - Math.floor(sin0)) * 2.0 - 1.0;
        vec[1] = (sin1 - Math.floor(sin1)) * 2.0 - 1.0;

        // 归一化，尽量消除正方形的方向性偏差
        double len = Math.sqrt(vec[0] * vec[0] + vec[1] * vec[1]);
        vec[0] /= len;
        vec[1] /= len;

        return vec;
    }
}