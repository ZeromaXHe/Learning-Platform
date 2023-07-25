package leetcode.from2201to2250;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zhuxi
 * @apiNote 2208. 将数组和减半的最少操作次数 | 难度：中等 | 标签：贪心、数组、堆（优先队列）
 * 给你一个正整数数组 nums 。每一次操作中，你可以从 nums 中选择 任意 一个数并将它减小到 恰好 一半。（注意，在后续操作中你可以对减半过的数继续执行操作）
 * <p>
 * 请你返回将 nums 数组和 至少 减少一半的 最少 操作数。
 * <p>
 * 示例 1：
 * 输入：nums = [5,19,8,1]
 * 输出：3
 * 解释：初始 nums 的和为 5 + 19 + 8 + 1 = 33 。
 * 以下是将数组和减少至少一半的一种方法：
 * 选择数字 19 并减小为 9.5 。
 * 选择数字 9.5 并减小为 4.75 。
 * 选择数字 8 并减小为 4 。
 * 最终数组为 [5, 4.75, 4, 1] ，和为 5 + 4.75 + 4 + 1 = 14.75 。
 * nums 的和减小了 33 - 14.75 = 18.25 ，减小的部分超过了初始数组和的一半，18.25 >= 33/2 = 16.5 。
 * 我们需要 3 个操作实现题目要求，所以返回 3 。
 * 可以证明，无法通过少于 3 个操作使数组和减少至少一半。
 * <p>
 * 示例 2：
 * 输入：nums = [3,8,20]
 * 输出：3
 * 解释：初始 nums 的和为 3 + 8 + 20 = 31 。
 * 以下是将数组和减少至少一半的一种方法：
 * 选择数字 20 并减小为 10 。
 * 选择数字 10 并减小为 5 。
 * 选择数字 3 并减小为 1.5 。
 * 最终数组为 [1.5, 8, 5] ，和为 1.5 + 8 + 5 = 14.5 。
 * nums 的和减小了 31 - 14.5 = 16.5 ，减小的部分超过了初始数组和的一半， 16.5 >= 31/2 = 16.5 。
 * 我们需要 3 个操作实现题目要求，所以返回 3 。
 * 可以证明，无法通过少于 3 个操作使数组和减少至少一半。
 * <p>
 * 提示：
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 107
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-operations-to-halve-array-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/7/25 15:48
 */
public class Solution2208 {
    /**
     * 执行用时：196 ms, 在所有 Java 提交中击败了 30.30% 的用户
     * 内存消耗：55.7 MB, 在所有 Java 提交中击败了 75.76% 的用户
     * 通过测试用例：62 / 62
     * @param nums
     * @return
     */
    public int halveArray(int[] nums) {
        double sum = 0;
        // 最大堆（大顶堆）
        PriorityQueue<Double> heap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int num : nums) {
            sum += num;
            heap.offer((double) num);
        }
        double temp = sum;
        int count = 0;
        while (temp > sum / 2) {
            Double poll = heap.poll();
            temp -= poll / 2;
            heap.offer(poll / 2);
            count++;
        }
        return count;
    }
}
