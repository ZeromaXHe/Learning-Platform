package leetcode.from601to700;

/**
 * @Author: zhuxi
 * @Time: 2021/2/5 9:51
 * @Description: 643.子数组的最大平均数I | 难度：简单 | 标签：数组
 * 给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
 * <p>
 * 示例：
 * 输入：[1,12,-5,-6,50,3], k = 4
 * 输出：12.75
 * 解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
 * <p>
 * 提示：
 * 1 <= k <= n <= 30,000。
 * 所给数据范围 [-10,000，10,000]。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-average-subarray-i
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution643 {
    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 42.8 MB , 在所有 Java 提交中击败了 28.65% 的用户
     *
     * @param nums
     * @param k
     * @return
     */
    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        int max = sum;
        for (int i = k; i < nums.length; i++) {
            sum -= nums[i - k];
            sum += nums[i];
            if (sum > max) {
                max = sum;
            }
        }
        return (double) max / k;
    }
}
