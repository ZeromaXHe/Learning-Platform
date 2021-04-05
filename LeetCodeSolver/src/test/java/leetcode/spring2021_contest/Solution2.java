package leetcode.spring2021_contest;

import org.junit.Test;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/4/5 15:10
 * @Description: 2.乐团站位 | 难度：简单 | 标签：
 * 某乐团的演出场地可视作 num * num 的二维矩阵 grid（左上角坐标为 [0,0])，每个位置站有一位成员。乐团共有 9 种乐器，乐器编号为 1~9，每位成员持有 1 个乐器。
 * <p>
 * 为保证声乐混合效果，成员站位规则为：自 grid 左上角开始顺时针螺旋形向内循环以 1，2，...，9 循环重复排列。例如当 num = 5 时，站位如图所示
 * image.png
 * 请返回位于场地坐标 [Xpos,Ypos] 的成员所持乐器编号。
 * <p>
 * 示例 1：
 * 输入：num = 3, Xpos = 0, Ypos = 2
 * 输出：3
 * 解释：
 * image.png
 * <p>
 * 示例 2：
 * 输入：num = 4, Xpos = 1, Ypos = 2
 * 输出：5
 * 解释：
 * image.png
 * <p>
 * 提示：
 * 1 <= num <= 10^9
 * 0 <= Xpos, Ypos < num
 * @Modified By: ZeromaXHe
 */
public class Solution2 {
    /**
     * 已完成
     *
     * @param num
     * @param xPos
     * @param yPos
     * @return
     */
    public int orchestraLayout(int num, int xPos, int yPos) {
        long sum = 0;
        int out = Math.min(Math.min(xPos, yPos), Math.min(num - 1 - xPos, num - 1 - yPos));
        sum += 4L * (num + num - 2 * (out - 1)) * out / 2 - 4 * out;
        if (xPos == out) {
            sum += yPos - out;
        } else if (num - 1 - yPos == out) {
            sum += num - 2L * out + xPos - out - 1;
        } else if (num - 1 - xPos == out) {
            sum += 2L * num - 4 * out - 1 + num - 1 - out - 1 - yPos;
        } else {
            sum += 2L * (num - 2 * out) + num - 2 * (out + 1) + num - 1 - out - 1 - xPos;
        }
        return (int) (sum % 9 + 1);
    }

    @Test
    public void orchestraLayoutTest() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(orchestraLayout(5, i, j) + ",");
            }
            System.out.println();
        }

        System.out.println("===============");

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(orchestraLayout(4, i, j) + ",");
            }
            System.out.println();
        }
    }
}
