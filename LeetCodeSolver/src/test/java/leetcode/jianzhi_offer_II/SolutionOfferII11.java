package leetcode.jianzhi_offer_II;

import java.util.HashMap;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer II 011. 0 和 1 个数相同的子数组 | 难度：中等 | 标签：数组、哈希表、前缀和
 * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
 * <p>
 * 示例 1：
 * 输入: nums = [0,1]
 * 输出: 2
 * 说明: [0, 1] 是具有相同数量 0 和 1 的最长连续子数组。
 * <p>
 * 示例 2：
 * 输入: nums = [0,1,0]
 * 输出: 2
 * 说明: [0, 1] (或 [1, 0]) 是具有相同数量 0 和 1 的最长连续子数组。
 * <p>
 * 提示：
 * 1 <= nums.length <= 105
 * nums[i] 不是 0 就是 1
 * <p>
 * 注意：本题与主站 525 题相同： https://leetcode-cn.com/problems/contiguous-array/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/A1NYOS
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/14 17:10
 */
public class SolutionOfferII11 {
    /**
     * 执行用时：22 ms, 在所有 Java 提交中击败了 81.60% 的用户
     * 内存消耗：53.2 MB, 在所有 Java 提交中击败了 10.61% 的用户
     * 通过测试用例：564 / 564
     *
     * @param nums
     * @return
     */
    public int findMaxLength(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        int max = 0;
        int preSum = 0;
        for (int i = 0; i < nums.length; i++) {
            preSum += (nums[i] == 1 ? 1 : -1);
            if (map.containsKey(preSum)) {
                max = Math.max(max, i + 1 - map.get(preSum));
            } else {
                map.put(preSum, i + 1);
            }
        }
        return max;
    }
}
