package leetcode.from301to400;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2021/4/23 9:47
 * @Description: 368.最大整除子集 | 难度：中等 | 标签：数学、动态规划
 * 给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，子集中每一元素对 (answer[i], answer[j]) 都应当满足：
 * answer[i] % answer[j] == 0 ，或
 * answer[j] % answer[i] == 0
 * 如果存在多个有效解子集，返回其中任何一个均可。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[1,2]
 * 解释：[1,3] 也会被视为正确答案。
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,4,8]
 * 输出：[1,2,4,8]
 * <p>
 * 提示：
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 2 * 10^9
 * nums 中的所有整数 互不相同
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-divisible-subset
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution368 {
    /**
     * 执行用时： 21 ms , 在所有 Java 提交中击败了 77.56% 的用户
     * 内存消耗： 38.6 MB , 在所有 Java 提交中击败了 76.00% 的用户
     *
     * @param nums
     * @return
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int[][] dp = new int[nums.length][2];
        dp[0][0] = 1;
        int maxMaxCount = 1;
        int maxMaxIndex = 0;
        for (int i = 1; i < dp.length; i++) {
            int maxCount = 1;
            int maxIndex = i;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    if (dp[j][0] + 1 > maxCount) {
                        maxCount = dp[j][0] + 1;
                        maxIndex = j;
                    }
                }
            }
            dp[i][0] = maxCount;
            dp[i][1] = maxIndex;
            if (maxCount > maxMaxCount) {
                maxMaxCount = maxCount;
                maxMaxIndex = i;
            }
        }
        LinkedList<Integer> result = new LinkedList<>();
        int index = maxMaxIndex;
        while (dp[index][1] != index) {
            result.addFirst(nums[index]);
            index = dp[index][1];
        }
        result.addFirst(nums[index]);
        return result;
    }

    @Test
    public void largestDivisibleSubsetTest() {
        // [1, 2]
        System.out.println(largestDivisibleSubset(new int[]{1, 2, 3}));
    }
}
