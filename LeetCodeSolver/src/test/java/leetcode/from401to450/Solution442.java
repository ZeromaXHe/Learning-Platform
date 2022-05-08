package leetcode.from401to450;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/8 12:00
 * @Description: 442. 数组中重复的数据 | 难度：中等 | 标签：数组、哈希表
 * 给你一个长度为 n 的整数数组 nums ，其中 nums 的所有整数都在范围 [1, n] 内，且每个整数出现 一次 或 两次 。请你找出所有出现 两次 的整数，并以数组形式返回。
 * <p>
 * 你必须设计并实现一个时间复杂度为 O(n) 且仅使用常量额外空间的算法解决此问题。
 * <p>
 * 示例 1：
 * 输入：nums = [4,3,2,7,8,2,3,1]
 * 输出：[2,3]
 * <p>
 * 示例 2：
 * 输入：nums = [1,1,2]
 * 输出：[1]
 * <p>
 * 示例 3：
 * 输入：nums = [1]
 * 输出：[]
 * <p>
 * 提示：
 * n == nums.length
 * 1 <= n <= 105
 * 1 <= nums[i] <= n
 * nums 中的每个元素出现 一次 或 两次
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-all-duplicates-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: ZeromaXHe
 */
public class Solution442 {
    /**
     * 执行用时： 5 ms , 在所有 Java 提交中击败了 72.46% 的用户
     * 内存消耗： 49.2 MB , 在所有 Java 提交中击败了 61.76% 的用户
     * 通过测试用例： 28 / 28
     *
     * @param nums
     * @return
     */
    public List<Integer> findDuplicates(int[] nums) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i + 1 && nums[i] != -1) {
                if (nums[nums[i] - 1] == nums[i]) {
                    list.add(nums[i]);
                    nums[i] = -1;
                    continue;
                }
                int temp = nums[i];
                nums[i] = nums[temp - 1];
                nums[temp - 1] = temp;
            }
        }
        return list;
    }
}
