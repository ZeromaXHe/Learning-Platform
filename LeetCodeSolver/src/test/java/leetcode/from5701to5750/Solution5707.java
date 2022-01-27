package leetcode.from5701to5750;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/4/3 22:54
 * @Description: 5707.得到新鲜甜甜圈的最多组数 | 难度：困难 | 标签：
 * 有一个甜甜圈商店，每批次都烤 batchSize 个甜甜圈。这个店铺有个规则，就是在烤一批新的甜甜圈时，之前 所有 甜甜圈都必须已经全部销售完毕。给你一个整数 batchSize 和一个整数数组 groups ，数组中的每个整数都代表一批前来购买甜甜圈的顾客，其中 groups[i] 表示这一批顾客的人数。每一位顾客都恰好只要一个甜甜圈。
 * <p>
 * 当有一批顾客来到商店时，他们所有人都必须在下一批顾客来之前购买完甜甜圈。如果一批顾客中第一位顾客得到的甜甜圈不是上一组剩下的，那么这一组人都会很开心。
 * <p>
 * 你可以随意安排每批顾客到来的顺序。请你返回在此前提下，最多 有多少组人会感到开心。
 * <p>
 * 示例 1：
 * 输入：batchSize = 3, groups = [1,2,3,4,5,6]
 * 输出：4
 * 解释：你可以将这些批次的顾客顺序安排为 [6,2,4,5,1,3] 。那么第 1，2，4，6 组都会感到开心。
 * <p>
 * 示例 2：
 * 输入：batchSize = 4, groups = [1,3,2,5,2,2,1,6]
 * 输出：4
 * <p>
 * 提示：
 * 1 <= batchSize <= 9
 * 1 <= groups.length <= 30
 * 1 <= groups[i] <= 109
 * @Modified By: ZeromaXHe
 */
public class Solution5707 {
    /**
     * 未完成
     *
     * @param batchSize
     * @param groups
     * @return
     */
    public int maxHappyGroups(int batchSize, int[] groups) {
        if (batchSize == 1) {
            return groups.length;
        }
        int[] count = new int[batchSize];
        for (int group : groups) {
            count[group % batchSize]++;
        }
        int result = count[0];
        int total = groups.length - count[0];
        count[0] = 0;
        for (int i = 1; i < (batchSize + 1) / 2; i++) {
            int min = Math.min(count[i], count[batchSize - i]);
            result += min;
            count[i] -= min;
            count[batchSize - i] -= min;
            total -= 2 * min;
        }
        if (batchSize % 2 == 0) {
            result += count[batchSize / 2] / 2;
            total -= count[batchSize / 2] / 2 * 2;
            count[batchSize / 2] %= 2;
        }
        if (total > 0) {
            int[] coins = new int[total];
            int index = 1;
            for (int i = 0; i < total; i++) {
                while (index < count.length && count[index] == 0) {
                    index++;
                }
                for (int j = 0; j < count[index]; j++) {
                    coins[i++] = index;
                }
                count[index] = 0;
                index++;
            }
            int amount = 0;
            while (coinChange(coins, (amount + 1) * batchSize) != -1) {
                amount++;
            }
            result += amount;
        }
        return result;
    }

    /**
     * 这个硬币凑整的方法用在这不对……
     * 其实应该是按coin数组不能多次使用的算法才符合我的要求
     *
     * @param coins
     * @param amount
     * @return
     */
    private int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    @Test
    public void maxHappyGroupsTest() {
        Assert.assertEquals(4, maxHappyGroups(3, new int[]{1, 2, 3, 4, 5, 6}));
        Assert.assertEquals(4, maxHappyGroups(4, new int[]{1, 3, 2, 5, 2, 2, 1, 6}));
    }
}
