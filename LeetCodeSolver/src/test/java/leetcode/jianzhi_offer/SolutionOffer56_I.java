package leetcode.jianzhi_offer;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer 56 - I. 数组中数字出现的次数 | 难度：中等 | 标签：位运算、数组
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 * <p>
 * 示例 1：
 * 输入：nums = [4,1,4,6]
 * 输出：[1,6] 或 [6,1]
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,10,4,1,4,3,3]
 * 输出：[2,10] 或 [10,2]
 * <p>
 * 限制：
 * 2 <= nums.length <= 10000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/10 16:50
 */
public class SolutionOffer56_I {
    /**
     * 官方题解分组异或是真的秀，参考了思路自己做的
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：43.7 MB, 在所有 Java 提交中击败了 18.00% 的用户
     * 通过测试用例：35 / 35
     *
     * @param nums
     * @return
     */
    public int[] singleNumbers(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        int last = xor & -xor;
        int xor1 = 0;
        int xor2 = 0;
        for (int num : nums) {
            if ((num & last) != 0) {
                xor1 ^= num;
            } else {
                xor2 ^= num;
            }
        }
        return new int[]{xor1, xor2};
    }
}
