package leetcode.from1501to1600;

/**
 * @Author: zhuxi
 * @Time: 2021/12/17 9:55
 * @Description: 1518. 换酒问题 | 难度：简单 | 标签：数学、模拟
 * 小区便利店正在促销，用 numExchange 个空酒瓶可以兑换一瓶新酒。你购入了 numBottles 瓶酒。
 * <p>
 * 如果喝掉了酒瓶中的酒，那么酒瓶就会变成空的。
 * <p>
 * 请你计算 最多 能喝到多少瓶酒。
 * <p>
 * 示例 1：
 * 输入：numBottles = 9, numExchange = 3
 * 输出：13
 * 解释：你可以用 3 个空酒瓶兑换 1 瓶酒。
 * 所以最多能喝到 9 + 3 + 1 = 13 瓶酒。
 * <p>
 * 示例 2：
 * 输入：numBottles = 15, numExchange = 4
 * 输出：19
 * 解释：你可以用 4 个空酒瓶兑换 1 瓶酒。
 * 所以最多能喝到 15 + 3 + 1 = 19 瓶酒。
 * <p>
 * 示例 3：
 * 输入：numBottles = 5, numExchange = 5
 * 输出：6
 * <p>
 * 示例 4：
 * 输入：numBottles = 2, numExchange = 3
 * 输出：2
 * <p>
 * 提示：
 * 1 <= numBottles <= 100
 * 2 <= numExchange <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/water-bottles
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution1518 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 35 MB , 在所有 Java 提交中击败了 85.69% 的用户
     * 通过测试用例： 64 / 64
     *
     * @param numBottles
     * @param numExchange
     * @return
     */
    public int numWaterBottles(int numBottles, int numExchange) {
        int count = numBottles;
        while (numBottles >= numExchange) {
            int newBottles = numBottles / numExchange;
            numBottles %= numExchange;
            numBottles += newBottles;
            count += newBottles;
        }
        return count;
    }
}
