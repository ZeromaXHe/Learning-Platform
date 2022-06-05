package leetcode.from451to500;

import java.util.Random;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/6/5 15:55
 * @Description: 478. 在圆内随机生成点 | 难度：中等 | 标签：几何、数学、拒绝采样、随机化
 * 给定圆的半径和圆心的位置，实现函数 randPoint ，在圆中产生均匀随机点。
 * <p>
 * 实现 Solution 类:
 * <p>
 * Solution(double radius, double x_center, double y_center) 用圆的半径 radius 和圆心的位置 (x_center, y_center) 初始化对象
 * randPoint() 返回圆内的一个随机点。圆周上的一点被认为在圆内。答案作为数组返回 [x, y] 。
 * <p>
 * 示例 1：
 * 输入:
 * ["Solution","randPoint","randPoint","randPoint"]
 * [[1.0, 0.0, 0.0], [], [], []]
 * 输出: [null, [-0.02493, -0.38077], [0.82314, 0.38945], [0.36572, 0.17248]]
 * 解释:
 * Solution solution = new Solution(1.0, 0.0, 0.0);
 * solution.randPoint ();//返回[-0.02493，-0.38077]
 * solution.randPoint ();//返回[0.82314,0.38945]
 * solution.randPoint ();//返回[0.36572,0.17248]
 * <p>
 * 提示：
 * 0 < radius <= 108
 * -107 <= x_center, y_center <= 107
 * randPoint 最多被调用 3 * 104 次
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/generate-random-point-in-a-circle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: ZeromaXHe
 */
public class Solution478 {
    /**
     * 执行用时： 219 ms , 在所有 Java 提交中击败了 35.94% 的用户
     * 内存消耗： 50.4 MB , 在所有 Java 提交中击败了 5.08% 的用户
     * 通过测试用例： 8 / 8
     */
    class Solution {
        private double radius;
        private double x_center;
        private double y_center;
        private Random random;

        public Solution(double radius, double x_center, double y_center) {
            this.radius = radius;
            this.x_center = x_center;
            this.y_center = y_center;
        }

        public double[] randPoint() {
            // Math.random() 无法取到上限 2，使用 2.0001 修正一下
            double x = 2.0001 * radius * Math.random() - radius;
            double y = 2.0001 * radius * Math.random() - radius;
            while (x * x + y * y > radius * radius) {
                x = 2.0001 * radius * Math.random() - radius;
                y = 2.0001 * radius * Math.random() - radius;
            }
            return new double[]{x_center + x, y_center + y};
        }
    }
}
