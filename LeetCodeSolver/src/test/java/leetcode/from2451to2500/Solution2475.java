package leetcode.from2451to2500;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zhuxi
 * @apiNote 2475. 数组中不等三元组的数目 | 难度：简单 | 标签：数组、哈希表
 * 给你一个下标从 0 开始的正整数数组 nums 。请你找出并统计满足下述条件的三元组 (i, j, k) 的数目：
 * <p>
 * 0 <= i < j < k < nums.length
 * nums[i]、nums[j] 和 nums[k] 两两不同 。
 * 换句话说：nums[i] != nums[j]、nums[i] != nums[k] 且 nums[j] != nums[k] 。
 * 返回满足上述条件三元组的数目。
 * <p>
 * 示例 1：
 * 输入：nums = [4,4,2,4,3]
 * 输出：3
 * 解释：下面列出的三元组均满足题目条件：
 * - (0, 2, 4) 因为 4 != 2 != 3
 * - (1, 2, 4) 因为 4 != 2 != 3
 * - (2, 3, 4) 因为 2 != 4 != 3
 * 共计 3 个三元组，返回 3 。
 * 注意 (2, 0, 4) 不是有效的三元组，因为 2 > 0 。
 * <p>
 * 示例 2：
 * 输入：nums = [1,1,1,1,1]
 * 输出：0
 * 解释：不存在满足条件的三元组，所以返回 0 。
 * <p>
 * 提示：
 * 3 <= nums.length <= 100
 * 1 <= nums[i] <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-unequal-triplets-in-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/13 9:45
 */
public class Solution2475 {
    /**
     * 脑子转不动，result 累加的逻辑是参考题解做的
     * <p>
     * 执行用时：8 ms, 在所有 Java 提交中击败了 14.22% 的用户
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了 5.61% 的用户
     * 通过测试用例：104 / 104
     *
     * @param nums
     * @return
     */
    public int unequalTriplets(int[] nums) {
        Map<Integer, Long> countMap = Arrays.stream(nums).boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        if (countMap.size() < 3) {
            return 0;
        }
        int result = 0;
        int n = nums.length;
        int preSum = 0;
        for (Map.Entry<Integer, Long> entry : countMap.entrySet()) {
            result += preSum * entry.getValue().intValue() * (n - preSum - entry.getValue());
            preSum += entry.getValue().intValue();
        }
        return result;
    }
}
