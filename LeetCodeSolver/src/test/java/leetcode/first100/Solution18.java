package leetcode.first100;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/1/10 13:43
 * @Description: 18. 四数之和 | 难度：中等 | 标签：数组、双指针、排序
 * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
 * <p>
 * 0 <= a, b, c, d < n
 * a、b、c 和 d 互不相同
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * 你可以按 任意顺序 返回答案 。
 * <p>
 * 示例 1：
 * 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * <p>
 * 示例 2：
 * 输入：nums = [2,2,2,2,2], target = 8
 * 输出：[[2,2,2,2]]
 * <p>
 * 提示：
 * 1 <= nums.length <= 200
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/4sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution18 {
    @Test
    public void fourSumTest() {
        Assert.assertEquals("[[-10, -8, -5, 2], [-10, -8, -4, 1], [-10, -8, -2, -1], [-10, -6, -5, 0]," +
                        " [-10, -6, -4, -1], [-10, -5, -4, -2], [-8, -6, -5, -2]]",
                fourSum(new int[]{7, 7, -1, -5, 2, -2, 8, -8, -10, 0, 1, -4, -1, 4, -6, 5, 4}, -21).toString());
        Assert.assertEquals("[[-8, -7, -6, 9], [-8, -7, -4, 7], [-8, -7, 0, 3], [-8, -4, 0, 0], [-7, -6, -4, 5]]",
                fourSum(new int[]{0, 5, -8, -7, 4, 8, -4, 3, 9, 7, 8, 10, 3, -6, 3, 7, 10, 0}, -12).toString());
    }

    /**
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 96.34% 的用户
     * 内存消耗： 38.2 MB , 在所有 Java 提交中击败了 98.53% 的用户
     * 通过测试用例： 289 / 289
     * <p>
     * 最内侧c、d的寻找应该用双指针的，能更快一点。
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums.length < 4) {
            return new LinkedList<>();
        }
        Arrays.sort(nums);

        List<List<Integer>> ans = new LinkedList<>();
        for (int a = 0; a < nums.length - 3; a++) {
            // 如果这四数之和大于target, 往后算也必大于target
            if ((long) nums[a] + nums[a + 1] + nums[a + 2] + nums[a + 3] > target) {
                break;
            }
            // 下面这四数之和小于target, 则nums[a]小了
            if ((long) nums[a] + nums[nums.length - 3] + nums[nums.length - 2] + nums[nums.length - 1] < target) {
                continue;
            }

            // 重复的 a
            if (a > 0 && nums[a] == nums[a - 1]) {
                continue;
            }
            for (int b = a + 1; b < nums.length - 2; b++) {
                // 如果这四数之和大于target, 往后算也必大于target
                if ((long) nums[a] + nums[b] + nums[b + 1] + nums[b + 2] > target) {
                    break;
                }
                // 下面这四数之和小于target, 则nums[b]小了
                if ((long) nums[a] + nums[b] + nums[nums.length - 2] + nums[nums.length - 1] < target) {
                    continue;
                }
                // 重复的 b
                if (b > a + 1 && nums[b] == nums[b - 1]) {
                    continue;
                }
                int tmp = target - nums[a] - nums[b];
                int d = nums.length - 1;
                for (int c = b + 1; c < nums.length - 1; c++) {
                    // 如果这四数之和大于target, 往后算也必大于target
                    if ((long) nums[c] + nums[c + 1] > tmp) {
                        break;
                    }
                    // 下面这四数之和小于target, 则nums[c]小了
                    if ((long) nums[c] + nums[nums.length - 1] < tmp) {
                        continue;
                    }
                    // 重复的 c
                    if (c > b + 1 && nums[c] == nums[c - 1]) {
                        continue;
                    }

                    // 2 ^ 7 = 128
                    if (nums.length - c > 7) {
                        // 这时，二分搜索一定会比按一个一个找快
                        if (tmp % 2 == 0 && nums[c] == tmp / 2) {
                            if (nums[c + 1] == nums[c]) {
                                ans.add(Arrays.asList(nums[a], nums[b], nums[c], nums[c]));
                            } else {
                                break;
                            }
                        } else if (Arrays.binarySearch(nums, tmp - nums[c]) > c) {
                            ans.add(Arrays.asList(nums[a], nums[b], nums[c], tmp - nums[c]));
                        }
                    } else {
                        while (c < d && nums[c] + nums[d] > tmp) {
                            d--;
                        }
                        if (c < d && nums[c] + nums[d] == tmp) {
                            ans.add(Arrays.asList(nums[a], nums[b], nums[c], nums[d]));
                        }
                    }
                }
            }
        }
        return ans;
    }
}
