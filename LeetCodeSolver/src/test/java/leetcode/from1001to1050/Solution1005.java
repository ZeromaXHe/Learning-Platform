package leetcode.from1001to1050;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: zhuxi
 * @Time: 2021/12/3 9:59
 * @Description: 1005. K 次取反后最大化的数组和 | 难度：简单 | 标签：贪心、数组、排序
 * 给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：
 * <p>
 * 选择某个下标 i 并将 nums[i] 替换为 -nums[i] 。
 * 重复这个过程恰好 k 次。可以多次选择同一个下标 i 。
 * <p>
 * 以这种方式修改数组后，返回数组 可能的最大和 。
 * <p>
 * 示例 1：
 * 输入：nums = [4,2,3], k = 1
 * 输出：5
 * 解释：选择下标 1 ，nums 变为 [4,-2,3] 。
 * <p>
 * 示例 2：
 * 输入：nums = [3,-1,0,2], k = 3
 * 输出：6
 * 解释：选择下标 (1, 2, 2) ，nums 变为 [3,1,0,2] 。
 * <p>
 * 示例 3：
 * 输入：nums = [2,-3,-1,5,-4], k = 2
 * 输出：13
 * 解释：选择下标 (1, 4) ，nums 变为 [2,3,-1,5,4] 。
 * <p>
 * 提示：
 * 1 <= nums.length <= 104
 * -100 <= nums[i] <= 100
 * 1 <= k <= 104
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximize-sum-of-array-after-k-negations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution1005 {
    @Test
    public void largestSumAfterKNegationsTest() {
        Assert.assertEquals(5, largestSumAfterKNegations(new int[]{4, 2, 3}, 1));
        Assert.assertEquals(6, largestSumAfterKNegations(new int[]{3, -1, 0, 2}, 3));
        Assert.assertEquals(13, largestSumAfterKNegations(new int[]{2, -3, -1, 5, -4}, 2));
        // 76 / 80
        Assert.assertEquals(53, largestSumAfterKNegations(new int[]{8, -7, -3, -9, 1, 9, -6, -9, 3}, 8));
        // 78 / 80
        Assert.assertEquals(22, largestSumAfterKNegations(new int[]{-8, 3, -5, -3, -5, -2}, 6));
        // 79 / 80
        Assert.assertEquals(5, largestSumAfterKNegations(new int[]{-4, -2, -3}, 4));
    }

    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 37.9 MB , 在所有 Java 提交中击败了 30.75% 的用户
     * 通过测试用例： 80 / 80
     *
     * @param nums
     * @param k
     * @return
     */
    public int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);
        int sum = 0;
        int i;
        for (i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                if (k == 0) {
                    break;
                }
                k--;
                sum -= nums[i];
            } else {
                if (nums[i] == 0) {
                    i++;
                } else if (k % 2 == 1) {
                    if (i == 0) {
                        sum -= nums[i++];
                    } else {
                        sum -= 2 * Math.min(-nums[i - 1], nums[i]);
                        sum += nums[i++];
                    }
                    k = 0;
                }
                break;
            }
        }
        if (i == nums.length && k > 0) {
            sum -= 2 * Math.abs(nums[i - 1]);
        } else {
            while (i < nums.length) {
                sum += nums[i++];
            }
        }
        return sum;
    }
}
