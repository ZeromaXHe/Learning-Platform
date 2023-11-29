package leetcode.lcr;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

/**
 * @author zhuxi
 * @apiNote LCR 039. 柱状图中最大的矩形 | 难度：困难 | 标签：栈、数组、单调栈
 * 给定非负整数数组 heights ，数组中的数字用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * <p>
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * <p>
 * 示例 1:
 * 输入：heights = [2,1,5,6,2,3]
 * 输出：10
 * 解释：最大的矩形为图中红色区域，面积为 10
 * <p>
 * 示例 2：
 * 输入： heights = [2,4]
 * 输出： 4
 * <p>
 * 提示：
 * 1 <= heights.length <=105
 * 0 <= heights[i] <= 104
 * <p>
 * 注意：本题与主站 84 题相同： https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
 * @implNote
 * @since 2023/11/29 17:06
 */
public class Solution039 {
    @Test
    public void largestRectangleAreaTest() {
        Assert.assertEquals(6, largestRectangleArea(new int[]{4, 2, 0, 3, 2, 5}));
    }

    /**
     * 参考了题解思路，然后频繁提交错误（还提交到 84 题里面去了）4次，修改后才过。
     * <p>
     * 执行用时分布 20 ms
     * 击败 79.10% 使用 Java 的用户
     * 消耗内存分布 53.87 MB
     * 击败 64.41% 使用 Java 的用户
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        LinkedList<Integer> stack = new LinkedList<>();
        int largest = heights[0];
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                Integer pop = stack.pop();
                int width = i - (stack.isEmpty() ? 0 : stack.peek());
                largest = Math.max(heights[pop] * (width - 1), largest);
                int height = stack.isEmpty() ? heights[pop] : heights[stack.peek()];
                if (width * height > largest) {
                    largest = width * height;
                }
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            int width = heights.length - (stack.isEmpty() ? 0 : stack.peek());
            largest = Math.max(heights[pop] * (width - 1), largest);
            int height = stack.isEmpty() ? heights[pop] : heights[stack.peek()];
            if (width * height > largest) {
                largest = width * height;
            }
        }
        return largest;
    }
}
