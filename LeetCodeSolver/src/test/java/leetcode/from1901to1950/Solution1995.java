package leetcode.from1901to1950;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhuxi
 * @Time: 2021/12/29 9:51
 * @Description: 1995. 统计特殊四元组 | 难度：简单 | 标签：数组、枚举
 * 给你一个 下标从 0 开始 的整数数组 nums ，返回满足下述条件的 不同 四元组 (a, b, c, d) 的 数目 ：
 * <p>
 * nums[a] + nums[b] + nums[c] == nums[d] ，且
 * a < b < c < d
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3,6]
 * 输出：1
 * 解释：满足要求的唯一一个四元组是 (0, 1, 2, 3) 因为 1 + 2 + 3 == 6 。
 * <p>
 * 示例 2：
 * 输入：nums = [3,3,6,4,5]
 * 输出：0
 * 解释：[3,3,6,4,5] 中不存在满足要求的四元组。
 * <p>
 * 示例 3：
 * 输入：nums = [1,1,1,3,5]
 * 输出：4
 * 解释：满足要求的 4 个四元组如下：
 * - (0, 1, 2, 3): 1 + 1 + 1 == 3
 * - (0, 1, 3, 4): 1 + 1 + 3 == 5
 * - (0, 2, 3, 4): 1 + 1 + 3 == 5
 * - (1, 2, 3, 4): 1 + 1 + 3 == 5
 * <p>
 * 提示：
 * 4 <= nums.length <= 50
 * 1 <= nums[i] <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-special-quadruplets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution1995 {
    /**
     * 执行用时： 21 ms , 在所有 Java 提交中击败了 18.33% 的用户
     * 内存消耗： 36.1 MB , 在所有 Java 提交中击败了 89.39% 的用户
     * 通过测试用例： 211 / 211
     *
     * @param nums
     * @return
     */
    public int countQuadruplets(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                for (int k = j + 1; k < nums.length - 1; k++) {
                    for (int l = k + 1; l < nums.length; l++) {
                        if (nums[i] + nums[j] + nums[k] == nums[l]) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    /**
     * 执行用时： 8 ms , 在所有 Java 提交中击败了 93.89% 的用户
     * 内存消耗： 38 MB , 在所有 Java 提交中击败了 8.36% 的用户
     * 通过测试用例： 211 / 211
     * <p>
     * 复杂度O(n^2)
     *
     * @param nums
     * @return
     */
    public int countQuadruplets2(int[] nums) {
        int n = nums.length;
        int ans = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int b = n - 3; b >= 1; --b) {
            for (int d = b + 2; d < n; ++d) {
                cnt.put(nums[d] - nums[b + 1], cnt.getOrDefault(nums[d] - nums[b + 1], 0) + 1);
            }
            for (int a = 0; a < b; ++a) {
                ans += cnt.getOrDefault(nums[a] + nums[b], 0);
            }
        }
        return ans;
    }
}
