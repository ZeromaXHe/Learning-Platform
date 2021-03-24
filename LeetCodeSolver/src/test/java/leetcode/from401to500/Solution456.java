package leetcode.from401to500;

import org.junit.Assert;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: zhuxi
 * @Time: 2021/3/24 10:01
 * @Description: 456.132模式 | 难度：中等 | 标签：栈
 * 给你一个整数数组 nums ，数组中共有 n 个整数。132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，并同时满足：i < j < k 和 nums[i] < nums[k] < nums[j] 。
 * <p>
 * 如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。
 * <p>
 * 进阶：很容易想到时间复杂度为 O(n^2) 的解决方案，你可以设计一个时间复杂度为 O(n logn) 或 O(n) 的解决方案吗？
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3,4]
 * 输出：false
 * 解释：序列中不存在 132 模式的子序列。
 * <p>
 * 示例 2：
 * 输入：nums = [3,1,4,2]
 * 输出：true
 * 解释：序列中有 1 个 132 模式的子序列： [1, 4, 2] 。
 * <p>
 * 示例 3：
 * 输入：nums = [-1,3,2,0]
 * 输出：true
 * 解释：序列中有 3 个 132 模式的的子序列：[-1, 3, 2]、[-1, 3, 0] 和 [-1, 2, 0] 。
 * <p>
 * 提示：
 * n == nums.length
 * 1 <= n <= 10^4
 * -10^9 <= nums[i] <= 10^9
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/132-pattern
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution456 {
    /**
     * 单调栈，还是不怎么会。这里是题解答案
     * <p>
     * 执行用时： 9 ms , 在所有 Java 提交中击败了 43.28% 的用户
     * 内存消耗： 39.3 MB , 在所有 Java 提交中击败了 6.83% 的用户
     *
     * @param nums
     * @return
     */
    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        Deque<Integer> candidateK = new LinkedList<>();
        candidateK.push(nums[n - 1]);
        int maxK = Integer.MIN_VALUE;

        for (int i = n - 2; i >= 0; --i) {
            if (nums[i] < maxK) {
                return true;
            }
            while (!candidateK.isEmpty() && nums[i] > candidateK.peek()) {
                maxK = candidateK.pop();
            }
            if (nums[i] > maxK) {
                candidateK.push(nums[i]);
            }
        }

        return false;
    }

    public boolean find132pattern_wrong(int[] nums) {
        int[] val2ofPattern32 = new int[nums.length];
        val2ofPattern32[nums.length - 1] = Integer.MIN_VALUE;
        int max = nums[nums.length - 1];
        // 这里secondMax应该由单调栈来维护
        int secondMax = Integer.MIN_VALUE;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > max) {
                val2ofPattern32[i] = secondMax = max;
                max = nums[i];
            } else if (nums[i] == max) {
                val2ofPattern32[i] = Math.max(val2ofPattern32[i + 1], secondMax);
            } else {
                if (nums[i] > nums[i + 1]) {
                    val2ofPattern32[i] = Math.max(nums[i + 1], val2ofPattern32[i + 1]);
                } else {
                    val2ofPattern32[i] = val2ofPattern32[i + 1];
                }
                if (nums[i] < max && nums[i] > secondMax) {
                    secondMax = nums[i];
                }
            }
        }
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] < val2ofPattern32[i + 1]) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void find132patternTest() {
        Assert.assertTrue(find132pattern(new int[]{3, 5, 0, 3, 4}));
        Assert.assertTrue(find132pattern(new int[]{3, 5, 4, 5, 1}));
        Assert.assertTrue(find132pattern(new int[]{-2, 1, 2, -2, 1, 2}));
        Assert.assertTrue(find132pattern(new int[]{42, 43, 6, 12, 3, 4, 6, 11, 20}));
    }
}
