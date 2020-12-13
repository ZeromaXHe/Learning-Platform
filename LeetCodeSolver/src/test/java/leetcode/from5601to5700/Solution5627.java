package leetcode.from5601to5700;

import com.zerox.util.Array2DUtils;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: ZeromaXHe
 * @Time: 2020/12/13 10:38
 * @Description: 5627. 石子游戏 VII | 难度：中等
 * 石子游戏中，爱丽丝和鲍勃轮流进行自己的回合，爱丽丝先开始 。
 * <p>
 * 有 n 块石子排成一排。每个玩家的回合中，可以从行中 移除 最左边的石头或最右边的石头，并获得与该行中剩余石头值之 和 相等的得分。当没有石头可移除时，得分较高者获胜。
 * <p>
 * 鲍勃发现他总是输掉游戏（可怜的鲍勃，他总是输），所以他决定尽力 减小得分的差值 。爱丽丝的目标是最大限度地 扩大得分的差值 。
 * <p>
 * 给你一个整数数组 stones ，其中 stones[i] 表示 从左边开始 的第 i 个石头的值，如果爱丽丝和鲍勃都 发挥出最佳水平 ，请返回他们 得分的差值 。
 * <p>
 * 示例 1：
 * 输入：stones = [5,3,1,4,2]
 * 输出：6
 * 解释：
 * - 爱丽丝移除 2 ，得分 5 + 3 + 1 + 4 = 13 。游戏情况：爱丽丝 = 13 ，鲍勃 = 0 ，石子 = [5,3,1,4] 。
 * - 鲍勃移除 5 ，得分 3 + 1 + 4 = 8 。游戏情况：爱丽丝 = 13 ，鲍勃 = 8 ，石子 = [3,1,4] 。
 * - 爱丽丝移除 3 ，得分 1 + 4 = 5 。游戏情况：爱丽丝 = 18 ，鲍勃 = 8 ，石子 = [1,4] 。
 * - 鲍勃移除 1 ，得分 4 。游戏情况：爱丽丝 = 18 ，鲍勃 = 12 ，石子 = [4] 。
 * - 爱丽丝移除 4 ，得分 0 。游戏情况：爱丽丝 = 18 ，鲍勃 = 12 ，石子 = [] 。
 * 得分的差值 18 - 12 = 6 。
 * <p>
 * 示例 2：
 * 输入：stones = [7,90,5,1,100,10,10,2]
 * 输出：122
 * <p>
 * 提示：
 * n == stones.length
 * 2 <= n <= 1000
 * 1 <= stones[i] <= 1000
 * @Modified By: ZeromaXHe
 */
public class Solution5627 {
    /**
     * 68 / 68 个通过测试用例
     * 状态：通过
     * 执行用时: 57 ms
     * 内存消耗: 56 MB
     *
     * @param stones
     * @return
     */
    public int stoneGameVII(int[] stones) {
        int length = stones.length;
        int[][] totalMatrix = new int[length][length];
        int[][] dp = new int[length][length];
        for (int i = 0; i < length; i++) {
            totalMatrix[i][i] = stones[i];
            for (int j = i + 1; j < length; j++) {
                totalMatrix[i][j] = totalMatrix[i][j - 1] + stones[j];
            }
        }
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                dp[i][j] = Math.max(totalMatrix[i][j] - stones[i] - dp[i + 1][j], totalMatrix[i][j] - stones[j] - dp[i][j - 1]);
            }
        }
//        System.out.println(Array2DUtils.print2DArrWithChangeLine(totalMatrix));
//        System.out.println("================");
//        System.out.println(Array2DUtils.print2DArrWithChangeLine(dp));
        return dp[0][length - 1];
    }

    @Test
    public void stoneGameVIITest() {
        // 6
        System.out.println(stoneGameVII(new int[]{5, 3, 1, 4, 2}));
        // 122
        System.out.println(stoneGameVII(new int[]{7, 90, 5, 1, 100, 10, 10, 2}));
    }
}
