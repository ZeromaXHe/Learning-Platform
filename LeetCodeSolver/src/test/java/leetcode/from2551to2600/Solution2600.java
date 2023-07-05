package leetcode.from2551to2600;

/**
 * @author zhuxi
 * @apiNote 2600. K 件物品的最大和 | 难度：简单 | 标签：贪心、数学
 * 袋子中装有一些物品，每个物品上都标记着数字 1 、0 或 -1 。
 * <p>
 * 给你四个非负整数 numOnes 、numZeros 、numNegOnes 和 k 。
 * <p>
 * 袋子最初包含：
 * <p>
 * numOnes 件标记为 1 的物品。
 * numZeroes 件标记为 0 的物品。
 * numNegOnes 件标记为 -1 的物品。
 * 现计划从这些物品中恰好选出 k 件物品。返回所有可行方案中，物品上所标记数字之和的最大值。
 * <p>
 * 示例 1：
 * 输入：numOnes = 3, numZeros = 2, numNegOnes = 0, k = 2
 * 输出：2
 * 解释：袋子中的物品分别标记为 {1, 1, 1, 0, 0} 。取 2 件标记为 1 的物品，得到的数字之和为 2 。
 * 可以证明 2 是所有可行方案中的最大值。
 * <p>
 * 示例 2：
 * 输入：numOnes = 3, numZeros = 2, numNegOnes = 0, k = 4
 * 输出：3
 * 解释：袋子中的物品分别标记为 {1, 1, 1, 0, 0} 。取 3 件标记为 1 的物品，1 件标记为 0 的物品，得到的数字之和为 3 。
 * 可以证明 3 是所有可行方案中的最大值。
 * <p>
 * 提示：
 * 0 <= numOnes, numZeros, numNegOnes <= 50
 * 0 <= k <= numOnes + numZeros + numNegOnes
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/k-items-with-the-maximum-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/7/5 10:00
 */
public class Solution2600 {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了 82.01% 的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了 47.30% 的用户
     * 通过测试用例：1310 / 1310
     *
     * @param numOnes
     * @param numZeros
     * @param numNegOnes
     * @param k
     * @return
     */
    public int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
        if (numOnes >= k) {
            return k;
        }
        if (numOnes + numZeros >= k) {
            return numOnes;
        }
        return numOnes - (k - numOnes - numZeros);
    }
}
