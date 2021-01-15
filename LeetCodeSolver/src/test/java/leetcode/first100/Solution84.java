package leetcode.first100;

import java.util.Stack;

/**
 * @Author: zhuxi
 * @Time: 2021/1/15 13:50
 * @Description: 84.柱状图中最大的矩形 | 难度：困难 | 标签：栈、数组
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * <p>
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * <p>
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 * <p>
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 * <p>
 * 示例:
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-rectangle-in-histogram
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution84 {
    /**
     * 执行用时： 44 ms , 在所有 Java 提交中击败了 30.41% 的用户
     * 内存消耗： 48.7 MB , 在所有 Java 提交中击败了 5.01% 的用户
     * 有点慢，待优化
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        int p = 0;
        while (p < heights.length) {
            //栈空入栈
            if (stack.isEmpty()) {
                stack.push(p);
                p++;
            } else {
                int top = stack.peek();
                //当前高度大于栈顶，入栈
                if (heights[p] >= heights[top]) {
                    stack.push(p);
                    p++;
                } else {
                    //保存栈顶高度
                    int height = heights[stack.pop()];
                    //左边第一个小于当前柱子的下标
                    int leftLessMin = stack.isEmpty() ? -1 : stack.peek();
                    //右边第一个小于当前柱子的下标
                    int rightLessMin = p;
                    //计算面积
                    int area = (rightLessMin - leftLessMin - 1) * height;
                    maxArea = Math.max(area, maxArea);
                }
            }
        }
        while (!stack.isEmpty()) {
            //保存栈顶高度
            int height = heights[stack.pop()];
            //左边第一个小于当前柱子的下标
            int leftLessMin = stack.isEmpty() ? -1 : stack.peek();
            //右边没有小于当前高度的柱子，所以赋值为数组的长度便于计算
            int rightLessMin = heights.length;
            int area = (rightLessMin - leftLessMin - 1) * height;
            maxArea = Math.max(area, maxArea);
        }
        return maxArea;
    }
}
