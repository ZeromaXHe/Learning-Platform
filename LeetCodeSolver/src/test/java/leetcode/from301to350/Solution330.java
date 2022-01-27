package leetcode.from301to350;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/29 9:46
 * @Description: 330. 按要求补齐数组 | 难度：困难 | 标签：贪心算法
 * 给定一个已排序的正整数数组 nums，和一个正整数 n 。
 * 从 [1, n] 区间内选取任意个数字补充到 nums 中，使得 [1, n] 区间内的任何数字都可以用 nums 中某几个数字的和来表示。
 * 请输出满足上述要求的最少需要补充的数字个数。
 * <p>
 * 示例 1:
 * 输入: nums = [1,3], n = 6
 * 输出: 1
 * 解释:
 * 根据 nums 里现有的组合 [1], [3], [1,3]，可以得出 1, 3, 4。
 * 现在如果我们将 2 添加到 nums 中， 组合变为: [1], [2], [3], [1,3], [2,3], [1,2,3]。
 * 其和可以表示数字 1, 2, 3, 4, 5, 6，能够覆盖 [1, 6] 区间里所有的数。
 * 所以我们最少需要添加一个数字。
 * <p>
 * 示例 2:
 * 输入: nums = [1,5,10], n = 20
 * 输出: 2
 * 解释: 我们需要添加 [2, 4]。
 * <p>
 * 示例 3:
 * 输入: nums = [1,2,2], n = 5
 * 输出: 0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/patching-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution330 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.1 MB , 在所有 Java 提交中击败了 63.69% 的用户
     * <p>
     * 参考题解的思路：
     * 对于正整数 x，如果区间 [1,x-1] 内的所有数字都已经被覆盖，且 x 在数组中，则区间 [1,2x−1] 内的所有数字也都被覆盖。
     * 如果区间 [1,x-1] 内的所有数字都已经被覆盖，则从贪心的角度考虑，补充 x 之后即可覆盖到 x，且满足补充的数字个数最少。
     * 在补充 x 之后，区间 [1,2x-1] 内的所有数字都被覆盖，下一个缺失的数字一定不会小于 2x。
     * <p>
     * 由此可以提出一个贪心的方案。
     * 每次找到未被数组 nums 覆盖的最小的整数 x，在数组中补充 x，然后寻找下一个未被覆盖的最小的整数，重复上述步骤直到区间 [1,n] 中的所有数字都被覆盖。
     *
     * @param nums
     * @param n
     * @return
     */
    public int minPatches(int[] nums, int n) {
        // 补充个数
        int patches = 0;
        // 覆盖区间
        long x = 1;
        int length = nums.length, index = 0;
        while (x <= n) {
            if (index < length && nums[index] <= x) {
                // nums[index]处于[1,x-1]区间或者等于x
                x += nums[index];
                index++;
            } else {
                // nums[index]处于区间外，且不等于x。需要补个x，使x变成2x，再继续看看nums[index]是否被区间覆盖。
                x *= 2;
                patches++;
            }
        }
        return patches;
    }
}
