package leetcode.from1451to1500;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author zhuxi
 * @apiNote 1465. 切割后面积最大的蛋糕 | 难度：中等 | 标签：贪心、数组、排序
 * 矩形蛋糕的高度为 h 且宽度为 w，给你两个整数数组 horizontalCuts 和 verticalCuts，其中：
 * <p>
 * horizontalCuts[i] 是从矩形蛋糕顶部到第  i 个水平切口的距离
 * verticalCuts[j] 是从矩形蛋糕的左侧到第 j 个竖直切口的距离
 * 请你按数组 horizontalCuts 和 verticalCuts 中提供的水平和竖直位置切割后，请你找出 面积最大 的那份蛋糕，并返回其 面积 。由于答案可能是一个很大的数字，因此需要将结果 对 109 + 7 取余 后返回。
 * <p>
 * 示例 1：
 * 输入：h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3]
 * 输出：4
 * 解释：上图所示的矩阵蛋糕中，红色线表示水平和竖直方向上的切口。切割蛋糕后，绿色的那份蛋糕面积最大。
 * <p>
 * 示例 2：
 * 输入：h = 5, w = 4, horizontalCuts = [3,1], verticalCuts = [1]
 * 输出：6
 * 解释：上图所示的矩阵蛋糕中，红色线表示水平和竖直方向上的切口。切割蛋糕后，绿色和黄色的两份蛋糕面积最大。
 * <p>
 * 示例 3：
 * 输入：h = 5, w = 4, horizontalCuts = [3], verticalCuts = [3]
 * 输出：9
 * <p>
 * 提示：
 * 2 <= h, w <= 109
 * 1 <= horizontalCuts.length <= min(h - 1, 105)
 * 1 <= verticalCuts.length <= min(w - 1, 105)
 * 1 <= horizontalCuts[i] < h
 * 1 <= verticalCuts[i] < w
 * 题目数据保证 horizontalCuts 中的所有元素各不相同
 * 题目数据保证 verticalCuts 中的所有元素各不相同
 * @implNote
 * @since 2023/10/27 10:46
 */
public class Solution1465 {
    @Test
    public void maxAreaTest() {
        Assert.assertEquals(6, maxArea(5, 4, new int[]{3, 1}, new int[]{1}));
    }

    /**
     * 时间 15 ms
     * 击败 85.56% 使用 Java 的用户
     * 内存 51.28 MB
     * 击败 91.11% 使用 Java 的用户
     *
     * @param h
     * @param w
     * @param horizontalCuts
     * @param verticalCuts
     * @return
     */
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        int maxW = Math.max(horizontalCuts[0], h - horizontalCuts[horizontalCuts.length - 1]);
        int maxH = Math.max(verticalCuts[0], w - verticalCuts[verticalCuts.length - 1]);
        for (int i = 1; i < horizontalCuts.length; i++) {
            maxW = Math.max(maxW, horizontalCuts[i] - horizontalCuts[i - 1]);
        }
        for (int i = 1; i < verticalCuts.length; i++) {
            maxH = Math.max(maxH, verticalCuts[i] - verticalCuts[i - 1]);
        }
        return (int) ((long) maxW * maxH % 1000000007);
    }
}
