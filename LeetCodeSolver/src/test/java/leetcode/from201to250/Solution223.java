package leetcode.from201to250;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/9/30 9:51
 * @Description: 223. 矩形面积 | 难度：中等 | 标签：几何、数学
 * 给你 二维 平面上两个 由直线构成的 矩形，请你计算并返回两个矩形覆盖的总面积。
 * <p>
 * 每个矩形由其 左下 顶点和 右上 顶点坐标表示：
 * <p>
 * 第一个矩形由其左下顶点 (ax1, ay1) 和右上顶点 (ax2, ay2) 定义。
 * 第二个矩形由其左下顶点 (bx1, by1) 和右上顶点 (bx2, by2) 定义。
 * <p>
 * 示例 1：
 * 输入：ax1 = -3, ay1 = 0, ax2 = 3, ay2 = 4, bx1 = 0, by1 = -1, bx2 = 9, by2 = 2
 * 输出：45
 * <p>
 * 示例 2：
 * 输入：ax1 = -2, ay1 = -2, ax2 = 2, ay2 = 2, bx1 = -2, by1 = -2, bx2 = 2, by2 = 2
 * 输出：16
 * <p>
 * 提示：
 * -104 <= ax1, ay1, ax2, ay2, bx1, by1, bx2, by2 <= 104
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rectangle-area
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution223 {
    @Test
    public void testComputeArea() {
        Assert.assertEquals(45, computeArea(-3, 0, 3, 4, 0, -1, 9, 2));
        Assert.assertEquals(16, computeArea(-2, -2, 2, 2, -2, -2, 2, 2));
        Assert.assertEquals(17, computeArea(-2, -2, 2, 2, 3, 3, 4, 4));
    }

    /**
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 57.47% 的用户
     * 内存消耗： 38 MB , 在所有 Java 提交中击败了 10.23% 的用户
     * 通过测试用例： 3080 / 3080
     *
     * @param ax1
     * @param ay1
     * @param ax2
     * @param ay2
     * @param bx1
     * @param by1
     * @param bx2
     * @param by2
     * @return
     */
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        // 计算x和y上两个区间交叉的长度
        int overX = Math.min(ax2, bx2) - Math.max(ax1, bx1);
        int overY = Math.min(ay2, by2) - Math.max(ay1, by1);
        return (ax2 - ax1) * (ay2 - ay1) + (bx2 - bx1) * (by2 - by1)
                - ((overX > 0 && overY > 0) ? (overX * overY) : 0);
    }
}
