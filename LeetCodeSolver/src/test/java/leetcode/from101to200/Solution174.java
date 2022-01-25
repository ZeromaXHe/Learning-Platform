package leetcode.from101to200;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2022/1/25 14:07
 * @Description: 174. 地下城游戏 | 难度：困难 | 标签：数组、动态规划、矩阵
 * 一些恶魔抓住了公主（P）并将她关在了地下城的右下角。地下城是由 M x N 个房间组成的二维网格。
 * 我们英勇的骑士（K）最初被安置在左上角的房间里，他必须穿过地下城并通过对抗恶魔来拯救公主。
 * <p>
 * 骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。
 * <p>
 * 有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；
 * 其他房间要么是空的（房间里的值为 0），要么包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。
 * <p>
 * 为了尽快到达公主，骑士决定每次只向右或向下移动一步。
 * <p>
 * 编写一个函数来计算确保骑士能够拯救到公主所需的最低初始健康点数。
 * <p>
 * 例如，考虑到如下布局的地下城，如果骑士遵循最佳路径 右 -> 右 -> 下 -> 下，则骑士的初始健康点数至少为 7。
 * <p>
 * -2 (K)	-3	3
 * -5	-10	1
 * 10	30	-5 (P)
 * <p>
 * 说明:
 * 骑士的健康点数没有上限。
 * 任何房间都可能对骑士的健康点数造成威胁，也可能增加骑士的健康点数，包括骑士进入的左上角房间以及公主被监禁的右下角房间。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/dungeon-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution174 {
    @Test
    public void calculateMinimumHPTest() {
        Assert.assertEquals(7, calculateMinimumHP(new int[][]{{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}}));
    }

    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 86.83% 的用户
     * 内存消耗： 41.5 MB , 在所有 Java 提交中击败了 5.10% 的用户
     * 通过测试用例： 45 / 45
     * <p>
     * 我一开始试了一下正向dp，后来又试了一下以每个坐标去计算正向dp和反向dp再加起来的思路，都不行。
     * 其实想到了要反向，但是没想到可以直接反向dp，看了题解才发现可以。
     *
     * @param dungeon
     * @return
     */
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[] dp = new int[n];
        // 计算从右下角出发到各个坐标的最小生命
        dp[n - 1] = Math.max(-dungeon[m - 1][n - 1], 0);
        for (int i = n - 2; i >= 0; i--) {
            dp[i] = Math.max(dp[i + 1] - dungeon[m - 1][i], 0);
        }
        for (int i = m - 2; i >= 0; i--) {
            dp[n - 1] = Math.max(dp[n - 1] - dungeon[i][n - 1], 0);
            for (int j = n - 2; j >= 0; j--) {
                dp[j] = Math.max(Math.min(dp[j], dp[j + 1]) - dungeon[i][j], 0);
            }
        }
        return dp[0] + 1;
    }
}
