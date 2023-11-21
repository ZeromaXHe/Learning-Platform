package leetcode.from2201to2250;

/**
 * @author zhuxi
 * @apiNote 2216. 美化数组的最少删除数 | 难度：中等 | 标签：栈、贪心、数组
 * 给你一个下标从 0 开始的整数数组 nums ，如果满足下述条件，则认为数组 nums 是一个 美丽数组 ：
 * <p>
 * nums.length 为偶数
 * 对所有满足 i % 2 == 0 的下标 i ，nums[i] != nums[i + 1] 均成立
 * 注意，空数组同样认为是美丽数组。
 * <p>
 * 你可以从 nums 中删除任意数量的元素。当你删除一个元素时，被删除元素右侧的所有元素将会向左移动一个单位以填补空缺，而左侧的元素将会保持 不变 。
 * <p>
 * 返回使 nums 变为美丽数组所需删除的 最少 元素数目。
 * <p>
 * 示例 1：
 * 输入：nums = [1,1,2,3,5]
 * 输出：1
 * 解释：可以删除 nums[0] 或 nums[1] ，这样得到的 nums = [1,2,3,5] 是一个美丽数组。可以证明，要想使 nums 变为美丽数组，至少需要删除 1 个元素。
 * <p>
 * 示例 2：
 * 输入：nums = [1,1,2,2,3,3]
 * 输出：2
 * 解释：可以删除 nums[0] 和 nums[5] ，这样得到的 nums = [1,2,2,3] 是一个美丽数组。可以证明，要想使 nums 变为美丽数组，至少需要删除 2 个元素。
 * <p>
 * 提示：
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 105
 * @implNote
 * @since 2023/11/21 9:51
 */
public class Solution2216 {
    /**
     * 执行用时分布 3ms
     * 击败 58.59% 使用 Java 的用户
     * 消耗内存分布 54.56 MB
     * 击败 20.20% 使用 Java 的用户
     *
     * @param nums
     * @return
     */
    public int minDeletion(int[] nums) {
        int count = 0;
        int diff = 1;
        for (int i = 0; i < nums.length; i++) {
            while (i + diff < nums.length && nums[i] == nums[i + diff]) {
                count++;
                diff++;
            }
            if (i + diff == nums.length) {
                count++;
                break;
            }
            i += diff;
            diff = 1;
        }
        return count;
    }
}
