package leetcode.from201to300;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: zhuxi
 * @Time: 2022/1/19 9:57
 * @Description: 219. 存在重复元素 II | 难度：简单 | 标签：数组、哈希表、滑动窗口
 * 给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，满足 nums[i] == nums[j] 且 abs(i - j) <= k 。
 * 如果存在，返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3,1], k = 3
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：nums = [1,0,1,1], k = 1
 * 输出：true
 * <p>
 * 示例 3：
 * 输入：nums = [1,2,3,1,2,3], k = 2
 * 输出：false
 * <p>
 * 提示：
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * 0 <= k <= 105
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contains-duplicate-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution219 {
    /**
     * 执行用时： 16 ms , 在所有 Java 提交中击败了 94.49% 的用户
     * 内存消耗： 47.4 MB , 在所有 Java 提交中击败了 75.85% 的用户
     * 通过测试用例： 51 / 51
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < Math.min(k, nums.length); i++) {
            set.add(nums[i]);
        }
        if (set.size() < Math.min(k, nums.length)) {
            return true;
        }
        for (int i = k; i < nums.length; i++) {
            set.add(nums[i]);
            if (set.size() < k + 1) {
                return true;
            }
            set.remove(nums[i - k]);
        }
        return false;
    }
}
