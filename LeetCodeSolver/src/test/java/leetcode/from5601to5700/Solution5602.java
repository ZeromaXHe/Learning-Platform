package leetcode.from5601to5700;

import java.util.Arrays;

/**
 * @author ZeromaXHe
 * @date 2020/11/15 10:55
 * @Description 5602.将 x 减到 0 的最小操作数 | 难度：中等 | 标签：贪心算法、双指针、二分查找
 * 给你一个整数数组 nums 和一个整数 x 。每一次操作时，你应当移除数组 nums 最左边或最右边的元素，然后从 x 中减去该元素的值。请注意，需要 修改 数组以供接下来的操作使用。
 * <p>
 * 如果可以将 x 恰好 减到 0 ，返回 最小操作数 ；否则，返回 -1 。
 * <p>
 * 示例 1：
 * 输入：nums = [1,1,4,2,3], x = 5
 * 输出：2
 * 解释：最佳解决方案是移除后两个元素，将 x 减到 0 。
 * <p>
 * 示例 2：
 * 输入：nums = [5,6,7,8,9], x = 4
 * 输出：-1
 * <p>
 * 示例 3：
 * 输入：nums = [3,2,20,1,1,3], x = 10
 * 输出：5
 * 解释：最佳解决方案是移除后三个元素和前两个元素（总共 5 次操作），将 x 减到 0 。
 * <p>
 * 提示：
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 104
 * 1 <= x <= 109
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-operations-to-reduce-x-to-zero
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution5602 {
    /**
     * 86 / 86 个通过测试用例
     * 状态：通过
     * 执行用时: 28 ms
     * 内存消耗: 52.8 MB
     *
     * @param nums
     * @param x
     * @return
     */
    public int minOperations(int[] nums, int x) {
        int[] leftSum = new int[nums.length];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            leftSum[i] = sum;
        }
        int[] rightSum = new int[nums.length];
        rightSum[0] = sum;
        for (int i = 1; i < nums.length; i++) {
            rightSum[i] = sum - leftSum[i - 1];
        }
        Arrays.sort(rightSum);
        //System.out.println("leftSum: " + Arrays.toString(leftSum));
        //System.out.println("rightSum: " + Arrays.toString(rightSum));
        int minStep = Integer.MAX_VALUE;
        int allRightStep = Arrays.binarySearch(rightSum, x);
        //System.out.println("allRightStep: " + allRightStep);
        if (allRightStep >= 0) {
            minStep = allRightStep + 1;
        }
        for (int i = 0; i < nums.length && leftSum[i] <= x; i++) {
            int step = Integer.MAX_VALUE;
            if (leftSum[i] == x) {
                step = i + 1;
            } else {
                int right = Arrays.binarySearch(
                        rightSum, 0, nums.length - i - 1, x - leftSum[i]);
                if (right > 0) {
                    step = right + i + 2;
                }
            }

            if (step < minStep) {
                minStep = step;
            }
        }
        return minStep == Integer.MAX_VALUE ? -1 : minStep;
    }

    public static void main(String[] args) {
        Solution5602 solution5602 = new Solution5602();
        // 2
        System.out.println(solution5602.minOperations(
                new int[]{1, 1, 4, 2, 3}, 5));
        // -1
        System.out.println(solution5602.minOperations(
                new int[]{5, 6, 7, 8, 9}, 4));
        // 5
        System.out.println(solution5602.minOperations(
                new int[]{3, 2, 20, 1, 1, 3}, 10));
        // -1
        System.out.println(solution5602.minOperations(
                new int[]{5, 6, 7, 8, 9}, 5 + 6 + 7 + 7 + 8 + 9));
        // 1
        System.out.println(solution5602.minOperations(
                new int[]{1, 1, 3, 2, 5}, 5));
    }
}
