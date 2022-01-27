package leetcode.from651to700;

import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/9/22 11:39
 * @Description: 673.最长递增子序列的个数 | 难度：中等 | 标签：树状数组、线段树、数组、动态规划
 * 给定一个未排序的整数数组，找到最长递增子序列的个数。
 * <p>
 * 示例 1:
 * 输入: [1,3,5,4,7]
 * 输出: 2
 * 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
 * <p>
 * 示例 2:
 * 输入: [2,2,2,2,2]
 * 输出: 5
 * 解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
 * 注意: 给定的数组长度不超过 2000 并且结果一定是32位有符号整数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution673 {
    /**
     * 动规的解法，O(n^2)。还有个O(nlogn)的解法，有时间再看看吧
     * <p>
     * 执行用时： 20 ms , 在所有 Java 提交中击败了 84.88% 的用户
     * 内存消耗： 38.2 MB , 在所有 Java 提交中击败了 21.90% 的用户
     * 通过测试用例： 223 / 223
     *
     * @param nums
     * @return
     */
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] dpLen = new int[n];
        int[] dpCnt = new int[n];
        int count = 0;
        int len = 0;
        for (int i = 0; i < n; i++) {
            dpLen[i] = 1;
            dpCnt[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dpLen[j] + 1 > dpLen[i]) {
                        dpLen[i] = dpLen[j] + 1;
                        dpCnt[i] = dpCnt[j];
                    } else if (dpLen[j] + 1 == dpLen[i]) {
                        dpCnt[i] += dpCnt[j];
                    }
                }
            }
            if (dpLen[i] > len) {
                len = dpLen[i];
                count = dpCnt[i];
            } else if (dpLen[i] == len) {
                count += dpCnt[i];
            }
        }
        return count;
    }

    @Test
    public void testFindNumberOfLIS() {
        System.out.println(findNumberOfLIS(new int[]{1, 3, 5, 4, 7}));
    }
}
