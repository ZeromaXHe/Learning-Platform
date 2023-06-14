package leetcode.jianzhi_offer;

import java.util.HashMap;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer II 010. 和为 k 的子数组 | 难度：中等 | 标签：数组、哈希表、前缀和
 * 给定一个整数数组和一个整数 k ，请找到该数组中和为 k 的连续子数组的个数。
 * <p>
 * 示例 1：
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2
 * 解释: 此题 [1,1] 与 [1,1] 为两种不同的情况
 * <p>
 * 示例 2：
 * 输入:nums = [1,2,3], k = 3
 * 输出: 2
 * <p>
 * 提示:
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 * <p>
 * 注意：本题与主站 560 题相同： https://leetcode-cn.com/problems/subarray-sum-equals-k/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/QTMn0o
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/14 16:42
 */
public class SolutionOfferII10 {
    /**
     * 执行用时：19 ms, 在所有 Java 提交中击败了 91.49% 的用户
     * 内存消耗：44.5 MB, 在所有 Java 提交中击败了 27.51% 的用户
     * 通过测试用例：89 / 89
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int[] preSum = new int[nums.length + 1];
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
            if (map.containsKey(preSum[i + 1] - k)) {
                count += map.get(preSum[i + 1] - k);
            }
            map.merge(preSum[i + 1], 1, Integer::sum);
        }
        return count;
    }

    /**
     * 执行用时：1557 ms, 在所有 Java 提交中击败了 5.02% 的用户
     * 内存消耗：44.1 MB, 在所有 Java 提交中击败了 66.23% 的用户
     * 通过测试用例：89 / 89
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum_2for(int[] nums, int k) {
        int[] preSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (preSum[j + 1] - preSum[i] == k) {
                    count++;
                }
            }
        }
        return count;
    }
}
