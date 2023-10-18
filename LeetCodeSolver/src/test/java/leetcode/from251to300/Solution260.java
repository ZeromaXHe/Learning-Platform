package leetcode.from251to300;

/**
 * @author zhuxi
 * @apiNote 260. 只出现一次的数字 III | 难度：中等 | 标签：位运算、数组
 * 给你一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。
 * <p>
 * 你必须设计并实现线性时间复杂度的算法且仅使用常量额外空间来解决此问题。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,1,3,2,5]
 * 输出：[3,5]
 * 解释：[5, 3] 也是有效的答案。
 * <p>
 * 示例 2：
 * 输入：nums = [-1,0]
 * 输出：[-1,0]
 * <p>
 * 示例 3：
 * 输入：nums = [0,1]
 * 输出：[1,0]
 * <p>
 * 提示：
 * 2 <= nums.length <= 3 * 104
 * -231 <= nums[i] <= 231 - 1
 * 除两个只出现一次的整数外，nums 中的其他数字都出现两次
 * @implNote
 * @since 2023/10/16 10:14
 */
public class Solution260 {
    /**
     * 参考题解做的
     * <p>
     * 时间 0 ms
     * 击败 100.00% 使用 Java 的用户
     * 内存 42.17 MB
     * 击败 64.84% 使用 Java 的用户
     *
     * @param nums
     * @return
     */
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        int lowestOne = (xor == Integer.MIN_VALUE ? xor : (xor & -xor));
        int res1 = 0;
        int res2 = 0;
        for (int num : nums) {
            if ((num & lowestOne) != 0) {
                res1 ^= num;
            } else {
                res2 ^= num;
            }
        }
        return new int[]{res1, res2};
    }
}
