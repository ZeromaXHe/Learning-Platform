package leetcode.first100;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: zhuxi
 * @Time: 2022/1/10 11:26
 * @Description: 16. 最接近的三数之和 | 难度：中等 | 标签：数组、双指针、排序
 * 给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。
 * <p>
 * 返回这三个数的和。
 * <p>
 * 假定每组输入只存在恰好一个解。
 * <p>
 * 示例 1：
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 * <p>
 * 示例 2：
 * 输入：nums = [0,0,0], target = 1
 * 输出：0
 * <p>
 * 提示：
 * 3 <= nums.length <= 1000
 * -1000 <= nums[i] <= 1000
 * -104 <= target <= 104
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum-closest
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution16 {
    @Test
    public void threeSumClosestTest() {
        Assert.assertEquals(2, threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
        Assert.assertEquals(0, threeSumClosest(new int[]{0, 0, 0}, 1));
    }

    /**
     * 执行用时： 7 ms , 在所有 Java 提交中击败了 28.55% 的用户
     * 内存消耗： 37.8 MB , 在所有 Java 提交中击败了 91.66% 的用户
     * 通过测试用例： 131 / 131
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closeSum = Integer.MAX_VALUE / 4;
        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            while (k > j) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target) {
                    return target;
                } else if (Math.abs(sum - target) < Math.abs(closeSum - target)) {
                    closeSum = sum;
                }
                if (sum > target) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return closeSum;
    }
}
