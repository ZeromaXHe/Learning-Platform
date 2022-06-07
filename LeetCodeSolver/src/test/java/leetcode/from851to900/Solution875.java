package leetcode.from851to900;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2022/6/7 11:37
 * @Description: 875. 爱吃香蕉的珂珂 | 难度：中等 | 标签：数组、二分查找
 * 珂珂喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 h 小时后回来。
 * <p>
 * 珂珂可以决定她吃香蕉的速度 k （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 k 根。
 * 如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。  
 * <p>
 * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 * <p>
 * 返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）。
 * <p>
 * 示例 1：
 * 输入：piles = [3,6,7,11], h = 8
 * 输出：4
 * <p>
 * 示例 2：
 * 输入：piles = [30,11,23,4,20], h = 5
 * 输出：30
 * <p>
 * 示例 3：
 * 输入：piles = [30,11,23,4,20], h = 6
 * 输出：23
 * <p>
 * 提示：
 * 1 <= piles.length <= 104
 * piles.length <= h <= 109
 * 1 <= piles[i] <= 109
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/koko-eating-bananas
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution875 {
    @Test
    public void minEatingSpeedTest() {
        Assert.assertEquals(4, minEatingSpeed(new int[]{3, 6, 7, 11}, 8));
        Assert.assertEquals(30, minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 5));
        Assert.assertEquals(23, minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 6));
        Assert.assertEquals(1, minEatingSpeed(new int[]{312884470}, 968709470));
    }

    /**
     * 执行用时： 12 ms , 在所有 Java 提交中击败了 51.58% 的用户
     * 内存消耗： 42.1 MB , 在所有 Java 提交中击败了 67.75% 的用户
     * 通过测试用例： 121 / 121
     *
     * @param piles
     * @param h
     * @return
     */
    public int minEatingSpeed(int[] piles, int h) {
        int max = piles[0];
        int sum = piles[0];
        for (int i = 1; i < piles.length; i++) {
            sum += piles[i];
            if (piles[i] > max) {
                max = piles[i];
            }
        }
        int min = Math.max(sum / h, 1);
        while (min < max) {
            int mid = (min + max) / 2;
            int times = 0;
            for (int pile : piles) {
                times += pile / mid + (pile % mid == 0 ? 0 : 1);
            }
            if (times <= h) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }
        return min;
    }
}
