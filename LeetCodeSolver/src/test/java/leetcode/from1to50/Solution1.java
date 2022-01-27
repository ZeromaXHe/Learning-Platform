package leetcode.from1to50;

import java.util.HashMap;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/11/20 17:16
 * @Description: 1.两数之和 | 难度: 简单 | 标签：数组、哈希表
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * <p>
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * <p>
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution1 {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了 95.69% 的用户
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    /**
     * 版本1：
     * 执行用时：5 ms, 在所有 Java 提交中击败了 56.47% 的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了 78.28% 的用户
     * 优化建议：可以不分target是否为偶数的情况的，直接一次循环。哈希表里只存左侧的数字对应索引
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum_version1(int[] nums, int target) {
        int index1 = -1;
        boolean targetEven = target % 2 == 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (targetEven && nums[i] == target / 2) {
                if (index1 == -1) {
                    index1 = i;
                } else {
                    return new int[]{index1, i};
                }
            }
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            if (!targetEven || nums[i] != target / 2) {
                if (map.containsKey(target - nums[i])) {
                    return new int[]{i, map.get(target - nums[i])};
                }
            }
        }
        return new int[]{};
    }
}
