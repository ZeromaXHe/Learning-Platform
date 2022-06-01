package leetcode.from451to500;

import java.util.Arrays;

/**
 * @Author: zhuxi
 * @Time: 2022/6/1 14:07
 * @Description: 473. 火柴拼正方形 | 难度：中等 | 标签：位运算、数组、动态规划、回溯、状态压缩
 * 你将得到一个整数数组 matchsticks ，其中 matchsticks[i] 是第 i 个火柴棒的长度。你要用 所有的火柴棍 拼成一个正方形。
 * 你 不能折断 任何一根火柴棒，但你可以把它们连在一起，而且每根火柴棒必须 使用一次 。
 * <p>
 * 如果你能使这个正方形，则返回 true ，否则返回 false 。
 * <p>
 * 示例 1:
 * 输入: matchsticks = [1,1,2,2,2]
 * 输出: true
 * 解释: 能拼成一个边长为2的正方形，每边两根火柴。
 * <p>
 * 示例 2:
 * 输入: matchsticks = [3,3,3,3,4]
 * 输出: false
 * 解释: 不能用所有火柴拼成一个正方形。
 * <p>
 * 提示:
 * 1 <= matchsticks.length <= 15
 * 1 <= matchsticks[i] <= 108
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/matchsticks-to-square
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution473 {
    /**
     * 执行用时： 57 ms , 在所有 Java 提交中击败了 41.29% 的用户
     * 内存消耗： 38.9 MB , 在所有 Java 提交中击败了 91.72% 的用户
     * 通过测试用例： 185 / 185
     * <p>
     * 参考题解回溯做的，状态压缩 + 动态规划那个看不懂。
     *
     * @param matchsticks
     * @return
     */
    public boolean makesquare(int[] matchsticks) {
        int totalLen = 0;
        for (int matchstick : matchsticks) {
            totalLen += matchstick;
        }
        if (totalLen % 4 != 0) {
            return false;
        }
        Arrays.sort(matchsticks);
        int[] edges = new int[4];
        return dfs(matchsticks.length - 1, matchsticks, edges, totalLen / 4);
    }

    private boolean dfs(int index, int[] matchsticks, int[] edges, int len) {
        if (index == -1) {
            return true;
        }
        for (int i = 0; i < 4; i++) {
            edges[i] += matchsticks[index];
            if (edges[i] <= len && dfs(index - 1, matchsticks, edges, len)) {
                return true;
            }
            edges[i] -= matchsticks[index];
        }
        return false;
    }
}
