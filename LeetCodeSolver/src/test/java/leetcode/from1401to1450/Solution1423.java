package leetcode.from1401to1450;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/2/6 14:49
 * @Description: 1423.可获得的最大点数 | 难度：中等 | 标签：数组、动态规划、滑动窗口
 * 几张卡牌 排成一行，每张卡牌都有一个对应的点数。点数由整数数组 cardPoints 给出。
 * <p>
 * 每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿 k 张卡牌。
 * <p>
 * 你的点数就是你拿到手中的所有卡牌的点数之和。
 * <p>
 * 给你一个整数数组 cardPoints 和整数 k，请你返回可以获得的最大点数。
 * <p>
 * 示例 1：
 * 输入：cardPoints = [1,2,3,4,5,6,1], k = 3
 * 输出：12
 * 解释：第一次行动，不管拿哪张牌，你的点数总是 1 。但是，先拿最右边的卡牌将会最大化你的可获得点数。最优策略是拿右边的三张牌，最终点数为 1 + 6 + 5 = 12 。
 * <p>
 * 示例 2：
 * 输入：cardPoints = [2,2,2], k = 2
 * 输出：4
 * 解释：无论你拿起哪两张卡牌，可获得的点数总是 4 。
 * <p>
 * 示例 3：
 * 输入：cardPoints = [9,7,7,9,7,7,9], k = 7
 * 输出：55
 * 解释：你必须拿起所有卡牌，可以获得的点数为所有卡牌的点数之和。
 * <p>
 * 示例 4：
 * 输入：cardPoints = [1,1000,1], k = 1
 * 输出：1
 * 解释：你无法拿到中间那张卡牌，所以可以获得的最大点数为 1 。
 * <p>
 * 示例 5：
 * 输入：cardPoints = [1,79,80,1,1,1,200,1], k = 3
 * 输出：202
 * <p>
 * 提示：
 * 1 <= cardPoints.length <= 10^5
 * 1 <= cardPoints[i] <= 10^4
 * 1 <= k <= cardPoints.length
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-points-you-can-obtain-from-cards
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution1423 {
    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 95.77% 的用户
     * 内存消耗： 47.7 MB , 在所有 Java 提交中击败了 47.75% 的用户
     *
     * @param cardPoints
     * @param k
     * @return
     */
    public int maxScore(int[] cardPoints, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += cardPoints[i];
        }
        int max = sum;
        for (int i = 0; i < k; i++) {
            sum -= cardPoints[k - 1 - i];
            sum += cardPoints[cardPoints.length - 1 - i];
            if (sum > max) {
                max = sum;
            }
        }
        return max;
    }

    @Test
    public void maxScoreTest() {
        Assert.assertEquals(12, maxScore(new int[]{1, 2, 3, 4, 5, 6, 1}, 3));
        Assert.assertEquals(4, maxScore(new int[]{2, 2, 2}, 2));
        Assert.assertEquals(55, maxScore(new int[]{9, 7, 7, 9, 7, 7, 9}, 7));
        Assert.assertEquals(1, maxScore(new int[]{1, 1000, 1}, 1));
        Assert.assertEquals(202, maxScore(new int[]{1, 79, 80, 1, 1, 1, 200, 1}, 3));
    }
}
