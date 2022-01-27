package leetcode.from1051to1100;

/**
 * @Author: zhuxi
 * @Time: 2021/2/23 17:57
 * @Description: 1052. 爱生气的书店老板 | 难度：中等 | 标签：数组、滑动窗口
 * 今天，书店老板有一家店打算试营业 customers.length 分钟。每分钟都有一些顾客（customers[i]）会进入书店，所有这些顾客都会在那一分钟结束后离开。
 * <p>
 * 在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。 当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。
 * <p>
 * 书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 X 分钟不生气，但却只能使用一次。
 * <p>
 * 请你返回这一天营业下来，最多有多少客户能够感到满意的数量。
 * <p>
 * 示例：
 * 输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
 * 输出：16
 * 解释：
 * 书店老板在最后 3 分钟保持冷静。
 * 感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
 * <p>
 * 提示：
 * 1 <= X <= customers.length == grumpy.length <= 20000
 * 0 <= customers[i] <= 1000
 * 0 <= grumpy[i] <= 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/grumpy-bookstore-owner
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution1052 {
    /**
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 79.44% 的用户
     * 内存消耗： 41.2 MB , 在所有 Java 提交中击败了 6.92% 的用户
     *
     * @param customers
     * @param grumpy
     * @param X
     * @return
     */
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int maxNoGrumpy = 0;
        int alwaysStatisfied = 0;
        int tempNoGrumpy = 0;
        for (int i = 0; i < customers.length; i++) {
            if (i >= X) {
                if (grumpy[i - X] == 1) {
                    tempNoGrumpy -= customers[i - X];
                }
                if (grumpy[i] == 0) {
                    alwaysStatisfied += customers[i];
                } else {
                    tempNoGrumpy += customers[i];
                    if (tempNoGrumpy > maxNoGrumpy) {
                        maxNoGrumpy = tempNoGrumpy;
                    }
                }
            } else {
                if (grumpy[i] == 0) {
                    alwaysStatisfied += customers[i];
                } else {
                    maxNoGrumpy += customers[i];
                    tempNoGrumpy += customers[i];
                }
            }
        }
        return maxNoGrumpy + alwaysStatisfied;
    }
}
