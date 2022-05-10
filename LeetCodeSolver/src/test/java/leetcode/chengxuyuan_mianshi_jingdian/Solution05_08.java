package leetcode.chengxuyuan_mianshi_jingdian;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2022/5/10 16:08
 * @Description: 面试题 05.08. 绘制直线 | 难度：中等 | 标签：位运算、数组、数学
 * 已知一个由像素点组成的单色屏幕，每行均有 w 个像素点，所有像素点初始为 0，左上角位置为 (0,0)。
 * <p>
 * 现将每行的像素点按照「每 32 个像素点」为一组存放在一个 int 中，再依次存入长度为 length 的一维数组中。
 * <p>
 * 我们将在屏幕上绘制一条从点 (x1,y) 到点 (x2,y) 的直线（即像素点修改为 1），请返回绘制过后的数组。
 * <p>
 * 注意：
 * 用例保证屏幕宽度 w 可被 32 整除（即一个 int 不会分布在两行上）
 * <p>
 * 示例1:
 * 输入：length = 1, w = 32, x1 = 30, x2 = 31, y = 0
 * 输出：[3]
 * 解释：在第 0 行的第 30 位到第 31 位画一条直线，屏幕二进制形式表示为 [00000000000000000000000000000011]，因此返回 [3]
 * <p>
 * 示例2:
 * 输入：length = 3, w = 96, x1 = 0, x2 = 95, y = 0
 * 输出：[-1, -1, -1]
 * 解释：由于二进制 11111111111111111111111111111111 的 int 类型代表 -1，因此返回 [-1,-1,-1]
 * <p>
 * 提示：
 * 1 <= length <= 10^5
 * 1 <= w <= 3 * 10^5
 * 0 <= x1 <= x2 < w
 * 0 <= y <= 10
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/draw-line-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution05_08 {
    @Test
    public void drawLineTest() {
        Assert.assertArrayEquals(new int[]{3}, drawLine(1, 32, 30, 31, 0));
        Assert.assertArrayEquals(new int[]{-1, -1, -1}, drawLine(3, 96, 0, 95, 0));
        Assert.assertArrayEquals(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1073737728, 0, 0, 0, 0, 0, 0, 0, 0},
                drawLine(24, 96, 2, 19, 5));
    }

    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 41.8 MB , 在所有 Java 提交中击败了 53.36% 的用户
     * 通过测试用例： 30 / 30
     *
     * @param length
     * @param w
     * @param x1
     * @param x2
     * @param y
     * @return
     */
    public int[] drawLine(int length, int w, int x1, int x2, int y) {
        int[] result = new int[length];
        if (x1 / 32 == x2 / 32) {
            result[y * w / 32 + x1 / 32] |= (-1 >>> (x1 % 32)) & (-1 << (31 - (x2 % 32)));
            return result;
        }
        result[y * w / 32 + x1 / 32] |= (-1 >>> (x1 % 32));
        for (int i = y * w / 32 + x1 / 32 + 1; i < y * w / 32 + x2 / 32; i++) {
            result[i] = -1;
        }
        result[y * w / 32 + x2 / 32] |= (-1 << (31 - (x2 % 32)));
        return result;
    }

    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 33.57% 的用户
     * 内存消耗： 41.5 MB , 在所有 Java 提交中击败了 96.11% 的用户
     * 通过测试用例： 30 / 30
     *
     * @param length
     * @param w
     * @param x1
     * @param x2
     * @param y
     * @return
     */
    public int[] drawLine_slow(int length, int w, int x1, int x2, int y) {
        int[] result = new int[length];
        for (int i = x1; i <= x2; i++) {
            result[y * w / 32 + i / 32] |= (1 << (31 - (i % 32)));
        }
        return result;
    }
}
