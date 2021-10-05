package leetcode.from401to500;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/10/6 5:29
 * @Description: 414. 第三大的数 | 难度：简单 | 标签：数组、排序
 * 给你一个非空数组，返回此数组中 第三大的数 。如果不存在，则返回数组中最大的数。
 * <p>
 * 示例 1：
 * 输入：[3, 2, 1]
 * 输出：1
 * 解释：第三大的数是 1 。
 * <p>
 * 示例 2：
 * 输入：[1, 2]
 * 输出：2
 * 解释：第三大的数不存在, 所以返回最大的数 2 。
 * <p>
 * 示例 3：
 * 输入：[2, 2, 3, 1]
 * 输出：1
 * 解释：注意，要求返回第三大的数，是指在所有不同数字中排第三大的数。
 * 此例中存在两个值为 2 的数，它们都排第二。在所有不同数字中排第三大的数为 1 。
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 104
 * -231 <= nums[i] <= 231 - 1
 * <p>
 * 进阶：你能设计一个时间复杂度 O(n) 的解决方案吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/third-maximum-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: ZeromaXHe
 */
public class Solution414 {
    @Test
    public void testThirdMax() {
        Assert.assertEquals(1, thirdMax(new int[]{2, 2, 3, 1}));
    }

    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 53.46% 的用户
     * 内存消耗： 38.4 MB , 在所有 Java 提交中击败了 26.63% 的用户
     * 通过测试用例： 31 / 31
     *
     * @param nums
     * @return
     */
    public int thirdMax(int[] nums) {
        Integer[] max = new Integer[3];
        for (int num : nums) {
            if (max[0] == null || num > max[0]) {
                max[2] = max[1];
                max[1] = max[0];
                max[0] = num;
            } else if (num == max[0]) {
            } else if (max[1] == null || num > max[1]) {
                max[2] = max[1];
                max[1] = num;
            } else if (num == max[1]) {
            } else if (max[2] == null || num > max[2]) {
                max[2] = num;
            }
        }
        return max[2] == null ? max[0] : max[2];
    }
}
