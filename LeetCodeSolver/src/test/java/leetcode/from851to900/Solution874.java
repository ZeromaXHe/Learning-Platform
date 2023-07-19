package leetcode.from851to900;

import java.util.HashSet;

/**
 * @author zhuxi
 * @apiNote 874. 模拟行走机器人 | 难度：中等 | 标签：数组、模拟
 * 机器人在一个无限大小的 XY 网格平面上行走，从点 (0, 0) 处开始出发，面向北方。该机器人可以接收以下三种类型的命令 commands ：
 * <p>
 * -2 ：向左转 90 度
 * -1 ：向右转 90 度
 * 1 <= x <= 9 ：向前移动 x 个单位长度
 * 在网格上有一些格子被视为障碍物 obstacles 。第 i 个障碍物位于网格点  obstacles[i] = (xi, yi) 。
 * <p>
 * 机器人无法走到障碍物上，它将会停留在障碍物的前一个网格方块上，但仍然可以继续尝试进行该路线的其余部分。
 * <p>
 * 返回从原点到机器人所有经过的路径点（坐标为整数）的最大欧式距离的平方。（即，如果距离为 5 ，则返回 25 ）
 * <p>
 * 注意：
 * 北表示 +Y 方向。
 * 东表示 +X 方向。
 * 南表示 -Y 方向。
 * 西表示 -X 方向。
 * <p>
 * 示例 1：
 * 输入：commands = [4,-1,3], obstacles = []
 * 输出：25
 * 解释：
 * 机器人开始位于 (0, 0)：
 * 1. 向北移动 4 个单位，到达 (0, 4)
 * 2. 右转
 * 3. 向东移动 3 个单位，到达 (3, 4)
 * 距离原点最远的是 (3, 4) ，距离为 32 + 42 = 25
 * <p>
 * 示例 2：
 * 输入：commands = [4,-1,4,-2,4], obstacles = [[2,4]]
 * 输出：65
 * 解释：机器人开始位于 (0, 0)：
 * 1. 向北移动 4 个单位，到达 (0, 4)
 * 2. 右转
 * 3. 向东移动 1 个单位，然后被位于 (2, 4) 的障碍物阻挡，机器人停在 (1, 4)
 * 4. 左转
 * 5. 向北走 4 个单位，到达 (1, 8)
 * 距离原点最远的是 (1, 8) ，距离为 12 + 82 = 65
 * <p>
 * 提示：
 * 1 <= commands.length <= 104
 * commands[i] is one of the values in the list [-2,-1,1,2,3,4,5,6,7,8,9].
 * 0 <= obstacles.length <= 104
 * -3 * 104 <= xi, yi <= 3 * 104
 * 答案保证小于 231
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/walking-robot-simulation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/7/19 9:56
 */
public class Solution874 {
    private final int[][] MOVE = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    /**
     * 执行用时：12 ms, 在所有 Java 提交中击败了 86.38% 的用户
     * 内存消耗：47.3 MB, 在所有 Java 提交中击败了 77.02% 的用户
     * 通过测试用例：48 / 48
     *
     * @param commands
     * @param obstacles
     * @return
     */
    public int robotSim(int[] commands, int[][] obstacles) {
        HashSet<Integer> set = new HashSet<>();
        for (int[] obstacle : obstacles) {
            set.add(obstacle[0] * 60002 + obstacle[1]);
        }
        int result = 0;
        int x = 0;
        int y = 0;
        // 正北 +Y
        int dir = 0;
        for (int command : commands) {
            switch (command) {
                case -1:
                    dir = (dir + 1) % 4;
                    break;
                case -2:
                    dir = (dir + 3) % 4;
                    break;
                default:
                    while (command > 0 && !set.contains((x + MOVE[dir][0]) * 60002 + y + MOVE[dir][1])) {
                        x += MOVE[dir][0];
                        y += MOVE[dir][1];
                        command--;
                    }
                    result = Math.max(x * x + y * y, result);
            }
        }
        return result;
    }
}
